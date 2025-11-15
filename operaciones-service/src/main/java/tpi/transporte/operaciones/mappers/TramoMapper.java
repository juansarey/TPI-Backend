package tpi.transporte.operaciones.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import tpi.transporte.operaciones.dtos.TramoRequestDTO;
import tpi.transporte.operaciones.dtos.TramoResponseDTO;
import tpi.transporte.operaciones.models.Tramo;

@Mapper(
    componentModel = "spring",
    uses = { TipoTramoMapper.class, EstadoTramoMapper.class }
)
public interface TramoMapper {

    // Entity -> ResponseDTO
    @Mappings({
        @Mapping(source = "ruta.id", target = "rutaId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE),
        @Mapping(source = "tipoTramo.id", target = "tipoTramoId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE),
        @Mapping(source = "estado.id", target = "estadoTramoId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    })
    TramoResponseDTO toResponseDTO(Tramo tramo);

    List<TramoResponseDTO> toResponseDTOList(List<Tramo> tramos);

    // RequestDTO -> Entity (las relaciones las completa el service)
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "ruta", ignore = true),
        @Mapping(target = "tipoTramo", ignore = true),
        @Mapping(target = "estado", ignore = true)
    })
    Tramo toEntity(TramoRequestDTO dto);
}
