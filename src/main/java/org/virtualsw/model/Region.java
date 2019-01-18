package org.virtualsw.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "region")
@XmlAccessorType(XmlAccessType.FIELD)
public class Region {

	@XmlElement(name="idRegion")
	private String idRegion;
	
	@XmlElement(name="idCountry")
	private String idCountry;
	
	@XmlElement(name="region")
	private String region;
	

	public Region() {}
	
	public Region(String idRegion, String idCountry, String region) {
		this.idRegion 	= idRegion;
		this.idCountry 	= idCountry;
		this.region 	= region;
	}
	
	
	
	public String getId() {
		return idRegion;
	}
	public void setId(String idRegion) {
		this.idRegion = idRegion;
	}


	public String getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(String idCountry) {
		this.idCountry = idCountry;
	}


	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}


	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    Region regionObj = (Region) o;

	    return Objects.equals(idRegion, regionObj.idRegion)
	            && Objects.equals(idCountry, regionObj.idCountry)
	            && Objects.equals(region, regionObj.region);
	}

}
