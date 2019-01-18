package org.virtualsw.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cities")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cities {
	
	@XmlElement(name="cities")
	private List<City> listCities;

	public Cities(){};

	public Cities(List<City> listCities){
		this.listCities = listCities;
	}
	
	public void setlistCities(List<City> listCities) {
		this.listCities = listCities;
	}
	
	public List<City> getlistCities() {
		return this.listCities ;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    List<City> listCitiesObj = (List<City>) o;

	    if(listCitiesObj.size() != this.listCities.size()) 
	    	return false;
	    
	    for (int i=0; i < listCitiesObj.size(); i++) {
	        if (listCitiesObj.get(i).equals(this.listCities.get(i))){
	        	return false;
	        }
	    }
	    
	    return true;
	}
}
