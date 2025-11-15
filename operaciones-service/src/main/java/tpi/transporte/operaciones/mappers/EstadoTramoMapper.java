package tpi.transporte.operaciones.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import tpi.transporte.operaciones.dtos.EstadoTramoDTO;
import tpi.transporte.operaciones.models.EstadoTramo;

@Mapper(componentModel = "spring")
public interface EstadoTramoMapper {

    EstadoTramoDTO toDTO(EstadoTramo entity);

    List<EstadoTramoDTO> toDTOList(List<EstadoTramo> entities);

    EstadoTramo toEntity(EstadoTramoDTO dto);
}

