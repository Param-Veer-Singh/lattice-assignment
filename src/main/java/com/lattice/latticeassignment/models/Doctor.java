package com.lattice.latticeassignment.models;

import com.lattice.latticeassignment.enums.City;
import com.lattice.latticeassignment.enums.Speciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Table
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private City city;

    private String email;
    private String phoneNo;

    @Enumerated(value = EnumType.STRING)
    private Speciality speciality;


}
