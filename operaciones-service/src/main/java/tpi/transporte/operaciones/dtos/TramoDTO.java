package tpi.transporte.operaciones.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TramoDTO {
    private Long id;
    private Long camionId;
    private Long origen;
    private Long destino;
    private Double costoAprox;
    private Double costoReal;
    private LocalDateTime fechaHoraInicioEstimada;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFinEstimada;
    private LocalDateTime fechaHoraFin;
    private RutaDTO rutaId;
    private TipoTramoDTO tipoTramo;
    private EstadoTramoDTO estadoTramo;
}
