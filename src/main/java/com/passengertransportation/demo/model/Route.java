package com.passengertransportation.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;

    @Column(name = "start_loc")
    private String startLocation;

    @Column(name = "arrival_loc")
    private String arrivalDestination;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @OneToMany(mappedBy = "route",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();
/*
    @OneToMany(mappedBy = "route",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Buss> busses = new ArrayList<>();*/

    @OneToOne(mappedBy = "route")
    private Buss buss;

    public void addTicket(Ticket ticket) {
        this.getTickets().add(ticket);


    }
}
