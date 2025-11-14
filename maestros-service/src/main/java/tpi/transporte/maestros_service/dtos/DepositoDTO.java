package tpi.transporte.maestros_service.dtos;

import lombok.Data;
// Usamos @Data (incluye Getter, Setter, AllArgsConstructor, NoArgsConstructor, etc.)
@Data
public class DepositoDTO {
    // Usamos publicId para la API, no el ID interno
    private String publicId;
    private String nombre;
    private String direccion;
    private Double latitud;
    private Double longitud;
    private Double costoDiario;
}