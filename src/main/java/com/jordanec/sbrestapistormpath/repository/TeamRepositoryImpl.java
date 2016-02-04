package com.jordanec.sbrestapistormpath.repository;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.jordanec.sbrestapistormpath.model.Team;

public class TeamRepositoryImpl implements EntityRepositoryCustom<Team>{
	@PersistenceContext
    private EntityManager em;

	@Transactional
	@Override
	public Team update(Team team) {
		return getEm().merge(team);
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Collection<Team> findOlderThan(int age) {
		System.out.println("Not supported");
		return null;
	}

}
