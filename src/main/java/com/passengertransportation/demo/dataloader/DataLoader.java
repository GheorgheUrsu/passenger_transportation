package com.passengertransportation.demo.dataloader;

import com.passengertransportation.demo.model.Buss;
import com.passengertransportation.demo.model.BussType;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.repo.BussRepository;
import com.passengertransportation.demo.repo.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RouteRepository repository;

    @Autowired
    private BussRepository bussRepository;

    @Override
    public void run(String... args) throws Exception {

        Buss buss = new Buss();
        buss.setLastInspection(new Date(2018,1,1));
        buss.setRegNumber("MDA 202");
        buss.setBussType(BussType.DOUBLE_DECKER);



        Route route1 = new Route();
        route1.setStartLocation("Chisinau");
        route1.setArrivalDestination("Berlin");
        route1.setStartDate(LocalDateTime.of(2019,9,9,10, 0));
        route1.setArrivalDate(LocalDateTime.of(2019,9,10,10,0));
        repository.save(route1);

        buss.setRoute(route1);
        bussRepository.save(buss);

    }
}
