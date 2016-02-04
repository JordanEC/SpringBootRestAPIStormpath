package com.jordanec.sbrestapistormpath.repository;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;


public interface EntityRepositoryCustom<T> {
	//@PreAuthorize("#oauth2.hasScope('write')")
	T update(T entity);
	Collection<T> findOlderThan(int age);
}
