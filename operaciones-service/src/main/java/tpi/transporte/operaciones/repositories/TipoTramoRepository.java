package tpi.transporte.operaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.operaciones.models.TipoTramo;

@Repository
public interface TipoTramoRepository extends JpaRepository<TipoTramo, Long> {

}