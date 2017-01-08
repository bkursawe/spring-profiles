package de.praxisit.tips.profiles.common;

import de.praxisit.tips.profiles.config.Profiles;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Implementation of {@link DateSupplier} for test cases.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@Component
@Profile(Profiles.TEST)
@Data
public class TestDateSupplier implements DateSupplier {

    private LocalDate date = LocalDate.now();

    @Override
    public LocalDate now() {
        return date;
    }

}
