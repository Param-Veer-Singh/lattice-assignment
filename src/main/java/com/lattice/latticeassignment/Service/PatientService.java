package com.lattice.latticeassignment.Service;

import com.lattice.latticeassignment.EmailValidator;
import com.lattice.latticeassignment.Repository.DoctorRepository;
import com.lattice.latticeassignment.Repository.PatientRepository;
import com.lattice.latticeassignment.enums.City;
import com.lattice.latticeassignment.enums.Speciality;
import com.lattice.latticeassignment.enums.Symptoms;
import com.lattice.latticeassignment.models.Doctor;
import com.lattice.latticeassignment.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    public ResponseEntity addPatient(Patient patient) throws Exception{
        String name = patient.getName();
        City city = patient.getCity();
        String email = patient.getEmail();
        String phoneNo = patient.getPhoneNo();
        Symptoms symptoms = patient.getSymptoms();

        if(name.length() < 3)throw new Exception("Name should contain at least 3 characters");
        else if(city.toString().length() > 20) throw new Exception("City name should be at max 20 characters");
        else if(!EmailValidator.isEmailValid(email)){
            throw new Exception("Invalid email");
        }else if(phoneNo.length() < 10) throw new Exception("Invalid phone number");

        patientRepository.save(patient);

        return new ResponseEntity("Patient has been saved successfully", HttpStatus.OK);
    }

    public ResponseEntity removePatient(Integer id) throws Exception {
        if(!patientRepository.findById(id).isPresent()) throw new Exception("Invalid patient id");

        patientRepository.deleteById(id);

        return new ResponseEntity("Patient has been deleted successfully", HttpStatus.OK);
    }

    public List<Doctor> findDoctors(Integer id) throws Exception {
        if(!patientRepository.findById(id).isPresent()) throw new Exception("Invalid patient id");
        Patient patient = patientRepository.findById(id).get();
        City city = patient.getCity();
        if(city.equals(City.DELHI) || city.equals(City.FARIDABAD) || city.equals(City.NOIDA)) {
            Symptoms symptoms = patient.getSymptoms();
            Speciality speciality = getSpeciality(symptoms);
            List<Doctor> doctorList = doctorRepository.findBySpecialityAndCity(speciality,city);
            if(doctorList.size() == 0)throw new Exception("There isn’t any doctor present at your location for your symptom");
            return doctorList;
        }
        else{
            throw new Exception("We are still waiting to expand to your location");
        }
    }

    public Speciality getSpeciality(Symptoms symptoms){
        if(symptoms.equals(Symptoms.Arthritis) || symptoms.equals(Symptoms.Back_Pain) || symptoms.equals(Symptoms.Tissue_injuries)){
            return Speciality.Orthopedic;
        }else if(symptoms.equals(Symptoms.Dysmenorrhea)){
            return Speciality.Gynecology;
        }else if(symptoms.equals(Symptoms.Skin_infection) || symptoms.equals(Symptoms.Skin_burn)){
            return Speciality.Dermatology;
        }else if(symptoms.equals(Symptoms.Ear_pain)){
            return Speciality.ENT;
        }
        return null;
    }
}
