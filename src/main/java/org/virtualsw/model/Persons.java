package org.virtualsw.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {
	
	@XmlElement(name="persons")
	private List<Person> listPersons;

	public Persons(){};

	public Persons(List<Person> listPersons){
		this.listPersons = listPersons;
	}
	
	public void setListPersons(List<Person> listPersons) {
		this.listPersons = listPersons;
	}
	
	public List<Person> getListPersons() {
		return this.listPersons ;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    List<Person> listPersonsObj = (List<Person>) o;

	    if(listPersonsObj.size() != this.listPersons.size()) 
	    	return false;
	    
	    for (int i=0; i < listPersonsObj.size(); i++) {
	        if (listPersonsObj.get(i).equals(this.listPersons.get(i))){
	        	return false;
	        }
	    }
	    
	    return true;
	}
}
