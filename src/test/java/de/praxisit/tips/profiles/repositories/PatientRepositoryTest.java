package de.praxisit.tips.profiles.repositories;

import de.praxisit.tips.profiles.TipsSpringProfilesApplication;
import de.praxisit.tips.profiles.config.Profiles;
import de.praxisit.tips.profiles.data.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Test for {@link PatientRepository}.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TipsSpringProfilesApplication.class)
@ActiveProfiles(Profiles.H2_MEM)
@DataJpaTest
@Transactional
@Sql(scripts = {"/db/schema.sql", "/db/patient.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class PatientRepositoryTest {

  @Autowired
  private PatientRepository repository;

  @Test
  public void findOne() {
    Patient result = repository.findOne(1).get();
    assertThat(result.getFirstName(), is("Donald"));
  }

  @Test
  public void findAll() {
    final List<Patient> all = repository.findAll().collect(toList());
    assertThat(all, hasSize(greaterThan(3)));
  }

  @Test
  public void findBirthday() throws Exception {
    final Patient result = repository.findOne(1).get();
    assertThat(result.getBirthday(), is(LocalDate.of(1905, 4, 1)));
  }

  @Test
  public void saveNewPatient() throws Exception {
    final Patient gunedel = Patient
        .builder()
        .firstName("Gundel")
        .lastName("Gaukelei")
        .birthday(LocalDate.of(1899, 9, 9))
        .build();
    final Patient result = repository.save(gunedel);
    assertThat(result.getId(), is(notNullValue()));
    assertThat(result.getFirstName(), is("Gundel"));
    assertThat(result.getLastName(), is("Gaukelei"));
    assertThat(result.getBirthday(), is(LocalDate.of(1899, 9, 9)));
  }

  @Test
  public void saveChangedPatient() throws Exception {
    final Patient patient = repository.findOne(1).get();
    assertThat(patient.getId(), is(1));
    patient.setFirstName("Test");
    final Patient savedPatient = repository.save(patient);
    assertThat(savedPatient.getFirstName(), is("Test"));
  }

  @Test
  public void deletePatient() throws Exception {
    repository.delete(1);
    final Optional<Patient> result = repository.findOne(1);
    assertThat(result.isPresent(), is(false));
  }

}
