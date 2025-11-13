package tpi.transporte.operaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO {
    private Long id;
    private Long clienteId;
    private Long contenedorId;
    private Long tarifaId;
    private Double costoEstimado;
    private Double tiempoEstimado;
    private RutaDTO ruta;
}
