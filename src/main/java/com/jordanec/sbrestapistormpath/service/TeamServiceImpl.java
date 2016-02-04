package com.jordanec.sbrestapistormpath.service;

import java.util.Collection;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	TeamRepository teamRepository;
	
	@Override
	public CustomRequRespObject<Team> createTeam(CustomRequRespObject<Team> customRequRespTeam){
		try {
			if(customRequRespTeam.isSingleObject())
				customRequRespTeam.setObject(teamRepository.save(customRequRespTeam.getObject()));
			else
				customRequRespTeam.setCollection((Collection<Team>) teamRepository.save(customRequRespTeam.getCollection()));
			
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_CREATED, "Team has been created successfully"));
		}catch(Exception e) {
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespTeam;
	}

	@Override
	public CustomRequRespObject<Team> readTeam(int id) {
		CustomRequRespObject<Team> customRequRespteam = new CustomRequRespObject<Team>(true);
		try {
			Team team = teamRepository.findOne(id);
			if (team == null)
				customRequRespteam.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Team not found"));
				
			else {
				customRequRespteam.setStatus(new Status(HttpStatus.SC_OK, "Team found"));
				customRequRespteam.setObject(team);
			}
		} catch (IllegalArgumentException e) {
			customRequRespteam.setStatus(new Status(HttpStatus.SC_BAD_REQUEST, e.getMessage()));
		}
		return customRequRespteam;
	}
	
	@Override
	public CustomRequRespObject<Team> findByName(String name) {
		CustomRequRespObject<Team> customRequRespteam = new CustomRequRespObject<Team>(true);
		Team team = teamRepository.findByName(name);
		if (team == null)
			customRequRespteam.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Team not found"));
		else {
			customRequRespteam.setStatus(new Status(HttpStatus.SC_OK, "Team found"));
			customRequRespteam.setObject(team);
		}
		return customRequRespteam;
	}

	@Override
	public CustomRequRespObject<Team> updateTeam(CustomRequRespObject<Team> customRequRespteam, int id) {
		try {
			Team team = teamRepository.update(customRequRespteam.getObject());
			customRequRespteam.setStatus(new Status(HttpStatus.SC_OK, "Team updated Successfully"));
			customRequRespteam.setObject(team);
		} catch (Exception e) {
			customRequRespteam.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespteam;
	}

	@Override
	public CustomRequRespObject<Team> deleteTeam(int id) {
		CustomRequRespObject<Team> customRequRespteam = new CustomRequRespObject<Team>(true);
		Team teamToDelete, teamDeleted = null;
		
		try {
			teamToDelete = teamRepository.findOne(id);
			if (teamToDelete == null)
				customRequRespteam.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Team not found"));
			else {
				teamDeleted = new Team(teamToDelete.getIdTeam(),
						new Country(teamToDelete.getCountry().getName()),
						teamToDelete.getName(),
						teamToDelete.getChampionships());
				teamRepository.delete(teamToDelete);
				customRequRespteam.setStatus(new Status(HttpStatus.SC_OK, "Team deleted Successfully"));
				customRequRespteam.setObject(teamDeleted);
			}
		} catch (Exception e) {
			customRequRespteam.setObject(teamDeleted);
			customRequRespteam.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		return customRequRespteam;
	}

	@Override
	public CustomRequRespObject<Team> listTeams() {
		CustomRequRespObject<Team> customRequRespTeam;
		Collection<Team> teams = Lists.newArrayList(teamRepository.findAll());
		Status status;
		if(teams.isEmpty())
			status = new Status(HttpStatus.SC_NO_CONTENT, "No Teams found");
		else
			status = new Status(HttpStatus.SC_OK, teams.size()+" Teams found");
		
		customRequRespTeam = new CustomRequRespObject<>(teams, status, false);
		return customRequRespTeam;
	}

	@Override
	public CustomRequRespObject<Country> readTeamCountry(int id) {
		CustomRequRespObject<Country> customRequRespCountry = new CustomRequRespObject<>();
		customRequRespCountry.setSingleObject(true);
		Country country;
		Team team = teamRepository.findOne(id);

		if(team == null) {
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Team found"));
			return customRequRespCountry;
		}
		
		country = team.getCountry();
		
		if(country == null)
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Countries found"));
		else
			customRequRespCountry.setStatus(new Status(HttpStatus.SC_OK, "Country found"));
		
		customRequRespCountry.setObject(country);
		return customRequRespCountry;
	}
	
	@Override
	public CustomRequRespObject<Stadium> readTeamStadiums(int id) {
		CustomRequRespObject<Stadium> customRequRespStadium = new CustomRequRespObject<>();
		customRequRespStadium.setSingleObject(false);
		Collection<Stadium> stadiums;
		Team team = teamRepository.findOne(id);

		if(team == null) {
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Team found"));
			return customRequRespStadium;
		}
		
		stadiums = team.getStadiums();
		
		if(stadiums.isEmpty())
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Stadiums found"));
		else
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_OK, stadiums.size()+" Stadiums found"));
		
		customRequRespStadium.setCollection(stadiums);
		return customRequRespStadium;
	}
	
	@Override
	public CustomRequRespObject<Player> readTeamPlayers(int id) {
		CustomRequRespObject<Player> customRequRespPlayer = new CustomRequRespObject<>();
		customRequRespPlayer.setSingleObject(false);
		Collection<Player> players;
		Team team = teamRepository.findOne(id);

		if(team == null) {
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Team found"));
			return customRequRespPlayer;
		}
		
		players = team.getPlayers();
		
		if(players.isEmpty())
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Players found"));
		else
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_OK, players.size()+" Players found"));
		
		customRequRespPlayer.setCollection(players);
		return customRequRespPlayer;
	}
	
	@Override
	public CustomRequRespObject<Sponsor> readTeamSponsors(int id) {
		CustomRequRespObject<Sponsor> customRequRespSponsor = new CustomRequRespObject<>();
		customRequRespSponsor.setSingleObject(false);
		Collection<Sponsor> sponsors;
		Team team = teamRepository.findOne(id);

		if(team == null) {
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Team found"));
			return customRequRespSponsor;
		}
		
		sponsors = team.getSponsors();
		
		if(sponsors.isEmpty())
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Sponsors found"));
		else
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_OK, sponsors.size()+" Sponsors found"));
		
		customRequRespSponsor.setCollection(sponsors);
		return customRequRespSponsor;
	}
	
	
	/*
	 * private boolean validAccount(HttpServletRequest req) { return
	 * AccountResolver.INSTANCE.getAccount(req) != null ? true : false; }
	 */

}
