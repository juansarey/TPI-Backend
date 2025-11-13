package tpi.transporte.maestros_service.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContenedorDTO {
    private Long idContenedor;
    private double pesoKg;
    private double volumenM3;
    private EstadoContenedorDTO estado;
    private Long idCliente;
}
