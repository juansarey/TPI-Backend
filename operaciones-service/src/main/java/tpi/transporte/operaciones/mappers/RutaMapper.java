package tpi.transporte.operaciones.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tpi.transporte.operaciones.dtos.RutaDTO;
import tpi.transporte.operaciones.models.Ruta;

@Mapper(componentModel = "spring")
public interface RutaMapper {

    // ===== Entity -> DTO =====
    @Mapping(source = "solicitud.idSolicitud", target = "solicitudId")
    @Mapping(target = "tramos", ignore = true) 
    // Los tramos se llenan aparte (con TramoMapper) si los necesitás
    RutaDTO toDTO(Ruta ruta);

    List<RutaDTO> toDTOList(List<Ruta> rutas);

    // ===== DTO -> Entity =====
    @Mapping(target = "solicitud", ignore = true) // la seteás en el service con el id
    @Mapping(target = "tramos", ignore = true)    // se crean y asocian en el service
    Ruta toEntity(RutaDTO dto);
}

