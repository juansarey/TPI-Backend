package com.tpi.operaciones.services;

import com.tpi.operaciones.models.Factura;
import com.tpi.operaciones.repositories.FacturaRepository;
import com.tpi.operaciones.repositories.SolicitudRepository;
import com.tpi.operaciones.models.Solicitud;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FacturaService {
  private final FacturaRepository repo;
  private final SolicitudRepository solicitudRepo;

  public FacturaService(FacturaRepository repo, SolicitudRepository solicitudRepo){
    this.repo = repo; this.solicitudRepo = solicitudRepo;
  }

  public List<Factura> listar(){ return repo.findAll(); }
  public Optional<Factura> porPublicId(UUID pid){ return repo.findByPublicId(pid); }
  public Optional<Factura> crearParaSolicitud(UUID solicitudPid, Factura f){
    return solicitudRepo.findByPublicId(solicitudPid).map(s -> { f.setSolicitud(s); return repo.save(f); });
  }
  public Optional<Factura> actualizar(UUID pid, Factura cambios){
    return repo.findByPublicId(pid).map(db -> {
      db.setMonto(cambios.getMonto());
      db.setMoneda(cambios.getMoneda());
      db.setEstado(cambios.getEstado());
      return repo.save(db);
    });
  }
  public boolean eliminar(UUID pid){
    return repo.findByPublicId(pid).map(x -> { repo.delete(x); return true; }).orElse(false);
  }
}
