package com.josh.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class DefaultRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration()
			.component("servlet");

		rest()
			.get("/hello")
			.to("direct:hello");
		
		from("direct:hello")
			.log(LoggingLevel.INFO, "Hello World")
			.transform().simple("Hello World");

		rest()
			.post("/api/jsontocsv").consumes("application/json")
			.to("direct://transformPayloadToCsv");
	}

}
