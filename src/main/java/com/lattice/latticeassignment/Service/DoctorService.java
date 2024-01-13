package com.lattice.latticeassignment.Service;

import com.lattice.latticeassignment.EmailValidator;
import com.lattice.latticeassignment.Repository.DoctorRepository;
import com.lattice.latticeassignment.enums.City;
import com.lattice.latticeassignment.enums.Speciality;
import com.lattice.latticeassignment.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public ResponseEntity addDoctor(Doctor doctor) throws Exception{
        String name = doctor.getName();
        City city = doctor.getCity();
        String email = doctor.getEmail();
        String phoneNo = doctor.getPhoneNo();
        Speciality speciality = doctor.getSpeciality();

        if(name.length() < 3)throw new Exception("Name should contain at least 3 characters");
        else if(city.toString().length() > 20) throw new Exception("City name should be at max 20 characters");
        else if(!EmailValidator.isEmailValid(email)){
            throw new Exception("Invalid email");
        }else if(phoneNo.length() < 10) throw new Exception("Invalid phone number");

        doctorRepository.save(doctor);

        return new ResponseEntity("Doctor has been saved successfully", HttpStatus.OK);
    }

    public ResponseEntity removeDoctor(Integer id) throws Exception {
        if(!doctorRepository.findById(id).isPresent()) throw new Exception("Invalid doctor id");

        doctorRepository.deleteById(id);

        return new ResponseEntity("Doctor has been deleted successfully", HttpStatus.OK);
    }

}
