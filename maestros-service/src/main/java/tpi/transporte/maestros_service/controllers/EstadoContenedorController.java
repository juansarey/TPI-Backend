package tpi.transporte.maestros_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.transporte.maestros_service.dtos.EstadoContenedorDTO;
import tpi.transporte.maestros_service.models.EstadoContenedor;
import tpi.transporte.maestros_service.mappers.EstadoContenedorMapper;
import tpi.transporte.maestros_service.services.EstadoContenedorService;

@RestController
@RequestMapping("/estados-contenedor")
public class EstadoContenedorController {

    @Autowired
    private EstadoContenedorService estadoContenedorService;

    @Autowired
    private EstadoContenedorMapper estadoContenedorMapper;

    @GetMapping
    public List<EstadoContenedorDTO> listar() {
        return estadoContenedorMapper.toDTOList(estadoContenedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoContenedorDTO> listarPorId(@PathVariable Long id) {
        try {
            EstadoContenedor estado = estadoContenedorService.obtenerPorId(id);
            return ResponseEntity.ok(estadoContenedorMapper.toDTO(estado));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstadoContenedorDTO> crear(@RequestBody EstadoContenedorDTO estadoDTO) {
        try {
            EstadoContenedor estado = estadoContenedorMapper.toEntity(estadoDTO);
            EstadoContenedor creado = estadoContenedorService.crear(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoContenedorMapper.toDTO(creado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoContenedorDTO> actualizar(@PathVariable Long id, @RequestBody EstadoContenedorDTO estadoDTO) {
        try {
            EstadoContenedor estado = estadoContenedorMapper.toEntity(estadoDTO);
            EstadoContenedor actualizado = estadoContenedorService.actualizar(id, estado);
            return ResponseEntity.ok(estadoContenedorMapper.toDTO(actualizado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            estadoContenedorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

