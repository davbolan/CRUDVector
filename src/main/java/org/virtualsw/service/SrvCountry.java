package org.virtualsw.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.virtualsw.dao.DAOCountry;
import org.virtualsw.model.Cities;
import org.virtualsw.model.City;
import org.virtualsw.model.Countries;
import org.virtualsw.model.Country;
import org.virtualsw.model.Region;
import org.virtualsw.model.Regions;

@Path("countries")
public class SrvCountry {

	DAOCountry daoCountry = new DAOCountry();

	@GET
	@Path("/")
    @Produces({ MediaType.APPLICATION_JSON})
	public Countries getCountries() {
		List<Country> listCountries = daoCountry.listCountries();
		Countries countries = null;
		
		if(listCountries!=null && !listCountries.isEmpty()) {
			countries = new Countries(listCountries);
		}
		
		return countries;
	}

	@GET
	@Path("/{idCountry}/regions")
    @Produces({ MediaType.APPLICATION_JSON})
	public Regions getRegions(@PathParam("idCountry") String idCountry) {
		List<Region> listRegions = daoCountry.listRegions(idCountry);
		Regions regions = null;
		
		if(listRegions!=null && !listRegions.isEmpty()) {
			regions = new Regions(listRegions);
		}
		return regions;
	}
	
	
	@GET
	@Path("/{idCountry}/regions/{idRegion}/cities")
    @Produces({ MediaType.APPLICATION_JSON})
	public Cities getCities(@PathParam("idCountry") String idCountry, @PathParam("idRegion") String idRegion) {
		List<City> listCities = daoCountry.listCities(idCountry, idRegion);
		Cities cities = null;
		
		if(listCities!=null && !listCities.isEmpty()) {
			cities = new Cities(listCities);
		}
		return cities;
	}

}
