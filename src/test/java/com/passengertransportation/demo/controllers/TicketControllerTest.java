package com.passengertransportation.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.service.impl.TicketServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TicketController.class)
class TicketControllerTest {
    private static final Long ID = 1L;

    @Autowired
    private TicketController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketServiceImpl service;

    private TicketDTO dto;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionController()).alwaysExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).build();

        dto = new TicketDTO();
        dto.setId(ID);
        dto.setPrice(100);
    }

    @Test
    void createListOfTickets() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<TicketDTO> dtos = new ArrayList<>();
        dtos.add(dto);

        when(service.addAllTickets(anyList())).thenReturn(dtos);

        mockMvc.perform(post("/api/v1/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dtos)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)));
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
    void deleteTicketByID() throws Exception {
        when(service.deleteTicketByID(ID)).thenReturn(dto);

        this.mockMvc.perform(delete("/api/v1/tickets/{ticketID}", dto.getId()))
                .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).deleteTicketByID(ID);
    }

    @Test
    void updateTicket() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TicketDTO toUpdate = new TicketDTO();
        toUpdate.setPrice(99);

        when(service.updateTicket(any(), anyLong())).thenReturn(toUpdate);

        this.mockMvc.perform(put("/api/v1/tickets/{ticketID}", dto.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(toUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("price", Matchers.is(toUpdate.getPrice())));

    }

    @Test
    void findTicketByID() throws Exception {
        when(service.findById(anyLong())).thenReturn(dto);

        this.mockMvc.perform(get("/api/v1/tickets/{ticketsID}", ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(Math.toIntExact(ID))));
    }
}