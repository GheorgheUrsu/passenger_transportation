package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.PassengerDTO;
import com.passengertransportation.demo.model.Passenger;
import com.passengertransportation.demo.repo.PassengerRepository;
import com.passengertransportation.demo.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {
    private final static Long ID = 1L;
    private PassengerService service;
    private Passenger passenger;
    @Mock
    private PassengerRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PassengerServiceImpl(repository);

        passenger = new Passenger();
        passenger.setId(ID);
        passenger.setPassportData("XXXX");
        passenger.setName("Jordan");
        passenger.setBirthDate("1990-01-01");

    }

    @Test
    void getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        when(repository.findAll()).thenReturn(passengers);

        List<PassengerDTO> dtos = service.getAllPassengers();

        assertFalse(dtos.isEmpty());
        assertEquals(passengers.size(), dtos.size());
    }

    @Test
    void deletePassengerById() {

        when(repository.findById(ID)).thenReturn(Optional.of(passenger));
        repository.deleteById(ID);
        verify(repository, times(1)).deleteById(ID);

        PassengerDTO dto = service.deletePassengerById(ID);
        assertEquals(ID, dto.getId());
    }

    @Test
    void updatePassenger() {
        PassengerDTO updatable = new PassengerDTO();
        updatable.setName("Jordan");
        updatable.setBirthDate("1990-01-01");
        updatable.setPassportData("XXXX");

        when(repository.findById(ID)).thenReturn(Optional.of(passenger));
        when(repository.save(passenger)).thenReturn(passenger);

        PassengerDTO updated = service.updatePassenger(ID, updatable);

        assertEquals(updated.getId(), passenger.getId());
        assertEquals(updated.getBirthDate(), updatable.getBirthDate());
        assertEquals(updated.getPassportData(), updatable.getPassportData());
        assertEquals(updated.getName(), updatable.getName());
    }

    @Test
    void findPassengerByID() {
        when(repository.findById(ID)).thenReturn(Optional.of(passenger));
        PassengerDTO dto = service.findPassengerByID(ID);

        assertEquals(passenger.getId(), dto.getId());
        verify(repository, times(1)).findById(ID);
    }

    @Test
    void createPassenger() {
        PassengerDTO dto = new PassengerDTO();
        dto.setId(ID);
        dto.setPassportData("XXXX");
        dto.setName("Jordan");
        dto.setBirthDate("1990-01-01");

        when(repository.save(passenger)).thenReturn(passenger);

        PassengerDTO created = service.createPassenger(dto);

        assertNotNull(created);
        assertEquals(created.getId(), passenger.getId());
        verify(repository, times(1)).save(passenger);
    }
}