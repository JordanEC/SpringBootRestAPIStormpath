package com.jordanec.sbrestapistormpath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.http.HttpStatus;
import com.jordanec.sbrestapistormpath.client.ConfederationApi;
import com.jordanec.sbrestapistormpath.client.ServiceGenerator;
import com.jordanec.sbrestapistormpath.model.Confederation;
import com.jordanec.sbrestapistormpath.model.Country;
import com.jordanec.sbrestapistormpath.model.CustomRequRespObject;
import com.jordanec.sbrestapistormpath.model.Token;
import retrofit.Call;

public class ConfederationTest {
	private ConfederationApi confederationApi;
	private static ConfederationTest confederationTest;
	private Token token;
	private static Collection<Confederation> validConfederationToInsert, invalidConfederationToInsert;

	protected ConfederationTest() {}

	public static ConfederationTest getInstance(Token token) {
		if (confederationTest == null) {
			confederationTest = new ConfederationTest();
			confederationTest.setToken(token);
			confederationTest.createConfederationApi();
			validConfederationToInsert = new ArrayList<>();
			validConfederationToInsert.add(new Confederation("New 1.1"));
			validConfederationToInsert.add(new Confederation("New 1.2"));
			validConfederationToInsert.add(new Confederation("New 1.3"));
			validConfederationToInsert.add(new Confederation("New 1.4"));
			validConfederationToInsert.add(new Confederation("New 1.5"));
			invalidConfederationToInsert = new ArrayList<>();
			invalidConfederationToInsert.add(new Confederation("New 2.1"));
			invalidConfederationToInsert.add(new Confederation("New 2.2"));
			invalidConfederationToInsert.add(new Confederation("New 2.3"));
			invalidConfederationToInsert.add(new Confederation("New 2.4"));
			invalidConfederationToInsert.add(new Confederation("New 2.4"));
			
		}
		return confederationTest;
	}

	public static ConfederationTest getInstance() {
		return confederationTest;
	}

