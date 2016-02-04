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
import com.jordanec.sbrestapistormpath.service.StadiumService;
import com.jordanec.sbrestapistormpath.util.Constants;
import com.google.common.collect.*;

@RestController
@RequestMapping("/" + Constants.API_VERSION + "/")
public class StadiumController {

	@Autowired
	StadiumService stadiumService;

	static final Logger logger = Logger.getLogger(StadiumController.class);

	@RequestMapping(value = Constants.STADIUMS_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Stadium> createStadium(@RequestBody CustomRequRespObject<Stadium> customRequRespStadium) {
		return stadiumService.createStadium(customRequRespStadium);
	}

	@RequestMapping(value = Constants.STADIUMS_PATH + "/{id}", method = RequestMethod.GET)
	CustomRequRespObject<Stadium> readStadium(@PathVariable("id") int id) {
		return stadiumService.readStadium(id);
	}

	@RequestMapping(value = Constants.STADIUMS_PATH + "/name={name}", method = RequestMethod.GET)
	CustomRequRespObject<Stadium> findByName(@PathVariable("name") String name) {
		return stadiumService.findByName(name);
	}

	@RequestMapping(value = Constants.STADIUMS_PATH
			+ "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Stadium> updateStadium(@RequestBody CustomRequRespObject<Stadium> customRequRespStadium,
			@PathVariable int id) {
		return stadiumService.updateStadium(customRequRespStadium, id);
	}

	@RequestMapping(value = Constants.STADIUMS_PATH + "/{id}", method = RequestMethod.DELETE)
	CustomRequRespObject<Stadium> deleteStadium(@PathVariable int id) {
		return stadiumService.deleteStadium(id);
	}

	@RequestMapping(value = Constants.STADIUMS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Stadium> listStadiums() {
		return stadiumService.listStadiums();
	}

	@RequestMapping(value = Constants.STADIUMS_PATH + "/{id}/" + Constants.TEAMS_PATH, method = RequestMethod.GET)
	CustomRequRespObject<Team> readStadiumTeams(@PathVariable("id") int id) {
		return stadiumService.readStadiumTeams(id);
	}
}