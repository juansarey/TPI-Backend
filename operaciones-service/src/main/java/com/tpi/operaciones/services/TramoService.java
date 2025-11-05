package com.tpi.operaciones.services;

import com.tpi.operaciones.models.Ruta;
import com.tpi.operaciones.models.Tramo;
import com.tpi.operaciones.repositories.RutaRepository;
import com.tpi.operaciones.repositories.TramoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TramoService {
  private final TramoRepository repo;
  private final RutaRepository rutaRepo;

  public TramoService(TramoRepository repo, RutaRepository rutaRepo){
    this.repo = repo; this.rutaRepo = rutaRepo;
  }

  public List<Tramo> listar(){ return repo.findAll(); }
  public Optional<Tramo> porPublicId(UUID pid){ return repo.findByPublicId(pid); }
  public Optional<Tramo> crearParaRuta(UUID rutaPid, Tramo t){
    return rutaRepo.findByPublicId(rutaPid).map(r -> {
      t.setRuta(r);
      return repo.save(t);
    });
  }
  public Optional<Tramo> actualizar(UUID pid, Tramo cambios){
    return repo.findByPublicId(pid).map(db -> {
      db.setOrden(cambios.getOrden());
      db.setOrigen(cambios.getOrigen());
      db.setDestino(cambios.getDestino());
      db.setEstado(cambios.getEstado());
      db.setInicioPlan(cambios.getInicioPlan());
      db.setFinPlan(cambios.getFinPlan());
      db.setInicioReal(cambios.getInicioReal());
      db.setFinReal(cambios.getFinReal());
      db.setCamionPublicId(cambios.getCamionPublicId());
      return repo.save(db);
    });
  }
  public boolean eliminar(UUID pid){
    return repo.findByPublicId(pid).map(x -> { repo.delete(x); return true; }).orElse(false);
  }
}
