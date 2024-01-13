package com.lattice.latticeassignment.Repository;

import com.lattice.latticeassignment.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
