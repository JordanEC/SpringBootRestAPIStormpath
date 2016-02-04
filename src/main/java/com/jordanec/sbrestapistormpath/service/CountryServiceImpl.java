package com.jordanec.sbrestapistormpath.service;

import java.util.Collection;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryRepository countryRepository;
	
	@Override
	public CustomRequRespObject<Country> createCountry(CustomRequRespObject<Country> customRequRespCountry){
		try {
			if(customRequRespCountry.isSingleObject())
				customRequRespCountry.setObject(countryRepository.save(customRequRespCountry.getObject()));
			else
				customRequRespCountry.setCollection((Collection<Country>) countryRepository.save(customRequRespCountry.getCollection()));
			
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_CREATED, "Country has been created successfully"));
		}catch(Exception e) {
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespCountry;
	}

	@Override
	public CustomRequRespObject<Country> readCountry(int id) {
		CustomRequRespObject<Country> customRequRespcountry = new CustomRequRespObject<Country>(true);
		try {
			Country country = countryRepository.findOne(id);
			if (country == null)
				customRequRespcountry.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Country not found"));
				
			else {
				customRequRespcountry.setStatus(new Status(HttpStatus.SC_OK, "Country found"));
				customRequRespcountry.setObject(country);
			}
		} catch (IllegalArgumentException e) {
			customRequRespcountry.setStatus(new Status(HttpStatus.SC_BAD_REQUEST, e.getMessage()));
		}
		return customRequRespcountry;
	}
	
	@Override
	public CustomRequRespObject<Country> findByName(String name) {
		CustomRequRespObject<Country> customRequRespcountry = new CustomRequRespObject<Country>(true);
		Country country = countryRepository.findByName(name);
		if (country == null)
			customRequRespcountry.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Country not found"));
		else {
			customRequRespcountry.setStatus(new Status(HttpStatus.SC_OK, "Country found"));
			customRequRespcountry.setObject(country);
		}
		return customRequRespcountry;
	}

	@Override
	public CustomRequRespObject<Country> updateCountry(CustomRequRespObject<Country> customRequRespCountry, int id) {
		try {
			Country country = countryRepository.update(customRequRespCountry.getObject());
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_OK, "Country updated Successfully"));
			customRequRespCountry.setObject(country);
		} catch (Exception e) {
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespCountry;
	}

	@Override
	public CustomRequRespObject<Country> deleteCountry(int id) {
		CustomRequRespObject<Country> customRequRespCountry = new CustomRequRespObject<Country>(true);
		Country countryToDelete, countryDeleted = null;
		
		try {
			countryToDelete = countryRepository.findOne(id);
			if (countryToDelete == null)
				customRequRespCountry.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Country not found"));
			else {
				countryDeleted = new Country(countryToDelete.getIdCountry(), new Confederation(countryToDelete.getConfederation().getName()),countryToDelete.getName(), countryToDelete.getPositionRankingFifa());
				countryRepository.delete(countryToDelete);
				customRequRespCountry.setStatus(new Status(HttpStatus.SC_OK, "Country deleted Successfully"));
				customRequRespCountry.setObject(countryDeleted);
			}
		} catch (Exception e) {
			customRequRespCountry.setObject(countryDeleted);
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		return customRequRespCountry;
	}

	@Override
	public CustomRequRespObject<Country> listCountries() {
		CustomRequRespObject<Country> customRequRespConfederation;
		Collection<Country> countries = Lists.newArrayList(countryRepository.findAll());
		Status status;
		if(countries.isEmpty())
			status = new Status(HttpStatus.SC_NO_CONTENT, "No Countries found");
		else
			status = new Status(HttpStatus.SC_OK, countries.size()+" Countries found");
		
		customRequRespConfederation = new CustomRequRespObject<>(countries, status, false);
		return customRequRespConfederation;
	}

	@Override
	public CustomRequRespObject<Team> readCountryTeams(int id) {
		CustomRequRespObject<Team> customRequRespTeam = new CustomRequRespObject<>();
		customRequRespTeam.setSingleObject(false);
		Collection<Team> teams;
		Country country = countryRepository.findOne(id);

		if(country == null) {
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Country found"));
			return customRequRespTeam;
		}
		
		teams = country.getTeams();
		
		if(teams.isEmpty())
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Teams found"));
		else
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_OK, teams.size()+" Teams found"));
		
		customRequRespTeam.setCollection(teams);
		return customRequRespTeam;
	}
	
	@Override
	public CustomRequRespObject<Player> readCountryPlayers(int id) {
		CustomRequRespObject<Player> customRequRespPlayer = new CustomRequRespObject<>();
		customRequRespPlayer.setSingleObject(false);
		Collection<Player> players;
		Country country = countryRepository.findOne(id);

		if(country == null) {
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Country found"));
			return customRequRespPlayer;
		}
		
		players = country.getPlayers();
		
		if(players.isEmpty())
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Players found"));
		else
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_OK, players.size()+" Players found"));
		
		customRequRespPlayer.setCollection(players);
		return customRequRespPlayer;
	}
	
	@Override
	public CustomRequRespObject<Confederation> readCountryConfederation(int id) {
		CustomRequRespObject<Confederation> customRequRespConfederation = new CustomRequRespObject<>();
		customRequRespConfederation.setSingleObject(true);
		Confederation confederation;
		Country country = countryRepository.findOne(id);

		if(country == null) {
			customRequRespConfederation.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Country found"));
			return customRequRespConfederation;
		}
		
		confederation = country.getConfederation();
		
		if(confederation == null)
			customRequRespConfederation.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Confederations found"));
		else
			customRequRespConfederation.setStatus(new Status(HttpStatus.SC_OK, "Confederation found"));
		
		customRequRespConfederation.setObject(confederation);
		return customRequRespConfederation;
	}
	
	/*
	 * private boolean validAccount(HttpServletRequest req) { return
	 * AccountResolver.INSTANCE.getAccount(req) != null ? true : false; }
	 */

}
