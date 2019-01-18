package org.virtualsw.dao.utils;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class Utils {

	public Utils(){};
	
	public String objectToJSONString(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonOutString = "";
		try {
			jsonOutString = mapper.writeValueAsString(object);
			System.out.println(jsonOutString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return jsonOutString;
	}
	
	public Object JSONStringToObject(String jsonInString, Class<?> classObject) {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		try {
			object = mapper.readValue(jsonInString, classObject);
			System.out.println(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return object;
	}
}
