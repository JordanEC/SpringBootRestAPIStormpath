package com.jordanec.sbrestapistormpath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.http.HttpStatus;

import com.jordanec.sbrestapistormpath.client.ConfederationApi;
import com.jordanec.sbrestapistormpath.client.CountryApi;
import com.jordanec.sbrestapistormpath.client.PlayerApi;
import com.jordanec.sbrestapistormpath.client.ServiceGenerator;
import com.jordanec.sbrestapistormpath.model.*;
import retrofit.Call;

public class PlayerTest {
	private PlayerApi playerApi;
	private CountryTest countryTest;
	private SponsorTest sponsorTest;
	private TeamTest teamTest;
	private static PlayerTest playerTest;
	private Token token;
	private Collection<Player> validPlayerToInsert, invalidPlayerToInsert;
	private Country country;
	private Sponsor sponsor;
	private Team team;
	protected PlayerTest(){}

	public static PlayerTest getInstance(Token token, CountryTest countryTest, SponsorTest sponsorTest, TeamTest teamTest) {
		if (playerTest == null) {
			playerTest = new PlayerTest();
			playerTest.setToken(token);
			playerTest.createPlayerApi();
			playerTest.countryTest = countryTest;
			playerTest.sponsorTest = sponsorTest;
			playerTest.teamTest = teamTest;
			playerTest.country = countryTest.countryReadTest(1);
			playerTest.sponsor = sponsorTest.sponsorReadTest(1);
			playerTest.team = teamTest.teamReadTest(1);
			/*playerTest.validPlayerToInsert = new ArrayList<>();
			playerTest.validPlayerToInsert.add(new Player(playerTest.confederation5, "New player 1.1", 500));	//new Player(confederationTest.confederationReadTest(1)
			playerTest.validPlayerToInsert.add(new Player(playerTest.confederation5, "New player 1.2", 501));
			playerTest.validPlayerToInsert.add(new Player(playerTest.confederation5, "New player 1.3", 502));
			playerTest.validPlayerToInsert.add(new Player(playerTest.confederation5, "New player 1.4", 503));
			playerTest.validPlayerToInsert.add(new Player(playerTest.confederation5, "New player 1.5", 504));
			playerTest.invalidPlayerToInsert = new ArrayList<>();
			playerTest.invalidPlayerToInsert.add(new Player(playerTest.confederation5, "New player 2.1", 505));
			playerTest.invalidPlayerToInsert.add(new Player(playerTest.confederation5, "New player 2.2", 506));
			playerTest.invalidPlayerToInsert.add(new Player(playerTest.confederation5, "New player 2.3", 507));
			playerTest.invalidPlayerToInsert.add(new Player(playerTest.confederation5, "New player 2.4", 508));
			playerTest.invalidPlayerToInsert.add(new Player(playerTest.confederation5, "New player 2.5", 508));	*/	
		}
		return playerTest;
	}

	public static PlayerTest getInstance() {
		return playerTest;
	}
	
