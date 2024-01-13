package com.lattice.latticeassignment.Repository;

import com.lattice.latticeassignment.enums.City;
import com.lattice.latticeassignment.enums.Speciality;
import com.lattice.latticeassignment.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    List<Doctor> findBySpecialityAndCity(Speciality symptoms, City city);
}
