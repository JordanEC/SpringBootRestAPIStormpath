package com.jordanec.sbrestapistormpath.repository;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.jordanec.sbrestapistormpath.model.Sponsor;

public class SponsorRepositoryImpl implements EntityRepositoryCustom<Sponsor>{
	@PersistenceContext
    private EntityManager em;
	
	@Transactional
	@Override
	public Sponsor update(Sponsor sponsor) {
		return getEm().merge(sponsor);
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Collection<Sponsor> findOlderThan(int age) {
		System.out.println("Not supported");
		return null;
	}

}
