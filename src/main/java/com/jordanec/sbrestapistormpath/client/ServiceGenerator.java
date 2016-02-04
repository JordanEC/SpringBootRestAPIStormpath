package com.jordanec.sbrestapistormpath.client;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.commons.codec.binary.Base64;

import com.jordanec.sbrestapistormpath.model.Token;
import com.jordanec.sbrestapistormpath.util.Constants;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

	private static OkHttpClient httpClient = new OkHttpClient();
	private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Constants.HOSTNAME)
			.addConverterFactory(JacksonConverterFactory.create());

	/*
	 * Creates a Retrofit <S> class service without interceptors
	 * 
	 */
	public static <S> S createService(Class<S> serviceClass) {
		return builder.build().create(serviceClass);
	}

	/*
	 * Creates a Retrofit <S> class service for clientId + secret and basic authentication
	 * 
	 */
	public static <S> S createService(Class<S> serviceClass, String clientId, String clientSecret, String usename,
			String password, String scope) {
		if (clientId != null && clientSecret != null) {
			String credentials = clientId + ":" + clientSecret;
			final String basic = "Basic " + Base64.encodeBase64String(credentials.getBytes());
			httpClient.interceptors().clear();
			httpClient.interceptors().add(new Interceptor() {
				@Override
				public Response intercept(Interceptor.Chain chain) throws IOException {
					Request original = chain.request();

					Request.Builder requestBuilder = original.newBuilder().header("Authorization", basic)
							.post(new FormEncodingBuilder().add("grant_type", "password").add("scope", scope)
									.add("username", usename).add("password", password).build());
					Request request = requestBuilder.build();
					return chain.proceed(request);
				}
			});
		}

		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(serviceClass);
	}

	/*
	 * Creates a Retrofit <S> class service for Stormpath's token autentication
	 * 
	 */
	public static <S> S createService(Class<S> serviceClass, Token token) {
		final String tokenHeader = token.getToken_type() + " " + token.getAccess_token();
		httpClient.interceptors().clear();
		httpClient.interceptors().add(new Interceptor() {
			@Override
			public Response intercept(Interceptor.Chain chain) throws IOException {
				Request original = chain.request();

				Request.Builder requestBuilder = original.newBuilder().header("Authorization", tokenHeader)
						.header("Authorization", tokenHeader)
						.method(original.method(), original.body());

				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		});

		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(serviceClass);
	}

	/*
	 * Creates a Retrofit <S> class service for basic authentication
	 * 
	 */
	public static <S> S createService(Class<S> serviceClass, String username, String password) {
		if (username != null && password != null) {
			String credentials = username + ":" + password;
			final String basic = "Basic " + Base64.encodeBase64String(credentials.getBytes());
			httpClient.interceptors().clear();
			httpClient.interceptors().add(new Interceptor() {
				@Override
				public Response intercept(Interceptor.Chain chain) throws IOException {
					Request original = chain.request();

					Request.Builder requestBuilder = original.newBuilder().header("Authorization", basic)
							.header("Content-Type", "applicaton/json").method(original.method(), original.body());

					Request request = requestBuilder.build();
					return chain.proceed(request);
				}
			});
		}

		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(serviceClass);
	}

	
	/*
	 * Creates a Retrofit <S> class service for oauth2 and basic authentication
	 * 
	 */
	public static <S> S createService(Class<S> serviceClass, LinkedHashMap<String, String> tokens) {
		String access_token = tokens.get("access_token");
		httpClient.interceptors().clear();
		httpClient.interceptors().add(new Interceptor() {
			@Override
			public Response intercept(Interceptor.Chain chain) throws IOException {
				Request original = chain.request();
				Request.Builder requestBuilder = original.newBuilder().header("Authorization", "bearer " + access_token)
						.header("Content-Type", "applicaton/json").method(original.method(), original.body());
				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		});

		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(serviceClass);
	}
}
