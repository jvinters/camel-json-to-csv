package com.josh.routes;

import org.apache.camel.builder.RouteBuilder;
import com.josh.main.SimpleRecordService;
import com.josh.main.ConcatenateAggregationStrategy;
import com.josh.processors.ProcessJsonPayload;
import com.josh.processors.LogRecordProcessor;

public class TransformPayloadToCSVRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		try {
			
			from("direct://transformPayloadToCsv")
			.process(new ProcessJsonPayload())
				.process(new LogRecordProcessor("Processed the json payload, added batchId as a header.", true))
			.split()
				.jsonpath("$.records")
					.bean(SimpleRecordService.class, "FilterEventFromHashMap")
					.process(new LogRecordProcessor("Filtered event from json payload.", true))
			.aggregate(header("batchId"), new ConcatenateAggregationStrategy())
				.completionSize(3).completionTimeout(60000)
				.process(new LogRecordProcessor("Aggregating payload batch.", true))
				.to("direct:outputToCsv");
			
		} catch (Exception e) {}
	}
}
