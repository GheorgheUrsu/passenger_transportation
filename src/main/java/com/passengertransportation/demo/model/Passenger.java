package com.passengertransportation.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Passenger {

    private String name;
    private String passportData;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    private int luggageWeight;

}
