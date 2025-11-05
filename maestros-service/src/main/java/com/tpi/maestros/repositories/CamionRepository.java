package com.tpi.maestros.repositories;

import com.tpi.maestros.models.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CamionRepository extends JpaRepository<Camion, Long> {
  Optional<Camion> findByPublicId(UUID publicId);
  Optional<Camion> findByPatente(String patente);
}
