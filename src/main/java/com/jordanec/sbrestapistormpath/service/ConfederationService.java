package com.jordanec.sbrestapistormpath.service;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import com.jordanec.sbrestapistormpath.model.Confederation;
import com.jordanec.sbrestapistormpath.model.Country;
import com.jordanec.sbrestapistormpath.model.CustomRequRespObject;
import com.jordanec.sbrestapistormpath.model.Status;

public interface ConfederationService {
	CustomRequRespObject<Confederation> createConfederation(CustomRequRespObject<Confederation> customRequRespconfederation);
	CustomRequRespObject<Confederation> readConfederation(int id);
	CustomRequRespObject<Confederation> findByName(String name);
	CustomRequRespObject<Confederation> updateConfederation(CustomRequRespObject<Confederation> customRequRespconfederation, int id);
	CustomRequRespObject<Confederation> deleteConfederation(int id);
	CustomRequRespObject<Confederation> listConfederations();
	CustomRequRespObject<Country> readConfederationCountries(int id);
}