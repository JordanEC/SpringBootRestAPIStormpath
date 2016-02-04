package com.jordanec.sbrestapistormpath.repository;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.jordanec.sbrestapistormpath.model.Player;

public class PlayerRepositoryImpl implements EntityRepositoryCustom<Player>{
	@PersistenceContext
    private EntityManager em;
	
	@Transactional
	@Override
	public Player update(Player player) {
		return getEm().merge(player);
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Player> findOlderThan(int age) {
		return (Collection<Player>) getEm()
				.createQuery("FROM Player where age > :age")
				.setParameter("age", age)
				.getResultList();
		
	}

}
