package tpi.transporte.maestros_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.transporte.maestros_service.models.Tarifa;
import tpi.transporte.maestros_service.repositories.TarifaRepository;

@Service
public class TarifaService {
    
    @Autowired
    private TarifaRepository tarifaRepo;

    public List<Tarifa> obtenerTodos(){
        return tarifaRepo.findAll();
    }

    public Tarifa obtenerPorId(Long id){
        return tarifaRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));
    }

    public Tarifa crear(Tarifa tarifa){
        validarTarifa(tarifa);
        return tarifaRepo.save(tarifa);
    }

    public Tarifa actualizar(Long id, Tarifa tarifa){
        validarTarifa(tarifa);
        tarifa.setIdTarifa(id);
        return tarifaRepo.save(tarifa);
    }

    public void eliminar(Long id){
        if (!tarifaRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada");
        }
        tarifaRepo.deleteById(id);
    }

    private void validarTarifa(Tarifa tarifa) {
        if (tarifa.getCostoBaseFijo() == null || tarifa.getCostoBaseFijo() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El costo base fijo debe ser mayor o igual a cero.");
        }

        if (tarifa.getCostoKm() == null || tarifa.getCostoKm() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El costo por kilómetro debe ser mayor o igual a cero.");
        }
    }
}

