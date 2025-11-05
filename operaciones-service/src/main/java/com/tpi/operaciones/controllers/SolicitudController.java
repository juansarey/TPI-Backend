package com.tpi.operaciones.controllers;

import com.tpi.operaciones.models.Solicitud;
import com.tpi.operaciones.services.SolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/operaciones/solicitudes")
public class SolicitudController {
  private final SolicitudService service;
  public SolicitudController(SolicitudService service){ this.service = service; }

  @GetMapping public List<Solicitud> listar(){ return service.listar(); }

  @GetMapping("/{publicId}")
  public ResponseEntity<Solicitud> porId(@PathVariable UUID publicId){
    return service.porPublicId(publicId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Solicitud> crear(@RequestBody Solicitud body){
    Solicitud creado = service.crear(body);
    return ResponseEntity.created(URI.create("/api/operaciones/solicitudes/"+creado.getPublicId())).body(creado);
  }

  @PutMapping("/{publicId}")
  public ResponseEntity<Solicitud> actualizar(@PathVariable UUID publicId, @RequestBody Solicitud body){
    return service.actualizar(publicId, body).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{publicId}")
  public ResponseEntity<Void> eliminar(@PathVariable UUID publicId){
    return service.eliminar(publicId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
