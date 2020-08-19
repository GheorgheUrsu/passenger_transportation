package com.passengertransportation.demo.model;

import com.passengertransportation.demo.model.enums.BussType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "busses")
public class Buss {

    @Id
    @Column(name = "buss_id")
    private Long id;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "last_inspection")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate lastInspection;

    @Enumerated(EnumType.STRING)
    @Column(name = "buss_type")
    private BussType bussType;

    @OneToOne(mappedBy = "buss")
    private Route route;

    public String getLastInspection(){
         return lastInspection.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setLastInspection(String lastInspection){
        this.lastInspection = LocalDate.parse(lastInspection);
    }
}
