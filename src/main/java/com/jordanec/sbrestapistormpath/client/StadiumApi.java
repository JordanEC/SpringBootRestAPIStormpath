package com.jordanec.sbrestapistormpath.client;

import java.util.Collection;

import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.util.Constants;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.DELETE;

public interface StadiumApi {
	public static final String MAIN_PATH = "/"+Constants.API_VERSION+"/"+Constants.STADIUMS_PATH;
	
	@POST(MAIN_PATH)
	Call<CustomRequRespObject<Stadium>> createStadium(@Body CustomRequRespObject<Stadium> customRequRespStadium);
	
	@GET(MAIN_PATH+"/{idStadium}")
	Call<CustomRequRespObject<Stadium>> readStadium(@Path("idStadium") int idStadium);
	
	@GET(MAIN_PATH+"/"+Constants.NAME_VARIABLE+"={"+Constants.NAME_VARIABLE+"}")
	Call<CustomRequRespObject<Stadium>> findByName(@Path(Constants.NAME_VARIABLE) String name);
	
	@PUT(MAIN_PATH+"/{idStadium}")
	Call<CustomRequRespObject<Stadium>> updateStadium(@Body CustomRequRespObject<Stadium> customRequRespStadium, @Path("idStadium") int idStadium);
	
	@DELETE(MAIN_PATH+"/{idStadium}")
	Call<CustomRequRespObject<Stadium>> deleteStadium(@Path("idStadium") int idStadium);
	
	@GET(MAIN_PATH)
	Call<CustomRequRespObject<Stadium>> listStadiums();
	
	@GET(MAIN_PATH+"/{idStadium}/"+Constants.TEAMS_PATH)
	Call<CustomRequRespObject<Team>> readStadiumTeams(@Path("idStadium") int idStadium);
	
	
}
