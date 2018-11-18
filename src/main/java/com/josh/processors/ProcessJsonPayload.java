package com.josh.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.josh.main.Main;

public class ProcessJsonPayload implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		try {
			String bodyStr = exchange.getIn().getBody(String.class);
			DocumentContext jsonString = JsonPath.parse(bodyStr);
			String batchId = jsonString.read("$.batchId");
			
			exchange.getOut().setHeader("batchId", batchId);
			exchange.getOut().setBody(bodyStr);
			
		} catch (Exception e) {
			Main.logger.debug(e.getMessage());
		}
	}
}
