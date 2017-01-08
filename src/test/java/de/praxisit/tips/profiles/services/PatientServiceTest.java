package de.praxisit.tips.profiles.services;

import de.praxisit.tips.profiles.TipsSpringProfilesApplication;
import de.praxisit.tips.profiles.common.TestDateSupplier;
import de.praxisit.tips.profiles.config.Profiles;
import de.praxisit.tips.profiles.data.Patient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test for {@link PatientService}.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TipsSpringProfilesApplication.class)
@ActiveProfiles(Profiles.TEST)
public class PatientServiceTest {

    @Autowired
    private PatientService service;

    @Autowired
    private TestDateSupplier dateSupplier;

    private Patient donald;

    @Before
    public void setUp() {
        donald = Patient.builder( ).firstName( "Donald" ).lastName( "Duck" ).birthday( LocalDate.of(
                1905,
                4,
                1 ) ).build( );
    }

    @Test
    public void ageOf() throws Exception {
        dateSupplier.setDate( LocalDate.of( 2015, 12, 31 ) );
        Optional<Integer> donaldsAge = service.ageOf( donald );
        assertThat( donaldsAge.isPresent(), is(true) );
        assertThat(donaldsAge.get(), is(110));
    }

}