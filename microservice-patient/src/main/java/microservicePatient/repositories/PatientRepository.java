package microservicePatient.repositories;

import microservicePatient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>, CrudRepository<Patient, Integer> {

    Optional<Patient> findById(Integer Id);
}
