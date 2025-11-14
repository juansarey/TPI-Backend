package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;

import tpi.transporte.maestros_service.dtos.ClienteRequestDTO;
import tpi.transporte.maestros_service.dtos.ClienteResponseDTO;
import tpi.transporte.maestros_service.models.Cliente;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponseDTO toResponseDTO(Cliente cliente);

    Cliente toEntity(ClienteRequestDTO dto);
    
    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes);
}