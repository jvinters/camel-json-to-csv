package com.josh.main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.camel.Message;

@SuppressWarnings("rawtypes") 
public class SimpleRecordService {
	
	public String ConvertStringRemoveSpecialChars(List<Message> messages) throws Exception {
		String out = "";
		for (Message message : messages) {
			HashMap map = message.getBody(HashMap.class);
			out += map.values().toString().replaceAll("\\[", "").replaceAll("\\]","").replaceAll(", ", ",");
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
