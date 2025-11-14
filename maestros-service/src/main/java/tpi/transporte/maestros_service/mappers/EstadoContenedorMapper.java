package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;

import tpi.transporte.maestros_service.dtos.EstadoContenedorDTO;
import tpi.transporte.maestros_service.models.EstadoContenedor;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoContenedorMapper {
    EstadoContenedorDTO toDTO(EstadoContenedor estadoContenedor);
    EstadoContenedor toEntity(EstadoContenedorDTO dto);
    List<EstadoContenedorDTO> toDTOList(List<EstadoContenedor> estados);

}
