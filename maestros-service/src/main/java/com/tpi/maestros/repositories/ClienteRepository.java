package com.tpi.maestros.repositories;

import com.tpi.maestros.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Optional<Cliente> findByPublicId(UUID publicId);
  boolean existsByEmail(String email);
}
