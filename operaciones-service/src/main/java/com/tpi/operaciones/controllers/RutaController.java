package com.tpi.operaciones.controllers;

import com.tpi.operaciones.models.Ruta;
import com.tpi.operaciones.services.RutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/operaciones/rutas")
public class RutaController {
  private final RutaService service;
  public RutaController(RutaService service){ this.service = service; }

  @GetMapping public List<Ruta> listar(){ return service.listar(); }

  @GetMapping("/{publicId}")
  public ResponseEntity<Ruta> porId(@PathVariable UUID publicId){
    return service.porPublicId(publicId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/solicitud/{solicitudPublicId}")
  public ResponseEntity<Ruta> crearParaSolicitud(@PathVariable UUID solicitudPublicId, @RequestBody Ruta body){
    return service.crearParaSolicitud(solicitudPublicId, body)
      .map(r -> ResponseEntity.created(URI.create("/api/operaciones/rutas/"+r.getPublicId())).body(r))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{publicId}")
  public ResponseEntity<Ruta> actualizar(@PathVariable UUID publicId, @RequestBody Ruta body){
    return service.actualizar(publicId, body).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{publicId}")
  public ResponseEntity<Void> eliminar(@PathVariable UUID publicId){
    return service.eliminar(publicId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
