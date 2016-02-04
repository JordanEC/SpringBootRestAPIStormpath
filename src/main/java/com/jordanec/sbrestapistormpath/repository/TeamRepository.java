package com.jordanec.sbrestapistormpath.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jordanec.sbrestapistormpath.model.*;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer>, EntityRepositoryCustom<Team>{
	public Team findByName(String name);
	

	
}