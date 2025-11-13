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
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camion")
    private Long idCamion;

    @Column(name = "nombre_transportista")
    private String nombreTransportista;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "capacidad_peso_kg", nullable = false)
    private Double capacidadPesoKg;

    @Column(name = "capacidad_volumen_m3", nullable = false)
    private Double capacidadVolumenM3;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

    @Column(name = "costo_base_km")
    private Double costoBaseKm;

}