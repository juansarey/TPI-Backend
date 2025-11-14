package tpi.transporte.maestros_service.dtos;

import lombok.Data;

@Data
public class TarifaDTO {
    private String publicId;
    private String descripcion;
    private Double rangoVolumenMinM3;
    private Double rangoVolumenMaxM3;
    private Double rangoPesoMinKg;
    private Double rangoPesoMaxKg;
    // Renombrado de 'costoKm' a 'costoKmBase' para coincidir con la Entity
    private Double costoKmBase;
    private Double costoBaseFijo;
}