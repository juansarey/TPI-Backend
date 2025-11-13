package tpi.transporte.operaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.operaciones.models.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

}