package com.jordanec.sbrestapistormpath;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootRestAPIStormpathTestsApplication.class)
@WebAppConfiguration
public class SpringBootRestAPIStormpathTests {
	AuthTest authTest;
	ConfederationTest confederationTest;
	CountryTest countryTest;
	
	//	Pending
	TeamTest teamTest;
	PlayerTest playerTest;
	StadiumTest stadiumTest;
	SponsorTest sponsorTest;
	

	@Before
	public void setUp() {
		authTest = AuthTest.getInstance();
		assertTrue(authTest.doAllTests());
	}

	@Test
	public void confederation() {
		confederationTest = ConfederationTest.getInstance(authTest.getToken());
		assertTrue(confederationTest.doAllTests());
	}
	
	@Test
	public void country() {
		confederationTest = ConfederationTest.getInstance(authTest.getToken());
		countryTest = CountryTest.getInstance(authTest.getToken(), confederationTest);
		assertTrue(countryTest.doAllTests());
	}
	
	@Test
	@Ignore
	public void team() {
		countryTest = CountryTest.getInstance(authTest.getToken(), confederationTest);
		teamTest = TeamTest.getInstance(authTest.getToken(), countryTest);
		//assertTrue(teamTest.doAllTests());
	}
	
	@Test
	@Ignore
	public void sponsor() {
		sponsorTest = SponsorTest.getInstance(authTest.getToken());
		//assertTrue(sponsorTest.doAllTests());
	}
	
	@Test
	@Ignore
	public void player() {
		sponsorTest = SponsorTest.getInstance(authTest.getToken());
		confederationTest = ConfederationTest.getInstance(authTest.getToken());
		countryTest = CountryTest.getInstance(authTest.getToken(), confederationTest);
		teamTest = TeamTest.getInstance(authTest.getToken(), countryTest);
		playerTest = PlayerTest.getInstance(authTest.getToken(), countryTest, sponsorTest, teamTest);
		//assertTrue(playerTest.doAllTests());
	}
	
	@Test
	@Ignore
	public void stadium() {
		stadiumTest = StadiumTest.getInstance(authTest.getToken());
		//assertTrue(stadiumTest.doAllTests());
	}
	
	
	@Test
	@Ignore
	public void contextLoads() {
		assertTrue(authTest.doAllTests());
		confederationTest = ConfederationTest.getInstance(authTest.getToken());
		countryTest = CountryTest.getInstance(authTest.getToken(), confederationTest);
	}
	
	
	
}