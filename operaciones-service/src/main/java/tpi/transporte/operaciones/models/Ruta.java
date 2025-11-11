package tpi.transporte.operaciones.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Total number of segments (tramos) in the route. */
    @Column(name = "cantidad_tramos")
    private Integer cantidadTramos;

    /** Total number of depots in the route. */
    @Column(name = "cantidad_depositos")
    private Integer cantidadDepositos;

    /** Reference to the transport request associated with this route (1:1). */
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false, unique = true)
    private Solicitud solicitud;

        /** 1 Ruta : N Tramos */
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tramo> tramos = new ArrayList<>();

}