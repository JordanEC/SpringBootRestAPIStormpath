package com.jordanec.sbrestapistormpath.service;

import com.jordanec.sbrestapistormpath.model.*;

public interface SponsorService {
	CustomRequRespObject<Sponsor> createSponsor(CustomRequRespObject<Sponsor> customRequRespSponsor);
	CustomRequRespObject<Sponsor> readSponsor(int id);
	CustomRequRespObject<Sponsor> findByName(String name);
	CustomRequRespObject<Sponsor> updateSponsor(CustomRequRespObject<Sponsor> customRequRespSponsor, int id);
	CustomRequRespObject<Sponsor> deleteSponsor(int id);
	CustomRequRespObject<Sponsor> listSponsors();
	CustomRequRespObject<Team> readSponsorTeams(int id);
	CustomRequRespObject<Player> readSponsorPlayers(int id);	
}