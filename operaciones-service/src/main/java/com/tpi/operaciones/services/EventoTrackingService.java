package com.tpi.operaciones.services;

import com.tpi.operaciones.models.EventoTracking;
import com.tpi.operaciones.models.Tramo;
import com.tpi.operaciones.repositories.EventoTrackingRepository;
import com.tpi.operaciones.repositories.TramoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventoTrackingService {
  private final EventoTrackingRepository repo;
  private final TramoRepository tramoRepo;

  public EventoTrackingService(EventoTrackingRepository repo, TramoRepository tramoRepo){
    this.repo = repo; this.tramoRepo = tramoRepo;
  }

  public List<EventoTracking> listar(){ return repo.findAll(); }
  public List<EventoTracking> porTramoPublicId(UUID tramoPid){ return repo.findByTramo_PublicId(tramoPid); }
  public Optional<EventoTracking> crearParaTramo(UUID tramoPid, EventoTracking e){
    return tramoRepo.findByPublicId(tramoPid).map(t -> { e.setTramo(t); return repo.save(e); });
  }
}
