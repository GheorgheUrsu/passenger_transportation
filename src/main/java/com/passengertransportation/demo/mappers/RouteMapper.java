package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.model.Route;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);


    RouteDTO toDTO(Route route, @Context CycleAvoidingMappingContex context);

    @InheritInverseConfiguration
    Route fromDto(RouteDTO routeDTO, @Context CycleAvoidingMappingContex context);
}
