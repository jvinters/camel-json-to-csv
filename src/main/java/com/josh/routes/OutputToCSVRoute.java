package com.josh.routes;

import org.apache.camel.builder.RouteBuilder;

import com.josh.processors.FileWriterProcessor;

public class OutputToCSVRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct://outputToCsv")
	 	.process(new FileWriterProcessor());
	}
}
