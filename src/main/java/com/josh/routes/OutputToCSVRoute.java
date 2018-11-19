package com.josh.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.josh.main.Application;
import com.josh.main.SimpleRecordService;
import com.josh.processors.FileWriterWithExchangeHeaderProcessor;
import com.josh.processors.LogRecordProcessor;

@Component
public class OutputToCSVRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct://outputToCsv")
		.bean(SimpleRecordService.class, "ConvertStringRemoveSpecialChars")
		.process(new LogRecordProcessor("Converted exchange body to CSV format.", true))
	 	.process(new FileWriterWithExchangeHeaderProcessor(Application.OutputFilePath, "batchId", ".csv", true))
		.process(new LogRecordProcessor("Wrote file to filepath: ", true));
	}
}
