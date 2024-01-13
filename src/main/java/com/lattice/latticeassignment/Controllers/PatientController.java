package com.lattice.latticeassignment.Controllers;

import com.lattice.latticeassignment.Service.PatientService;
import com.lattice.latticeassignment.models.Doctor;
import com.lattice.latticeassignment.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity addPatient(@RequestBody Patient patient) throws Exception {
        try{
            return patientService.addPatient(patient);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity removePatient(@RequestParam("id") Integer id) throws Exception {
        try{

        return patientService.removePatient(id);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findDoctors")
    public ResponseEntity findDoctors(@RequestParam("id") Integer id) throws Exception {
        try{
            List<Doctor> doctorList = patientService.findDoctors(id);
            return new ResponseEntity(doctorList,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
