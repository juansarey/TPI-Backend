package com.tpi.operaciones.services;

import com.tpi.operaciones.models.Ruta;
import com.tpi.operaciones.models.Solicitud;
import com.tpi.operaciones.repositories.RutaRepository;
import com.tpi.operaciones.repositories.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RutaService {
  private final RutaRepository repo;
  private final SolicitudRepository solicitudRepo;

  public RutaService(RutaRepository repo, SolicitudRepository solicitudRepo){
    this.repo = repo; this.solicitudRepo = solicitudRepo;
  }

  public List<Ruta> listar(){ return repo.findAll(); }
  public Optional<Ruta> porPublicId(UUID pid){ return repo.findByPublicId(pid); }
  public Optional<Ruta> crearParaSolicitud(UUID solicitudPid, Ruta r){
    return solicitudRepo.findByPublicId(solicitudPid).map(sol -> {
      r.setSolicitud(sol);
      return repo.save(r);
    });
  }
  public Optional<Ruta> actualizar(UUID pid, Ruta cambios){
    return repo.findByPublicId(pid).map(db -> {
      db.setDescripcion(cambios.getDescripcion());
      return repo.save(db);
    });
  }
  public boolean eliminar(UUID pid){
    return repo.findByPublicId(pid).map(x -> { repo.delete(x); return true; }).orElse(false);
  }
}
