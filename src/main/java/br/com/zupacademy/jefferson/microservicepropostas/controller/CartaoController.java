package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.*;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.BloqueioApiResponse;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.ResultadoAvisoViagemResponse;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.ResultadoCarteiraResponse;
import br.com.zupacademy.jefferson.microservicepropostas.entity.*;
import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusCartao;
import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusCarteira;
import br.com.zupacademy.jefferson.microservicepropostas.repository.*;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private CartaoRepository cartaoRepository;

    private BiometriaRepository biometriaRepository;

    private BloqueioCartaoRepository bloqueioCartaoRepository;

    private AvisoViagemRepository avisoViagemRepository;

    private CarteiraDigitalRepository carteiraDigitalRepository;

    private ApiCardClient apiCardClient;

    public CartaoController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository, BloqueioCartaoRepository bloqueioCartaoRepository, AvisoViagemRepository avisoViagemRepository, CarteiraDigitalRepository carteiraDigitalRepository, ApiCardClient apiCardClient) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
        this.avisoViagemRepository = avisoViagemRepository;
        this.carteiraDigitalRepository = carteiraDigitalRepository;
        this.apiCardClient = apiCardClient;
    }

    @PostMapping("/{numeroCartao}/biometria")
    @Transactional
    public ResponseEntity cadastrarBiometria(@PathVariable String numeroCartao, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder builder){
        Optional<Cartao> existsCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if(existsCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = existsCartao.get();

        Biometria biometria = biometriaRequest.convertRequestToEntity(cartao);
        Biometria biometriaSalva = biometriaRepository.save(biometria);

        URI uri = builder.path("/cartoes/{numeroCartao}/biometria").buildAndExpand(biometriaSalva.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{numeroCartao}/bloqueios")
    public ResponseEntity bloquearCartao(@PathVariable String numeroCartao, HttpServletRequest request){
        Optional<Cartao> existsCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        String userAgent =  request.getHeader("User-Agent");
        String ipClient = request.getHeader("X-FORWARDED-FOR");

        if(ipClient == null){
            ipClient = request.getRemoteAddr();
        }

        if (existsCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = existsCartao.get();

        if(cartao.getStatusCartao() == StatusCartao.BLOQUEADO){
            return ResponseEntity.unprocessableEntity().build();
        }

        BloqueioCartao solicitacaoBloqueio = new BloqueioCartao(ipClient, userAgent, cartao);

        try{
            BloqueioApiRequest bloqueioApiRequest = new BloqueioApiRequest("Proposta");
            BloqueioApiResponse bloqueioApiResponse = apiCardClient.blockCard(numeroCartao, bloqueioApiRequest);
            System.out.println(bloqueioApiResponse.getResultado());
        }catch (FeignException e){
            return ResponseEntity.internalServerError().body("Falha de comunicação com sistema externo.");
        }

        bloqueioCartaoRepository.save(solicitacaoBloqueio);
        cartao.bloqueiaCartao();
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().body("Cartão Bloqueado!");
    }

    @PostMapping("/{numeroCartao}/avisos")
    public ResponseEntity avisoViagem(@PathVariable String numeroCartao, @RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest request){
        Optional<Cartao> existsCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (existsCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        String ipClient = request.getHeader("X-FORWARDED-FOR");
        String userAgent = request.getHeader("User-Agent");

        if(ipClient == null){
            ipClient = request.getRemoteAddr();
        }

        Cartao cartao = existsCartao.get();

        try{
            System.out.println("Entrou no try");
            SolicitacaoAvisoViagemRequest solicitacaoAvisoViagemRequest = new SolicitacaoAvisoViagemRequest(avisoViagemRequest.getDestinoViagem(), avisoViagemRequest.getTerminoViagem());
            System.out.println("Instanciou a classe Solicitacao Aviso Viagem");
            ResultadoAvisoViagemResponse resultadoAvisoViagemResponse = apiCardClient.tripNotice(numeroCartao, solicitacaoAvisoViagemRequest);
            System.out.println("Comunicou a API");
            System.out.println(resultadoAvisoViagemResponse.getResultado());
        }catch (FeignException e){
            return ResponseEntity.internalServerError().body("Não foi possível realizar comunicação com sistema externo.");
        }

        AvisoViagem avisoViagem = avisoViagemRequest.convertRequestToEntity(ipClient, userAgent, cartao);

        AvisoViagem avisoViagemSalva = avisoViagemRepository.save(avisoViagem);

        return ResponseEntity.ok().body("Cartão foi notificado da Viagem!");
    }

    @PostMapping("/{numeroCartao}/carteiras")
    public ResponseEntity associaCarteiraDigital(@PathVariable String numeroCartao, @RequestBody @Valid AssociaCarteiraDigitalRequest associaCarteiraDigitalRequest, UriComponentsBuilder builder){
        Optional<Cartao> existsCartao = cartaoRepository.findByNumeroCartao(numeroCartao);
        if (existsCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = existsCartao.get();

        List<CarteiraDigital> existsCarteiraAssociada = carteiraDigitalRepository.findByStatusCarteiraAndCartaoNumeroCartao(StatusCarteira.ATIVO, numeroCartao);

        for(CarteiraDigital carteiraDigital: existsCarteiraAssociada){
            if(carteiraDigital.getTipoCarteira() == associaCarteiraDigitalRequest.getTipoCarteira()){
                return ResponseEntity.unprocessableEntity().body("Não foi possivel associar cartão a carteira digital "
                        + associaCarteiraDigitalRequest.getTipoCarteira().toString());
            }
        }

        CarteiraDigital carteiraDigital = associaCarteiraDigitalRequest.convertRequestToEntity(cartao);

        try{
            SolicitacaoInclusaoCarteiraRequest solicitacaoInclusaoCarteira = new SolicitacaoInclusaoCarteiraRequest(associaCarteiraDigitalRequest.getEmail(), associaCarteiraDigitalRequest.getTipoCarteira());
            ResultadoCarteiraResponse resultadoCarteiraResponse = apiCardClient.associateDigitalWallet(numeroCartao, solicitacaoInclusaoCarteira);
            System.out.println(resultadoCarteiraResponse.getResultado());
        }catch (FeignException e){
            return ResponseEntity.internalServerError().body("Não foi possível realizar comunicação com sistema externo.");
        }

        CarteiraDigital carteiraDigitalSalva = carteiraDigitalRepository.save(carteiraDigital);

        URI uri = builder.path("/cartoes/{numeroCartao}/carteiras").buildAndExpand(carteiraDigitalSalva.getId()).toUri();
        return ResponseEntity.created(uri).body("Carteira digital cadastrada com sucesso!");
    }
}
