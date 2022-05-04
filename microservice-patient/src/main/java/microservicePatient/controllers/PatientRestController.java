package microservicePatient.controllers;

import microservicePatient.models.Patient;
import microservicePatient.services.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientRestController {

    private PatientService patientService;

    public PatientRestController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/patient/assess/list")
    public List<Patient> findAll(){
        return patientService.findAll();
    }

    @GetMapping("/patient/assess/patientId")
    public Patient findById(@RequestParam("id") int patientId){
        return patientService.findById(patientId);
    }

    @GetMapping("/patient/assess/patientLastName")
    public Patient findByLastName(@RequestParam("lastName") String lastName){
        return patientService.findByLastName(lastName);
    }

    @PostMapping("/patient/assess/update")
    public void updatePatientDangerLevel(@RequestBody Patient patient){
        patientService.update(patient);
    }
}
