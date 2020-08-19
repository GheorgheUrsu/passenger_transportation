package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.BussDTO;
import com.passengertransportation.demo.model.Buss;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BussMapper {
    BussMapper INSTANCE = Mappers.getMapper(BussMapper.class);


    Buss fromBussDto(BussDTO bussDTO, @Context CycleAvoidingMappingContex context);

    @InheritInverseConfiguration
    BussDTO toBussDto(Buss buss, @Context CycleAvoidingMappingContex context);
}
