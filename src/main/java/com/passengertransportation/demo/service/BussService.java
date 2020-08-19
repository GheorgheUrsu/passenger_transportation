package com.passengertransportation.demo.service;

import com.passengertransportation.demo.dto.BussDTO;

import java.util.List;

public interface BussService {
    List<BussDTO> getAllBusses();

    BussDTO getBussByID(Long bussID);

    BussDTO deleteBussByID(Long bussID);

    BussDTO createBuss(BussDTO bussDTO);

    BussDTO update(BussDTO bussDTO, Long bussID);
}
