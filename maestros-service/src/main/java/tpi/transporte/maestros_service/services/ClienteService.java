package tpi.transporte.maestros_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.transporte.maestros_service.models.Cliente;
import tpi.transporte.maestros_service.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepo;

    public List<Cliente> obtenerTodos(){
        return clienteRepo.findAll();
    }

    public Cliente obtenerPorId(Long id){
        return clienteRepo.findById(id).orElse(null);
    }

    public Cliente crear(Cliente cliente){
        validarCliente(cliente);
        return clienteRepo.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente cliente){
        validarCliente(cliente);
        cliente.setIdCliente(id);
        return clienteRepo.save(cliente);
    }

    public void eliminar(Long id){
        clienteRepo.deleteById(id);
    }

    private void validarCliente(Cliente cliente) {

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }
        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del cliente es obligatorio.");
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono del cliente es obligatorio.");
        }

        if (cliente.getDireccion() == null || cliente.getDireccion().trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección del cliente es obligatoria.");
        }
    }
}
