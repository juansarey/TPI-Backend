package tpi.transporte.operaciones.controllers;

import tpi.transporte.operaciones.dtos.EstadoTramoDTO;
import tpi.transporte.operaciones.mappers.EstadoTramoMapper;
import tpi.transporte.operaciones.services.EstadoTramoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados-tramo")
public class EstadoTramoController {

    @Autowired
    private EstadoTramoService estadoTramoService;

    @Autowired
    private EstadoTramoMapper estadoTramoMapper;

    @GetMapping
    public ResponseEntity<List<EstadoTramoDTO>> getTodos() {
        List<EstadoTramoDTO> dtos = estadoTramoService.obtenerTodos().stream()
            .map(estadoTramoMapper::toDTO)
            .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoTramoDTO> getPorId(@PathVariable Long id) {
        try {
            EstadoTramoDTO dto = estadoTramoMapper.toDTO(estadoTramoService.obtenerPorId(id));
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}