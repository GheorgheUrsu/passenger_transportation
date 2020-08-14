package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    RouteDTO toDTO(Route route);
    Route fromDto(RouteDTO routeDTO);
}
