package tpi.transporte.maestros_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "camion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camion")
    private Long idCamion;

    @Column(name = "public_id", unique = true, nullable = false)
    private String publicId;

    @Column(name = "dominio", unique = true, nullable = false)
    private String dominio;

    @Column(name = "nombre_transportista")
    private String nombreTransportista;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "capacidad_peso_kg", nullable = false)
    private Double capacidadPesoKg;

    @Column(name = "capacidad_volumen_m3", nullable = false)
    private Double capacidadVolumenM3;

    @Column(name = "consumo_1_km")
    private Double consumo1Km;

    @Column(name = "costo_base_km")
    private Double costoBaseKm;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

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