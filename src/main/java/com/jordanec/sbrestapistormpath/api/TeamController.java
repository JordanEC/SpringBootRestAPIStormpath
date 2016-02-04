package com.jordanec.sbrestapistormpath.api;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.repository.*;
import com.jordanec.sbrestapistormpath.service.TeamService;
import com.jordanec.sbrestapistormpath.util.Constants;
import com.google.common.collect.*;

@RestController
@RequestMapping("/" + Constants.API_VERSION + "/")
public class TeamController {

	@Autowired
	TeamService teamService;

	static final Logger logger = Logger.getLogger(TeamController.class);

	@RequestMapping(value = Constants.TEAMS_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Team> createTeam(@RequestBody CustomRequRespObject<Team> customRequRespTeam) {
		return teamService.createTeam(customRequRespTeam);
	}

	@RequestMapping(value = Constants.TEAMS_PATH + "/{id}", method = RequestMethod.GET)
	CustomRequRespObject<Team> readTeam(@PathVariable("id") int id) {
		return teamService.readTeam(id);
	}

	@RequestMapping(value = Constants.TEAMS_PATH + "/name={name}", method = RequestMethod.GET)
	CustomRequRespObject<Team> findByName(@PathVariable("name") String name) {
		return teamService.findByName(name);
	}

	@RequestMapping(value = Constants.TEAMS_PATH
			+ "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Team> updateTeam(@RequestBody CustomRequRespObject<Team> customRequRespTeam,
			@PathVariable int id) {
		return teamService.updateTeam(customRequRespTeam, id);
	}

	@RequestMapping(value = Constants.TEAMS_PATH + "/{id}", method = RequestMethod.DELETE)
	CustomRequRespObject<Team> deleteTeam(@PathVariable int id) {
		return teamService.deleteTeam(id);
	}

	@RequestMapping(value = Constants.TEAMS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Team> listTeams() {
		return teamService.listTeams();
	}

	@RequestMapping(value = Constants.TEAMS_PATH + "/{id}/" + Constants.STADIUMS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Stadium> readTeamStadiums(@PathVariable("id") int id) {
		return teamService.readTeamStadiums(id);
	}

	@RequestMapping(value = Constants.TEAMS_PATH + "/{id}/" + Constants.PLAYERS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Player> readTeamPlayers(@PathVariable("id") int id) {
		return teamService.readTeamPlayers(id);
	}

	@RequestMapping(value = Constants.TEAMS_PATH + "/{id}/" + Constants.COUNTRY_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Country> readTeamCountry(@PathVariable("id") int id) {
		return teamService.readTeamCountry(id);
	}

	@RequestMapping(value = Constants.TEAMS_PATH + "/{id}/" + Constants.SPONSORS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Sponsor> readTeamSponsors(@PathVariable("id") int id) {
		return teamService.readTeamSponsors(id);
	}
}
