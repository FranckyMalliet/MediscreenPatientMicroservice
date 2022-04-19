package microservicePatient.controllers;

import microservicePatient.models.Patient;
import microservicePatient.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PatientController {

    private final static Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/patient/home")
    public String home(){
        return "home";
    }

    @GetMapping("/patient/list")
    public String userList(Model model){
        model.addAttribute("patients", patientService.findAll());
        return "patient/list";
    }

    @GetMapping("/patient/add")
    public String addUser(Patient patient, Model model){
        model.addAttribute("patient", patient);
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid Patient patient, Model model, BindingResult result){
        if(result.hasErrors()){
            logger.debug("Patient incorrect");
            return "patient/add";
        }

        patientService.addNew(patient);

        logger.debug("Adding a new Patient to database");
        model.addAttribute("patients", patientService.findAll());
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer patientId, Model model){
        Patient patient = patientService.findById(patientId);
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String update(@PathVariable("id") Integer patientId, @Valid Patient patient, Model model, BindingResult result){
        if(result.hasErrors()){
            logger.debug("Patient not found or incorrect");
            return "patient/update";
        }

        patient.setPatientId(patientId);
        patientService.update(patient);

        logger.info("Updating patient number " + patientId);
        model.addAttribute("patients", patientService.findAll());
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String delete(@PathVariable("id") Integer patientId, Model model){
        patientService.deleteById(patientId);
        model.addAttribute("patients", patientService.findAll());
        return "redirect:/patient/list";
    }
}
