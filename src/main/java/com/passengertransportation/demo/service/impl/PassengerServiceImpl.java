package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.PassengerDTO;
import com.passengertransportation.demo.excepions.ApplicationException;
import com.passengertransportation.demo.excepions.ExceptionType;
import com.passengertransportation.demo.mappers.CycleAvoidingMappingContex;
import com.passengertransportation.demo.mappers.PassengerMapper;
import com.passengertransportation.demo.model.Passenger;
import com.passengertransportation.demo.repo.PassengerRepository;
import com.passengertransportation.demo.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(passenger -> PassengerMapper.INSTANCE.toDTO(passenger, new CycleAvoidingMappingContex()))
                .collect(Collectors.toList());
    }


    @Override
    public PassengerDTO deletePassengerById(Long passengerID) {
        Passenger deletable = passengerRepository.findById(passengerID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PASSENGER_NOT_FOUND));
        passengerRepository.deleteById(passengerID);

        return PassengerMapper.INSTANCE.toDTO(deletable, new CycleAvoidingMappingContex());
    }

    @Override
    public PassengerDTO updatePassenger(Long passengerID, PassengerDTO passengerDTO) {
        Passenger updatable = passengerRepository.findById(passengerID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PASSENGER_NOT_FOUND));
        updatable.setLuggageWeight(passengerDTO.getLuggageWeight());
        updatable.setName(passengerDTO.getName());
        updatable.setPassportData(passengerDTO.getPassportData());
        updatable.setBirthDate(passengerDTO.getBirthDate());
        Passenger persisted = passengerRepository.save(updatable);

        return PassengerMapper.INSTANCE.toDTO(persisted, new CycleAvoidingMappingContex());
    }

    @Override
    public PassengerDTO findPassengerByID(Long passengerID) {
        Passenger passenger = passengerRepository.findById(passengerID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PASSENGER_NOT_FOUND));
        return PassengerMapper.INSTANCE.toDTO(passenger, new CycleAvoidingMappingContex());
    }

    @Override
    public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = PassengerMapper.INSTANCE.fromDTO(passengerDTO, new CycleAvoidingMappingContex());
        passengerRepository.save(passenger);

        return PassengerMapper.INSTANCE.toDTO(passenger, new CycleAvoidingMappingContex());
    }
}
