package com.jordanec.sbrestapistormpath.client;

import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.util.Constants;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.DELETE;

public interface TeamApi {
	public static final String MAIN_PATH = "/"+Constants.API_VERSION+"/"+Constants.TEAMS_PATH;
	
	@POST(MAIN_PATH)
	Call<CustomRequRespObject<Team>> createTeam(@Body CustomRequRespObject<Team> customRequRespTeam);
	
	@GET(MAIN_PATH+"/{idTeam}")
	Call<CustomRequRespObject<Team>> readTeam(@Path("idTeam") int idTeam);
	
	@GET(MAIN_PATH+"/"+Constants.NAME_VARIABLE+"={"+Constants.NAME_VARIABLE+"}")
	Call<CustomRequRespObject<Team>> findByName(@Path(Constants.NAME_VARIABLE) String name);
	
	@PUT(MAIN_PATH+"/{idTeam}")
	Call<CustomRequRespObject<Team>> updateTeam(@Body CustomRequRespObject<Team> customRequRespTeam, @Path("idTeam") int idTeam);
	
	@DELETE(MAIN_PATH+"/{idTeam}")
	Call<CustomRequRespObject<Team>> deleteTeam(@Path("idTeam") int idTeam);
	
	@GET(MAIN_PATH)
	Call<CustomRequRespObject<Team>> listTeams();
	
	@GET(MAIN_PATH+"/{idTeam}/"+Constants.STADIUMS_PATH)
	Call<CustomRequRespObject<Stadium>> readTeamStadiums(@Path("idTeam") int idTeam);
	
	@GET(MAIN_PATH+"/{idTeam}/"+Constants.PLAYERS_PATH)
	Call<CustomRequRespObject<Player>> readTeamPlayers(@Path("idTeam") int idTeam);
	
	@GET(MAIN_PATH+"/{idTeam}/"+Constants.COUNTRIES_PATH)
	Call<CustomRequRespObject<Country>> readTeamCountry(@Path("idTeam") int idTeam);
	
	@GET(MAIN_PATH+"/{idTeam}/"+Constants.CONFEDERATIONS_PATH)
	Call<CustomRequRespObject<Confederation>> readTeamConfederations(@Path("idTeam") int idTeam);
}
