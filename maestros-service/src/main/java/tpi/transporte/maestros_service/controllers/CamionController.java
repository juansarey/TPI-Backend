package tpi.transporte.maestros_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.transporte.maestros_service.dtos.CamionDTO;
import tpi.transporte.maestros_service.models.Camion;
import tpi.transporte.maestros_service.mappers.CamionMapper;
import tpi.transporte.maestros_service.services.CamionService;

@RestController
@RequestMapping("/camiones")
public class CamionController {

    @Autowired
    private CamionService camionService;

    @Autowired
    private CamionMapper camionMapper;

    @GetMapping
    public List<CamionDTO> listar() {
        return camionService.obtenerTodos().stream().map(camionMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public CamionDTO listarPorId(@PathVariable Long id) {
        Camion camion = camionService.obtenerPorId(id);
        return camionMapper.toDTO(camion);
    }

    @PostMapping
    public CamionDTO crear(@RequestBody CamionDTO camionDTO) {
        Camion camion = camionMapper.toEntity(camionDTO);
        Camion save = camionService.crear(camion);
        return camionMapper.toDTO(save);
    }

    @PutMapping("/{id}")
    public CamionDTO actualizar(@PathVariable Long id, @RequestBody CamionDTO camionDTO) {
        Camion camionActualizado = camionService.actualizar(id, camionMapper.toEntity(camionDTO));
        return camionMapper.toDTO(camionActualizado);

    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        camionService.eliminar(id);
    }

}
