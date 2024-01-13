package com.lattice.latticeassignment.models;

import com.lattice.latticeassignment.enums.City;
import com.lattice.latticeassignment.enums.Symptoms;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private City city;

    private String email;
    private String phoneNo;

    @Enumerated(value = EnumType.STRING)
    private Symptoms symptoms;
}
