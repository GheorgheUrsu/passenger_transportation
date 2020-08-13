package com.passengertransportation.demo.controllers;

import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.service.RouteService;
import com.passengertransportation.demo.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
@Api(value = "route", description = "CRUD operations for routes", tags = "ROUTE")
public class RouteController {

    private final RouteService routeService;
    private final TicketService  ticketService;

    @GetMapping
    @ApiOperation(value = "GET ALL ROUTES", notes = "\n"+
                        "This function gets all the routes")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @PostMapping
    @ApiOperation(value = "CREATE A ROUTE", notes = "\n" +
                         "This function create route")
    public Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

    @GetMapping("/{routeID}")
    @ApiOperation(value = "FIND ROUTE BY ID", notes = "\n"+
                         "This function finds route with specific ID")
    public Route findRouteByID(@PathVariable Long routeID){
        return routeService.findByID(routeID);
    }

    @PutMapping("/routeID")
    @ApiOperation(value = "UPDATE ROUTE WITH ID", notes = "\n" +
                          "This function updates route with specific ID")
    public Route updateRoute(@RequestBody Route route, @PathVariable Long routeID){
        return routeService.updateRoute(route, routeID);
    }

    @DeleteMapping("/{routeID}")
    @ApiOperation(value = "DELETE A ROUTE", notes = "\n" +
                         "This function deletes a route with specific routeID")
    public Route deleteRoute(@PathVariable Long routeID){
        return routeService.deleteRouteByID(routeID);
    }

    @GetMapping("/{routeID}/tickets")
    @ApiOperation(value = "GET ALL SOLD TICKETS", notes = "\n" +
                        "This function show all sold tickets for a specific route")
    public List<Ticket> findAllSoldedTicket(@PathVariable Long routeID){
        return routeService.findAllSoldTicket(routeID);
    }

    @PutMapping("{routeID}/tickets/{removed_ticketID}/{new_ticketID}")
    public Ticket updateTicketOnRoute(@PathVariable Long routeID, @PathVariable Long removed_ticketID, @PathVariable Long new_ticketID){
        return routeService.updateTicketOnRoute(routeID,removed_ticketID,new_ticketID);
    }

    @DeleteMapping("/{routeID}/tickets")
    @ApiOperation(value = "DELETE ALL TICKETS", notes = "\n" +
                 "This function delets all tickets for a specific Route")
    public List<Ticket> deleteAllTickets(@PathVariable Long routeID){
       return routeService.deleteAllTickets(routeID);
    }
}
