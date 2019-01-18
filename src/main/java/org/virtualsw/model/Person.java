package org.virtualsw.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	
	@XmlElement(name="documentId")
	private String documentId;
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="firstSurname")
	private String firstSurname;
	
	@XmlElement(name="secondSurname")
	private String secondSurname;
	
	@XmlElement(name="birth")
	private Birth birth;
	
	@XmlElement(name="address")
	private Address address;
	
	public Person(){}
	
	public Person(String documentId, String name, String firstSurname, String secondSurname, Birth birth,Address address) {
		this.documentId 	= documentId;
		this.name 			= name;
		this.firstSurname 	= firstSurname;
		this.secondSurname 	= secondSurname;
		this.birth 			= birth;
		this.address 		= address;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public Birth getBirth() {
		return birth;
	}

	public void setBirth(Birth birth) {
		this.birth = birth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	@Override
	public boolean equals(Object o) {
	    // self check
	    if (this == o)
	        return true;
	    // null check
	    if (o == null)
	        return false;
	    // type check and cast
	    if (getClass() != o.getClass())
	        return false;
	    Person person = (Person) o;
	    // field comparison
	    return Objects.equals(documentId, person.documentId)
	            && Objects.equals(name, person.name)
	            && Objects.equals(firstSurname, person.firstSurname)
	            && Objects.equals(secondSurname, person.secondSurname)
	            && Objects.equals(birth, person.birth)
	            && Objects.equals(address, person.address);
	}
	

	
}
