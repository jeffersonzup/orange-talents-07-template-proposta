package br.com.zupacademy.jefferson.microservicepropostas.repository;

import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends JpaRepository<NovaProposta, Long> {

    Optional<NovaProposta> findByDocumento(String documento);
}
