package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tpi.transporte.maestros_service.models.Deposito;
import tpi.transporte.maestros_service.dtos.DepositoDTO;

@Mapper(componentModel = "spring")
public interface DepositoMapper {

    @Mapping(target = "publicId", ignore = true)
    DepositoDTO toDTO(Deposito deposito);
    
    @Mapping(target = "publicId", ignore = true)
    Deposito toEntity(DepositoDTO depositoDTO);
}