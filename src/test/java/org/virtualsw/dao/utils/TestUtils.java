package org.virtualsw.dao.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import org.mockito.junit.MockitoJUnitRunner;
import org.virtualsw.dao.DAOPerson;
import org.virtualsw.model.Address;
import org.virtualsw.model.Birth;
import org.virtualsw.model.Person;
import org.virtualsw.service.SrvPerson;

@RunWith(MockitoJUnitRunner.class)
public class TestUtils {
	
	@Mock 
	DAOPerson daoPerson;
	
	@InjectMocks 
	SrvPerson srvPerson = new SrvPerson();
	
	@Test
	public void testAddPerson() {
		Birth birth = new Birth("12.12.12", "españa");
		Address address = new Address("Sevilla", "2", "2B", "28014", "espa�a", "madrid", "madrid");
		
		Person person = new Person("12345","nombre", "apellido1", "apellido2", birth, address);
		
		Mockito
		.when(daoPerson.addPerson((Person) any()))
		.thenReturn(person);
		
		Person personResult = srvPerson.addPerson(person);
		
		personResult.setDocumentId(personResult.getDocumentId());
	}
}
