package com.passengertransportation.demo.controllers;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.service.RouteService;
import com.passengertransportation.demo.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
@Api(value = "route", description = "CRUD OPERATIONS FOR ROUTES", tags = "ROUTE")
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    @ApiOperation(value = "GET ALL ROUTES", notes = "\n" +
            "This function gets all the routes")
    @ResponseStatus(HttpStatus.OK)
    public List<RouteDTO> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PostMapping
    @ApiOperation(value = "CREATE A ROUTE", notes = "\n" +
            "This function create route")
    @ResponseStatus(HttpStatus.CREATED)
    public RouteDTO createRoute(@RequestBody @Validated RouteDTO routeDTO) {
        return routeService.createRoute(routeDTO);
    }

    @GetMapping("/{routeID}")
    @ApiOperation(value = "FIND ROUTE BY ID", notes = "\n" +
            "This function finds route with specific ID")
    @ResponseStatus(HttpStatus.OK)
    public RouteDTO findRouteByID(@PathVariable Long routeID) {
        return routeService.findByID(routeID);
    }

    @PutMapping("/{routeID}")
    @ApiOperation(value = "UPDATE ROUTE WITH ID", notes = "\n" +
            "This function updates route with specific ID")
    @ResponseStatus(HttpStatus.OK)
    public RouteDTO updateRoute(@RequestBody RouteDTO routeDTO, @PathVariable Long routeID) {
        return routeService.updateRoute(routeDTO, routeID);
    }

    @DeleteMapping("/{routeID}")
    @ApiOperation(value = "DELETE A ROUTE", notes = "\n" +
            "This function deletes a route with specific routeID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RouteDTO deleteRoute(@PathVariable Long routeID) {
        return routeService.deleteRouteByID(routeID);
    }

    @GetMapping("/{routeID}/tickets")
    @ApiOperation(value = "GET ALL SOLD TICKETS", notes = "\n" +
            "This function show all sold tickets for a specific route")
    public Set<TicketDTO> findAllSoldTicketByRoute(@PathVariable Long routeID) {
        return routeService.findAllSoldTicketByBuss(routeID);
    }

    @PutMapping("{routeID}/tickets/{removed_ticketID}/{new_ticketID}")
    @ApiOperation(value = "UPDATE TICKET", notes = "\n" +
            "This function updates a ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO updateTicketOnRoute(@PathVariable Long routeID, @PathVariable Long removed_ticketID, @RequestBody TicketDTO newTicketDto) {
        return routeService.updateTicketOnRoute(routeID, removed_ticketID, newTicketDto);
    }

    @DeleteMapping("/{routeID}/tickets")
    @ApiOperation(value = "DELETE ALL TICKETS", notes = "\n" +
            "This function deletes all tickets for a specific Route")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<TicketDTO> deleteAllTickets(@PathVariable Long routeID) {
        return routeService.deleteAllTicketsByRouteId(routeID);
    }

    @DeleteMapping("/{routeID}/tickets/{ticketID}")
    @ApiOperation(value = "DELETE A TICKET", notes = "\n" +
            "This function deletes a ticket from a specific route")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TicketDTO deleteTicketFromRoute(@PathVariable Long routeID, @PathVariable Long ticketID) {
        return routeService.deleteTicketFromRoute(routeID, ticketID);
    }
}
