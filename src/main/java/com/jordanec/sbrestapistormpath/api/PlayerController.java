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
import com.jordanec.sbrestapistormpath.service.PlayerService;
import com.jordanec.sbrestapistormpath.util.Constants;

@RestController
@RequestMapping("/" + Constants.API_VERSION + "/")
public class PlayerController {

	@Autowired
	PlayerService playerService;

	static final Logger logger = Logger.getLogger(PlayerController.class);

	@RequestMapping(value = Constants.PLAYERS_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Player> createPlayer(@RequestBody CustomRequRespObject<Player> customRequRespPlayer) {
		return playerService.createPlayer(customRequRespPlayer);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH + "/{id}", method = RequestMethod.GET)
	CustomRequRespObject<Player> readPlayer(@PathVariable("id") int id) {
		return playerService.readPlayer(id);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH + "/name={name}", method = RequestMethod.GET)
	CustomRequRespObject<Player> findByName(@PathVariable("name") String name) {
		return playerService.findByName(name);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH
			+ "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Player> updatePlayer(@RequestBody CustomRequRespObject<Player> customRequRespPlayer,
			@PathVariable int id) {
		return playerService.updatePlayer(customRequRespPlayer, id);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH + "/{id}", method = RequestMethod.DELETE)
	CustomRequRespObject<Player> deletePlayer(@PathVariable("id") int id) {
		return playerService.deletePlayer(id);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Player> listPlayers() {
		return playerService.listPlayers();
	}

	@RequestMapping(value = Constants.PLAYERS_PATH + "/older={age}", method = RequestMethod.GET)
	CustomRequRespObject<Player> findOlderThan(@PathVariable int age) {
		return playerService.findOlderThan(age);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH + "/{id}/" + Constants.TEAM_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Team> readPlayerTeam(@PathVariable("id") int id) {
		return playerService.readPlayerTeam(id);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH + "/{id}/" + Constants.COUNTRY_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Country> readPlayerCountry(@PathVariable("id") int id) {
		return playerService.readPlayerCountry(id);
	}

	@RequestMapping(value = Constants.PLAYERS_PATH + "/{id}/" + Constants.SPONSOR_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Sponsor> readPlayerSponsor(@PathVariable("id") int id) {
		return playerService.readPlayerSponsor(id);
	}
}