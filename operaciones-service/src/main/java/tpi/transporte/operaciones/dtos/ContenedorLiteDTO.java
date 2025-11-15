// tpi.transporte.operaciones.dtos.ContenedorLiteDTO
package tpi.transporte.operaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorLiteDTO {
    private Long idContenedor;
    private Double pesoKg;
    private Double volumenM3;
}
