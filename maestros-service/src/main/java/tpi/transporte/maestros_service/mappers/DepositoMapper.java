package tpi.transporte.maestros_service.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tpi.transporte.maestros_service.models.Deposito;
import tpi.transporte.maestros_service.dtos.DepositoDTO;

@Mapper(componentModel = "spring")
public interface DepositoMapper {

    // Entity -> DTO
    DepositoDTO toDTO(Deposito deposito);

    List<DepositoDTO> toDTOList(List<Deposito> depositos);

    // DTO -> Entity
    @Mapping(target = "idDeposito", ignore = true) // la BD lo genera en "crear"
    Deposito toEntity(DepositoDTO dto);
}
