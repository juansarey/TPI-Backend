package tpi.transporte.maestros_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenedor")
    private Long idContenedor;

    @Column(name = "peso_kg", nullable = false)
    private Double pesoKg;

    @Column(name = "volumen_m3", nullable = false)
    private Double volumenM3;

    @ManyToOne
    @JoinColumn(name = "estado_contenedor_id", nullable = false)
    private EstadoContenedor estadoContenedor;


    // Relación: Muchos contenedores pertenecen a un cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

}