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

import tpi.transporte.maestros_service.dtos.DepositoDTO;
import tpi.transporte.maestros_service.models.Deposito;
import tpi.transporte.maestros_service.mappers.DepositoMapper;
import tpi.transporte.maestros_service.services.DepositoService;

@RestController
@RequestMapping("/depositos")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    @Autowired
    private DepositoMapper depositoMapper;

    @GetMapping
    public List<DepositoDTO> listar() {
        return depositoService.obtenerTodos().stream()
            .map(depositoMapper::toDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositoDTO> listarPorId(@PathVariable Long id) {
        try {
            Deposito deposito = depositoService.obtenerPorId(id);
            return ResponseEntity.ok(depositoMapper.toDTO(deposito));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DepositoDTO> crear(@RequestBody DepositoDTO depositoDTO) {
        try {
            Deposito deposito = depositoMapper.toEntity(depositoDTO);
            Deposito creado = depositoService.crear(deposito);
            return ResponseEntity.status(HttpStatus.CREATED).body(depositoMapper.toDTO(creado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepositoDTO> actualizar(@PathVariable Long id, @RequestBody DepositoDTO depositoDTO) {
        try {
            Deposito deposito = depositoMapper.toEntity(depositoDTO);
            Deposito actualizado = depositoService.actualizar(id, deposito);
            return ResponseEntity.ok(depositoMapper.toDTO(actualizado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            depositoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

