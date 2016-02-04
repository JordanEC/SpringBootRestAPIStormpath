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
import com.jordanec.sbrestapistormpath.service.SponsorService;
import com.jordanec.sbrestapistormpath.util.Constants;

@RestController
@RequestMapping("/" + Constants.API_VERSION + "/")
public class SponsorController {

	@Autowired
	SponsorService sponsorService;

	static final Logger logger = Logger.getLogger(SponsorController.class);

	@RequestMapping(value = Constants.SPONSORS_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Sponsor> createSponsor(@RequestBody CustomRequRespObject<Sponsor> customRequRespSponsor) {
		return sponsorService.createSponsor(customRequRespSponsor);
	}

	@RequestMapping(value = Constants.SPONSORS_PATH + "/{id}", method = RequestMethod.GET)
	CustomRequRespObject<Sponsor> readSponsor(@PathVariable("id") int id) {
		return sponsorService.readSponsor(id);
	}

	@RequestMapping(value = Constants.SPONSORS_PATH + "/name={name}", method = RequestMethod.GET)
	CustomRequRespObject<Sponsor> findByName(@PathVariable("name") String name) {
		return sponsorService.findByName(name);
	}

	@RequestMapping(value = Constants.SPONSORS_PATH
			+ "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Sponsor> updateSponsor(@RequestBody CustomRequRespObject<Sponsor> customRequRespSponsor,
			@PathVariable int id) {
		return sponsorService.updateSponsor(customRequRespSponsor, id);
	}

	@RequestMapping(value = Constants.SPONSORS_PATH + "/{id}", method = RequestMethod.DELETE)
	CustomRequRespObject<Sponsor> deleteSponsor(@PathVariable int id) {
		return sponsorService.deleteSponsor(id);
	}

	@RequestMapping(value = Constants.SPONSORS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Sponsor> listSponsors() {
		return sponsorService.listSponsors();
	}

	@RequestMapping(value = Constants.SPONSORS_PATH + "/{id}/" + Constants.TEAMS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Team> readSponsorTeams(@PathVariable("id") int id) {
		return sponsorService.readSponsorTeams(id);
	}

	@RequestMapping(value = Constants.SPONSORS_PATH + "/{id}/" + Constants.PLAYERS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Player> readSponsorPlayers(@PathVariable("id") int id) {
		return sponsorService.readSponsorPlayers(id);
	}
}
