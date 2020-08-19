package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.BussDTO;
import com.passengertransportation.demo.excepions.ApplicationException;
import com.passengertransportation.demo.excepions.ExceptionType;
import com.passengertransportation.demo.mappers.BussMapper;
import com.passengertransportation.demo.mappers.CycleAvoidingMappingContex;
import com.passengertransportation.demo.mappers.RouteMapper;
import com.passengertransportation.demo.model.Buss;
import com.passengertransportation.demo.repo.BussRepository;
import com.passengertransportation.demo.service.BussService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BussServiceImpl implements BussService {

    private final BussRepository bussRepository;

    @Override
    public List<BussDTO> getAllBusses() {
        return bussRepository.findAll().stream()
                .map(buss -> BussMapper.INSTANCE.toBussDto(buss, new CycleAvoidingMappingContex()))
                .collect(Collectors.toList());
    }

    @Override
    public BussDTO getBussByID(Long bussID) {
        final Buss buss = bussRepository.findById(bussID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.BUSS_NOT_FOUND));
        return BussMapper.INSTANCE.toBussDto(buss, new CycleAvoidingMappingContex());
    }

    @Override
    public BussDTO deleteBussByID(Long bussID) {
        final Buss deletable = bussRepository.findById(bussID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.BUSS_NOT_FOUND));
        bussRepository.deleteById(bussID);

        return BussMapper.INSTANCE.toBussDto(deletable, new CycleAvoidingMappingContex());
    }

    @Override
    public BussDTO createBuss(BussDTO bussDTO) {
        final Buss created = BussMapper.INSTANCE.fromBussDto(bussDTO, new CycleAvoidingMappingContex());
        final Buss saved = bussRepository.save(created);

        return BussMapper.INSTANCE.toBussDto(saved, new CycleAvoidingMappingContex());
    }

    @Override
    public BussDTO update(BussDTO bussDTO, Long bussID) {
        final Buss updatable = bussRepository.findById(bussID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.BUSS_NOT_FOUND));

        updatable.setLastInspection(bussDTO.getLastInspection());
        updatable.setRegNumber(bussDTO.getRegNumber());
        //TODO CHECK
        updatable.setBussType(bussDTO.getBussType());
        bussRepository.save(updatable);

        return BussMapper.INSTANCE.toBussDto(updatable, new CycleAvoidingMappingContex());
    }
}
