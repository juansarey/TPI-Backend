package tpi.transporte.maestros_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tpi.transporte.maestros_service.models.Contenedor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorResponseDTO {

    private Long idContenedor;
    private Double pesoKg;
    private Double volumenM3;
    private ClienteResponseDTO cliente;
    
    //private EstadoDTO estado;
    //private Long estadoId;

    public ContenedorResponseDTO(Contenedor contenedor) {
        this.idContenedor = contenedor.getIdContenedor();
        this.pesoKg = contenedor.getPesoKg();
        this.volumenM3 = contenedor.getVolumenM3();
        //this.estadoId = contenedor.getEstado() != null ? contenedor.getEstado().getId() : null;
    }
}
