package org.virtualsw.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.virtualsw.dao.DAOPerson;
import org.virtualsw.model.Person;
import org.virtualsw.model.Persons;

@Path("persons")
public class SrvPerson {

	DAOPerson daoPerson = new DAOPerson();
	
	@PUT
	@Path("/")
    @Produces({ MediaType.APPLICATION_JSON})
	public Person addPerson(Person person) {
    	Person personOut = daoPerson.addPerson(person);
		return personOut;
	}

	@DELETE
	@Path("/{documentId}")
    @Produces({ MediaType.APPLICATION_JSON})
	public void deletePerson(@PathParam("documentId") String documentId) {
    	daoPerson.deletePerson(documentId);
	}
	

	@POST
	@Path("/{documentId}")
    @Produces({ MediaType.APPLICATION_JSON})
	public Person modifyPerson(@PathParam("documentId") String documentId, Person person) {
		person.setDocumentId(documentId);
		Person personOut = daoPerson.modifyPerson(person);
		return personOut;
	}
	

	@GET
	@Path("/{documentId}")
    @Produces({ MediaType.APPLICATION_JSON})
	public Person getPerson(@PathParam("documentId") String documentId) {
    	Person personOut = daoPerson.getPerson(documentId);
		return personOut;
	}

	@GET
	@Path("/")
    @Produces({ MediaType.APPLICATION_JSON})
	public Persons listPersons() {
    	Persons persons = daoPerson.listPersons();
		return persons;
	}
}
