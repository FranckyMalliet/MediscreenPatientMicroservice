package microservicePatient.controllers;

import microservicePatient.models.Patient;
import microservicePatient.services.PatientService;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PatientRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private PatientRestController patientRestController;

    @Autowired
    private PatientService patientService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAnUrl_ReturnAllPatient() throws Exception {
        //WHEN
        List<Patient> patientList = patientRestController.findAll();
        Assertions.assertNotNull(patientList);

        //THEN
        mockMvc.perform(get("/patient/assess/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnId_ReturnAPatient() throws Exception {
        //GIVEN
        int patientId = 5;

        //WHEN
        Patient patient = patientRestController.findById(patientId);
        Assertions.assertNotNull(patient);

        //THEN
        mockMvc.perform(get("/patient/assess/patientId")
                        .param("id", String.valueOf(patientId)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenALastName_ReturnAPatient() throws Exception {
        //GIVEN
        String lastName = "Valante";

        //WHEN
        Patient patient = patientRestController.findByLastName(lastName);
        Assertions.assertNotNull(patient);

        //THEN
        mockMvc.perform(get("/patient/assess/patientLastName")
                        .param("lastName", lastName))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNewInformationToAPatient_UpdateItIntoTheDatabase() throws Exception {
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

        patient.setDangerLevel("Early onset");

        patientRestController.updatePatientDangerLevel(patient);
        Assertions.assertEquals("Early onset", patientService.findById(patient.getPatientId()).getDangerLevel());

        patientService.deleteById(patient.getPatientId());
    }
}
