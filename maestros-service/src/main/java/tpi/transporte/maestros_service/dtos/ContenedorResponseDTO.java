package tpi.transporte.maestros_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorResponseDTO {

    private Long idContenedor;
    private Double pesoKg;
    private Double volumenM3;
    private ClienteResponseDTO cliente;
    private EstadoContenedorDTO estadoContenedor;
}
