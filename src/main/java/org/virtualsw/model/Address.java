package org.virtualsw.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

	@XmlElement(name="street")
	private String street;
	
	@XmlElement(name="number")
	private String number;
	
	@XmlElement(name="floor")
	private String floor;
	
	@XmlElement(name="zipCode")
	private String zipCode;
	
	@XmlElement(name="country")
	private String country;
	
	@XmlElement(name="region")
	private String region;
	
	@XmlElement(name="city")
	private String city;
	
	public Address(){};
	
	public Address(String street, String number, String floor, String zipCode, String country, String region, String city) {
		this.street = street;
		this.number = number;
		this.floor = floor;
		this.zipCode = zipCode;
		this.country = country;
		this.region = region;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	public String getZipCode() {
		return zipCode;
	}	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getCountry() {
		return country;
	}	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getRegion() {
		return region;
	}	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getCity() {
		return city;
	}	
	public void setCity(String city) {
		this.city = city;
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
	    Address address = (Address) o;
	    // field comparison
	    return Objects.equals(street, address.street)
	            && Objects.equals(number, address.number)
	            && Objects.equals(floor, address.floor)
	            && Objects.equals(zipCode, address.zipCode)
	            && Objects.equals(country, address.country)
	            && Objects.equals(region, address.region)
	            && Objects.equals(city, address.city);
	}
	
}
