package com.tpi.operaciones.repositories;

import com.tpi.operaciones.models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
  Optional<Factura> findByPublicId(UUID publicId);
}
