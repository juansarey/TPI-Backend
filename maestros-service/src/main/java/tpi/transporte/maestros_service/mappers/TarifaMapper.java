package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;
import tpi.transporte.maestros_service.models.Tarifa; // Asegurate que sea 'entities'
import tpi.transporte.maestros_service.dtos.TarifaDTO;

@Mapper(componentModel = "spring")
public interface TarifaMapper {

    TarifaDTO toDTO(Tarifa tarifa);
    
    Tarifa toEntity(TarifaDTO tarifaDTO);
}