package br.com.zupacademy.jefferson.microservicepropostas.repository;

import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends JpaRepository<NovaProposta, Long> {

    Optional<NovaProposta> findByDocumento(String documento);

    @Query("SELECT p FROM NovaProposta p WHERE p.statusProposta = 'ELEGIVEL' and p.cartao = null")
    List<NovaProposta> findProposesWithoutCard();
}
