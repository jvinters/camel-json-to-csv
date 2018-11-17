package com.josh.main;

import java.util.LinkedHashMap;

@SuppressWarnings("rawtypes") 
public class SimpleRecordService {
	
	public String ConvertHashMapToStringAndRemoveSpecialChars(LinkedHashMap hashMap) throws Exception {
		String str =  hashMap.values()
				.toString()
				.replaceAll(", ", ",")
				.replaceAll("\\[", "")
				.replaceAll("\\]","");
		
		return str;
	}

	public LinkedHashMap FilterEventFromHashMap(LinkedHashMap hashMap) throws Exception{
		hashMap.remove("event");
		return hashMap;
	}
}
