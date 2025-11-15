package tpi.transporte.operaciones.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.transporte.operaciones.clients.MaestrosServiceClient;
import tpi.transporte.operaciones.models.EstadoTramo;
import tpi.transporte.operaciones.models.Ruta;
import tpi.transporte.operaciones.models.Tramo;
import tpi.transporte.operaciones.repositories.TramoRepository;

@Service
public class TramoService {

    @Autowired
    private TramoRepository tramoRepository;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private EstadoTramoService estadoTramoService;

    @Autowired
    private TipoTramoService tipoTramoService;

    @Autowired
    private MaestrosServiceClient maestrosServiceClient;

    /**
     * Obtiene todos los tramos
     */
    public List<Tramo> obtenerTodos() {
        return tramoRepository.findAll();
    }

    /**
     * Obtiene un tramo por su ID
     */
    public Tramo obtenerPorId(Long id) {
        return tramoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tramo no encontrado con ID: " + id));
    }

    /**
     * Obtiene todos los tramos asignados a un camión
     */
    public List<Tramo> obtenerPorCamionId(Long camionId) {
        return tramoRepository.findByCamionId(camionId);
    }

    /**
     * Obtiene todos los tramos de un camión con un estado específico
     */
    public List<Tramo> obtenerPorCamionIdYEstado(Long camionId, String nombreEstado) {
        return tramoRepository.findByCamionIdAndEstado_Nombre(camionId, nombreEstado);
    }

    /**
     * Obtiene todos los tramos de una ruta
     */
    public List<Tramo> obtenerPorRutaId(Long rutaId) {
        Ruta ruta = rutaService.obtenerPorId(rutaId);
        return ruta.getTramos();
    }

    /**
     * Crea un nuevo tramo
     */
    public Tramo crear(Tramo tramo) {
        validarTramo(tramo);
        Tramo tramoCreado = tramoRepository.save(tramo);
        
        // Actualizar las cantidades de la ruta
        if (tramo.getRuta() != null) {
            rutaService.actualizarCantidades(tramo.getRuta());
        }
        
        return tramoCreado;
    }

    /**
     * Actualiza un tramo existente
     */
    public Tramo actualizar(Long id, Tramo tramo) {
        validarTramo(tramo);
        Tramo tramoExistente = obtenerPorId(id);
        
        tramoExistente.setCamionId(tramo.getCamionId());
        tramoExistente.setOrigen(tramo.getOrigen());
        tramoExistente.setDestino(tramo.getDestino());
        tramoExistente.setCostoAprox(tramo.getCostoAprox());
        tramoExistente.setCostoReal(tramo.getCostoReal());
        tramoExistente.setFechaHoraInicioEstimada(tramo.getFechaHoraInicioEstimada());
        tramoExistente.setFechaHoraInicio(tramo.getFechaHoraInicio());
        tramoExistente.setFechaHoraFinEstimada(tramo.getFechaHoraFinEstimada());
        tramoExistente.setFechaHoraFin(tramo.getFechaHoraFin());
        
        if (tramo.getTipoTramo() != null) {
            tramoExistente.setTipoTramo(tramo.getTipoTramo());
        }
        if (tramo.getEstado() != null) {
            tramoExistente.setEstado(tramo.getEstado());
        }
        
        return tramoRepository.save(tramoExistente);
    }

    /**
     * Elimina un tramo
     */
    public void eliminar(Long id) {
        Tramo tramo = obtenerPorId(id);
        Ruta ruta = tramo.getRuta();
        
        tramoRepository.deleteById(id);
        
        // Actualizar las cantidades de la ruta
        if (ruta != null) {
            rutaService.actualizarCantidades(ruta);
        }
    }

    /**
     * Inicia un tramo (cambia el estado a EN_CURSO y registra la fecha/hora de inicio real)
     */
    public Tramo iniciarTramo(Long id) {
        Tramo tramo = obtenerPorId(id);
        
        // Validar que el tramo esté en estado PENDIENTE
        if (!tramo.getEstado().getNombre().equals("PENDIENTE")) {
            throw new IllegalStateException("Solo se pueden iniciar tramos en estado PENDIENTE. Estado actual: " + tramo.getEstado().getNombre());
        }
        
        EstadoTramo estadoEnCurso = estadoTramoService.obtenerPorNombre("EN_CURSO");
        tramo.setEstado(estadoEnCurso);
        tramo.setFechaHoraInicio(LocalDateTime.now());
        
        return tramoRepository.save(tramo);
    }

    /**
     * Finaliza un tramo (cambia el estado a FINALIZADO y registra la fecha/hora de fin real)
     */
    public Tramo finalizarTramo(Long id, Double costoReal) {
        Tramo tramo = obtenerPorId(id);
        
        // Validar que el tramo esté en estado EN_CURSO
        if (!tramo.getEstado().getNombre().equals("EN_CURSO")) {
            throw new IllegalStateException("Solo se pueden finalizar tramos en estado EN_CURSO. Estado actual: " + tramo.getEstado().getNombre());
        }
        
        EstadoTramo estadoFinalizado = estadoTramoService.obtenerPorNombre("FINALIZADO");
        tramo.setEstado(estadoFinalizado);
        tramo.setFechaHoraFin(LocalDateTime.now());
        
        if (costoReal != null) {
            tramo.setCostoReal(costoReal);
        }
        
        return tramoRepository.save(tramo);
    }

    private void validarTramo(Tramo tramo) {
        if (tramo == null) {
            throw new IllegalArgumentException("El tramo no puede ser nulo");
        }
        if (tramo.getRuta() == null) {
            throw new IllegalArgumentException("El tramo debe estar asociado a una ruta");
        }
        if (tramo.getTipoTramo() == null) {
            throw new IllegalArgumentException("El tramo debe tener un tipo de tramo");
        }
        if (tramo.getEstado() == null) {
            throw new IllegalArgumentException("El tramo debe tener un estado");
        }
        if (tramo.getCamionId() != null) {
            // Validar que el camión exista en maestros-service
            if (!maestrosServiceClient.validarCamion(tramo.getCamionId())) {
                throw new IllegalArgumentException("El camión con ID " + tramo.getCamionId() + " no existe en maestros-service");
            }
        }
        if (tramo.getOrigen() == null || tramo.getOrigen().trim().isEmpty()) {
            throw new IllegalArgumentException("El origen del tramo es obligatorio");
        }
        if (tramo.getDestino() == null || tramo.getDestino().trim().isEmpty()) {
            throw new IllegalArgumentException("El destino del tramo es obligatorio");
        }
    }
}

