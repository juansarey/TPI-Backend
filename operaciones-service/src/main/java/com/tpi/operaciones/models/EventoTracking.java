package com.tpi.operaciones.models;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
@Entity @Table(name="evento_tracking")
public class EventoTracking {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_evento")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_tramo", foreignKey = @ForeignKey(name="fk_evento_tramo"))
  private Tramo tramo;

  @Column(name="tipo", length=40)  private String tipo; // INICIO_TRAMO, FIN_TRAMO, CHECKPOINT, etc
  @Column(name="detalle", length=200) private String detalle;
  @Column(name="lat")  private Double lat;
  @Column(name="lng")  private Double lng;

  @Column(name="timestamp", columnDefinition="timestamp with time zone")
  private OffsetDateTime timestamp = OffsetDateTime.now();
}
