package tpi.transporte.operaciones.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    /** Primary key for internal use. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    /** Logical reference to the client (UUID from Maestro). */
    @Column(name = "cliente_id")
    private Long clienteId;

    /** Logical reference to the container (UUID from Maestro). */
    @Column(name = "contenedor_id")
    private Long contenedorId;

    /** Logical reference to the selected tariff (UUID from Maestro). */
    @Column(name = "tarifa_id")
    private Long tarifaId;

    /** Estimated cost calculated at the time of scheduling. */
    @Column(name = "costo_estimado")
    private Double costoEstimado;

    /** Estimated time (in minutes) for the entire delivery. */
    @Column(name = "tiempo_estimado")
    private Double tiempoEstimado;

    @OneToOne(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ruta ruta;

}