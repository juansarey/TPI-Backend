package com.tpi.operaciones.repositories;

import com.tpi.operaciones.models.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
  Optional<Solicitud> findByPublicId(UUID publicId);
}
