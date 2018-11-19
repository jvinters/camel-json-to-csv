package com.josh.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DefaultRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration()
			.component("servlet");

		rest()
			.post("/jsontocsv").consumes("application/json")
			.to("direct://transformPayloadToCsv");
	}

}
