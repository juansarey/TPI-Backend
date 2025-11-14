package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;

import tpi.transporte.maestros_service.dtos.ContenedorRequestDTO;
import tpi.transporte.maestros_service.models.Contenedor;



@Mapper(componentModel = "spring", uses =  {ClienteMapper.class, EstadoContenedorMapper.class})
public interface ContenedorMapper {
    ContenedorRequestDTO toDTO(Contenedor contenedor);
    Contenedor toEntity(ContenedorRequestDTO dto);
}
