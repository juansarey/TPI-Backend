package tpi.transporte.operaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.operaciones.models.Tramo;

@Repository
public interface TramoRepository extends JpaRepository<Tramo, Long> {

}