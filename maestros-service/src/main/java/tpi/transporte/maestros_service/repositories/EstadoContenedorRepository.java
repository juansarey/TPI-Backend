package tpi.transporte.maestros_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.maestros_service.models.EstadoContenedor; 

@Repository
public interface EstadoContenedorRepository extends JpaRepository<EstadoContenedor, Long> {

}
