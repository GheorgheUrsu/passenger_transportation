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
    private Date lastInspection;

    @Enumerated(EnumType.STRING)
    @Column(name = "buss_type")
    private BussType bussType;

    @OneToOne(mappedBy = "buss")
    private Route route;
}
