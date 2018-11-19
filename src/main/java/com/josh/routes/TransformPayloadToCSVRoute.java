package com.josh.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedMessageAggregationStrategy;
import org.springframework.stereotype.Component;

import com.josh.main.SimpleRecordService;
import com.josh.processors.ProcessJsonPayload;

@Component
public class TransformPayloadToCSVRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		try {
			
			from("direct://transformPayloadToCsv")
				.process(new ProcessJsonPayload())
					.log(LoggingLevel.INFO, "$simple{in.header.batchId} - Processed the json payload, added batchId as a header.")
				.split()
					.jsonpath("$.records")
						.bean(SimpleRecordService.class, "FilterEventFromHashMap")
				.aggregate(header("batchId"), new GroupedMessageAggregationStrategy())
					.completionSize(3).completionTimeout(60000)
						.log(LoggingLevel.INFO, "$simple{in.header.batchId} - Aggregating payload batch.")
					.to("direct:outputToCsv");
			
		} catch (Exception e) {}
	}
}
