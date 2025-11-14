package tpi.transporte.maestros_service.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContenedorRequestDTO implements Serializable {
    private double peso;
    private double volumen;
    private Long clienteId;
    private Long estadoId;
}
