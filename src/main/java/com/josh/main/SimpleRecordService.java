package com.josh.main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("rawtypes") 
public class SimpleRecordService {
	
	public String ConvertStringRemoveSpecialChars(String body) throws Exception {
		
		body = body.replaceAll(", ", ",")
				.replaceAll("\\[", "") .replaceAll("\\]","")
				.replaceAll("\\{", "") .replaceAll("\\}","");
		
		String out = "";
		
		//Split each line in the body
		String[] lines = body.split("\n");
		//Iterate the lines
		for (String element : lines) {
			Map<String,String> expected = new HashMap<>();
			//Split each field in the element
			String[] fields = element.split(",");
			//Iterate the fields
			for (String field : fields) {
				//Split each element in the field
				String[] elements = field.split("=");
				//Check there is only 2 elements in the array (key, value)
				if(elements.length == 2)
					//Put the elements in the hash map
					expected.put(elements[0], elements[1]);
			}
			
			//Concatenate the return string with the hashmaps values, remove brackets
			out += expected.values().toString().replaceAll("\\[", "").replaceAll("\\]","");
			out += "\n";
		}
		
		return out;	
	}

	public LinkedHashMap FilterEventFromHashMap(LinkedHashMap hashMap) throws Exception{
		if(hashMap.containsKey("event"))
			hashMap.remove("event");
		return hashMap;
	}
}
