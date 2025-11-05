package com.tpi.operaciones.models;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
@Entity @Table(name="ruta")
public class Ruta {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_ruta")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  // Una Ruta pertenece a una Solicitud
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_solicitud", foreignKey = @ForeignKey(name="fk_ruta_solicitud"))
  private Solicitud solicitud;

  @Column(name="descripcion", length=200)
  private String descripcion;

  @Column(name="creado_at")    private OffsetDateTime creadoAt = OffsetDateTime.now();
  @Column(name="actualizado_at") private OffsetDateTime actualizadoAt = OffsetDateTime.now();
  @PreUpdate void touch(){ this.actualizadoAt = OffsetDateTime.now(); }
}
