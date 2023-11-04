package gov.va.vba.framework.services;

import gov.va.vba.framework.domain.entities.Person;

public interface VeteranSession {

	Person findPerson(Long partId);
	Person findPerson(String ssn);
	String getCorpXMLDataStream(String ssn);
}
