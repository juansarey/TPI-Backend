package tpi.transporte.operaciones.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoTramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_tramo")
    private Long id;

    /** Nombre del tipo de tramo (por ejemplo: ORIGEN_A_DEPOSITO, DEPOSITO_A_DESTINO). */
    @Column(nullable = false, unique = true)
    private String nombre;

    /** Descripción opcional para mayor claridad. */
    @Column
    private String descripcion;
}
