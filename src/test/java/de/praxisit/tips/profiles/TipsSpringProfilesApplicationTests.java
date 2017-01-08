package de.praxisit.tips.profiles;

import de.praxisit.tips.profiles.config.Profiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.TEST)
public class TipsSpringProfilesApplicationTests {

	@Test
	public void contextLoads() {
	}

}
