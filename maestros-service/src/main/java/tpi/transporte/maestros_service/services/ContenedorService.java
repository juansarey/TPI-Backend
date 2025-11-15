package tpi.transporte.maestros_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.transporte.maestros_service.models.Contenedor;
import tpi.transporte.maestros_service.models.Cliente;
import tpi.transporte.maestros_service.models.EstadoContenedor;
import tpi.transporte.maestros_service.repositories.ContenedorRepository;
import tpi.transporte.maestros_service.repositories.ClienteRepository;
import tpi.transporte.maestros_service.repositories.EstadoContenedorRepository;

@Service
public class ContenedorService {
    
    @Autowired
    private ContenedorRepository contenedorRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private EstadoContenedorRepository estadoContenedorRepo;

    public List<Contenedor> obtenerTodos(){
        return contenedorRepo.findAll();
    }

    public Contenedor obtenerPorId(Long id){
        return contenedorRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenedor no encontrado"));
    }

    public Contenedor crear(Contenedor contenedor){
        validarContenedor(contenedor);
        return contenedorRepo.save(contenedor);
    }

    public Contenedor actualizar(Long id, Contenedor contenedor){
        validarContenedor(contenedor);
        contenedor.setIdContenedor(id);
        return contenedorRepo.save(contenedor);
    }

    public void eliminar(Long id){
        if (!contenedorRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenedor no encontrado");
        }
        contenedorRepo.deleteById(id);
    }

    private void validarContenedor(Contenedor contenedor) {
        if (contenedor.getPesoKg() == null || contenedor.getPesoKg() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El peso del contenedor debe ser mayor a cero.");
        }

        if (contenedor.getVolumenM3() == null || contenedor.getVolumenM3() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El volumen del contenedor debe ser mayor a cero.");
        }

        if (contenedor.getCliente() == null || contenedor.getCliente().getIdCliente() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El contenedor debe estar asociado a un cliente válido.");
        }

        // Verificar que el cliente existe
        Cliente cliente = clienteRepo.findById(contenedor.getCliente().getIdCliente())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente especificado no existe."));

        if (contenedor.getEstadoContenedor() == null || contenedor.getEstadoContenedor().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El contenedor debe tener un estado válido.");
        }

        // Verificar que el estado existe
        EstadoContenedor estado = estadoContenedorRepo.findById(contenedor.getEstadoContenedor().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estado especificado no existe."));

        // Asignar las entidades completas para evitar problemas de persistencia
        contenedor.setCliente(cliente);
        contenedor.setEstadoContenedor(estado);
    }
}

