package de.praxisit.tips.profiles.repositories;

import de.praxisit.tips.profiles.TipsSpringProfilesApplication;
import de.praxisit.tips.profiles.config.Profiles;
import de.praxisit.tips.profiles.entities.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
@Sql(scripts = {"/db/schema.sql", "/db/patient.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository repository;

    @Test
    public void findOne() {
        Patient result = repository.findOne( 1 );
        assertThat( result.getFirstName( ), is( "Donald" ) );
    }

}