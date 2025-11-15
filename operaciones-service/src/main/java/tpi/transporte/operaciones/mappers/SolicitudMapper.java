package tpi.transporte.operaciones.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tpi.transporte.operaciones.dtos.SolicitudRequestDTO;
import tpi.transporte.operaciones.dtos.SolicitudResponseDTO;
import tpi.transporte.operaciones.models.Solicitud;

@Mapper(componentModel = "spring")
public interface SolicitudMapper {

    // Entity -> ResponseDTO
    @Mapping(target = "cliente", ignore = true)      // se completa con RestTemplate
    @Mapping(target = "contenedor", ignore = true)   // se completa con RestTemplate
    @Mapping(target = "ruta", ignore = true)         // si la armás aparte
    @Mapping(target = "costoFinal", ignore = true)   // se calcula dinámicamente con calcularCostoFinal()
    @Mapping(target = "tiempoReal", ignore = true)   // se calcula dinámicamente con calcularTiempoReal()
    SolicitudResponseDTO toResponseDTO(Solicitud entity);

    List<SolicitudResponseDTO> toResponseDTOList(List<Solicitud> entities);

    // RequestDTO -> Entity (para crear/actualizar)
    // Si el ID lo genera la BD, lo ignorás acá
    @Mapping(target = "idSolicitud", ignore = true)
    @Mapping(target = "ruta", ignore = true)   // la ruta se crea por separado
    Solicitud toEntity(SolicitudRequestDTO dto);
}
