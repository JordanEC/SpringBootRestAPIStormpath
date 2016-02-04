package com.jordanec.sbrestapistormpath.service;

import java.util.Collection;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.jordanec.sbrestapistormpath.model.Confederation;
import com.jordanec.sbrestapistormpath.model.Country;
import com.jordanec.sbrestapistormpath.model.CustomRequRespObject;
import com.jordanec.sbrestapistormpath.model.Status;
import com.jordanec.sbrestapistormpath.repository.ConfederationRepository;

@Service
public class ConfederationServiceImpl implements ConfederationService {
	@Autowired
	ConfederationRepository confederationRepository;
	
	@Override
	public CustomRequRespObject<Confederation> createConfederation(CustomRequRespObject<Confederation> customRequRespConfederation){
		try {
			if(customRequRespConfederation.isSingleObject())
				customRequRespConfederation.setObject(confederationRepository.save(customRequRespConfederation.getObject()));
			else
				customRequRespConfederation.setCollection((Collection<Confederation>) confederationRepository.save(customRequRespConfederation.getCollection()));
			
			customRequRespConfederation.setStatus(new Status(HttpStatus.SC_CREATED, "Confederation has been created successfully"));
		}catch(Exception e) {
			customRequRespConfederation.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespConfederation;
	}

	@Override
	public CustomRequRespObject<Confederation> readConfederation(int id) {
		CustomRequRespObject<Confederation> customRequRespconfederation = new CustomRequRespObject<Confederation>(true);
		try {
			Confederation confederation = confederationRepository.findOne(id);
			if (confederation == null)
				customRequRespconfederation.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Confederation not found"));
				
			else {
				customRequRespconfederation.setStatus(new Status(HttpStatus.SC_OK, "Confederation found"));
				customRequRespconfederation.setObject(confederation);
			}
		} catch (IllegalArgumentException e) {
			customRequRespconfederation.setStatus(new Status(HttpStatus.SC_BAD_REQUEST, e.getMessage()));
		}
		return customRequRespconfederation;
	}
	
	@Override
	public CustomRequRespObject<Confederation> findByName(String name) {
		CustomRequRespObject<Confederation> customRequRespconfederation = new CustomRequRespObject<Confederation>(true);
		Confederation confederation = confederationRepository.findByName(name);
		if (confederation == null)
			customRequRespconfederation.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Confederation not found"));
		else {
			customRequRespconfederation.setStatus(new Status(HttpStatus.SC_OK, "Confederation found"));
			customRequRespconfederation.setObject(confederation);
		}
		return customRequRespconfederation;
	}

	@Override
	public CustomRequRespObject<Confederation> updateConfederation(CustomRequRespObject<Confederation> customRequRespconfederation, int id) {
		try {
			Confederation confederation = confederationRepository.update(customRequRespconfederation.getObject());
			customRequRespconfederation.setStatus(new Status(HttpStatus.SC_OK, "Confederation updated Successfully"));
			customRequRespconfederation.setObject(confederation);
		} catch (Exception e) {
			customRequRespconfederation.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespconfederation;
	}

	@Override
	public CustomRequRespObject<Confederation> deleteConfederation(int id) {
		CustomRequRespObject<Confederation> customRequRespconfederation = new CustomRequRespObject<Confederation>(true);
		Confederation confederationToDelete, confederationDeleted = null;
		
		try {
			confederationToDelete = confederationRepository.findOne(id);
			if (confederationToDelete == null)
				customRequRespconfederation.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Confederation not found"));
			else {
				confederationDeleted = new Confederation(confederationToDelete.getName());
				confederationDeleted.setIdConfederation(confederationToDelete.getIdConfederation());
				confederationDeleted.setTotalCountries(confederationToDelete.getTotalCountries());
				confederationRepository.delete(confederationToDelete);
				customRequRespconfederation.setStatus(new Status(HttpStatus.SC_OK, "Confederation deleted Successfully"));
				customRequRespconfederation.setObject(confederationDeleted);
			}
		} catch (Exception e) {
			customRequRespconfederation.setObject(confederationDeleted);
			customRequRespconfederation.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		return customRequRespconfederation;
	}

	@Override
	public CustomRequRespObject<Confederation> listConfederations() {
		CustomRequRespObject<Confederation> customRequRespConfederation;
		Collection<Confederation> confederations = Lists.newArrayList(confederationRepository.findAll());
		Status status;
		if(confederations.isEmpty())
			status = new Status(HttpStatus.SC_NO_CONTENT, "No Confederations found");
		else
			status = new Status(HttpStatus.SC_OK, confederations.size()+" Confederations found");
		
		customRequRespConfederation = new CustomRequRespObject<>(confederations, status, false);
		return customRequRespConfederation;
	}

	@Override
	public CustomRequRespObject<Country> readConfederationCountries(int id) {
		CustomRequRespObject<Country> customRequRespObject = new CustomRequRespObject<>();
		customRequRespObject.setSingleObject(false);
		Collection<Country> countries;
		Confederation confederation = confederationRepository.findOne(id);

		if(confederation == null) {
			customRequRespObject.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Confederation found"));
			return customRequRespObject;
		}
		
		countries = confederation.getCountries();
		
		if(countries.isEmpty())
			customRequRespObject.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Countries found"));
		else
			customRequRespObject.setStatus(new Status(HttpStatus.SC_OK, countries.size()+" Countries found"));
		
		customRequRespObject.setCollection(countries);
		return customRequRespObject;
	}
	
	/*
	 * private boolean validAccount(HttpServletRequest req) { return
	 * AccountResolver.INSTANCE.getAccount(req) != null ? true : false; }
	 */

}
