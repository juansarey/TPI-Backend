package tpi.transporte.operaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.transporte.operaciones.models.EstadoTramo;
import tpi.transporte.operaciones.repositories.EstadoTramoRepository;

@Service
public class EstadoTramoService {

    @Autowired
    private EstadoTramoRepository estadoTramoRepository;

    /**
     * Obtiene todos los estados de tramo
     */
    public List<EstadoTramo> obtenerTodos() {
        return estadoTramoRepository.findAll();
    }

    /**
     * Obtiene un estado de tramo por su ID
     */
    public EstadoTramo obtenerPorId(Long id) {
        return estadoTramoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Estado de tramo no encontrado con ID: " + id));
    }

    /**
     * Obtiene un estado de tramo por su nombre (ej: "PENDIENTE", "EN_CURSO", "FINALIZADO")
     */
    public EstadoTramo obtenerPorNombre(String nombre) {
        return estadoTramoRepository.findAll().stream()
            .filter(estado -> estado.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Estado de tramo no encontrado con nombre: " + nombre));
    }
}

