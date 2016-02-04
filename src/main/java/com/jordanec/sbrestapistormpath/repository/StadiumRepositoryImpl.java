package com.jordanec.sbrestapistormpath.repository;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.jordanec.sbrestapistormpath.model.Stadium;

public class StadiumRepositoryImpl implements EntityRepositoryCustom<Stadium>{
	@PersistenceContext
    private EntityManager em;
	
	@Transactional
	@Override
	public Stadium update(Stadium stadium) {
		return getEm().merge(stadium);
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Collection<Stadium> findOlderThan(int age) {
		System.out.println("Not supported");
		return null;
	}

}
