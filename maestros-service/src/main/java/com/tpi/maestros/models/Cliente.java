package com.tpi.maestros.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "cliente")
public class Cliente {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_cliente")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  @NotBlank @Size(max=100)
  private String nombre;

  @Size(max=100)
  private String apellido;

  @Email @NotBlank @Size(max=150)
  @Column(unique = true)
  private String email;

  @Size(max=30)
  private String telefono;

  @Column(columnDefinition = "text")
  private String direccion;

  @Column(name="creado_at")
  private OffsetDateTime creadoAt = OffsetDateTime.now();

  @Column(name="actualizado_at")
  private OffsetDateTime actualizadoAt = OffsetDateTime.now();

  @PreUpdate
  void touch() { this.actualizadoAt = OffsetDateTime.now(); }
}
