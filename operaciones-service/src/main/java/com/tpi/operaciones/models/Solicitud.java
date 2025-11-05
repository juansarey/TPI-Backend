package com.tpi.operaciones.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
@Entity @Table(name = "solicitud")
public class Solicitud {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_solicitud")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  // Referencias lógicas a maestros
  @Column(name="cliente_public_id", nullable=false)
  private UUID clientePublicId;

  @Column(name="contenedor_public_id", nullable=false)
  private UUID contenedorPublicId;

  @Column(name="estado", nullable=false, length=30)
  private String estado = "PENDIENTE"; // PENDIENTE | EN_PROCESO | COMPLETADA | CANCELADA

  @Column(name="origen", length=120)   private String origen;
  @Column(name="destino", length=120)  private String destino;

  @Column(name="creado_at")    private OffsetDateTime creadoAt = OffsetDateTime.now();
  @Column(name="actualizado_at") private OffsetDateTime actualizadoAt = OffsetDateTime.now();

  @PreUpdate void touch(){ this.actualizadoAt = OffsetDateTime.now(); }
}
