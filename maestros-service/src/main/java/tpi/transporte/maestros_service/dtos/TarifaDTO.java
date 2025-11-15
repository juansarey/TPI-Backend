package tpi.transporte.maestros_service.dtos;

import lombok.Data;

@Data
public class TarifaDTO {
    private Long idTarifa;
    // Renombrado de 'costoKm' a 'costoKmBase' para coincidir con la Entity
    private Double costoKmBase;
    private Double costoBaseFijo;
}