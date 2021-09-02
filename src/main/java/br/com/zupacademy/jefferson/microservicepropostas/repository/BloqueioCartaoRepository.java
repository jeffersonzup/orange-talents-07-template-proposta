package br.com.zupacademy.jefferson.microservicepropostas.repository;

import br.com.zupacademy.jefferson.microservicepropostas.entity.BloqueioCartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {
}
