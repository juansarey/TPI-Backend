package tpi.transporte.operaciones.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // Ej: "PENDIENTE", "EN_CURSO", "FINALIZADO"

    @Column
    private String descripcion;
}
