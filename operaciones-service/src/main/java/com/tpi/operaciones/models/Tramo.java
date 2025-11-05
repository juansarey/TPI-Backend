package com.tpi.operaciones.models;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
@Entity @Table(name="tramo")
public class Tramo {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_tramo")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_ruta", foreignKey = @ForeignKey(name="fk_tramo_ruta"))
  private Ruta ruta;

  // chofer/camion asignado desde maestros por UUID lógico
  @Column(name="camion_public_id")
  private UUID camionPublicId;

  @Column(name="orden")  private Integer orden; // secuencia del tramo dentro de la ruta
  @Column(name="origen", length=120)   private String origen;
  @Column(name="destino", length=120)  private String destino;
  @Column(name="estado", length=30)    private String estado = "PENDIENTE"; // PENDIENTE|EN_CURSO|FINALIZADO

  @Column(name="inicio_plan", columnDefinition="timestamp with time zone")
  private OffsetDateTime inicioPlan;
  @Column(name="fin_plan", columnDefinition="timestamp with time zone")
  private OffsetDateTime finPlan;

  @Column(name="inicio_real", columnDefinition="timestamp with time zone")
  private OffsetDateTime inicioReal;
  @Column(name="fin_real", columnDefinition="timestamp with time zone")
  private OffsetDateTime finReal;

  @Column(name="creado_at")    private OffsetDateTime creadoAt = OffsetDateTime.now();
  @Column(name="actualizado_at") private OffsetDateTime actualizadoAt = OffsetDateTime.now();
  @PreUpdate void touch(){ this.actualizadoAt = OffsetDateTime.now(); }
}
