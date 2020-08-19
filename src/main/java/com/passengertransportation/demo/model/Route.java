package com.passengertransportation.demo.model;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @Column(name = "route_id")
    private Long id;

    @Column(name = "start_loc")

    private String startLocation;

    @Column(name = "arrival_loc")
    private String arrivalDestination;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startDate;

    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime arrivalDate;

    @OneToMany(mappedBy = "route",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buss_id", referencedColumnName = "buss_id")
    private Buss buss;


    public String getStartDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.startDate.format(formatter);
    }

    public void setStartDate(String startDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startDate = LocalDateTime.parse(startDate,formatter);
    }

    public String getArrivalDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.arrivalDate.format(formatter);
    }

    public void setArrivalDate(String arrivalDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.arrivalDate = LocalDateTime.parse(arrivalDate, formatter);
    }

    public void addTicket(Ticket ticket){
        if(tickets == null) {
            tickets = new HashSet<>();
        }
        tickets.add(ticket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equal(getId(), route.getId()) &&
                Objects.equal(getStartLocation(), route.getStartLocation()) &&
                Objects.equal(getArrivalDestination(), route.getArrivalDestination()) &&
                Objects.equal(getStartDate(), route.getStartDate()) &&
                Objects.equal(getArrivalDate(), route.getArrivalDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getStartLocation(), getArrivalDestination(), getStartDate(), getArrivalDate());
    }
}
