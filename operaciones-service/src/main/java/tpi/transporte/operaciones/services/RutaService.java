package tpi.transporte.operaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.transporte.operaciones.models.Ruta;
import tpi.transporte.operaciones.repositories.RutaRepository;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    /**
     * Obtiene todas las rutas
     */
    public List<Ruta> obtenerTodas() {
        return rutaRepository.findAll();
    }

    /**
     * Obtiene una ruta por su ID
     */
    public Ruta obtenerPorId(Long id) {
        return rutaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada con ID: " + id));
    }

    /**
     * Obtiene una ruta por el ID de su solicitud asociada
     */
    public Ruta obtenerPorSolicitudId(Long solicitudId) {
        return rutaRepository.findAll().stream()
            .filter(ruta -> ruta.getSolicitud() != null && ruta.getSolicitud().getIdSolicitud().equals(solicitudId))
            .findFirst()
            .orElse(null);
    }

    /**
     * Crea una nueva ruta asociada a una solicitud
     */
    public Ruta crear(Ruta ruta) {
        validarRuta(ruta);
        return rutaRepository.save(ruta);
    }

    /**
     * Actualiza una ruta existente
     */
    public Ruta actualizar(Long id, Ruta ruta) {
        validarRuta(ruta);
        Ruta rutaExistente = obtenerPorId(id);
        
        rutaExistente.setCantidadTramos(ruta.getCantidadTramos());
        rutaExistente.setCantidadDepositos(ruta.getCantidadDepositos());
        
        return rutaRepository.save(rutaExistente);
    }

    /**
     * Elimina una ruta
     */
    public void eliminar(Long id) {
        if (!rutaRepository.existsById(id)) {
            throw new IllegalArgumentException("Ruta no encontrada con ID: " + id);
        }
        rutaRepository.deleteById(id);
    }

    /**
     * Actualiza la cantidad de tramos y depósitos de una ruta basándose en sus tramos
     */
    public void actualizarCantidades(Ruta ruta) {
        if (ruta.getTramos() != null) {
            int cantidadTramos = ruta.getTramos().size();
            ruta.setCantidadTramos(cantidadTramos);
            
            // Calcular cantidad de depósitos (puede ser más complejo según la lógica de negocio)
            // Por ahora, asumimos que cada tramo intermedio representa un depósito
            int cantidadDepositos = Math.max(0, cantidadTramos - 1);
            ruta.setCantidadDepositos(cantidadDepositos);
            
            rutaRepository.save(ruta);
        }
    }

    private void validarRuta(Ruta ruta) {
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta no puede ser nula");
        }
        if (ruta.getSolicitud() == null) {
            throw new IllegalArgumentException("La ruta debe estar asociada a una solicitud");
        }
        if (ruta.getCantidadTramos() == null || ruta.getCantidadTramos() < 0) {
            throw new IllegalArgumentException("La cantidad de tramos debe ser mayor o igual a 0");
        }
        if (ruta.getCantidadDepositos() == null || ruta.getCantidadDepositos() < 0) {
            throw new IllegalArgumentException("La cantidad de depósitos debe ser mayor o igual a 0");
        }
    }
}

