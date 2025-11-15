package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tpi.transporte.maestros_service.dtos.ClienteRequestDTO;
import tpi.transporte.maestros_service.dtos.ClienteResponseDTO;
import tpi.transporte.maestros_service.models.Cliente;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // Entity -> DTO
    ClienteResponseDTO toResponseDTO(Cliente cliente);
    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes);

    // DTO -> Entity (ignoramos la lista de contenedores)
    @Mapping(target = "contenedores", ignore = true)
    Cliente toEntity(ClienteRequestDTO dto);
}