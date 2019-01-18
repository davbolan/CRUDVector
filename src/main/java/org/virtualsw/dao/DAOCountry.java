package org.virtualsw.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.virtualsw.model.City;
import org.virtualsw.model.Country;
import org.virtualsw.model.Region;

public class DAOCountry {

	public List<Country> listCountries() {
		System.out.println("Paises consultados");
		List<Country> listCountries = new ArrayList<>();
		 
        try {
        	JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader("src/main/webapp/db/countries.json"));
			 JSONArray jsonCountries = (JSONArray) object;
			
			for (Object itemCountry : jsonCountries) {
				JSONObject jsonCountry = (JSONObject) itemCountry;
				String strCountry 	= (String) jsonCountry.get("country");
				String strIdCountry = (String) jsonCountry.get("idCountry");

				Country country = new Country(strIdCountry, strCountry);
				
				listCountries.add(country);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
			
			
		return listCountries;
	}

	public List<Region> listRegions(String idCountry) {
		 System.out.println("Regiones consultadas");
		 List<Region> regions = new ArrayList<>();
		 
		try {
			JSONParser parser = new JSONParser();
	        Object object = parser.parse(new FileReader("src/main/webapp/db/countries.json"));
			JSONArray jsonCountries = (JSONArray) object;
			
			for (Object itemCountry : jsonCountries) {
				JSONObject jsonCountry = (JSONObject) itemCountry;
				String strIdCountry = (String) jsonCountry.get("idCountry");
				
				if(strIdCountry.equalsIgnoreCase(idCountry)) {
					JSONArray  jsonRegions = (JSONArray) jsonCountry.get("regions");

					for (Object itemRegion : jsonRegions) {
						JSONObject jsonRegion = (JSONObject) itemRegion;

						String strRegion 	= (String) jsonRegion.get("region");
						String strIdRegion = (String) jsonRegion.get("idRegion");
						
						Region region = new Region(strIdRegion, strIdCountry, strRegion);
						regions.add(region);
					}			
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		 
		 
		 return regions;
	}
	

	public List<City> listCities(String idCountry, String idRegion) {
		 System.out.println("Ciudades consultadas");
	
		 List<City> cities = new ArrayList<>();
		 
	 	try {
		 	JSONParser parser = new JSONParser();
			Object object = parser.parse(new FileReader("src/main/webapp/db/countries.json"));
			JSONArray jsoncities = (JSONArray) object;
			
			for (Object itemCountry : jsoncities) {
				JSONObject jsonCountry = (JSONObject) itemCountry;
				String strIdCountry = (String) jsonCountry.get("idCountry");
				
				if(strIdCountry.equalsIgnoreCase(idCountry)) {
					JSONArray jsonRegions = (JSONArray) jsonCountry.get("regions");

					for (Object itemRegion : jsonRegions) {
						JSONObject jsonRegion = (JSONObject) itemRegion;

						String strIdRegion = (String) jsonRegion.get("idRegion");
						
						if(strIdRegion.equalsIgnoreCase(idRegion)) {
							JSONArray jsonCities = (JSONArray) jsonRegion.get("cities");

							for (Object itemCity : jsonCities) {
								JSONObject jsonCity = (JSONObject) itemCity;

								String strCity 	= (String) jsonCity.get("city");
								String strIdCity = (String) jsonCity.get("idCity");
								
								City city = new City(strIdCity, strIdRegion, strIdCountry, strCity);
								cities.add(city);
							}					
						}
					}				
				}
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

	      

		 
		 return cities;
	}

	
}
