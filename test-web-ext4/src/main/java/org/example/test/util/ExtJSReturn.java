package org.example.test.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.test.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ExtJSReturn {

	public static Map<String,Object> mapOK(List<Contact> contacts){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", contacts.size());
		modelMap.put("data", contacts);
		modelMap.put("success", true);
		
		return modelMap;
	}

	public static Map<String,Object> mapOK(List<Contact> contacts, int total){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", total);
		modelMap.put("data", contacts);
		modelMap.put("success", true);
		
		return modelMap;
	}

	public static Map<String,Object> mapError(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return modelMap;
	} 
}
