package com.tpi.operaciones.repositories;

import com.tpi.operaciones.models.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TramoRepository extends JpaRepository<Tramo, Long> {
  Optional<Tramo> findByPublicId(UUID publicId);
  List<Tramo> findByRuta_Id(Long idRuta);
}
