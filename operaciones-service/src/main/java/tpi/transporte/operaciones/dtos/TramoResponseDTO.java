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
public class TramoResponseDTO {

    private Long id;

    private Long camionId;

    private String origen;
    private String destino;

    private Double costoAprox;
    private Double costoReal;

    private LocalDateTime fechaHoraInicioEstimada;
    private LocalDateTime fechaHoraInicio;

    private LocalDateTime fechaHoraFinEstimada;
    private LocalDateTime fechaHoraFin;

    private Long rutaId;
    private Long tipoTramoId;
    private Long estadoTramoId;
}
