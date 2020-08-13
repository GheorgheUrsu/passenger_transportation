package com.passengertransportation.demo.dataloader;

import com.passengertransportation.demo.model.Buss;
import com.passengertransportation.demo.model.Passenger;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.model.enums.BussType;
import com.passengertransportation.demo.model.enums.TicketType;
import com.passengertransportation.demo.repo.BussRepository;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.repo.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final RouteRepository routeRepository;
    private final BussRepository bussRepository;
    private final TicketRepository  ticketRepository;

    public DataLoader(RouteRepository repository, BussRepository bussRepository, TicketRepository ticketRepository) {
        this.routeRepository = repository;
        this.bussRepository = bussRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) {

        if (routeRepository.findAll().size() <= 0) {
            loadDate();
        }
    }


    private void loadDate(){

        Buss buss = new Buss();
        buss.setLastInspection(new Date(2018, 1, 1));
        buss.setRegNumber("MDA 202");
        buss.setBussType(BussType.DOUBLE_DECKER);
        Buss savedDocker =  bussRepository.save(buss);

        Buss buss1 = new Buss();
        buss1.setLastInspection(new Date(2018, 1, 1));
        buss1.setRegNumber("MDA 205");
        buss1.setBussType(BussType.STANDARD);
        Buss savedStandardBuss = bussRepository.save(buss1);

        Buss buss3 = new Buss();
        buss3.setLastInspection(new Date(2018, 1, 1));
        buss3.setRegNumber("MDA 203");
        buss3.setBussType(BussType.MINIVAN);
        Buss minivanBuss = bussRepository.save(buss3);

        // ================================================================================================================

        Route route1 = new Route();
        route1.setStartLocation("Chisinau");
        route1.setArrivalDestination("Berlin");
        route1.setStartDate(LocalDateTime.of(2019, 9, 9, 10, 0));
        route1.setArrivalDate(LocalDateTime.of(2019, 9, 10, 10, 0));
        route1.setBuss(buss);
        Route chisinauBerlin = routeRepository.save(route1);

        Route route2 = new Route();
        route2.setStartLocation("Chisinau");
        route2.setArrivalDestination("Moscow");
        route2.setStartDate(LocalDateTime.of(2019, 9, 9, 12, 0));
        route2.setArrivalDate(LocalDateTime.of(2019, 9, 11, 12, 0));
        route2.setBuss(buss1);
        Route chisinauMoscow = routeRepository.save(route2);


        Passenger passenger = new Passenger();
        passenger.setBirthDate(new Date(1991, 9, 12));
        passenger.setName("John");
        passenger.setPassportData("USA001002");
        passenger.setLuggageWeight(15);

        Ticket ticket = new Ticket();
        ticket.setPrice(15);
        ticket.setPassenger(passenger);
        ticket.setTicketType(TicketType.BUSSINESS);
        ticket.setRoute(route1);
        Ticket savedTicket1 = ticketRepository.save(ticket);

        chisinauMoscow.getTickets().add(savedTicket1);

    }
}
