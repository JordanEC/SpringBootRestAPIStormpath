package com.jordanec.sbrestapistormpath.service;

import java.util.Collection;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.repository.StadiumRepository;

@Service
public class StadiumServiceImpl implements StadiumService {
	@Autowired
	StadiumRepository stadiumRepository;
	
	@Override
	public CustomRequRespObject<Stadium> createStadium(CustomRequRespObject<Stadium> customRequRespStadium){
		try {
			if(customRequRespStadium.isSingleObject())
				customRequRespStadium.setObject(stadiumRepository.save(customRequRespStadium.getObject()));
			else
				customRequRespStadium.setCollection((Collection<Stadium>) stadiumRepository.save(customRequRespStadium.getCollection()));
			
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_CREATED, "Stadium has been created successfully"));
		}catch(Exception e) {
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespStadium;
	}

	@Override
	public CustomRequRespObject<Stadium> readStadium(int id) {
		CustomRequRespObject<Stadium> customRequRespstadium = new CustomRequRespObject<Stadium>(true);
		try {
			Stadium stadium = stadiumRepository.findOne(id);
			if (stadium == null)
				customRequRespstadium.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Stadium not found"));
				
			else {
				customRequRespstadium.setStatus(new Status(HttpStatus.SC_OK, "Stadium found"));
				customRequRespstadium.setObject(stadium);
			}
		} catch (IllegalArgumentException e) {
			customRequRespstadium.setStatus(new Status(HttpStatus.SC_BAD_REQUEST, e.getMessage()));
		}
		return customRequRespstadium;
	}
	
	@Override
	public CustomRequRespObject<Stadium> findByName(String name) {
		CustomRequRespObject<Stadium> customRequRespstadium = new CustomRequRespObject<Stadium>(true);
		Stadium stadium = stadiumRepository.findByName(name);
		if (stadium == null)
			customRequRespstadium.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Stadium not found"));
		else {
			customRequRespstadium.setStatus(new Status(HttpStatus.SC_OK, "Stadium found"));
			customRequRespstadium.setObject(stadium);
		}
		return customRequRespstadium;
	}

	@Override
	public CustomRequRespObject<Stadium> updateStadium(CustomRequRespObject<Stadium> customRequRespstadium, int id) {
		try {
			Stadium stadium = stadiumRepository.update(customRequRespstadium.getObject());
			customRequRespstadium.setStatus(new Status(HttpStatus.SC_OK, "Stadium updated Successfully"));
			customRequRespstadium.setObject(stadium);
		} catch (Exception e) {
			customRequRespstadium.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespstadium;
	}

	@Override
	public CustomRequRespObject<Stadium> deleteStadium(int id) {
		CustomRequRespObject<Stadium> customRequRespstadium = new CustomRequRespObject<Stadium>(true);
		Stadium stadiumToDelete, stadiumDeleted = null;
		
		try {
			stadiumToDelete = stadiumRepository.findOne(id);
			if (stadiumToDelete == null)
				customRequRespstadium.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Stadium not found"));
			else {
				stadiumDeleted = new Stadium(stadiumToDelete.getName());
				stadiumDeleted.setIdStadium(stadiumToDelete.getIdStadium());
				stadiumDeleted.setCapacity(stadiumToDelete.getCapacity());
				stadiumRepository.delete(stadiumToDelete);
				customRequRespstadium.setStatus(new Status(HttpStatus.SC_OK, "Stadium deleted Successfully"));
				customRequRespstadium.setObject(stadiumDeleted);
			}
		} catch (Exception e) {
			customRequRespstadium.setObject(stadiumDeleted);
			customRequRespstadium.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		return customRequRespstadium;
	}

	@Override
	public CustomRequRespObject<Stadium> listStadiums() {
		CustomRequRespObject<Stadium> customRequRespStadium;
		Collection<Stadium> stadiums = Lists.newArrayList(stadiumRepository.findAll());
		Status status;
		if(stadiums.isEmpty())
			status = new Status(HttpStatus.SC_NO_CONTENT, "No Stadiums found");
		else
			status = new Status(HttpStatus.SC_OK, stadiums.size()+" Stadiums found");
		
		customRequRespStadium = new CustomRequRespObject<>(stadiums, status, false);
		return customRequRespStadium;
	}

	@Override
	public CustomRequRespObject<Team> readStadiumTeams(int id) {
		CustomRequRespObject<Team> customRequRespStadium = new CustomRequRespObject<>();
		customRequRespStadium.setSingleObject(false);
		Collection<Team> teams;
		Stadium stadium = stadiumRepository.findOne(id);

		if(stadium == null) {
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Stadium found"));
			return customRequRespStadium;
		}
		
		teams = stadium.getTeams();
		
		if(teams.isEmpty())
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Teams found"));
		else
			customRequRespStadium.setStatus(new Status(HttpStatus.SC_OK, teams.size()+" Teams found"));
		
		customRequRespStadium.setCollection(teams);
		return customRequRespStadium;
	}
	
	/*
	 * private boolean validAccount(HttpServletRequest req) { return
	 * AccountResolver.INSTANCE.getAccount(req) != null ? true : false; }
	 */

}
