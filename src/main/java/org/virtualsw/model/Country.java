package org.virtualsw.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {
	
	@XmlElement(name="idCountry")
	private String idCountry;
	
	@XmlElement(name="country")
	private String country;
	
	public Country(){}

	public Country(String idCountry, String country) {
		this.idCountry = idCountry;
		this.country = country;
	}

	public String getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(String idCountry) {
		this.idCountry = idCountry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    Country countryObj = (Country) o;

	    return Objects.equals(idCountry, countryObj.idCountry)
	            && Objects.equals(country, countryObj.country);
	}



}
