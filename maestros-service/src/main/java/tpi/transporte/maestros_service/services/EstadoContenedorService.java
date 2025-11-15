package tpi.transporte.maestros_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.transporte.maestros_service.models.EstadoContenedor;
import tpi.transporte.maestros_service.repositories.EstadoContenedorRepository;

@Service
public class EstadoContenedorService {
    
    @Autowired
    private EstadoContenedorRepository estadoContenedorRepo;

    public List<EstadoContenedor> obtenerTodos(){
        return estadoContenedorRepo.findAll();
    }

    public EstadoContenedor obtenerPorId(Long id){
        return estadoContenedorRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado de contenedor no encontrado"));
    }

    public EstadoContenedor crear(EstadoContenedor estadoContenedor){
        validarEstadoContenedor(estadoContenedor);
        return estadoContenedorRepo.save(estadoContenedor);
    }

    public EstadoContenedor actualizar(Long id, EstadoContenedor estadoContenedor){
        validarEstadoContenedor(estadoContenedor);
        estadoContenedor.setId(id);
        return estadoContenedorRepo.save(estadoContenedor);
    }

    public void eliminar(Long id){
        if (!estadoContenedorRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado de contenedor no encontrado");
        }
        estadoContenedorRepo.deleteById(id);
    }

    private void validarEstadoContenedor(EstadoContenedor estadoContenedor) {
        if (estadoContenedor.getNombre() == null || estadoContenedor.getNombre().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del estado es obligatorio.");
        }
    }
}

