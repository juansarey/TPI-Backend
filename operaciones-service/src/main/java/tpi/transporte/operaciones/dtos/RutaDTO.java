package tpi.transporte.operaciones.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RutaDTO {
    private Long id;
    private Integer cantidadTramos;
    private Integer cantidadDepositos;
    private Long solicitudId;
    private List<TramoRequestDTO> tramos;
}
