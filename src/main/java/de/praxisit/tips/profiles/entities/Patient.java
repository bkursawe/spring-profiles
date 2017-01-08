package de.praxisit.tips.profiles.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Patient data.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@Data
@Entity
@Table(name = "PATIENT")
public class Patient implements Serializable {

    /** GUID. */
    private static final long serialVersionUID = -7241415157028312231L;

    @Id
    private Integer id;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

}
