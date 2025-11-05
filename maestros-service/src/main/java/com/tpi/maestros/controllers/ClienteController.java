package com.tpi.maestros.controllers;

import com.tpi.maestros.models.Cliente;
import com.tpi.maestros.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maestros/clientes")
public class ClienteController {
  private final ClienteService service;
  public ClienteController(ClienteService service){ this.service = service; }

  @GetMapping public List<Cliente> listar(){ return service.listar(); }

  @GetMapping("/{publicId}")
  public ResponseEntity<Cliente> porId(@PathVariable UUID publicId){
    return service.buscarPorPublicId(publicId)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Cliente> crear(@RequestBody Cliente body){
    Cliente creado = service.crear(body);
    return ResponseEntity.created(URI.create("/api/maestros/clientes/"+creado.getPublicId())).body(creado);
  }

  @PutMapping("/{publicId}")
  public ResponseEntity<Cliente> actualizar(@PathVariable UUID publicId, @RequestBody Cliente body){
    return service.actualizar(publicId, body)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{publicId}")
  public ResponseEntity<Void> eliminar(@PathVariable UUID publicId){
    return service.eliminar(publicId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
