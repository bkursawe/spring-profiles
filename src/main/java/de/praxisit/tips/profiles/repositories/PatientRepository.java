package de.praxisit.tips.profiles.repositories;

import de.praxisit.tips.profiles.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link JpaRepository} for {@link Patient}.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // Nothing to do
}
