package tpi.transporte.operaciones.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponseDTO {

    private Long idSolicitud;
    private ClienteLiteDTO cliente;       // o solo clienteId
    private ContenedorLiteDTO contenedor; // o solo contenedorId
    private Long tarifaId;

    private Double costoEstimado;
    private Double tiempoEstimado;
    private Double costoFinal;
    private Double tiempoReal;

    private RutaDTO ruta; // cuando ya exista una ruta asociada
}

