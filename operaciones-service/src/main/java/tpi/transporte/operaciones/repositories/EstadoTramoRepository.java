package tpi.transporte.operaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.operaciones.models.EstadoTramo;


@Repository
public interface EstadoTramoRepository extends JpaRepository<EstadoTramo, Long> {

}
