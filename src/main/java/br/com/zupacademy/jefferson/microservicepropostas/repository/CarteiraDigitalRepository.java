package br.com.zupacademy.jefferson.microservicepropostas.repository;

import br.com.zupacademy.jefferson.microservicepropostas.entity.CarteiraDigital;
import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusCarteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {

    List<CarteiraDigital> findByStatusCarteiraAndCartaoNumeroCartao(StatusCarteira statusCarteira, String numero);
}
