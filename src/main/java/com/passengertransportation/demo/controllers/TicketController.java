package com.passengertransportation.demo.controllers;

import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.service.TicketService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vi/tickets")
@RequiredArgsConstructor
@Api(value = "ticket",description = "CRUD OPERATIONS FOR TICKETS", tags = "TICKET")
public class TicketController {

    private final TicketService ticketService;


    @GetMapping()
    public List<TicketDTO>  getAllSoldTickets(){
        return ticketService.getAllSoldTickets();
    }


}
