package microservicePatient.controllers;

import microservicePatient.models.Patient;
import microservicePatient.services.PatientService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PatientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private PatientService patientService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAnUrl_ReturnAHomeOfThisMicroservice() throws Exception {
        mockMvc.perform(get("/patient/home"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_ReturnAPageWithAllPatients() throws Exception {
        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_ReturnAPageToAddANewPatient() throws Exception {
        mockMvc.perform(get("/patient/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAPatient_AddItToDatabase_ReturnAPageWithAllPatients() throws Exception {
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

        patientService.addNew(patient);
        Assertions.assertNotNull(patientService.findById(patient.getPatientId()));
        Assertions.assertEquals("Selene", patientService.findById(patient.getPatientId()).getFirstName());

        mockMvc.perform(post("/patient/validate"))
                .andExpect(status().isFound());

        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk());

        patientService.deleteById(patient.getPatientId());
    }

    @Test
    public void givenAnId_ReturnPathToAPageWithASpecificPatient() throws Exception {
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

        patientService.addNew(patient);
        Assertions.assertNotNull(patientService.findById(patient.getPatientId()));

        mockMvc.perform(get("/patient/update/{id}", patient.getPatientId()))
                .andExpect(status().isOk());

        patientService.deleteById(patient.getPatientId());
    }

    @Test
    public void givenNewInformationToAPatient_UpdateItIntoTheDatabase_ReturnAPageWithAllPatients() throws Exception {
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

        patientService.addNew(patient);
        Assertions.assertNotNull(patientService.findById(patient.getPatientId()));

        patient.setLastName("Harkonnen");
        patientService.update(patient);
        Assertions.assertEquals("Harkonnen", patientService.findById(patient.getPatientId()).getLastName());
        Assertions.assertNotNull(patientService.findById(patient.getPatientId()).getRevisionDate());

        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk());

        patientService.deleteById(patient.getPatientId());
    }

    @Test
    public void givenAnId_DeleteAPatientInTheDatabase_ReturnAPageWithAllPatients() throws Exception {
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

        patientService.addNew(patient);
        Assertions.assertNotNull(patientService.findById(patient.getPatientId()));

        patientService.deleteById(patient.getPatientId());
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> patientService.findById(patient.getPatientId()));
        Assert.assertEquals("Invalid patient Id " + patient.getPatientId(), exception.getMessage());

        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk());
    }
}
