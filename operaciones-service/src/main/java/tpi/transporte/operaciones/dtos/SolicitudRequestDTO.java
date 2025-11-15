package tpi.transporte.operaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRequestDTO {
    private Long clienteId;
    private Long contenedorId;
    private Long tarifaId;
}
