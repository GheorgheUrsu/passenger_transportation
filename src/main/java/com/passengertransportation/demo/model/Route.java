package com.passengertransportation.demo.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
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

    @NotNull
    @Embedded
    private Buss buss;

}
