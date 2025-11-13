package tpi.transporte.maestros_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpi.transporte.maestros_service.models.Deposito;


@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

}
