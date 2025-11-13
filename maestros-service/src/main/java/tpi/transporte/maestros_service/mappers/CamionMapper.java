package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;

import tpi.transporte.maestros_service.models.Camion;
import tpi.transporte.maestros_service.dtos.CamionDTO;

@Mapper(componentModel = "spring") // Le dice a MapStruct que genere una implementación de Spring Bean
public interface CamionMapper {
    
    CamionDTO toDTO(Camion camion);
    
    Camion toEntity(CamionDTO camionDTO);
}