	public Player playerReadTest(int idPlayer) {
		System.out.println("\nplayerReadTest... idPlayer = " + idPlayer + "\n\n");
		Call<CustomRequRespObject<Player>> call = playerApi.readPlayer(idPlayer);
		try {
			retrofit.Response<CustomRequRespObject<Player>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Player> customRequRespPlayer = response.body();
				System.out.println(customRequRespPlayer.getStatus());
				System.out.println(customRequRespPlayer.getObject());
				return customRequRespPlayer.getObject();
			}
			System.out.println(response.message());
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/*private boolean countryCreateTest(String name, int positionRankingFifa, int idConfederation) {
		System.out.println("\ncountryCreateTest... name = " + name + "\n");
		CustomRequRespObject<Country> customRequRespCountry = new CustomRequRespObject<>(
				new Country(confederationTest.confederationReadTest(idConfederation), name, positionRankingFifa), true);
		Call<CustomRequRespObject<Country>> call = countryApi.createCountry(customRequRespCountry);
		try {
			retrofit.Response<CustomRequRespObject<Country>> response = call.execute();
			if (response.isSuccess()) {
				customRequRespCountry = response.body();
				System.out.println(customRequRespCountry.getStatus());
				System.out.println(customRequRespCountry.getObject());
				return true;
			}
			System.out.println(response.message());
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean countryCreateTest(Collection<Country> collection) {
		System.out.println("\ncountryCreateTest... collection = " + collection.toString() + "\n");
		CustomRequRespObject<Country> customRequRespCountry = 
				new CustomRequRespObject<>(collection, false);
		Call<CustomRequRespObject<Country>> call = countryApi.createCountry(customRequRespCountry);
		try {
			retrofit.Response<CustomRequRespObject<Country>> response = call.execute();
			if (response.isSuccess()) {
				customRequRespCountry = response.body();
				System.out.println(customRequRespCountry.getStatus());
				System.out.println(customRequRespCountry.getCollection());
				return true;
			}
			System.out.println(response.message());
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean countryUpdateTest(int idCountry, String name) {
		System.out.println("\n\ncountryUpdateTest...\n\n");
		CustomRequRespObject<Country> customRequRespCountry = new CustomRequRespObject<>(true);
		Country country = countryReadTest(idCountry);
		if(country == null) {
			System.out.println("Country doesn't exists");
			return true;
		}
		country.setName(name);
		customRequRespCountry.setObject(country);
		Call<CustomRequRespObject<Country>> call = countryApi.updateCountry(customRequRespCountry, idCountry);

		try {
			retrofit.Response<CustomRequRespObject<Country>> response = call.execute();
			if (response.isSuccess()) {
				customRequRespCountry = response.body();
				System.out.println(customRequRespCountry.getStatus());
				System.out.println(customRequRespCountry.getObject());
				return true;
			}
			System.out.println(response.message());
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean countryDeleteTest(int idCountry) {
		System.out.println("\n\ncountryDeleteTest...\n\n");
		Call<CustomRequRespObject<Country>> call = countryApi.deleteCountry(idCountry);

		try {
			retrofit.Response<CustomRequRespObject<Country>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Country> customRequRespCountry = response.body();
				System.out.println(customRequRespCountry.getStatus());
				System.out.println(customRequRespCountry.getObject());
				return true;
			}
			System.out.println(response.message());
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean countryListTest() {
		System.out.println("\n\ncountryListTest...\n\n");
		Call<CustomRequRespObject<Country>> call = countryApi.listCountries();

		try {
			retrofit.Response<CustomRequRespObject<Country>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Country> customRequRespCountry = response.body();
				if(customRequRespCountry.getStatus().getCode() == HttpStatus.SC_OK) {
					System.out.println(customRequRespCountry.getStatus());
					Iterator<Country> iterator = customRequRespCountry.getCollection().iterator();
					Country country;
					while (iterator.hasNext()) {
						country = iterator.next();
						System.out.println(country);
					}
				}
				else
					System.out.println(customRequRespCountry.getStatus());
				return true;
			}
			else {
				System.out.println(response.message());
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Country countryFindByNameTest(String name) {
		System.out.println("\n\ncountryFindByNameTest...\n\n");
		Call<CustomRequRespObject<Country>> call = countryApi.findByName(name);

		try {
			retrofit.Response<CustomRequRespObject<Country>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Country> customRequRespCountry = response.body();
				System.out.println(customRequRespCountry.getStatus());
				System.out.println(customRequRespCountry.getObject());
				return customRequRespCountry.getObject();
			}
			System.out.println(response.message());
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean countryTeamsReadTest(int idCountry) {
		System.out.println("\n\ncountryTeamsReadTest... "+"idCountry:" +idCountry+"\n\n");
		Call<CustomRequRespObject<Team>> call = countryApi.readCountryTeams(idCountry);

		try {
			retrofit.Response<CustomRequRespObject<Team>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Team> customRequRespTeam = response.body();
				if(customRequRespTeam.getStatus().getCode() == HttpStatus.SC_OK) {
					System.out.println(customRequRespTeam.getStatus());
					Iterator<Team> iterator = customRequRespTeam.getCollection().iterator();
					int i=1;
					while (iterator.hasNext()) {
						System.out.println(i++ +": "+ iterator.next().getName());
					}
				}
				else
					System.out.println(customRequRespTeam.getStatus());

				return true;
			}
			else {
				System.out.println(response.message());
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean countryPlayersReadTest(int idCountry) {
		System.out.println("\n\ncountryPlayersReadTest... "+"idCountry:" +idCountry+"\n\n");
		Call<CustomRequRespObject<Player>> call = countryApi.readCountryPlayers(idCountry);

		try {
			retrofit.Response<CustomRequRespObject<Player>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Player> customRequRespPlayer = response.body();
				if(customRequRespPlayer.getStatus().getCode() == HttpStatus.SC_OK) {
					System.out.println(customRequRespPlayer.getStatus());
					Iterator<Player> iterator = customRequRespPlayer.getCollection().iterator();
					int i=1;
					while (iterator.hasNext()) {
						System.out.println(i++ +": "+ iterator.next().getName());
					}
				}
				else
					System.out.println(customRequRespPlayer.getStatus());

				return true;
			}
			else {
				System.out.println(response.message());
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Confederation countryConfederationReadTest(int idCountry) {
		System.out.println("\ncountryConfederationReadTest... idCountry = " + idCountry + "\n\n");
		Call<CustomRequRespObject<Confederation>> call = countryApi.readCountryConfederation(idCountry);
		try {
			retrofit.Response<CustomRequRespObject<Confederation>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Confederation> customRequRespConfederation = response.body();
				System.out.println(customRequRespConfederation.getStatus());
				System.out.println(customRequRespConfederation.getObject());
				return customRequRespConfederation.getObject();
			}
			System.out.println(response.message());
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	public boolean doAllTests() {
		System.out.println("\n\n****CountryTest****\n");
		if (countryCreateTest("New Zealand", 151, 5) && 				//ok
			countryCreateTest("New Zealand", 151, 5) && 				//constraint exception
			countryCreateTest(validCountryToInsert) && 					//ok
			countryCreateTest(invalidCountryToInsert) && 				//constraint exception
			countryReadTest(1) != null && 								//ok
			countryReadTest(150) == null && 							//not exists
			countryUpdateTest(48, "New Zealand_Updated") && 			//ok
			countryUpdateTest(48, "Aruba") && 							//constraint exception
			countryListTest() && 										//ok
			countryFindByNameTest("New Zealand_Updated") != null && 	//ok
			countryFindByNameTest("Unexisting country") == null && 		//not exists
			countryDeleteTest(48) && 									//ok
			countryDeleteTest(900) &&									//not exists
			countryTeamsReadTest(1) && 									//ok
			countryPlayersReadTest(15) && 								//ok
			countryConfederationReadTest(1) != null) {					//ok
			System.out.println("Country's tests successful!");
			return true;
		} else {
			System.out.println("Error in Country's tests");
			return false;
		}
	}
*/
	public void createPlayerApi() {
		playerTest.playerApi = ServiceGenerator.createService(PlayerApi.class, getToken());
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
}