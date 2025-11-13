package tpi.transporte.maestros_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CamionDTO {
    private Long idCamion;
    private String nombreTransportista;
    private String telefono;
    private Double capacidadPesoKg;
    private Double capacidadVolumenM3;
    private Boolean disponibilidad;
    private Double costoBaseKm;
}