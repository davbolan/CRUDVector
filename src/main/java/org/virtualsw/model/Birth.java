package org.virtualsw.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "birth")
@XmlAccessorType(XmlAccessType.FIELD)
public class Birth {
		
	@XmlElement(name="date")
	private String date;
	
	@XmlElement(name="country")
	private String country;
	
	
	public Birth(){}
	
	public Birth(String date, String country) {
		this.date 		= date;
		this.country 	= country;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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
	    Birth birth = (Birth) o;
	    // field comparison
	    return Objects.equals(date, birth.date)
	            && Objects.equals(country, birth.country);
	}
}
