package com.tpi.maestros.services;

import com.tpi.maestros.models.Cliente;
import com.tpi.maestros.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
  private final ClienteRepository repo;
  public ClienteService(ClienteRepository repo){ this.repo = repo; }

  public List<Cliente> listar(){ return repo.findAll(); }
  public Optional<Cliente> buscarPorPublicId(UUID pid){ return repo.findByPublicId(pid); }

  public Cliente crear(Cliente c){
    if (repo.existsByEmail(c.getEmail())) throw new IllegalArgumentException("Email ya registrado");
    return repo.save(c);
  }

  public Optional<Cliente> actualizar(UUID pid, Cliente cambios){
    return repo.findByPublicId(pid).map(db -> {
      db.setNombre(cambios.getNombre());
      db.setApellido(cambios.getApellido());
      db.setTelefono(cambios.getTelefono());
      db.setDireccion(cambios.getDireccion());
      return repo.save(db);
    });
  }

  public boolean eliminar(UUID pid){
    return repo.findByPublicId(pid).map(c -> { repo.delete(c); return true; }).orElse(false);
  }
}
