package org.virtualsw.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

	@XmlElement(name="idCity")
	private String idCity;

	@XmlElement(name="idRegion")
	private String idRegion;

	@XmlElement(name="idCountry")
	private String idCountry;

	@XmlElement(name="city")
	private String city;
	

	public City() {}
	
	public City(String idCity, String idRegion, String idCountry, String city) {
		this.idCity 	= idCity;
		this.idRegion 	= idRegion;
		this.idCountry  = idCountry;
		this.city 		= city;
	}
	
	
	
	public String getId() {
		return idCity;
	}
	public void setId(String idCity) {
		this.idCity = idCity;
	}


	public String getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(String idCountry) {
		this.idCountry = idCountry;
	}


	public String getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}


	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    City cityObj = (City) o;

	    return Objects.equals(idCity, cityObj.idCity)
	            && Objects.equals(idRegion, cityObj.idRegion)
	            && Objects.equals(idCountry, cityObj.idCountry)
	            && Objects.equals(city, cityObj.city);
	}
	
}
