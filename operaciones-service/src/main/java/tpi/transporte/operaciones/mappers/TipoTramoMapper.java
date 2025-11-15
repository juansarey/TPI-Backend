package tpi.transporte.operaciones.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import tpi.transporte.operaciones.dtos.TipoTramoDTO;
import tpi.transporte.operaciones.models.TipoTramo;

@Mapper(componentModel = "spring")
public interface TipoTramoMapper {

    TipoTramoDTO toDTO(TipoTramo entity);

    List<TipoTramoDTO> toDTOList(List<TipoTramo> entities);

    // Para crear/actualizar
    // Si el DTO no tiene id, podés ignorarlo:
    // @Mapping(target = "idTipoTramo", ignore = true)
    TipoTramo toEntity(TipoTramoDTO dto);
}
