package tpi.transporte.operaciones.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tramo {

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Reference to the assigned truck's public UUID. */
    @Column(name = "camion_ref")
    private Long camionId;

    /** Logical reference to the origin depot (UUID) if applicable.  Nullable for origin. */
    @Column(name = "origen_deposito_ref")
    private Long origenDepositoId;

    /** Logical reference to the destination depot (UUID) if applicable.  Nullable for final destination. */
    @Column(name = "destino_deposito_ref")
    private Long destinoDepositoId;

    /** Estimated distance in metres for this segment. */
    @Column(name = "distancia_m")
    private Double distanciaM;

    /** Estimated duration in seconds for this segment. */
    @Column(name = "duracion_sec")
    private Long duracionSec;

    /** Estimated cost for this segment. */
    @Column(name = "costo_aprox")
    private Double costoAprox;

    /** Actual cost calculated after completion. */
    @Column(name = "costo_real")
    private Double costoReal;

    /** Estimated start date/time for the segment. */
    @Column(name = "fecha_inicio_estimada")
    private LocalDateTime fechaInicioEstimada;

    /** Real start date/time recorded when the segment begins. */
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    /** Estimated finish date/time for the segment. */
    @Column(name = "fecha_fin_estimada")
    private LocalDateTime fechaFinEstimada;
    /** Real finish date/time recorded when the segment ends. */
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

        /** Parent route to which this segment belongs (many segments per route). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ruta")
    private Ruta ruta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramo", nullable = false)
    private TipoTramo tipoTramo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoTramo estado;


}