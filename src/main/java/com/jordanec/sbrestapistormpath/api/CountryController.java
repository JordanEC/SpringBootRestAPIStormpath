package com.jordanec.sbrestapistormpath.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.service.CountryService;
import com.jordanec.sbrestapistormpath.util.Constants;

@RestController
@RequestMapping("/" + Constants.API_VERSION + "/")
public class CountryController {

	@Autowired
	CountryService countryService;

	static final Logger logger = Logger.getLogger(CountryController.class);

	@RequestMapping(value = Constants.COUNTRIES_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Country> createCountry(@RequestBody CustomRequRespObject<Country> customRequRespCountry) {
		return countryService.createCountry(customRequRespCountry);
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH + "/{id}", method = RequestMethod.GET)
	CustomRequRespObject<Country> readCountry(@PathVariable("id") int id) {
		return countryService.readCountry(id);
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH + "/name={name}", method = RequestMethod.GET)
	CustomRequRespObject<Country> findByName(@PathVariable("name") String name) {
		return countryService.findByName(name);
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH
			+ "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Country> updateCountry(@RequestBody CustomRequRespObject<Country> customRequRespCountry,
			@PathVariable int id) {
		return countryService.updateCountry(customRequRespCountry, id);
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH + "/{id}", method = RequestMethod.DELETE)
	CustomRequRespObject<Country> deleteCountry(@PathVariable int id) {
		return countryService.deleteCountry(id);
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Country> listCountries() {
		return countryService.listCountries();
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH + "/{id}/" + Constants.TEAMS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Team> readCountryTeams(@PathVariable("id") int id) {
		return countryService.readCountryTeams(id);
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH + "/{id}/" + Constants.PLAYERS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Player> readCountryPlayers(@PathVariable("id") int id) {
		return countryService.readCountryPlayers(id);
	}

	@RequestMapping(value = Constants.COUNTRIES_PATH + "/{id}/"
			+ Constants.CONFEDERATION_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Confederation> readCountryConfederation(@PathVariable("id") int id) {
		return countryService.readCountryConfederation(id);
	}
}