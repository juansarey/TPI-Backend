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
public class TramoRequestDTO {

    // Id del camión en Maestros (se guarda en camionId)
    private Long camionId;

    private String origen;
    private String destino;

    private Double costoAprox;
    private Double costoReal; // puede venir null al crear

    private LocalDateTime fechaHoraInicioEstimada;
    private LocalDateTime fechaHoraInicio;

    private LocalDateTime fechaHoraFinEstimada;
    private LocalDateTime fechaHoraFin;

    // Relaciones internas de Operaciones
    private Long rutaId;
    private Long tipoTramoId;
    private Long estadoTramoId;
}

