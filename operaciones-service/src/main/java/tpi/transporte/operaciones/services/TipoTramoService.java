package tpi.transporte.operaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.transporte.operaciones.models.TipoTramo;
import tpi.transporte.operaciones.repositories.TipoTramoRepository;

@Service
public class TipoTramoService {

    @Autowired
    private TipoTramoRepository tipoTramoRepository;

    /**
     * Obtiene todos los tipos de tramo
     */
    public List<TipoTramo> obtenerTodos() {
        return tipoTramoRepository.findAll();
    }

    /**
     * Obtiene un tipo de tramo por su ID
     */
    public TipoTramo obtenerPorId(Long id) {
        return tipoTramoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tipo de tramo no encontrado con ID: " + id));
    }

    /**
     * Obtiene un tipo de tramo por su nombre (ej: "ORIGEN_A_DEPOSITO", "DEPOSITO_A_DESTINO")
     */
    public TipoTramo obtenerPorNombre(String nombre) {
        return tipoTramoRepository.findAll().stream()
            .filter(tipo -> tipo.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Tipo de tramo no encontrado con nombre: " + nombre));
    }
}

