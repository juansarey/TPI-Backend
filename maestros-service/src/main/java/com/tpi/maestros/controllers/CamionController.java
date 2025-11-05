package com.tpi.maestros.controllers;

import com.tpi.maestros.models.Camion;
import com.tpi.maestros.services.CamionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maestros/camiones")
public class CamionController {
  private final CamionService service;
  public CamionController(CamionService service){ this.service = service; }

  @GetMapping public List<Camion> listar(){ return service.listar(); }

  @GetMapping("/{publicId}")
  public ResponseEntity<Camion> porId(@PathVariable UUID publicId){
    return service.buscarPorPublicId(publicId)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Camion> crear(@RequestBody Camion body){
    Camion creado = service.crear(body);
    return ResponseEntity.created(URI.create("/api/maestros/camiones/"+creado.getPublicId())).body(creado);
  }

  @PutMapping("/{publicId}")
  public ResponseEntity<Camion> actualizar(@PathVariable UUID publicId, @RequestBody Camion body){
    return service.actualizar(publicId, body)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{publicId}")
  public ResponseEntity<Void> eliminar(@PathVariable UUID publicId){
    return service.eliminar(publicId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
