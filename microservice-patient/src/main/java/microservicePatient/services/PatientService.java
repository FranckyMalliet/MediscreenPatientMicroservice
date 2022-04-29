package microservicePatient.services;

import microservicePatient.models.Patient;
import microservicePatient.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final static Logger logger = LoggerFactory.getLogger(PatientService.class);
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    /**
     * Typical Create method from CRUD except that it create
     * a timestamp when a Patient is saved using the application
     * @param patient
     * @return a Patient
     */

    public Patient addNew(Patient patient){
        logger.debug("Adding a new patient in database : "
                + patient.getFirstName() + ", "
                + patient.getLastName());
        return patientRepository.save(patient);
    }

    /**
     * Typical Update method from CRUD except that it create
     * a timestamp when a Patient is updated using the application
     * @param patient
     * @return a Patient
     */

    public void update(Patient patient){
        logger.debug("Updating patient : " + patient.getFirstName() + ", "
                + patient.getLastName() + ", "
                + patient.getBirthDate() + ", "
                + patient.getGender() + ", "
                + patient.getAddress() + ", "
                + patient.getPhone());
        patientRepository.save(patient);
    }

    public List<Patient> findAll(){
        logger.debug("Retrieving all patients from database");
        return patientRepository.findAll();
    }

    public Patient findById(int patientId){
        logger.debug("Retrieving patient with id number : " + patientId);
        return patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id " + patientId));
    }

    public Patient findByLastName(String lastName){
        logger.info("Retrieving patient with last name : " + lastName);
        return patientRepository.findByLastName(lastName).orElseThrow(() -> new IllegalArgumentException("Invalid patient lastName " + lastName));
    }

    public void deleteById(int patientId){
        logger.debug("Deleting patient with id number : " + patientId);
        patientRepository.deleteById(patientId);
    }
}
