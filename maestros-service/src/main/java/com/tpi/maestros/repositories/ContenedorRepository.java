package com.tpi.maestros.repositories;

import com.tpi.maestros.models.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {
  Optional<Contenedor> findByPublicId(UUID publicId);
  Optional<Contenedor> findByIdentificacionUnica(String identificacionUnica);
}
