package de.praxisit.tips.profiles.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Patient data.
 * Combination of Entity and DTO.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PATIENT")
public class Patient implements Serializable {

  /** GUID. */
  private static final long serialVersionUID = -7241415157028312231L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "FIRST_NAME", length = 50)
  @Size(max = 50)
  private String firstName;

  @Column(name = "LAST_NAME", length = 50, nullable = false)
  @Size(min = 1, max = 50)
  private String lastName;

  @Column(name = "BIRTHDAY")
  private LocalDate birthday;

}
