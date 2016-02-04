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
import com.jordanec.sbrestapistormpath.client.StadiumApi;
import com.jordanec.sbrestapistormpath.model.*;
import retrofit.Call;

public class StadiumTest {
	private StadiumApi stadiumApi;
	private static StadiumTest stadiumTest;
	private Token token;
	private Collection<Stadium> validStadiumToInsert, invalidStadiumToInsert;

	protected StadiumTest(){}

	public static StadiumTest getInstance(Token token) {
		if (stadiumTest == null) {
			stadiumTest = new StadiumTest();
			stadiumTest.setToken(token);
			stadiumTest.createStadiumApi();
			/*stadiumTest.validStadiumToInsert = new ArrayList<>();
			stadiumTest.validStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 1.1", 500));	//new Stadium(confederationTest.confederationReadTest(1)
			stadiumTest.validStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 1.2", 501));
			stadiumTest.validStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 1.3", 502));
			stadiumTest.validStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 1.4", 503));
			stadiumTest.validStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 1.5", 504));
			stadiumTest.invalidStadiumToInsert = new ArrayList<>();
			stadiumTest.invalidStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 2.1", 505));
			stadiumTest.invalidStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 2.2", 506));
			stadiumTest.invalidStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 2.3", 507));
			stadiumTest.invalidStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 2.4", 508));
			stadiumTest.invalidStadiumToInsert.add(new Stadium(stadiumTest.confederation5, "New stadium 2.5", 508));	*/	
		}
		return stadiumTest;
	}

	public static StadiumTest getInstance() {
		return stadiumTest;
	}
	
	public Stadium stadiumReadTest(int idStadium) {
		System.out.println("\nstadiumReadTest... idStadium = " + idStadium + "\n\n");
		Call<CustomRequRespObject<Stadium>> call = stadiumApi.readStadium(idStadium);
		try {
			retrofit.Response<CustomRequRespObject<Stadium>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Stadium> customRequRespStadium = response.body();
				System.out.println(customRequRespStadium.getStatus());
				System.out.println(customRequRespStadium.getObject());
				return customRequRespStadium.getObject();
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
	public void createStadiumApi() {
		stadiumTest.stadiumApi = ServiceGenerator.createService(StadiumApi.class, getToken());
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
}