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
@Table(name = "camion")
public class Camion {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_camion")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  @NotBlank @Size(max=40)
  private String patente;

  @Digits(integer=8, fraction=2)
  @Column(name="capacidad_peso_kg", precision=10, scale=2)
  private BigDecimal capacidadPesoKg;

  @Digits(integer=7, fraction=3)
  @Column(name="capacidad_volumen_m3", precision=10, scale=3)
  private BigDecimal capacidadVolumenM3;

  @NotBlank @Size(max=30)
  private String estado = "HABILITADO";

  @Column(name="creado_at")
  private OffsetDateTime creadoAt = OffsetDateTime.now();

  @Column(name="actualizado_at")
  private OffsetDateTime actualizadoAt = OffsetDateTime.now();

  @PreUpdate
  void touch() { this.actualizadoAt = OffsetDateTime.now(); }
}
