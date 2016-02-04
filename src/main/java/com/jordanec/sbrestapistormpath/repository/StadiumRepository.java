package com.jordanec.sbrestapistormpath.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jordanec.sbrestapistormpath.model.*;


@Repository
public interface StadiumRepository extends CrudRepository<Stadium, Integer>, EntityRepositoryCustom<Stadium>{
	public Stadium findByName(String name);
	

	
}