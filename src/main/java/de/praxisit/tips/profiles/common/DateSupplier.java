package de.praxisit.tips.profiles.common;

import java.time.LocalDate;

/**
 * A Single Abstract Method (SAM) for getting the current Date.
 * <p>
 * There are several methods to change the current date in test
 * cases but this one seems to be elegant.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@FunctionalInterface
public interface DateSupplier {

    /**
     * @return the current date
     */
    LocalDate now();

}
