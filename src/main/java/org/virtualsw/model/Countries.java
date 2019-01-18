package org.virtualsw.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "countries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Countries {
	
	@XmlElement(name="countries")
	private List<Country> listCountries;

	public Countries(){};

	public Countries(List<Country> listCountries){
		this.listCountries = listCountries;
	}
	
	public void setlistCountries(List<Country> listCountries) {
		this.listCountries = listCountries;
	}
	
	public List<Country> getlistCountries() {
		return this.listCountries ;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    List<Country> listCountriesObj = (List<Country>) o;

	    if(listCountriesObj.size() != this.listCountries.size()) 
	    	return false;
	    
	    for (int i=0; i < listCountriesObj.size(); i++) {
	        if (listCountriesObj.get(i).equals(this.listCountries.get(i))){
	        	return false;
	        }
	    }
	    
	    return true;
	}
}
