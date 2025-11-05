package com.tpi.operaciones.services;

import com.tpi.operaciones.models.Solicitud;
import com.tpi.operaciones.repositories.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitudService {
  private final SolicitudRepository repo;
  public SolicitudService(SolicitudRepository repo){ this.repo = repo; }

  public List<Solicitud> listar(){ return repo.findAll(); }
  public Optional<Solicitud> porPublicId(UUID pid){ return repo.findByPublicId(pid); }
  public Solicitud crear(Solicitud s){ return repo.save(s); }
  public Optional<Solicitud> actualizar(UUID pid, Solicitud cambios){
    return repo.findByPublicId(pid).map(db -> {
      db.setEstado(cambios.getEstado());
      db.setOrigen(cambios.getOrigen());
      db.setDestino(cambios.getDestino());
      db.setClientePublicId(cambios.getClientePublicId());
      db.setContenedorPublicId(cambios.getContenedorPublicId());
      return repo.save(db);
    });
  }
  public boolean eliminar(UUID pid){
    return repo.findByPublicId(pid).map(x -> { repo.delete(x); return true; }).orElse(false);
  }
}
