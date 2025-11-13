package tpi.transporte.maestros_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.maestros_service.models.Tarifa;


@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

}
