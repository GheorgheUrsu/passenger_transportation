package com.passengertransportation.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startDate;

    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime arrivalDate;

    @OneToMany(mappedBy = "route",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "buss_id", referencedColumnName = "buss_id")
    private Buss buss;

}
