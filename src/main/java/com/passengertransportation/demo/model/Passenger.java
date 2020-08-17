package com.passengertransportation.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @Column(name = "passenger_id")
    private Long id;


    @Column(name = "passenger_name")
    @NonNull
    private String name;

    @Column(name = "passport_data")
    @NonNull
    private String passportData;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NonNull
    private Date birthDate;

    @Column(name = "luggage_weight")
    private int luggageWeight;

    @OneToOne(mappedBy = "passenger")
    private Ticket ticket;
}
