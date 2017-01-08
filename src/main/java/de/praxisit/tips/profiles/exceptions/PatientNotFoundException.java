package de.praxisit.tips.profiles.exceptions;

import de.praxisit.tips.profiles.entities.Patient;

/**
 * Exception if {@link Patient} is not found.
 *
 * @author Bernd Kursawe (bernd.kursawe@hennig-fahrzeugteile.de)
 * @since 08.01.2017
 */
public class PatientNotFoundException extends RuntimeException {

  public PatientNotFoundException() {
    super("Patient not found");
  }

  public PatientNotFoundException(int id) {
    super("Patient " + id + " not found");
  }

}
