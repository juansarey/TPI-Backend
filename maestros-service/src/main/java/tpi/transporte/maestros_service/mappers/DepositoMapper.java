package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;
import tpi.transporte.maestros_service.models.Deposito; // Asegurate que sea 'entities'
import tpi.transporte.maestros_service.dtos.DepositoDTO;

@Mapper(componentModel = "spring")
public interface DepositoMapper {

    DepositoDTO toDTO(Deposito deposito);
    
    Deposito toEntity(DepositoDTO depositoDTO);
}