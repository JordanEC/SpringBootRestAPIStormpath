package com.jordanec.sbrestapistormpath.service;

import com.jordanec.sbrestapistormpath.model.*;

public interface PlayerService {
	CustomRequRespObject<Player> createPlayer(CustomRequRespObject<Player> customRequRespPlayer);
	CustomRequRespObject<Player> readPlayer(int id);
	CustomRequRespObject<Player> findByName(String name);
	CustomRequRespObject<Player> findOlderThan(int age);
	CustomRequRespObject<Player> updatePlayer(CustomRequRespObject<Player> customRequRespPlayer, int id);
	CustomRequRespObject<Player> deletePlayer(int id);
	CustomRequRespObject<Player> listPlayers();
	CustomRequRespObject<Team> readPlayerTeam(int id);
	CustomRequRespObject<Country> readPlayerCountry(int id);
	CustomRequRespObject<Sponsor> readPlayerSponsor(int id);
}