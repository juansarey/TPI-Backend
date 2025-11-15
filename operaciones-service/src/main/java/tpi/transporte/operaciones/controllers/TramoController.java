package tpi.transporte.operaciones.controllers;

import tpi.transporte.operaciones.dtos.TramoRequestDTO;
import tpi.transporte.operaciones.dtos.TramoResponseDTO;
import tpi.transporte.operaciones.mappers.TramoMapper;
import tpi.transporte.operaciones.models.EstadoTramo;
import tpi.transporte.operaciones.models.Ruta;
import tpi.transporte.operaciones.models.TipoTramo;
import tpi.transporte.operaciones.models.Tramo;
import tpi.transporte.operaciones.services.EstadoTramoService;
import tpi.transporte.operaciones.services.RutaService;
import tpi.transporte.operaciones.services.TipoTramoService;
import tpi.transporte.operaciones.services.TramoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tramos")
public class TramoController {

    @Autowired
    private TramoService tramoService;

    @Autowired
    private TramoMapper tramoMapper;

    @Autowired
    private RutaService rutaService; // Necesario para asociar el tramo

    @Autowired
    private EstadoTramoService estadoTramoService; // Necesario para setear estado

    @Autowired
    private TipoTramoService tipoTramoService; // Necesario para setear tipo

    /**
     * Crea un nuevo tramo y lo asocia a una ruta existente.
     * El servicio valida que el camionId exista en Maestros.
     */
    @PostMapping
    public ResponseEntity<TramoResponseDTO> crearTramo(@RequestBody TramoRequestDTO requestDTO) {
        try {
            Tramo tramo = tramoMapper.toEntity(requestDTO);
            
            // Asignar entidades relacionadas basadas en los IDs del DTO
            Ruta ruta = rutaService.obtenerPorId(requestDTO.getRutaId());
            TipoTramo tipo = tipoTramoService.obtenerPorId(requestDTO.getTipoTramoId());
            EstadoTramo estado = estadoTramoService.obtenerPorId(requestDTO.getEstadoTramoId());

            tramo.setRuta(ruta);
            tramo.setTipoTramo(tipo);
            tramo.setEstado(estado);

            Tramo tramoCreado = tramoService.crear(tramo);
            
            return new ResponseEntity<>(tramoMapper.toResponseDTO(tramoCreado), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Obtiene un tramo por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TramoResponseDTO> getTramoPorId(@PathVariable Long id) {
        try {
            Tramo tramo = tramoService.obtenerPorId(id);
            return ResponseEntity.ok(tramoMapper.toResponseDTO(tramo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene todos los tramos de una ruta específica.
     */
    @GetMapping("/ruta/{rutaId}")
    public ResponseEntity<List<TramoResponseDTO>> getTramosPorRuta(@PathVariable Long rutaId) {
        List<Tramo> tramos = tramoService.obtenerPorRutaId(rutaId);
        List<TramoResponseDTO> dtos = tramos.stream()
            .map(tramoMapper::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * Obtiene todos los tramos asignados a un camión (transportista).
     */
    @GetMapping("/camion/{camionId}")
    public ResponseEntity<List<TramoResponseDTO>> getTramosPorCamion(@PathVariable Long camionId) {
        List<Tramo> tramos = tramoService.obtenerPorCamionId(camionId);
        List<TramoResponseDTO> dtos = tramos.stream()
            .map(tramoMapper::toResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Inicia un tramo (cambia estado a EN_CURSO).
     */
    @PostMapping("/{id}/iniciar")
    public ResponseEntity<TramoResponseDTO> iniciarTramo(@PathVariable Long id) {
        try {
            Tramo tramoIniciado = tramoService.iniciarTramo(id);
            return ResponseEntity.ok(tramoMapper.toResponseDTO(tramoIniciado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Ej: si ya estaba iniciado
        }
    }

    /**
     * Finaliza un tramo (cambia estado a FINALIZADO y registra costo real).
     */
    @PostMapping("/{id}/finalizar")
    public ResponseEntity<TramoResponseDTO> finalizarTramo(@PathVariable Long id, @RequestParam(required = false) Double costoReal) {
        try {
            Tramo tramoFinalizado = tramoService.finalizarTramo(id, costoReal);
            return ResponseEntity.ok(tramoMapper.toResponseDTO(tramoFinalizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Ej: si no estaba EN_CURSO
        }
    }
    
    /**
     * Elimina un tramo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTramo(@PathVariable Long id) {
        try {
            tramoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}