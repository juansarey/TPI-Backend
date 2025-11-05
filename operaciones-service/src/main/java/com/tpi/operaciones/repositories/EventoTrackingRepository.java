package com.tpi.operaciones.repositories;

import com.tpi.operaciones.models.EventoTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface EventoTrackingRepository extends JpaRepository<EventoTracking, Long> {
  List<EventoTracking> findByTramo_PublicId(UUID publicIdTramo);
}
