package tpi.transporte.maestros_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpi.transporte.maestros_service.models.Cliente;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    // Helper para mapear desde la entidad
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                cliente.getDireccion()
        );
    }
}

