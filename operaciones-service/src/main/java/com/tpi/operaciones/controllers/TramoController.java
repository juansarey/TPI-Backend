package com.tpi.operaciones.controllers;

import com.tpi.operaciones.models.Tramo;
import com.tpi.operaciones.services.TramoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/operaciones/tramos")
public class TramoController {
  private final TramoService service;
  public TramoController(TramoService service){ this.service = service; }

  @GetMapping public List<Tramo> listar(){ return service.listar(); }

  @GetMapping("/{publicId}")
  public ResponseEntity<Tramo> porId(@PathVariable UUID publicId){
    return service.porPublicId(publicId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/ruta/{rutaPublicId}")
  public ResponseEntity<Tramo> crearParaRuta(@PathVariable UUID rutaPublicId, @RequestBody Tramo body){
    return service.crearParaRuta(rutaPublicId, body)
      .map(t -> ResponseEntity.created(URI.create("/api/operaciones/tramos/"+t.getPublicId())).body(t))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{publicId}")
  public ResponseEntity<Tramo> actualizar(@PathVariable UUID publicId, @RequestBody Tramo body){
    return service.actualizar(publicId, body).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{publicId}")
  public ResponseEntity<Void> eliminar(@PathVariable UUID publicId){
    return service.eliminar(publicId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
