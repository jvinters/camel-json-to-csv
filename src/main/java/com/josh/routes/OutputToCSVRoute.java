package com.josh.routes;

import org.apache.camel.builder.RouteBuilder;

import com.josh.main.SimpleRecordService;
import com.josh.processors.FileWriterProcessor;
import com.josh.processors.LogRecordProcessor;

public class OutputToCSVRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct://outputToCsv")
		.process(new LogRecordProcessor(true) )
		.bean(SimpleRecordService.class, "ConvertStringRemoveSpecialChars")
	 	.process(new FileWriterProcessor());
	}
}
