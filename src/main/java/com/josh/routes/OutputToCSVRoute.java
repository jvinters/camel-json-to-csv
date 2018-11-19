package com.josh.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.josh.main.Application;
import com.josh.main.SimpleRecordService;

@Component
public class OutputToCSVRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct://outputToCsv")
		.bean(SimpleRecordService.class, "ConvertStringRemoveSpecialChars")
		.log(LoggingLevel.INFO, "$simple{in.header.batchId} - Converted exchange body to CSV format.")
		.to("file://" + Application.OutputFilePath + "?fileName=output.csv");
	}
}
