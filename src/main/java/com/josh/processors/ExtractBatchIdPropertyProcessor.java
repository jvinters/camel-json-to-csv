package com.josh.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.jayway.jsonpath.JsonPath;

public class ExtractBatchIdPropertyProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		try {
			
			String body = exchange.getIn().getBody(String.class);
			String batchId = JsonPath.parse(body).read("$.batchId");
			exchange.setProperty("batchId", batchId);
			exchange.setOut(exchange.getIn());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
