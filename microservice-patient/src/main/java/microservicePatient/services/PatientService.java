package microservicePatient.services;

import microservicePatient.models.Patient;
import microservicePatient.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class PatientService {

    private final static Logger logger = LoggerFactory.getLogger(PatientService.class);
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public void addNew(Patient patient){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        patient.setCreationDate(timestamp.from(Instant.now()));

        logger.debug("Adding a new patient in database : "
                + patient.getFirstName() + ", "
                + patient.getLastName());
        patientRepository.save(patient);
    }

    public void update(Patient patient){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        patient.setRevisionDate(timestamp.from(Instant.now()));

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
        return patientRepository.getById(patientId);
    }

    public void deleteById(int patientId){
        logger.debug("Deleting patient with id number : " + patientId);
        patientRepository.deleteById(patientId);
    }
}
