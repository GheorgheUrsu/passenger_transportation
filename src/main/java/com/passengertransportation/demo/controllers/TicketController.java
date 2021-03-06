package com.passengertransportation.demo.controllers;

import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tickets")
@RequiredArgsConstructor
@Api(value = "ticket", description = "CRUD OPERATIONS FOR TICKETS", tags = "TICKET")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @ApiOperation(value = "CREATE ONE OR MORE TICKETS", notes = "\n" +
            "This operations create one or more tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public List<TicketDTO> createListOfTickets(@RequestBody List<TicketDTO> ticketDTOS) {
        return ticketService.addAllTickets(ticketDTOS);
    }

    @GetMapping
    @ApiOperation(value = "GET ALL SOLD TICKETS", notes = "\n" +
            "This function get all sold ticket")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDTO> getAllSoldTickets() {
        return ticketService.getAllSoldTickets();
    }

    @DeleteMapping("/{ticketID}")
    @ApiOperation(value = "DELETE TICKET BY ID", notes = "\n" +
        "This function delete a ticket by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TicketDTO deleteTicketByID(@PathVariable Long ticketID) {
        return ticketService.deleteTicketByID(ticketID);
    }

    @PutMapping(value = "/{ticketID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "UPDATE TICKET BY ID", notes = "\n" +
            "This function updates a ticket")
    @ResponseStatus(HttpStatus.OK)
    public TicketDTO updateTicket(@PathVariable Long ticketID, @RequestBody TicketDTO ticketDTO) {
        return ticketService.updateTicket(ticketDTO, ticketID);
    }

    @GetMapping("/{ticketID}")
    @ApiOperation(value = "FIND TICKET BY ID", notes = "\n" +
       "This function find ticket by id")
    @ResponseStatus(HttpStatus.OK)
    public TicketDTO findTicketByID(@PathVariable Long ticketID){
        return ticketService.findById(ticketID);
    }
}
