package com.tpi.maestros.controllers;

import com.tpi.maestros.models.Contenedor;
import com.tpi.maestros.services.ContenedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maestros/contenedores")
public class ContenedorController {
  private final ContenedorService service;
  public ContenedorController(ContenedorService service){ this.service = service; }

  @GetMapping public List<Contenedor> listar(){ return service.listar(); }

  @GetMapping("/{publicId}")
  public ResponseEntity<Contenedor> porId(@PathVariable UUID publicId){
    return service.buscarPorPublicId(publicId)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Contenedor> crear(@RequestBody Contenedor body){
    Contenedor creado = service.crear(body);
    return ResponseEntity.created(URI.create("/api/maestros/contenedores/"+creado.getPublicId())).body(creado);
  }

  @PutMapping("/{publicId}")
  public ResponseEntity<Contenedor> actualizar(@PathVariable UUID publicId, @RequestBody Contenedor body){
    return service.actualizar(publicId, body)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{publicId}")
  public ResponseEntity<Void> eliminar(@PathVariable UUID publicId){
    return service.eliminar(publicId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
