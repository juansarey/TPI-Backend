package tpi.transporte.maestros_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tpi.transporte.maestros_service.models.Tarifa;
import tpi.transporte.maestros_service.dtos.TarifaDTO;

@Mapper(componentModel = "spring")
public interface TarifaMapper {

    @Mapping(source = "costoKm", target = "costoKmBase")
    @Mapping(target = "idTarifa", ignore = true)
    @Mapping(target = "descripcion", ignore = true)
    @Mapping(target = "rangoVolumenMinM3", ignore = true)
    @Mapping(target = "rangoVolumenMaxM3", ignore = true)
    @Mapping(target = "rangoPesoMinKg", ignore = true)
    @Mapping(target = "rangoPesoMaxKg", ignore = true)
    TarifaDTO toDTO(Tarifa tarifa);
    
    @Mapping(source = "costoKmBase", target = "costoKm")
    Tarifa toEntity(TarifaDTO tarifaDTO);
}