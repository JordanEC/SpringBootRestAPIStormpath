package com.jordanec.sbrestapistormpath.service;

import com.jordanec.sbrestapistormpath.model.*;

public interface CountryService {
	CustomRequRespObject<Country> createCountry(CustomRequRespObject<Country> customRequRespCountry);
	CustomRequRespObject<Country> readCountry(int id);
	CustomRequRespObject<Country> findByName(String name);
	CustomRequRespObject<Country> updateCountry(CustomRequRespObject<Country> customRequRespCountry, int id);
	CustomRequRespObject<Country> deleteCountry(int id);
	CustomRequRespObject<Country> listCountries();
	CustomRequRespObject<Team> readCountryTeams(int id);
	CustomRequRespObject<Player> readCountryPlayers(int id);
	CustomRequRespObject<Confederation> readCountryConfederation(int id);
}