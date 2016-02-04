package com.jordanec.sbrestapistormpath.service;

import com.jordanec.sbrestapistormpath.model.*;

public interface StadiumService {
	CustomRequRespObject<Stadium> createStadium(CustomRequRespObject<Stadium> customRequRespStadium);
	CustomRequRespObject<Stadium> readStadium(int id);
	CustomRequRespObject<Stadium> findByName(String name);
	CustomRequRespObject<Stadium> updateStadium(CustomRequRespObject<Stadium> customRequRespStadium, int id);
	CustomRequRespObject<Stadium> deleteStadium(int id);
	CustomRequRespObject<Stadium> listStadiums();
	CustomRequRespObject<Team> readStadiumTeams(int id);
}