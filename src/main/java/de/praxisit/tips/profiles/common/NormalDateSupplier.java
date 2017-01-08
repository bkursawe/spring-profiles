package de.praxisit.tips.profiles.common;

import java.time.LocalDate;

/**
 * Normal implementation of {@link DateSupplier}.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
public class NormalDateSupplier implements DateSupplier {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }

}
