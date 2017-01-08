package de.praxisit.tips.profiles.repositories;

import de.praxisit.tips.profiles.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * {@link JpaRepository} for {@link Patient}.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
public interface PatientRepository extends Repository<Patient, Integer> {

  Optional<Patient> findOne(Integer id);

  Stream<Patient> findAll();

  Stream<Patient> findByLastName(String lastName);

  Patient save(Patient patient);

  void delete(int id);

}
