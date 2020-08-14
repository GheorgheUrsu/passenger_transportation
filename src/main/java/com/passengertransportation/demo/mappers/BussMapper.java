package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.BussDTO;
import com.passengertransportation.demo.model.Buss;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BussMapper {
    BussMapper INSTANCE = Mappers.getMapper(BussMapper.class);

    Buss fromBussDto(BussDTO bussDTO);
    BussDTO toBussDto(Buss buss);
}
