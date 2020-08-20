package com.passengertransportation.demo.model;

import com.passengertransportation.demo.model.enums.BussType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "busses")
public class Buss {

    @Id
    @Column(name = "buss_id")
    private Long id;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "last_inspection")
    private LocalDate lastInspection;

    @Enumerated(EnumType.STRING)
    @Column(name = "buss_type")
    private BussType bussType;

    @OneToOne(mappedBy = "buss")
    private Route route;

    public String getLastInspection() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return lastInspection.format(formatter);
    }

    public void setLastInspection(String lastInspection) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.lastInspection = LocalDate.parse(lastInspection,formatter);
    }
}
