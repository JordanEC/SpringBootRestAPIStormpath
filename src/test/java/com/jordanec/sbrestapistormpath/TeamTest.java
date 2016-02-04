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
import com.jordanec.sbrestapistormpath.client.TeamApi;
import com.jordanec.sbrestapistormpath.model.*;
import retrofit.Call;

public class TeamTest {
	private TeamApi teamApi;
	private CountryTest countryTest;
	private static TeamTest teamTest;
	private Token token;
	private Collection<Team> validTeamToInsert, invalidTeamToInsert;
	private Country country;

	protected TeamTest(){}

	public static TeamTest getInstance(Token token, CountryTest countryTest) {
		if (teamTest == null) {
			teamTest = new TeamTest();
			teamTest.setToken(token);
			teamTest.createTeamApi();
			teamTest.countryTest = countryTest;
			teamTest.country = countryTest.countryReadTest(1);
			/*teamTest.validTeamToInsert = new ArrayList<>();
			teamTest.validTeamToInsert.add(new Team(teamTest.confederation5, "New team 1.1", 500));	//new Team(confederationTest.confederationReadTest(1)
			teamTest.validTeamToInsert.add(new Team(teamTest.confederation5, "New team 1.2", 501));
			teamTest.validTeamToInsert.add(new Team(teamTest.confederation5, "New team 1.3", 502));
			teamTest.validTeamToInsert.add(new Team(teamTest.confederation5, "New team 1.4", 503));
			teamTest.validTeamToInsert.add(new Team(teamTest.confederation5, "New team 1.5", 504));
			teamTest.invalidTeamToInsert = new ArrayList<>();
			teamTest.invalidTeamToInsert.add(new Team(teamTest.confederation5, "New team 2.1", 505));
			teamTest.invalidTeamToInsert.add(new Team(teamTest.confederation5, "New team 2.2", 506));
			teamTest.invalidTeamToInsert.add(new Team(teamTest.confederation5, "New team 2.3", 507));
			teamTest.invalidTeamToInsert.add(new Team(teamTest.confederation5, "New team 2.4", 508));
			teamTest.invalidTeamToInsert.add(new Team(teamTest.confederation5, "New team 2.5", 508));	*/	
		}
		return teamTest;
	}

	public static TeamTest getInstance() {
		return teamTest;
	}
	
	public Team teamReadTest(int idTeam) {
		System.out.println("\nteamReadTest... idTeam = " + idTeam + "\n\n");
		Call<CustomRequRespObject<Team>> call = teamApi.readTeam(idTeam);
		try {
			retrofit.Response<CustomRequRespObject<Team>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Team> customRequRespTeam = response.body();
				System.out.println(customRequRespTeam.getStatus());
				System.out.println(customRequRespTeam.getObject());
				return customRequRespTeam.getObject();
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
	public void createTeamApi() {
		teamTest.teamApi = ServiceGenerator.createService(TeamApi.class, getToken());
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
}