package tpi.transporte.maestros_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tarifa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa")
    private Long idTarifa;

    @Column(name = "public_id", unique = true, nullable = false)
    private String publicId;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "rango_volumen_min_m3")
    private Double rangoVolumenMinM3;

    @Column(name = "rango_volumen_max_m3")
    private Double rangoVolumenMaxM3;

    @Column(name = "rango_peso_min_kg")
    private Double rangoPesoMinKg;

    @Column(name = "rango_peso_max_kg")
    private Double rangoPesoMaxKg;

    @Column(name = "costo_km_base")
    private Double costoKmBase;

    @Column(name = "costo_base_fijo")
    private Double costoBaseFijo;

    @CreationTimestamp
    @Column(name = "creado_at", updatable = false)
    private Timestamp creadoAt;

    @PrePersist
    public void prePersist() {
        if (this.publicId == null) {
            this.publicId = UUID.randomUUID().toString();
        }
    }
}