package com.jordanec.sbrestapistormpath.service;

import java.util.Collection;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.jordanec.sbrestapistormpath.model.*;
import com.jordanec.sbrestapistormpath.repository.SponsorRepository;

@Service
public class SponsorServiceImpl implements SponsorService {
	@Autowired
	SponsorRepository sponsorRepository;
	
	@Override
	public CustomRequRespObject<Sponsor> createSponsor(CustomRequRespObject<Sponsor> customRequRespSponsor){
		try {
			if(customRequRespSponsor.isSingleObject())
				customRequRespSponsor.setObject(sponsorRepository.save(customRequRespSponsor.getObject()));
			else
				customRequRespSponsor.setCollection((Collection<Sponsor>) sponsorRepository.save(customRequRespSponsor.getCollection()));
			
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_CREATED, "Sponsor has been created successfully"));
		}catch(Exception e) {
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespSponsor;
	}

	@Override
	public CustomRequRespObject<Sponsor> readSponsor(int id) {
		CustomRequRespObject<Sponsor> customRequRespsponsor = new CustomRequRespObject<Sponsor>(true);
		try {
			Sponsor sponsor = sponsorRepository.findOne(id);
			if (sponsor == null)
				customRequRespsponsor.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Sponsor not found"));
				
			else {
				customRequRespsponsor.setStatus(new Status(HttpStatus.SC_OK, "Sponsor found"));
				customRequRespsponsor.setObject(sponsor);
			}
		} catch (IllegalArgumentException e) {
			customRequRespsponsor.setStatus(new Status(HttpStatus.SC_BAD_REQUEST, e.getMessage()));
		}
		return customRequRespsponsor;
	}
	
	@Override
	public CustomRequRespObject<Sponsor> findByName(String name) {
		CustomRequRespObject<Sponsor> customRequRespsponsor = new CustomRequRespObject<Sponsor>(true);
		Sponsor sponsor = sponsorRepository.findByName(name);
		if (sponsor == null)
			customRequRespsponsor.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Sponsor not found"));
		else {
			customRequRespsponsor.setStatus(new Status(HttpStatus.SC_OK, "Sponsor found"));
			customRequRespsponsor.setObject(sponsor);
		}
		return customRequRespsponsor;
	}

	@Override
	public CustomRequRespObject<Sponsor> updateSponsor(CustomRequRespObject<Sponsor> customRequRespsponsor, int id) {
		try {
			Sponsor sponsor = sponsorRepository.update(customRequRespsponsor.getObject());
			customRequRespsponsor.setStatus(new Status(HttpStatus.SC_OK, "Sponsor updated Successfully"));
			customRequRespsponsor.setObject(sponsor);
		} catch (Exception e) {
			customRequRespsponsor.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return customRequRespsponsor;
	}

	@Override
	public CustomRequRespObject<Sponsor> deleteSponsor(int id) {
		CustomRequRespObject<Sponsor> customRequRespsponsor = new CustomRequRespObject<Sponsor>(true);
		Sponsor sponsorToDelete, sponsorDeleted = null;
		
		try {
			sponsorToDelete = sponsorRepository.findOne(id);
			if (sponsorToDelete == null)
				customRequRespsponsor.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "Sponsor not found"));
			else {
				sponsorDeleted = new Sponsor(sponsorToDelete.getName());
				sponsorDeleted.setIdSponsor(sponsorToDelete.getIdSponsor());
				sponsorRepository.delete(sponsorToDelete);
				customRequRespsponsor.setStatus(new Status(HttpStatus.SC_OK, "Sponsor deleted Successfully"));
				customRequRespsponsor.setObject(sponsorDeleted);
			}
		} catch (Exception e) {
			customRequRespsponsor.setObject(sponsorDeleted);
			customRequRespsponsor.setStatus(new Status(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		return customRequRespsponsor;
	}

	@Override
	public CustomRequRespObject<Sponsor> listSponsors() {
		CustomRequRespObject<Sponsor> customRequRespSponsor;
		Collection<Sponsor> sponsors = Lists.newArrayList(sponsorRepository.findAll());
		Status status;
		if(sponsors.isEmpty())
			status = new Status(HttpStatus.SC_NO_CONTENT, "No Sponsors found");
		else
			status = new Status(HttpStatus.SC_OK, sponsors.size()+" Sponsors found");
		
		customRequRespSponsor = new CustomRequRespObject<>(sponsors, status, false);
		return customRequRespSponsor;
	}

	@Override
	public CustomRequRespObject<Team> readSponsorTeams(int id) {
		CustomRequRespObject<Team> customRequRespSponsor = new CustomRequRespObject<>();
		customRequRespSponsor.setSingleObject(false);
		Collection<Team> teams;
		Sponsor sponsor = sponsorRepository.findOne(id);

		if(sponsor == null) {
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Sponsor found"));
			return customRequRespSponsor;
		}
		
		teams = sponsor.getTeams();
		
		if(teams.isEmpty())
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Teams found"));
		else
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_OK, teams.size()+" Teams found"));
		
		customRequRespSponsor.setCollection(teams);
		return customRequRespSponsor;
	}
	
	@Override
	public CustomRequRespObject<Player> readSponsorPlayers(int id) {
		CustomRequRespObject<Player> customRequRespSponsor = new CustomRequRespObject<>();
		customRequRespSponsor.setSingleObject(false);
		Collection<Player> countries;
		Sponsor sponsor = sponsorRepository.findOne(id);

		if(sponsor == null) {
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NOT_FOUND, "No Sponsor found"));
			return customRequRespSponsor;
		}
		
		countries = sponsor.getPlayers();
		
		if(countries.isEmpty())
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_NO_CONTENT, "No Countries found"));
		else
			customRequRespSponsor.setStatus(new Status(HttpStatus.SC_OK, countries.size()+" Countries found"));
		
		customRequRespSponsor.setCollection(countries);
		return customRequRespSponsor;
	}
	
	
	/*
	 * private boolean validAccount(HttpServletRequest req) { return
	 * AccountResolver.INSTANCE.getAccount(req) != null ? true : false; }
	 */

}
