package com.jordanec.sbrestapistormpath;

import java.io.IOException;
import com.jordanec.sbrestapistormpath.client.AuthAPI;
import com.jordanec.sbrestapistormpath.client.ServiceGenerator;
import com.jordanec.sbrestapistormpath.model.Token;
import retrofit.Call;
import retrofit.Response;

public class AuthTest {
	private AuthAPI authAPI;
	private static AuthTest authTest; 
	private Token token;
	private static final String grant_type = "password";
	private static final String username = "";//Set it
	private static final String password = "";//Set it
	
	public AuthTest() {}
	
	public static AuthTest getInstance() {
		if(authTest == null) {
			authTest = new AuthTest();
			authTest.createAuthApi();
		}
		return authTest;
	}

	public void createAuthApi() {
		authTest.authAPI = ServiceGenerator.createService(AuthAPI.class);
	}	
	
	public boolean doAllTests() {
		setToken(authTest.authTokenTest());
		if(getToken() != null) {
			System.out.println("Authentication's test successful! ");
			return true;			
		}
		return false;
	}

	private Token authTokenTest() {
		Call<Token> call = authTest.authAPI.token(grant_type, username, password);
		Token token = null;
		try {
			Response<Token> response = call.execute();
			if (response.isSuccess()) {
				token = response.body();
				System.out.println(token);
			}
			else
				System.out.println(response.errorBody().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return token;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
}