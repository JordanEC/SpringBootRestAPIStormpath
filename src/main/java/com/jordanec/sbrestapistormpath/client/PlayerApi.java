package com.jordanec.sbrestapistormpath.client;

import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.util.Constants;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.DELETE;

public interface PlayerApi {
	public static final String MAIN_PATH = "/"+Constants.API_VERSION+"/"+Constants.PLAYERS_PATH;
	
	@Headers("Content-Type: application/json")
	@POST(MAIN_PATH)
	Call<CustomRequRespObject<Player>> createPlayer(@Body CustomRequRespObject<Sponsor> customRequRespPlayer);
	
	@Headers("Content-Type: application/json")
	@GET(MAIN_PATH+"/{idPlayer}")
	Call<CustomRequRespObject<Player>> readPlayer(@Path("idPlayer") int idPlayer);
	
	@Headers("Content-Type: application/json")
	@GET(MAIN_PATH+"/"+Constants.NAME_VARIABLE+"={"+Constants.NAME_VARIABLE+"}")
	Call<CustomRequRespObject<Player>> findByName(@Path(Constants.NAME_VARIABLE) String name);
	
	@Headers("Content-Type: application/json")
	@GET(MAIN_PATH+"/older={age}")
	CustomRequRespObject<Player> findOlderThan(@Path("age") int age);
	
	@Headers("Content-Type: application/json")
	@PUT(MAIN_PATH+"/{idPlayer}")
	Call<CustomRequRespObject<Player>> updatePlayer(@Body CustomRequRespObject<Sponsor> customRequRespPlayer, @Path("idPlayer") int idPlayer);
	
	@Headers("Content-Type: application/json")
	@DELETE(MAIN_PATH+"/{idPlayer}")
	Call<CustomRequRespObject<Player>> deletePlayer(@Path("idPlayer") int idPlayer);
	
	@Headers("Content-Type: application/json")
	@GET(MAIN_PATH)
	Call<CustomRequRespObject<Player>> listPlayers();
	
	@Headers("Content-Type: application/json")
	@GET(MAIN_PATH+"/{idPlayer}/"+Constants.PLAYER_PATH)
	Call<CustomRequRespObject<Team>> readPlayerTeam(@Path("idPlayer") int idPlayer);
	
	@Headers("Content-Type: application/json")
	@GET(MAIN_PATH+"/{idPlayer}/"+Constants.COUNTRY_PATH)
	Call<CustomRequRespObject<Country>> readPlayerCountry(@Path("idPlayer") int idPlayer);
	
	@Headers("Content-Type: application/json")
	@GET(MAIN_PATH+"/{idPlayer}/"+Constants.SPONSOR_PATH)
	Call<CustomRequRespObject<Sponsor>> readPlayerSponsor(@Path("idPlayer") int idPlayer);
}
