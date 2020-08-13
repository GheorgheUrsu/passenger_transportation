package com.passengertransportation.demo.model;

import com.passengertransportation.demo.model.enums.BussType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "busses")
public class Buss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buss_id")
    private Long id;

    @Column(name = "buss_reg_nb")
    private String regNumber;

    @Column(name = "lastInspection")
    private Date lastInspection;

    @Enumerated(EnumType.STRING)
    @Column(name = "buss_type")
    private BussType bussType;
    
    @OneToOne(mappedBy = "buss")
    private Route route;

}
