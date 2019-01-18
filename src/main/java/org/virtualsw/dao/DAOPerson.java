package org.virtualsw.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.virtualsw.model.Address;
import org.virtualsw.model.Birth;
import org.virtualsw.model.Person;
import org.virtualsw.model.Persons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@SuppressWarnings("unchecked")
public class DAOPerson {

	
	private String PERSONS_FILE = "src/main/webapp/db/persons.json";

	/** Main methods**/
	public Person addPerson(Person person) {
        System.out.println("Se agrega nueva persona...");
        
        try {
        	JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(PERSONS_FILE ));
			JSONArray jsonPersons = (JSONArray) object;

			boolean exist = false;
			JSONObject jsonPerson = null;
			String strDocumentId  = null;
			for (int i = 0; i < jsonPersons.size() && !exist; i++) {
				jsonPerson 	= (JSONObject) jsonPersons.get(i);
				strDocumentId = (String) jsonPerson.get("documentId");

				exist = person.getDocumentId().equalsIgnoreCase(strDocumentId);
			}
			
			if(!exist) {
		        JSONObject personJson = generateJsonPerson(person);
		        jsonPersons.add(personJson);
		        
		        updatePersonsJsonFile(jsonPersons);

		        System.out.println("Persona agregada correctamente");
			}
			else {
		        System.out.println("No se ha añadido la persona porque ya existe");
		        person = null;
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
        
        return person;
              
    }

	public void deletePerson(String documentId) {
        System.out.println("Se va a borrar la persona con DNI " + documentId);
        try {
        	JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(PERSONS_FILE));
			JSONArray jsonPersons = (JSONArray) object;

			boolean exist = false;
			JSONObject jsonPerson = null;
			String strDocumentId  = null;
			for (int i = 0; i < jsonPersons.size() && !exist; i++) {
				jsonPerson 	= (JSONObject) jsonPersons.get(i);
				strDocumentId = (String) jsonPerson.get("documentId");

				exist = documentId.equalsIgnoreCase(strDocumentId);
			}
			
			if(exist) {
		        jsonPersons.remove(jsonPerson);
		        updatePersonsJsonFile(jsonPersons);

		        System.out.println("Persona eliminada correctamente");
			}
			else {
		        System.out.println("No se ha eliminado la persona porque no existe");
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
    }

	public Person modifyPerson(Person person) {
        System.out.println("Se va a modificar la persona con DNI " + person.getDocumentId());
        try {
        	JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(PERSONS_FILE));
			JSONArray jsonPersons = (JSONArray) object;

			boolean exist = false;
			JSONObject jsonPerson = null;
			String strDocumentId  = null;
			int pos = 0;
			for (; pos < jsonPersons.size() && !exist; pos++) {
				jsonPerson 	= (JSONObject) jsonPersons.get(pos);
				strDocumentId = (String) jsonPerson.get("documentId");

				exist = person.getDocumentId().equalsIgnoreCase(strDocumentId);
			}
			
			if(exist) {
		        JSONObject personJson = modifyJsonPerson(jsonPerson, person);
		        jsonPersons.set(pos-1, personJson);
		        updatePersonsJsonFile(jsonPersons);

		        System.out.println("Persona modificada correctamente");
			}
			else {
		        System.out.println("No se ha modificado la persona porque no existe");
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
        return person;
	}

	public Person getPerson(String documentId) {
        System.out.println("Se va a consultar la persona con DNI " + documentId);
	    Person person = null;
	    
	    try {
        	JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(PERSONS_FILE));
			JSONArray jsonPersons = (JSONArray) object;

			boolean exist = false;
			JSONObject jsonPerson = null;
			String strDocumentId  = null;
			int pos = 0;
			for (; pos < jsonPersons.size() && !exist; pos++) {
				jsonPerson 	= (JSONObject) jsonPersons.get(pos);
				strDocumentId = (String) jsonPerson.get("documentId");

				exist = documentId.equalsIgnoreCase(strDocumentId);
			}
			
			if(exist) {
		        person = generatePerson(jsonPerson);
		        System.out.println("Persona consultada correctamente");
			}
			else {
		        System.out.println("No se ha devuelto la persona porque no existe");
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	    
        return person;
	}

	public Persons listPersons() {
		System.out.println("Se va a consultar el listado de personas");
		Persons persons = null;
		List<Person> listPersons = new ArrayList<>();
	    
	    try {
        	JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(PERSONS_FILE));
			JSONArray jsonPersons = (JSONArray) object;

			boolean exist = false;
			JSONObject jsonPerson = null;
			
			for (int pos = 0; pos < jsonPersons.size() && !exist; pos++) {
				jsonPerson 	= (JSONObject) jsonPersons.get(pos);
				
				Person person = generatePerson(jsonPerson);
				listPersons.add(person);
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
	    if(!listPersons.isEmpty()) {
	    	persons = new Persons(listPersons);
	        System.out.println("Lista de personas generada correctamente");
	    }
	    else {
	        System.out.println("No hay resultados");
	    }
	    
		return persons;
	}
	
	
	
	/** Auxiliary methods**/
	private void updatePersonsJsonFile(JSONArray jsonPersons){
		try{
        	String prettyJson = toPrettyFormat(jsonPersons.toJSONString());
        	FileWriter file = new FileWriter(PERSONS_FILE);
            file.write(prettyJson);
            file.flush();
            file.close();
        	System.out.println(prettyJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private String toPrettyFormat(String jsonString) {
		JsonParser parser = new JsonParser();
		JsonArray json = parser.parse(jsonString).getAsJsonArray();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(json);
		
		return prettyJson;
	}
	
	private JSONObject modifyJsonPerson(JSONObject personJson, Person person) {
        personJson.replace("name", 			person.getName());
        personJson.replace("firstSurname", 	person.getFirstSurname());
        personJson.replace("secondSurname", person.getSecondSurname());
        
        JSONObject jsonBirth = (JSONObject) personJson.get("birth");
        if(null!=person.getBirth()) {
	        jsonBirth.put("country", person.getBirth().getCountry());
	        jsonBirth.replace("date", 	 person.getBirth().getDate());

        }

        JSONObject jsonAddress = (JSONObject) personJson.get("address");
        if(null!=person.getAddress()) {
            jsonAddress.replace("street"	, person.getAddress().getStreet());
            jsonAddress.replace("number"	, person.getAddress().getNumber());
            jsonAddress.replace("floor"		, person.getAddress().getFloor());
            jsonAddress.replace("zipCode"	, person.getAddress().getZipCode());
            jsonAddress.replace("country"	, person.getAddress().getCountry());
            jsonAddress.replace("region"	, person.getAddress().getRegion());
            jsonAddress.replace("city"		, person.getAddress().getCity());
        }
		return personJson;
	}
	
	private JSONObject generateJsonPerson(Person person) {
		JSONObject personJson = new JSONObject();
        personJson.put("documentId", 	person.getDocumentId());
        personJson.put("name", 			person.getName());
        personJson.put("firstSurname", 	person.getFirstSurname());
        personJson.put("secondSurname", person.getSecondSurname());
        
        JSONObject jsonBirth = new JSONObject();
        if(null!=person.getBirth()) {
	        jsonBirth.put("country", person.getBirth().getCountry());
	        jsonBirth.put("date", 	 person.getBirth().getDate());
	        personJson.put("birth", jsonBirth);
        }

        JSONObject jsonAddress = new JSONObject();
        if(null!=person.getAddress()) {
            jsonAddress.put("street"	, person.getAddress().getStreet());
            jsonAddress.put("number"	, person.getAddress().getNumber());
            jsonAddress.put("floor"		, person.getAddress().getFloor());
            jsonAddress.put("zipCode"	, person.getAddress().getZipCode());
            jsonAddress.put("country"	, person.getAddress().getCountry());
            jsonAddress.put("region"	, person.getAddress().getRegion());
            jsonAddress.put("city"		, person.getAddress().getCity());
        }

        personJson.put("address", jsonAddress);
        
        return personJson;
	}
	
	private Person generatePerson(JSONObject jsonPerson) {
		Person person 	= new Person();
		
		person.setDocumentId(	(String) jsonPerson.get("documentId"));
		person.setName(			(String)jsonPerson.get("name"));
		person.setFirstSurname(	(String)jsonPerson.get("firstSurname"));
		person.setSecondSurname((String)jsonPerson.get("secondSurname"));
		
		Birth birth = new Birth();
		JSONObject jsonBirth = (JSONObject) jsonPerson.get("birth");
		birth.setDate(		(String)jsonBirth.get("date"));
		birth.setCountry(	(String)jsonBirth.get("country"));
		person.setBirth(birth);
		
		Address address = new Address();
		JSONObject jsonAddress = (JSONObject) jsonPerson.get("address");
		address.setCity(	(String)jsonAddress.get("city"));
		address.setCountry(	(String)jsonAddress.get("country"));
		address.setFloor(	(String)jsonAddress.get("floor"));
		address.setNumber(	(String)jsonAddress.get("number"));
		address.setRegion(	(String)jsonAddress.get("region"));
		address.setStreet(	(String)jsonAddress.get("street"));
		address.setZipCode(	(String)jsonAddress.get("zipCode"));
		person.setAddress(address);
		
		return person;
	}
}
