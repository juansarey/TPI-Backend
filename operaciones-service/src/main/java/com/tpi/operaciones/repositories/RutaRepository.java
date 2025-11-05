package com.tpi.operaciones.repositories;

import com.tpi.operaciones.models.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
  Optional<Ruta> findByPublicId(UUID publicId);
}
