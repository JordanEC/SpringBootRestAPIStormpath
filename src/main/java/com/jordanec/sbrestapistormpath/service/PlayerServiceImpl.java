package com.jordanec.sbrestapistormpath.service;

import java.util.Collection;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	PlayerRepository playerRepository;
	
	@Override
	public CustomRequRespObject<Player> createPlayer(CustomRequRespObject<Player> customRequRespPlayer){
		try {
			if(customRequRespPlayer.isSingleObject())
				customRequRespPlayer.setObject(playerRepository.save(customRequRespPlayer.getObject()));
			else
				customRequRespPlayer.setCollection((Collection<Player>) playerRepository.save(customRequRespPlayer.getCollection()));
			
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_CREATED, "Player has been created successfully"));
		}catch(Exception e) {
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespPlayer;
	}

	@Override
	public CustomRequRespObject<Player> readPlayer(int id) {
		CustomRequRespObject<Player> customRequRespplayer = new CustomRequRespObject<Player>(true);
		try {
			Player player = playerRepository.findOne(id);
			if (player == null)
				customRequRespplayer.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Player not found"));
				
			else {
				customRequRespplayer.setStatus(new Status(HttpStatus.SC_OK, "Player found"));
				customRequRespplayer.setObject(player);
			}
		} catch (IllegalArgumentException e) {
			customRequRespplayer.setStatus(new Status(HttpStatus.SC_BAD_REQUEST, e.getMessage()));
		}
		return customRequRespplayer;
	}
	
	@Override
	public CustomRequRespObject<Player> findByName(String name) {
		CustomRequRespObject<Player> customRequRespPlayer = new CustomRequRespObject<Player>(true);
		Player player = playerRepository.findByName(name);
		if (player == null)
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Player not found"));
		else {
			customRequRespPlayer.setStatus(new Status(HttpStatus.SC_OK, "Player found"));
			customRequRespPlayer.setObject(player);
		}
		return customRequRespPlayer;
	}

	@Override
	public CustomRequRespObject<Player> findOlderThan(int age) {
		CustomRequRespObject<Player> customRequRespplayer = new CustomRequRespObject<Player>(false);
		Collection<Player> players = playerRepository.findOlderThan(age);
		if (players.isEmpty())
			customRequRespplayer.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Players not found"));
		else {
			customRequRespplayer.setStatus(new Status(HttpStatus.SC_OK, players.size() + " Players found"));
			customRequRespplayer.setCollection(players);
		}
		return customRequRespplayer;
	}
	
	@Override
	public CustomRequRespObject<Player> updatePlayer(CustomRequRespObject<Player> customRequRespplayer, int id) {
		try {
			Player player = playerRepository.update(customRequRespplayer.getObject());
			customRequRespplayer.setStatus(new Status(HttpStatus.SC_OK, "Player updated Successfully"));
			customRequRespplayer.setObject(player);
		} catch (Exception e) {
			customRequRespplayer.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespplayer;
	}

	@Override
	public CustomRequRespObject<Player> deletePlayer(int id) {
		CustomRequRespObject<Player> customRequRespplayer = new CustomRequRespObject<Player>(true);
		Player playerToDelete, playerDeleted = null;
		
		try {
			playerToDelete = playerRepository.findOne(id);
			if (playerToDelete == null)
				customRequRespplayer.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Player not found"));
			else {
				playerDeleted = new Player(playerToDelete.getIdPlayer(), 
						new Team(playerToDelete.getTeam().getName()), 
						new Country(playerToDelete.getCountry().getName()),
						new Sponsor(playerToDelete.getSponsor().getName()), 
						playerToDelete.getDni(), 
						playerToDelete.getName(),
						playerToDelete.getLastName(),
						playerToDelete.getSecondLastName(),
						playerToDelete.getSalary(),
						playerToDelete.getYearsOfRemainingContract(),
						playerToDelete.getBirthDate(),
						playerToDelete.getPosition(),
						playerToDelete.getAge());
				
				playerRepository.delete(playerToDelete);
				customRequRespplayer.setStatus(new Status(HttpStatus.SC_OK, "Player deleted Successfully"));
				customRequRespplayer.setObject(playerDeleted);
			}
		} catch (Exception e) {
			customRequRespplayer.setObject(playerDeleted);
			customRequRespplayer.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		return customRequRespplayer;
	}

	@Override
	public CustomRequRespObject<Player> listPlayers() {
		CustomRequRespObject<Player> customRequRespPlayer;
		Collection<Player> players = Lists.newArrayList(playerRepository.findAll());
		Status status;
		if(players.isEmpty())
			status = new Status(HttpStatus.SC_NO_CONTENT, "No Players found");
		else
			status = new Status(HttpStatus.SC_OK, players.size()+" Players found");
		
		customRequRespPlayer = new CustomRequRespObject<>(players, status, false);
		return customRequRespPlayer;
	}

	@Override
	public CustomRequRespObject<Team> readPlayerTeam(int id) {
		CustomRequRespObject<Team> customRequRespTeam = new CustomRequRespObject<>();
		customRequRespTeam.setSingleObject(true);
		Team team;
		Player player = playerRepository.findOne(id);

		if(player == null) {
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Player found"));
			return customRequRespTeam;
		}
		
		team = player.getTeam();
		
		if(team == null)
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Teams found"));
		else
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_OK, "Team found"));
		
		customRequRespTeam.setObject(team);
		return customRequRespTeam;
	}
	
	@Override
	public CustomRequRespObject<Country> readPlayerCountry(int id) {
		CustomRequRespObject<Country> customRequRespTeam = new CustomRequRespObject<>();
		customRequRespTeam.setSingleObject(true);
		Country country;
		Player player = playerRepository.findOne(id);

		if(player == null) {
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Player found"));
			return customRequRespTeam;
		}
		
		country = player.getCountry();
		
		if(country == null)
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Countries found"));
		else
			customRequRespTeam.setStatus(new Status(HttpStatus.SC_OK, "Countries found"));
		
		customRequRespTeam.setObject(country);
		return customRequRespTeam;
	}
	
	@Override
	public CustomRequRespObject<Sponsor> readPlayerSponsor(int id) {
		CustomRequRespObject<Sponsor> customRequRespSponsor = new CustomRequRespObject<>();
		customRequRespSponsor.setSingleObject(true);
		Sponsor sponsor;
		Player player = playerRepository.findOne(id);

		if(player == null) {
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Player found"));
			return customRequRespSponsor;
		}
		
		sponsor = player.getSponsor();
		
		if(sponsor == null)
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Sponsors found"));
		else
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_OK, "Sponsor found"));
		
		customRequRespSponsor.setObject(sponsor);
		return customRequRespSponsor;
	}

	/*
	 * private boolean validAccount(HttpServletRequest req) { return
	 * AccountResolver.INSTANCE.getAccount(req) != null ? true : false; }
	 */

}
