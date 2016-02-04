package com.jordanec.sbrestapistormpath.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.jordanec.sbrestapistormpath.model.Confederation;
import com.jordanec.sbrestapistormpath.model.Player;
import com.jordanec.sbrestapistormpath.model.Country;

public class CountryRepositoryImpl implements EntityRepositoryCustom<Country>{
	
	@PersistenceContext
    private EntityManager em;
	
	@Transactional
	@Override
	public Country update(Country country) {
		Country countryOld = getEm().find(Country.class, country.getIdCountry());
		countryOld.setName(country.getName());
		countryOld.setPositionRankingFifa(country.getPositionRankingFifa());
		return getEm().merge(countryOld);
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Collection<Country> findOlderThan(int age) {
		System.out.println("Not supported");
		return null;
	}

}
