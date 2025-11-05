package com.tpi.maestros.services;

import com.tpi.maestros.models.Contenedor;
import com.tpi.maestros.repositories.ContenedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContenedorService {
  private final ContenedorRepository repo;
  public ContenedorService(ContenedorRepository repo){ this.repo = repo; }

  public List<Contenedor> listar(){ return repo.findAll(); }
  public Optional<Contenedor> buscarPorPublicId(UUID pid){ return repo.findByPublicId(pid); }
  public Optional<Contenedor> buscarPorIdentificacion(String id){ return repo.findByIdentificacionUnica(id); }
  public Contenedor crear(Contenedor c){ return repo.save(c); }

  public Optional<Contenedor> actualizar(UUID pid, Contenedor cambios){
    return repo.findByPublicId(pid).map(db -> {
      db.setIdentificacionUnica(cambios.getIdentificacionUnica());
      db.setPesoKg(cambios.getPesoKg());
      db.setVolumenM3(cambios.getVolumenM3());
      db.setEstado(cambios.getEstado());
      db.setCliente(cambios.getCliente());
      return repo.save(db);
    });
  }
  public boolean eliminar(UUID pid){
    return repo.findByPublicId(pid).map(x -> { repo.delete(x); return true; }).orElse(false);
  }
}
