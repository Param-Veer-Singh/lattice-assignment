package com.lattice.latticeassignment.Controllers;

import com.lattice.latticeassignment.Service.DoctorService;
import com.lattice.latticeassignment.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody Doctor doctor) throws Exception {
        try{
            return doctorService.addDoctor(doctor);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/remove")
    public ResponseEntity removeDoctor(@RequestParam("id") Integer id) throws Exception {
        try{
            return doctorService.removeDoctor(id);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
