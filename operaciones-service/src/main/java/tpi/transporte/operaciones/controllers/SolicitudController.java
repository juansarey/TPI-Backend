package tpi.transporte.operaciones.controllers;

import tpi.transporte.operaciones.dtos.SolicitudRequestDTO;
import tpi.transporte.operaciones.dtos.SolicitudResponseDTO;
import tpi.transporte.operaciones.mappers.SolicitudMapper;
import tpi.transporte.operaciones.models.Solicitud;
import tpi.transporte.operaciones.services.SolicitudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private SolicitudMapper solicitudMapper;
    
    // NOTA: El SolicitudMapper ignora cliente y contenedor, 
    // así que los llenamos manualmente aquí si es necesario, 
    // o (mejor) ajustamos el mapper. Asumiré que el service 
    // o una lógica posterior los llena.

    /**
     * Crea una nueva solicitud de transporte.
     * El servicio valida que clienteId, contenedorId y tarifaId existan.
     */
    @PostMapping
    public ResponseEntity<SolicitudResponseDTO> crearSolicitud(@RequestBody SolicitudRequestDTO requestDTO) {
        try {
            Solicitud solicitud = solicitudMapper.toEntity(requestDTO);
            Solicitud solicitudCreada = solicitudService.crear(solicitud);
            
            // El servicio de creación ya crea la ruta asociada
            SolicitudResponseDTO response = solicitudMapper.toResponseDTO(solicitudCreada);
            
            // Aquí podrías enriquecer el DTO con los datos de Maestros si fuera necesario
            // (aunque SolicitudService ya los valida)
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Obtiene una solicitud por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponseDTO> getSolicitudPorId(@PathVariable Long id) {
        try {
            Solicitud solicitud = solicitudService.obtenerPorId(id);
            return ResponseEntity.ok(solicitudMapper.toResponseDTO(solicitud));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene todas las solicitudes.
     */
    @GetMapping
    public ResponseEntity<List<SolicitudResponseDTO>> getTodasLasSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.obtenerTodas();
        List<SolicitudResponseDTO> dtos = solicitudes.stream()
            .map(solicitudMapper::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtiene todas las solicitudes de un cliente específico.
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<SolicitudResponseDTO>> getSolicitudesPorCliente(@PathVariable Long clienteId) {
        List<Solicitud> solicitudes = solicitudService.obtenerPorClienteId(clienteId);
        List<SolicitudResponseDTO> dtos = solicitudes.stream()
            .map(solicitudMapper::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Actualiza una solicitud.
     * El servicio impide la actualización si ya hay tramos asignados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudResponseDTO> actualizarSolicitud(@PathVariable Long id, @RequestBody SolicitudRequestDTO requestDTO) {
        try {
            Solicitud solicitud = solicitudMapper.toEntity(requestDTO);
            Solicitud actualizada = solicitudService.actualizar(id, solicitud);
            return ResponseEntity.ok(solicitudMapper.toResponseDTO(actualizada));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // O un DTO de error
        }
    }

    /**
     * Elimina una solicitud y su ruta y tramos asociados.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        try {
            solicitudService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Calcula y devuelve el costo final real de una solicitud (sumando tramos).
     */
    @GetMapping("/{id}/costo-final")
    public ResponseEntity<Double> getCostoFinal(@PathVariable Long id) {
        try {
            Double costo = solicitudService.calcularCostoFinal(id);
            return ResponseEntity.ok(costo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}