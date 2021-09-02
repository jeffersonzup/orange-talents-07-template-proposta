package br.com.zupacademy.jefferson.microservicepropostas.repository;

import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findByNumeroCartao(String numeroCartao);
}
