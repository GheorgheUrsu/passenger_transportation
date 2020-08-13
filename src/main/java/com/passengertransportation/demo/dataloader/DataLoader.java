package com.passengertransportation.demo.dataloader;

import com.passengertransportation.demo.model.Buss;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.enums.BussType;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.repo.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final RouteRepository routeRepository;
    private final TicketRepository  ticketRepository;

    public DataLoader(RouteRepository repository, TicketRepository ticketRepository) {
        this.routeRepository = repository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) {

    }


    private void loadData(){

        Route route = new Route();
        route.setStartDate(LocalDateTime.of(20,9,20,10,0));
        route.setArrivalDate(LocalDateTime.of(20,9,21,10,0));
        route.setStartLocation("Chisinau");
        route.setArrivalDestination("Budapest");
        route.setBuss(new Buss("MDA 999",new Date(12,12, 2019),BussType.MINIVAN));
        routeRepository.save(route);
    }
}
