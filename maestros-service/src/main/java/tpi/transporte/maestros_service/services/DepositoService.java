package tpi.transporte.maestros_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.transporte.maestros_service.models.Deposito;
import tpi.transporte.maestros_service.repositories.DepositoRepository;

@Service
public class DepositoService {
    
    @Autowired
    private DepositoRepository depositoRepo;

    public List<Deposito> obtenerTodos(){
        return depositoRepo.findAll();
    }

    public Deposito obtenerPorId(Long id){
        return depositoRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Depósito no encontrado"));
    }

    public Deposito crear(Deposito deposito){
        validarDeposito(deposito);
        return depositoRepo.save(deposito);
    }

    public Deposito actualizar(Long id, Deposito deposito){
        validarDeposito(deposito);
        deposito.setIdDeposito(id);
        return depositoRepo.save(deposito);
    }

    public void eliminar(Long id){
        if (!depositoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Depósito no encontrado");
        }
        depositoRepo.deleteById(id);
    }

    private void validarDeposito(Deposito deposito) {
        if (deposito.getNombre() == null || deposito.getNombre().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del depósito es obligatorio.");
        }

        if (deposito.getDireccion() == null || deposito.getDireccion().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La dirección del depósito es obligatoria.");
        }

        if (deposito.getLatitud() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La latitud es obligatoria.");
        }

        if (deposito.getLongitud() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La longitud es obligatoria.");
        }

        if (deposito.getCostoDiario() == null || deposito.getCostoDiario() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El costo diario debe ser mayor o igual a cero.");
        }

        // Validar rango de latitud (-90 a 90)
        if (deposito.getLatitud() < -90 || deposito.getLatitud() > 90) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La latitud debe estar entre -90 y 90.");
        }

        // Validar rango de longitud (-180 a 180)
        if (deposito.getLongitud() < -180 || deposito.getLongitud() > 180) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La longitud debe estar entre -180 y 180.");
        }
    }
}

