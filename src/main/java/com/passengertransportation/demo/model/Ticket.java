package com.passengertransportation.demo.model;


import com.google.common.base.Objects;
import com.passengertransportation.demo.model.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

    @Id
    @Column(name = "tickets_id")
    private Long id;

    @Column(name = "ticket_price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type")
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private Route route;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "passenger_id")
    private Passenger passenger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equal(getId(), ticket.getId()) &&
                Objects.equal(getRoute(), ticket.getRoute()) &&
                Objects.equal(getPassenger(), ticket.getPassenger());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getRoute(), getPassenger());
    }
}
