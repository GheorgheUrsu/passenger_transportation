package com.passengertransportation.demo.model;

import com.passengertransportation.demo.model.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "tickets_id")
    private Long id;

    @Column(name = "ticket_price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private Route route;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "passenger_id")
    private Passenger passenger;

}
