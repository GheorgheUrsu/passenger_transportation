package com.passengertransportation.demo.model;

import com.passengertransportation.demo.model.enums.BussType;
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
public class Buss {

    private String regNumber;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date lastInspection;

    @Enumerated(EnumType.STRING)
    private BussType bussType;


}
