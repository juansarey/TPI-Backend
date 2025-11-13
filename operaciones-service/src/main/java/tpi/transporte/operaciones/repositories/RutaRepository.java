package tpi.transporte.operaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.operaciones.models.Ruta;


@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    
}
