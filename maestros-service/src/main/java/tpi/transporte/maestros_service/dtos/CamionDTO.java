package tpi.transporte.maestros_service.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tpi.transporte.maestros_service.models.Camion;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CamionDTO implements Serializable{
    private Long idCamion;
    private String nombreTransportista;
    private String telefono;
    private Double capacidadPesoKg;
    private Double capacidadVolumenM3;
    private Boolean disponibilidad;
    private Double costoBaseKm;

    // Helper para mapear desde la entidad
    public CamionDTO(Camion camion) {
        this.idCamion = camion.getIdCamion();
        this.nombreTransportista = camion.getNombreTransportista();
        this.telefono = camion.getTelefono();
        this.capacidadPesoKg = camion.getCapacidadPesoKg();
        this.capacidadVolumenM3 = camion.getCapacidadVolumenM3();
        this.disponibilidad = camion.getDisponibilidad();
        this.costoBaseKm = camion.getCostoBaseKm();
    }

}