package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.BussDTO;
import com.passengertransportation.demo.excepions.ApplicationException;
import com.passengertransportation.demo.excepions.ExceptionType;
import com.passengertransportation.demo.mappers.BussMapper;
import com.passengertransportation.demo.mappers.CycleAvoidingMappingContex;
import com.passengertransportation.demo.model.Buss;
import com.passengertransportation.demo.repo.BussRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BussServiceImplTest {
    private static final Long ID_VALUE = 1L;
    private BussServiceImpl service;

    @Mock
    private BussRepository bussRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new BussServiceImpl(bussRepository);

    }

    @Test
    void getAllBusses() {
        List<Buss> busses = new ArrayList<>();
        Buss b1 = new Buss();
        b1.setId(1L);
        b1.setLastInspection("2020-12-12");
        busses.add(b1);

        Buss b2 = new Buss();
        b1.setId(2L);
        b2.setLastInspection("2020-12-12");
        busses.add(b2);

        when(bussRepository.findAll()).thenReturn(busses);

        List<BussDTO> dtos = service.getAllBusses();
        assertEquals(busses.size(), dtos.size());

        verify(bussRepository, times(1)).findAll();
    }

    @Test
    void getBussByID() {
        Buss buss = new Buss();
        buss.setId(ID_VALUE);
        buss.setLastInspection("2020-12-12");

        when(bussRepository.findById(ID_VALUE)).thenReturn(Optional.of(buss));
        BussDTO bussDTO = service.getBussByID(ID_VALUE);

        assertEquals(buss.getId(), bussDTO.getId());

        verify(bussRepository, times(1)).findById(ID_VALUE);
    }

    @Test
    void deleteBussByID() {
        Buss deletable = new Buss();
        deletable.setId(ID_VALUE);
        deletable.setLastInspection("2020-12-12");

        when(bussRepository.findById(ID_VALUE)).thenReturn(Optional.of(deletable));
        bussRepository.deleteById(ID_VALUE);
        verify(bussRepository, times(1)).deleteById(ID_VALUE);

        BussDTO dto = service.deleteBussByID(ID_VALUE);
        assertEquals(dto.getId(), deletable.getId());
    }

    @Test
    void createBuss() {
        Buss persistedBuss = new Buss();
        persistedBuss.setId(ID_VALUE);
        persistedBuss.setLastInspection("2020-12-12");

        BussDTO dto = new BussDTO();
        dto.setId(ID_VALUE);
        dto.setLastInspection("2020-12-12");

        when(bussRepository.save(persistedBuss)).thenReturn(persistedBuss);
        BussDTO bussDTO = service.createBuss(dto);

        assertNotNull(bussDTO);
        assertEquals(persistedBuss.getId(), bussDTO.getId());
        assertEquals(persistedBuss.getLastInspection(), bussDTO.getLastInspection());
    }

    @Test
    void update() {
        Buss updatable = new Buss();
        updatable.setId(ID_VALUE);
        updatable.setLastInspection("2020-12-12");
        updatable.setRegNumber("MDA 111");

        BussDTO dto = new BussDTO();
        dto.setId(2L);
        dto.setLastInspection("2019-11-11");
        dto.setRegNumber("MDA 555");

        when(bussRepository.findById(ID_VALUE)).thenReturn(Optional.of(updatable));
        when(bussRepository.save(updatable)).thenReturn(updatable);
        BussDTO updated = service.update(dto, ID_VALUE);

        assertNotNull(updated);
        assertEquals(updatable.getId(), updated.getId());
        assertEquals(updatable.getLastInspection(), updated.getLastInspection());
        assertEquals(updatable.getRegNumber(), updated.getRegNumber());

        verify(bussRepository, times(1)).save(updatable);
    }
}