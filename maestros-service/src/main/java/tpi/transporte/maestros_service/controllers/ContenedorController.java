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

import tpi.transporte.maestros_service.dtos.ContenedorRequestDTO;
import tpi.transporte.maestros_service.dtos.ContenedorResponseDTO;
import tpi.transporte.maestros_service.models.Contenedor;
import tpi.transporte.maestros_service.mappers.ContenedorMapper;
import tpi.transporte.maestros_service.services.ContenedorService;

@RestController
@RequestMapping("/contenedores")
public class ContenedorController {

    @Autowired
    private ContenedorService contenedorService;

    @Autowired
    private ContenedorMapper contenedorMapper;

    @GetMapping
    public List<ContenedorResponseDTO> listar() {
        return contenedorService.obtenerTodos().stream()
            .map(contenedorMapper::toResponseDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContenedorResponseDTO> listarPorId(@PathVariable Long id) {
        try {
            Contenedor contenedor = contenedorService.obtenerPorId(id);
            return ResponseEntity.ok(contenedorMapper.toResponseDTO(contenedor));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContenedorResponseDTO> crear(@RequestBody ContenedorRequestDTO contenedorDTO) {
        try {
            Contenedor contenedor = contenedorMapper.toEntity(contenedorDTO);
            Contenedor creado = contenedorService.crear(contenedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(contenedorMapper.toResponseDTO(creado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContenedorResponseDTO> actualizar(@PathVariable Long id, @RequestBody ContenedorRequestDTO contenedorDTO) {
        try {
            Contenedor contenedor = contenedorMapper.toEntity(contenedorDTO);
            Contenedor actualizado = contenedorService.actualizar(id, contenedor);
            return ResponseEntity.ok(contenedorMapper.toResponseDTO(actualizado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            contenedorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

