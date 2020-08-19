package com.passengertransportation.demo.model;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @NonNull
    private LocalDate birthDate;

    @Column(name = "luggage_weight")
    private Integer luggageWeight;

    @OneToOne(mappedBy = "passenger")
    private Ticket ticket;


    public String getBirthDate(){
        return birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setBirthDate(String birthDate){
        this.birthDate = LocalDate.parse(birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equal(passportData, passenger.passportData);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(passportData);
    }
}
