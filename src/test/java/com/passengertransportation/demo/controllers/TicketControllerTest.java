package com.passengertransportation.demo.controllers;

import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TicketController.class)
class TicketControllerTest {
    private static final Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketServiceImpl service;

    private TicketDTO dto;



    @BeforeEach
    void setUp() {
        dto = new TicketDTO();
        dto.setId(ID);
        dto.setPrice(100);
    }

    @Test
    void createListOfTickets() {
    }

    @Test
    void getAllSoldTickets() throws Exception {
        List<TicketDTO> dtos = new ArrayList<>();
        dtos.add(dto);
        when(service.getAllSoldTickets()).thenReturn(dtos);

       mockMvc.perform(get("/api/v1/tickets"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void deleteTicketByID() {
    }

    @Test
    void updateTicket() {
    }

    @Test
    void findTicketByID() {
    }
}