package com.tpi.operaciones.models;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
@Entity @Table(name="factura")
public class Factura {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_factura")
  private Long id;

  @Column(name="public_id", nullable=false, unique=true, updatable=false)
  private UUID publicId = UUID.randomUUID();

  // referencia a la solicitud facturada
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_solicitud", foreignKey = @ForeignKey(name="fk_factura_solicitud"))
  private Solicitud solicitud;

  @Column(name="monto", precision=12, scale=2)
  private BigDecimal monto;

  @Column(name="moneda", length=10)
  private String moneda = "ARS";

  @Column(name="estado", length=30)
  private String estado = "EMITIDA"; // EMITIDA | PAGADA | ANULADA

  @Column(name="creado_at")    private OffsetDateTime creadoAt = OffsetDateTime.now();
  @Column(name="actualizado_at") private OffsetDateTime actualizadoAt = OffsetDateTime.now();
  @PreUpdate void touch(){ this.actualizadoAt = OffsetDateTime.now(); }
}
