package tpi.transporte.maestros_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "contenedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenedor")
    private Long idContenedor;

    @Column(name = "public_id", unique = true, nullable = false)
    private String publicId;

    @Column(name = "identificacion_unica", unique = true)
    private String identificacionUnica;

    @Column(name = "peso_kg", nullable = false)
    private Double pesoKg;

    @Column(name = "volumen_m3", nullable = false)
    private Double volumenM3;

    @Column(name = "estado")
    private String estado;

    @CreationTimestamp
    @Column(name = "creado_at", updatable = false)
    private Timestamp creadoAt;

    @UpdateTimestamp
    @Column(name = "actualizado_at")
    private Timestamp actualizadoAt;

    // Relación: Muchos contenedores pertenecen a un cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnore // Evita bucles infinitos al serializar a JSON
    private Cliente cliente;

    @PrePersist
    public void prePersist() {
        if (this.publicId == null) {
            this.publicId = UUID.randomUUID().toString();
        }
    }
}