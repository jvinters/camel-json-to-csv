package com.josh.routes;

import org.apache.camel.builder.RouteBuilder;

import com.josh.main.SimpleRecordService;
import com.josh.processors.ExtractBatchIdPropertyProcessor;

public class TransformPayloadToCSVRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:transformPayloadToCsv")
			.process(new ExtractBatchIdPropertyProcessor())
			.split().jsonpath("$.records")
				.bean(SimpleRecordService.class, "FilterEventFromHashMap")
				.bean(SimpleRecordService.class, "ConvertHashMapToStringAndRemoveSpecialChars")
				.to("log:split")
			.to("direct:outputToCsv");
	}
}
