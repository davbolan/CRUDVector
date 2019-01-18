package org.virtualsw.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "regions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Regions {
	
	@XmlElement(name="regions")
	private List<Region> listRegions;

	public Regions(){};

	public Regions(List<Region> listRegions){
		this.listRegions = listRegions;
	}
	
	public void setlistRegions(List<Region> listRegions) {
		this.listRegions = listRegions;
	}
	
	public List<Region> getlistRegions() {
		return this.listRegions ;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    List<Region> listRegionsObj = (List<Region>) o;

	    if(listRegionsObj.size() != this.listRegions.size()) 
	    	return false;
	    
	    for (int i=0; i < listRegionsObj.size(); i++) {
	        if (listRegionsObj.get(i).equals(this.listRegions.get(i))){
	        	return false;
	        }
	    }
	    
	    return true;
	}
}
