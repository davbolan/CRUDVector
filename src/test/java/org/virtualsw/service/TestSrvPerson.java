package org.virtualsw.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import org.mockito.junit.MockitoJUnitRunner;
import org.virtualsw.dao.DAOPerson;
import org.virtualsw.dao.utils.Utils;
import org.virtualsw.model.Address;
import org.virtualsw.model.Birth;
import org.virtualsw.model.Person;
import org.virtualsw.service.SrvPerson;

@RunWith(MockitoJUnitRunner.class)
public class TestSrvPerson {
	
	@Mock 
	DAOPerson daoPerson;
	
	@InjectMocks 
	Utils utils = new Utils();
	
	@Test
	public void testObjectToJSONString() {
		Birth birth = new Birth("12.12.12", "españa");
		Address address = new Address("Sevilla", "2", "2B", "28014", "espa�a", "madrid", "madrid");
		
		Person person = new Person("12345","nombre", "apellido1", "apellido2", birth, address);
		
		String result = utils.objectToJSONString(person);
		
		Person personResult = (Person) utils.JSONStringToObject(result, Person.class);
		
		Assert.assertEquals(person, personResult);
		
		System.out.println(result);

	}
}
