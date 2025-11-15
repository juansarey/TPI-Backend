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

import tpi.transporte.maestros_service.dtos.TarifaDTO;
import tpi.transporte.maestros_service.models.Tarifa;
import tpi.transporte.maestros_service.mappers.TarifaMapper;
import tpi.transporte.maestros_service.services.TarifaService;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @Autowired
    private TarifaMapper tarifaMapper;

    @GetMapping
    public List<TarifaDTO> listar() {
        return tarifaService.obtenerTodos().stream()
            .map(tarifaMapper::toDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaDTO> listarPorId(@PathVariable Long id) {
        try {
            Tarifa tarifa = tarifaService.obtenerPorId(id);
            return ResponseEntity.ok(tarifaMapper.toDTO(tarifa));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TarifaDTO> crear(@RequestBody TarifaDTO tarifaDTO) {
        try {
            Tarifa tarifa = tarifaMapper.toEntity(tarifaDTO);
            Tarifa creada = tarifaService.crear(tarifa);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaMapper.toDTO(creada));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaDTO> actualizar(@PathVariable Long id, @RequestBody TarifaDTO tarifaDTO) {
        try {
            Tarifa tarifa = tarifaMapper.toEntity(tarifaDTO);
            Tarifa actualizada = tarifaService.actualizar(id, tarifa);
            return ResponseEntity.ok(tarifaMapper.toDTO(actualizada));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            tarifaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

