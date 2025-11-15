package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tpi.transporte.maestros_service.dtos.ContenedorRequestDTO;
import tpi.transporte.maestros_service.dtos.ContenedorResponseDTO;
import tpi.transporte.maestros_service.models.Contenedor;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, EstadoContenedorMapper.class})
public interface ContenedorMapper {
    
    @Mapping(source = "pesoKg", target = "peso")
    @Mapping(source = "volumenM3", target = "volumen")
    @Mapping(source = "cliente.idCliente", target = "clienteId")
    @Mapping(source = "estadoContenedor.id", target = "estadoId")
    ContenedorRequestDTO toRequestDTO(Contenedor contenedor);
    
    @Mapping(source = "peso", target = "pesoKg")
    @Mapping(source = "volumen", target = "volumenM3")
    @Mapping(source = "clienteId", target = "cliente.idCliente")
    @Mapping(source = "estadoId", target = "estadoContenedor.id")
    Contenedor toEntity(ContenedorRequestDTO dto);
    
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "estadoContenedor", target = "estadoContenedor")
    ContenedorResponseDTO toResponseDTO(Contenedor contenedor);
}
