package de.praxisit.tips.profiles.services;

import de.praxisit.tips.profiles.common.DateSupplier;
import de.praxisit.tips.profiles.data.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

/**
 * Common functions for {@link Patient}s.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@Service
public class PatientService {

    private final DateSupplier date;

    @Autowired
    public PatientService( DateSupplier now ) {
        this.date = now;
    }

    /**
     * Computes the current age of a patient.
     *
     * @param patient
     *         {@link Patient}
     * @return age of patient in years or {@link Optional#empty()} if {@code patient} is {@code
     * null} or {@link Patient#birthday} is unknown
     */
    public Optional<Integer> ageOf( final Patient patient ) {
        return Optional.ofNullable( patient )
                .map( p -> p.getBirthday( ) )
                .map( birthday -> Period.between( birthday, date.now( ) ).getYears( ) );
    }
}
