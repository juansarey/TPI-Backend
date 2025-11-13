package tpi.transporte.maestros_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.maestros_service.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // ¡Eso es todo!
    // JpaRepository ya te da todos los métodos comunes:
    // save(), findById(), findAll(), delete(), etc.
}