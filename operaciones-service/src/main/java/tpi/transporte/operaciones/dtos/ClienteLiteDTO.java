// tpi.transporte.operaciones.dtos.ClienteLiteDTO
package tpi.transporte.operaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteLiteDTO {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
}
