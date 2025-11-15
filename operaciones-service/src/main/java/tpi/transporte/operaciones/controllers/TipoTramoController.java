package tpi.transporte.operaciones.controllers;

import tpi.transporte.operaciones.dtos.TipoTramoDTO;
import tpi.transporte.operaciones.mappers.TipoTramoMapper;
import tpi.transporte.operaciones.services.TipoTramoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-tramo")
public class TipoTramoController {

    @Autowired
    private TipoTramoService tipoTramoService;

    @Autowired
    private TipoTramoMapper tipoTramoMapper;

    @GetMapping
    public ResponseEntity<List<TipoTramoDTO>> getTodos() {
        List<TipoTramoDTO> dtos = tipoTramoService.obtenerTodos().stream()
            .map(tipoTramoMapper::toDTO)
            .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTramoDTO> getPorId(@PathVariable Long id) {
        try {
            TipoTramoDTO dto = tipoTramoMapper.toDTO(tipoTramoService.obtenerPorId(id));
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}