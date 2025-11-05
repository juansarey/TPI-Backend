package com.tpi.maestros.services;

import com.tpi.maestros.models.Camion;
import com.tpi.maestros.repositories.CamionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CamionService {
  private final CamionRepository repo;
  public CamionService(CamionRepository repo){ this.repo = repo; }

  public List<Camion> listar(){ return repo.findAll(); }
  public Optional<Camion> buscarPorPublicId(UUID pid){ return repo.findByPublicId(pid); }
  public Optional<Camion> buscarPorPatente(String patente){ return repo.findByPatente(patente); }
  public Camion crear(Camion c){ return repo.save(c); }

  public Optional<Camion> actualizar(UUID pid, Camion cambios){
    return repo.findByPublicId(pid).map(db -> {
      db.setPatente(cambios.getPatente());
      db.setCapacidadPesoKg(cambios.getCapacidadPesoKg());
      db.setCapacidadVolumenM3(cambios.getCapacidadVolumenM3());
      db.setEstado(cambios.getEstado());
      return repo.save(db);
    });
  }
  public boolean eliminar(UUID pid){
    return repo.findByPublicId(pid).map(x -> { repo.delete(x); return true; }).orElse(false);
  }
}
