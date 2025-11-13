package tpi.transporte.maestros_service.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositoDTO {
    private Long idDeposito;
    private String nombre;
    private String direccion;
    private Double latitud;
    private Double longitud;
    private Double costoDiario;
}
