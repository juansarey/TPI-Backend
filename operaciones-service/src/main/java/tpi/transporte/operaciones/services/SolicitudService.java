package tpi.transporte.operaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpi.transporte.operaciones.clients.MaestrosServiceClient;
import tpi.transporte.operaciones.dtos.ClienteLiteDTO;
import tpi.transporte.operaciones.dtos.ContenedorLiteDTO;
import tpi.transporte.operaciones.models.Ruta;
import tpi.transporte.operaciones.models.Solicitud;
import tpi.transporte.operaciones.models.Tramo;
import tpi.transporte.operaciones.repositories.SolicitudRepository;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private TramoService tramoService;

    @Autowired
    private MaestrosServiceClient maestrosServiceClient;

    /**
     * Obtiene todas las solicitudes
     */
    public List<Solicitud> obtenerTodas() {
        return solicitudRepository.findAll();
    }

    /**
     * Obtiene una solicitud por su ID
     */
    public Solicitud obtenerPorId(Long id) {
        return solicitudRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada con ID: " + id));
    }

    /**
     * Obtiene todas las solicitudes de un cliente
     */
    public List<Solicitud> obtenerPorClienteId(Long clienteId) {
        return solicitudRepository.findAll().stream()
            .filter(solicitud -> solicitud.getClienteId().equals(clienteId))
            .toList();
    }

    /**
     * Crea una nueva solicitud de transporte
     * Valida que el cliente, contenedor y tarifa existan en maestros-service
     * Calcula el costo y tiempo estimado basado en la tarifa
     * Crea automáticamente la ruta asociada (sin tramos inicialmente)
     */
    @Transactional
    public Solicitud crear(Solicitud solicitud) {
        validarSolicitud(solicitud);
        
        // Validar y obtener datos de maestros-service
        ClienteLiteDTO cliente = maestrosServiceClient.validarYObtenerCliente(solicitud.getClienteId());
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente con ID " + solicitud.getClienteId() + " no existe en maestros-service");
        }
        
        ContenedorLiteDTO contenedor = maestrosServiceClient.validarYObtenerContenedor(solicitud.getContenedorId());
        if (contenedor == null) {
            throw new IllegalArgumentException("El contenedor con ID " + solicitud.getContenedorId() + " no existe en maestros-service");
        }
        
        MaestrosServiceClient.TarifaDTO tarifaDTO = maestrosServiceClient.validarYObtenerTarifa(solicitud.getTarifaId());
        if (tarifaDTO == null) {
            throw new IllegalArgumentException("La tarifa con ID " + solicitud.getTarifaId() + " no existe en maestros-service");
        }
        
        // Calcular costo y tiempo estimado (lógica simplificada - ajustar según requerimientos)
        // Por ahora, usamos valores por defecto o calculamos basado en la tarifa
        if (solicitud.getCostoEstimado() == null) {
            // Ejemplo: costo base fijo + (costo por km * distancia estimada)
            // Aquí deberías tener la distancia real, por ahora usamos un valor estimado
            Double distanciaEstimada = 100.0; // km (esto debería venir del request o calcularse)
            Double costoEstimado = tarifaDTO.getCostoBaseFijo() != null ? tarifaDTO.getCostoBaseFijo() : 0.0;
            costoEstimado += tarifaDTO.getCostoKmBase() != null ? (tarifaDTO.getCostoKmBase() * distanciaEstimada) : 0.0;
            solicitud.setCostoEstimado(costoEstimado);
        }
        
        if (solicitud.getTiempoEstimado() == null) {
            // Ejemplo: tiempo estimado basado en distancia (asumiendo velocidad promedio)
            // Esto debería calcularse según la lógica de negocio
            Double tiempoEstimado = 120.0; // minutos (valor por defecto)
            solicitud.setTiempoEstimado(tiempoEstimado);
        }
        
        // Guardar la solicitud
        Solicitud solicitudCreada = solicitudRepository.save(solicitud);
        
        // Crear la ruta asociada (1:1) sin tramos inicialmente
        Ruta ruta = new Ruta();
        ruta.setSolicitud(solicitudCreada);
        ruta.setCantidadTramos(0);
        ruta.setCantidadDepositos(0);
        ruta = rutaService.crear(ruta);
        
        solicitudCreada.setRuta(ruta);
        
        return solicitudRepository.save(solicitudCreada);
    }

    /**
     * Actualiza una solicitud existente
     */
    @Transactional
    public Solicitud actualizar(Long id, Solicitud solicitud) {
        validarSolicitud(solicitud);
        Solicitud solicitudExistente = obtenerPorId(id);
        
        // Solo permitir actualizar ciertos campos si la solicitud ya tiene una ruta con tramos
        if (solicitudExistente.getRuta() != null && 
            solicitudExistente.getRuta().getTramos() != null && 
            !solicitudExistente.getRuta().getTramos().isEmpty()) {
            throw new IllegalStateException("No se puede modificar una solicitud que ya tiene tramos asignados");
        }
        
        // Validar referencias si se cambian
        if (!solicitudExistente.getClienteId().equals(solicitud.getClienteId())) {
            ClienteLiteDTO cliente = maestrosServiceClient.validarYObtenerCliente(solicitud.getClienteId());
            if (cliente == null) {
                throw new IllegalArgumentException("El cliente con ID " + solicitud.getClienteId() + " no existe en maestros-service");
            }
        }
        
        if (!solicitudExistente.getContenedorId().equals(solicitud.getContenedorId())) {
            ContenedorLiteDTO contenedor = maestrosServiceClient.validarYObtenerContenedor(solicitud.getContenedorId());
            if (contenedor == null) {
                throw new IllegalArgumentException("El contenedor con ID " + solicitud.getContenedorId() + " no existe en maestros-service");
            }
        }
        
        if (!solicitudExistente.getTarifaId().equals(solicitud.getTarifaId())) {
            MaestrosServiceClient.TarifaDTO tarifaDTO = maestrosServiceClient.validarYObtenerTarifa(solicitud.getTarifaId());
            if (tarifaDTO == null) {
                throw new IllegalArgumentException("La tarifa con ID " + solicitud.getTarifaId() + " no existe en maestros-service");
            }
            // Recalcular costos si cambia la tarifa
            Double distanciaEstimada = 100.0; // km
            Double costoEstimado = tarifaDTO.getCostoBaseFijo() != null ? tarifaDTO.getCostoBaseFijo() : 0.0;
            costoEstimado += tarifaDTO.getCostoKmBase() != null ? (tarifaDTO.getCostoKmBase() * distanciaEstimada) : 0.0;
            solicitud.setCostoEstimado(costoEstimado);
        }
        
        solicitudExistente.setClienteId(solicitud.getClienteId());
        solicitudExistente.setContenedorId(solicitud.getContenedorId());
        solicitudExistente.setTarifaId(solicitud.getTarifaId());
        solicitudExistente.setCostoEstimado(solicitud.getCostoEstimado());
        solicitudExistente.setTiempoEstimado(solicitud.getTiempoEstimado());
        
        return solicitudRepository.save(solicitudExistente);
    }

    /**
     * Elimina una solicitud
     */
    @Transactional
    public void eliminar(Long id) {
        Solicitud solicitud = obtenerPorId(id);
        
        // Si tiene una ruta con tramos, eliminar primero los tramos
        if (solicitud.getRuta() != null) {
            if (solicitud.getRuta().getTramos() != null && !solicitud.getRuta().getTramos().isEmpty()) {
                // Eliminar todos los tramos
                solicitud.getRuta().getTramos().forEach(tramo -> tramoService.eliminar(tramo.getId()));
            }
            // Eliminar la ruta
            rutaService.eliminar(solicitud.getRuta().getId());
        }
        
        solicitudRepository.deleteById(id);
    }

    /**
     * Calcula el costo final de una solicitud basándose en los costos reales de sus tramos
     */
    public Double calcularCostoFinal(Long solicitudId) {
        Solicitud solicitud = obtenerPorId(solicitudId);
        
        if (solicitud.getRuta() == null || solicitud.getRuta().getTramos() == null || solicitud.getRuta().getTramos().isEmpty()) {
            return solicitud.getCostoEstimado();
        }
        
        return solicitud.getRuta().getTramos().stream()
            .filter(tramo -> tramo.getCostoReal() != null)
            .mapToDouble(Tramo::getCostoReal)
            .sum();
    }

    /**
     * Calcula el tiempo real de una solicitud basándose en las fechas de inicio y fin de sus tramos
     */
    public Double calcularTiempoReal(Long solicitudId) {
        Solicitud solicitud = obtenerPorId(solicitudId);
        
        if (solicitud.getRuta() == null || solicitud.getRuta().getTramos() == null || solicitud.getRuta().getTramos().isEmpty()) {
            return solicitud.getTiempoEstimado();
        }
        
        var tramos = solicitud.getRuta().getTramos();
        var primerTramo = tramos.stream()
            .filter(t -> t.getFechaHoraInicio() != null)
            .min((t1, t2) -> t1.getFechaHoraInicio().compareTo(t2.getFechaHoraInicio()));
        
        var ultimoTramo = tramos.stream()
            .filter(t -> t.getFechaHoraFin() != null)
            .max((t1, t2) -> t1.getFechaHoraFin().compareTo(t2.getFechaHoraFin()));
        
        if (primerTramo.isPresent() && ultimoTramo.isPresent()) {
            var inicio = primerTramo.get().getFechaHoraInicio();
            var fin = ultimoTramo.get().getFechaHoraFin();
            var minutos = java.time.Duration.between(inicio, fin).toMinutes();
            return (double) minutos;
        }
        
        return solicitud.getTiempoEstimado();
    }

    private void validarSolicitud(Solicitud solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        if (solicitud.getClienteId() == null) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }
        if (solicitud.getContenedorId() == null) {
            throw new IllegalArgumentException("El ID del contenedor es obligatorio");
        }
        if (solicitud.getTarifaId() == null) {
            throw new IllegalArgumentException("El ID de la tarifa es obligatorio");
        }
    }
}

