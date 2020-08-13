package com.passengertransportation.demo.model;

import com.passengertransportation.demo.model.enums.TicketType;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Embedded
    private Passenger passenger;

}
