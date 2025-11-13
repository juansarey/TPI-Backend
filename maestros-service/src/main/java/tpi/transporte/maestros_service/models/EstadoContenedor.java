package tpi.transporte.maestros_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "estado_contenedor")
@NoArgsConstructor
@AllArgsConstructor
public class EstadoContenedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_contenedor_id")
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
}
