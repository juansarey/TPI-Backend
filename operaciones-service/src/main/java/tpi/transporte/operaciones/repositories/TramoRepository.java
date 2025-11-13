package tpi.transporte.operaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.operaciones.models.Tramo;

@Repository
public interface TramoRepository extends JpaRepository<Tramo, Long> {
    // Para que el transportista vea sus tramos asignados 
    List<Tramo> findByCamionId(Long camionId);

    // También podrías necesitar buscar tramos por estado para un camión
    List<Tramo> findByCamionIdAndEstado_Nombre(Long camionId, String nombreEstado);
}   