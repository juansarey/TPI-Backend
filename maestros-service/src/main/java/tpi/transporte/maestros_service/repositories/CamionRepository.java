package tpi.transporte.maestros_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.maestros_service.models.Camion;


@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
    // Para cumplir con: "determinar los camiones libres y ocupados" 
    List<Camion> findByDisponibilidad(Boolean disponibilidad);

    // Para cumplir con: "camiones elegibles por características del contenedor" [cite: 309]
    // Esto te permitirá encontrar camiones libres que puedan manejar un peso y volumen específicos.
    List<Camion> findByDisponibilidadTrueAndCapacidadPesoKgGreaterThanEqualAndCapacidadVolumenM3GreaterThanEqual(Double peso, Double volumen);
}
