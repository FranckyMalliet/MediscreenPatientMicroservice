package microservicePatient.services;

import microservicePatient.models.Patient;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    public void patientTest(){
        Patient patient = new Patient();
        patient.setFirstName("Selene");
        patient.setLastName("Shadow");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1980,04,25);
        Date date = calendar.getTime();
        patient.setBirthDate(date);

        patient.setGender("F");
        patient.setAddress("15 London Street");
        patient.setPhone("0645656565");
        patient.setDangerLevel("None");

        //SAVE
        patientService.addNew(patient);
        Assertions.assertNotNull(patientService.findById(patient.getPatientId()));
        Assertions.assertEquals("Selene", patientService.findById(patient.getPatientId()).getFirstName());

        //UPDATE
        patient.setLastName("Harkonnen");
        patientService.update(patient);
        Assertions.assertEquals("Harkonnen", patientService.findById(patient.getPatientId()).getLastName());

        //FIND
        List<Patient> patientList = patientService.findAll();
        Assertions.assertTrue(patientList.size() > 0);

        //DELETE
        Integer patientId = patient.getPatientId();
        patientService.deleteById(patientId);

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> patientService.findById(patientId));
        Assertions.assertEquals("Invalid patient Id " + patientId, exception.getMessage());
    }
}
