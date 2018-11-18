package com.josh.routes;

import org.apache.camel.builder.RouteBuilder;
import com.josh.main.SimpleRecordService;
import com.josh.processors.ProcessJsonPayload;
import com.josh.processors.LogRecordProcessor;

public class TransformPayloadToCSVRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct://transformPayloadToCsv")
			.process(new ProcessJsonPayload())
			.process(new LogRecordProcessor(true) )
			.split()
				.jsonpath("$.records")
					.bean(SimpleRecordService.class, "FilterEventFromHashMap")
					.bean(SimpleRecordService.class, "ConvertHashMapToStringAndRemoveSpecialChars")
					.to("log:split")
				
			.to("direct:outputToCsv");
	}
}
