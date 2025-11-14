package tpi.transporte.maestros_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    
}