	public Confederation confederationReadTest(int idConfederation) {
		System.out.println("\nconfederationReadTest... idConfederation = " + idConfederation + "\n\n");
		Call<CustomRequRespObject<Confederation>> call = confederationApi.readConfederation(idConfederation);
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
	
	private boolean confederationCreateTest(String name) {
		System.out.println("\nconfederationCreateTest... name = " + name + "\n");
		CustomRequRespObject<Confederation> customRequRespConfederation = 
				new CustomRequRespObject<>(new Confederation(name), true);
		Call<CustomRequRespObject<Confederation>> call = confederationApi.createConfederation(customRequRespConfederation);
		try {
			retrofit.Response<CustomRequRespObject<Confederation>> response = call.execute();
			if (response.isSuccess()) {
				customRequRespConfederation = response.body();
				System.out.println(customRequRespConfederation.getStatus());
				System.out.println(customRequRespConfederation.getObject());
				return true;
			}
			System.out.println(response.message());
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean confederationCreateTest(Collection<Confederation> collection) {
		System.out.println("\nconfederationCreateTest... collection = " + collection.toString() + "\n");
		CustomRequRespObject<Confederation> customRequRespConfederation = 
				new CustomRequRespObject<>(collection, false);
		Call<CustomRequRespObject<Confederation>> call = confederationApi.createConfederation(customRequRespConfederation);
		try {
			retrofit.Response<CustomRequRespObject<Confederation>> response = call.execute();
			if (response.isSuccess()) {
				customRequRespConfederation = response.body();
				System.out.println(customRequRespConfederation.getStatus());
				System.out.println(customRequRespConfederation.getCollection());
				return true;
			}
			System.out.println(response.message());
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean confederationUpdateTest(int idConfederation, String name) {
		System.out.println("\n\nconfederationUpdateTest..."+"idConfederation:"+ idConfederation+" to name: "+ name + "\n\n");
		CustomRequRespObject<Confederation> customRequRespConfederation = new CustomRequRespObject<>(true);
		Confederation confederation = confederationReadTest(idConfederation);
		if(confederation == null) {
			System.out.println("Confederation doesn't exists");
			return true;
		}
		confederation.setName(name);
		customRequRespConfederation.setObject(confederation);
		Call<CustomRequRespObject<Confederation>> call = confederationApi.updateConfederation(customRequRespConfederation, idConfederation);

		try {
			retrofit.Response<CustomRequRespObject<Confederation>> response = call.execute();
			if (response.isSuccess()) {
				customRequRespConfederation = response.body();
				System.out.println(customRequRespConfederation.getStatus());
				System.out.println(customRequRespConfederation.getObject());
				return true;
			}
			System.out.println(response.message());
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean confederationDeleteTest(int idConfederation) {
		System.out.println("\n\nconfederationDeleteTest... idConfederation: "+idConfederation+"\n\n");
		Call<CustomRequRespObject<Confederation>> call = confederationApi.deleteConfederation(idConfederation);

		try {
			retrofit.Response<CustomRequRespObject<Confederation>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Confederation> customRequRespConfederation = response.body();
				System.out.println(customRequRespConfederation.getStatus());
				System.out.println(customRequRespConfederation.getObject());
				return true;
			}
			System.out.println(response.message());
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean confederationListTest() {
		System.out.println("\n\nconfederationListTest...\n\n");
		Call<CustomRequRespObject<Confederation>> call = confederationApi.listConfederations();

		try {
			retrofit.Response<CustomRequRespObject<Confederation>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Confederation> customRequRespConfederation = response.body();
				if(customRequRespConfederation.getStatus().getCode() == HttpStatus.SC_OK) {
					System.out.println(customRequRespConfederation.getStatus());
					Iterator<Confederation> iterator = customRequRespConfederation.getCollection().iterator();
					Confederation confederation;
					while (iterator.hasNext()) {
						confederation = iterator.next();
						System.out.println(confederation);
					}
				}
				else
					System.out.println(customRequRespConfederation.getStatus());
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

	public Confederation confederationFindByNameTest(String name) {
		System.out.println("\n\nFindByNameTest..."+"name: "+name+"\n\n");
		Call<CustomRequRespObject<Confederation>> call = confederationApi.findByName(name);

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

	public boolean confederationCountriesReadTest(int idConfederation) {
		System.out.println("\n\nconfederationCountriesReadTest..."+"idConfederation: "+idConfederation+"\n\n");
		Call<CustomRequRespObject<Country>> call = confederationApi.readConfederationCountries(idConfederation);

		try {
			retrofit.Response<CustomRequRespObject<Country>> response = call.execute();
			if (response.isSuccess()) {
				CustomRequRespObject<Country> customRequRespCountry = response.body();
				if(customRequRespCountry.getStatus().getCode() == HttpStatus.SC_OK) {
					System.out.println(customRequRespCountry.getStatus());
					Iterator<Country> iterator = customRequRespCountry.getCollection().iterator();
					int i=1;
					while (iterator.hasNext()) {
						System.out.println(i++ +": "+ iterator.next().getName());
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
	
	public boolean doAllTests() {
		System.out.println("\n\n****ConfederationTest****\n");
		if (confederationCreateTest("OFC") &&									//ok
			confederationCreateTest("CONCACAF") &&								//constraint exception
			confederationCreateTest(validConfederationToInsert) &&				//ok
			confederationCreateTest(invalidConfederationToInsert) &&			//constraint exception
			confederationReadTest(1) != null &&									//ok
			confederationReadTest(25) == null &&								//not exists
			confederationUpdateTest(6, "OFC_Updated") &&						//ok
			confederationUpdateTest(1, "CONMEBOL") &&							//constraint exception
			confederationUpdateTest(25, "Some confederation's name") &&			//not exists
			confederationListTest() &&											//ok
			confederationFindByNameTest("Unexisting confederation") == null &&	//not exists
			confederationFindByNameTest("OFC_Updated") != null &&				//ok
			confederationDeleteTest(25) &&										//not exists
			confederationDeleteTest(1) &&										//can't be deleted
			confederationDeleteTest(6) &&										//ok
			confederationCountriesReadTest(25) &&								//not exists
			confederationCountriesReadTest(1)) {								//ok

			System.out.println("Confederation's test successful! ");
			return true;
		} else {
			System.out.println("Error during Confederation's test");
			return false;
		}
	}

	public void createConfederationApi() {
		confederationTest.confederationApi = ServiceGenerator.createService(ConfederationApi.class, getToken());
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
	
}
