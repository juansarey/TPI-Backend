package tpi.transporte.maestros_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.transporte.maestros_service.models.Camion;
import tpi.transporte.maestros_service.repositories.CamionRepository;

@Service
public class CamionService {
    @Autowired
    private CamionRepository camionRepo;

    public List<Camion> obtenerTodos(){
        return camionRepo.findAll();
    }

    public Camion obtenerPorId(Long idCamion){
        return camionRepo.findById(idCamion)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Camión no encontrado"));
        //RuntimeException("Camión no encontrado"));
    }

    public Camion crear(Camion camion){
        validarCamion(camion);
        return camionRepo.save(camion);
    }

    public Camion actualizar(Long idCamion, Camion camion){
        validarCamion(camion);
        camion.setIdCamion(idCamion);
        return camionRepo.save(camion);
    }

    public void eliminar(Long idCamion){
        camionRepo.deleteById(idCamion);
    }

        //VALIDACIONES DE CAMION

    private void validarCamion(Camion camion) {
    if (camion.getCapacidadPesoKg() <= 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La capacidad de peso debe ser mayor a cero.");
    }

    if (camion.getCapacidadVolumenM3() <= 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El volumen debe ser mayor a cero.");
    }
    }
}
