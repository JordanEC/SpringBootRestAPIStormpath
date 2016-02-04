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
import com.jordanec.sbrestapistormpath.service.ConfederationService;
import com.jordanec.sbrestapistormpath.util.Constants;

@RestController
@RequestMapping("/"+Constants.API_VERSION+"/")
public class ConfederationController {
	
	@Autowired
	ConfederationService confederationService;
	
	static final Logger logger = Logger.getLogger(ConfederationController.class);
	
	@RequestMapping(value =Constants.CONFEDERATIONS_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Confederation> createConfederation(@RequestBody CustomRequRespObject<Confederation> customRequRespConfederation) {
		return confederationService.createConfederation(customRequRespConfederation);
	}
	
	@RequestMapping(value=Constants.CONFEDERATIONS_PATH+"/{id}", method= RequestMethod.GET)
	CustomRequRespObject<Confederation> readConfederation(@PathVariable("id") int id) {
		return confederationService.readConfederation(id);
	}
	
	@RequestMapping(value=Constants.CONFEDERATIONS_PATH+"/name={name}", method=RequestMethod.GET)
	CustomRequRespObject<Confederation> findByName(@PathVariable("name") String name) {
		return confederationService.findByName(name);
	}
	
	@RequestMapping(value=Constants.CONFEDERATIONS_PATH+"/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	CustomRequRespObject<Confederation> updateConfederation(@RequestBody CustomRequRespObject<Confederation> customRequRespConfederation, @PathVariable int id) {
		return confederationService.updateConfederation(customRequRespConfederation, id);
	}
	
	@RequestMapping(value=Constants.CONFEDERATIONS_PATH+"/{id}", method=RequestMethod.DELETE)
	CustomRequRespObject<Confederation> deleteConfederation(@PathVariable int id) {
		return confederationService.deleteConfederation(id);
	}
	
	@RequestMapping(value=Constants.CONFEDERATIONS_PATH, method=RequestMethod.GET)
	CustomRequRespObject<Confederation> listConfederations() {
		return confederationService.listConfederations();
	}

	@RequestMapping(value=Constants.CONFEDERATIONS_PATH+"/{id}/"+Constants.COUNTRIES_PATH, method= RequestMethod.GET)
	CustomRequRespObject<Country> readConfederationCountries(@PathVariable("id") int id) {
		return confederationService.readConfederationCountries(id);
	}
}