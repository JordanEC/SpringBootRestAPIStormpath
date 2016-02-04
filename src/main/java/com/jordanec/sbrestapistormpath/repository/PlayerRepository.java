package com.jordanec.sbrestapistormpath.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jordanec.sbrestapistormpath.model.*;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>, EntityRepositoryCustom<Player>{
	public Player findByName(String name);
}