package com.jordanec.sbrestapistormpath.service;

import com.jordanec.sbrestapistormpath.model.*;


public interface TeamService {
	CustomRequRespObject<Team> createTeam(CustomRequRespObject<Team> customRequRespTeam);
	CustomRequRespObject<Team> readTeam(int id);
	CustomRequRespObject<Team> findByName(String name);
	CustomRequRespObject<Team> updateTeam(CustomRequRespObject<Team> customRequRespTeam, int id);
	CustomRequRespObject<Team> deleteTeam(int id);
	CustomRequRespObject<Team> listTeams();
	CustomRequRespObject<Country> readTeamCountry(int id);
	CustomRequRespObject<Stadium> readTeamStadiums(int id);
	CustomRequRespObject<Player> readTeamPlayers(int id);
	CustomRequRespObject<Sponsor> readTeamSponsors(int id);
}