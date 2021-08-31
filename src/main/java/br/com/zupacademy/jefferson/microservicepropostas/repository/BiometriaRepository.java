package br.com.zupacademy.jefferson.microservicepropostas.repository;

import br.com.zupacademy.jefferson.microservicepropostas.entity.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
