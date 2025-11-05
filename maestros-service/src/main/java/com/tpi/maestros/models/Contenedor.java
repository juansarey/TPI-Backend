package com.tpi.maestros.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "contenedor")
public class Contenedor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_contenedor")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  @NotBlank @Size(max=100)
  @Column(name="identificacion_unica", unique=true)
  private String identificacionUnica;

  @Digits(integer=8, fraction=2)
  @Column(name="peso_kg", precision=10, scale=2)
  private BigDecimal pesoKg;

  @Digits(integer=7, fraction=3)
  @Column(name="volumen_m3", precision=10, scale=3)
  private BigDecimal volumenM3;

  @NotBlank @Size(max=30)
  private String estado = "DISPONIBLE";

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_cliente", foreignKey = @ForeignKey(name="fk_contenedor_cliente"))
  private Cliente cliente;

  @Column(name="creado_at")
  private OffsetDateTime creadoAt = OffsetDateTime.now();

  @Column(name="actualizado_at")
  private OffsetDateTime actualizadoAt = OffsetDateTime.now();

  @PreUpdate
  void touch() { this.actualizadoAt = OffsetDateTime.now(); }
}
