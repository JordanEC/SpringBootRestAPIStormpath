package com.jordanec.sbrestapistormpath.repository;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.jordanec.sbrestapistormpath.model.Confederation;

public class ConfederationRepositoryImpl implements EntityRepositoryCustom<Confederation>{
	
	@PersistenceContext
    private EntityManager em;
	
	@Transactional
	@Override
	public Confederation update(Confederation object) {
		Confederation confederation = getEm().merge(object);
		return confederation;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Collection<Confederation> findOlderThan(int age) {
			System.out.println("Not supported");
		return null;
	}

}
