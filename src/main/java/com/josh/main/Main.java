package com.josh.main;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josh.routes.OutputToCSVRoute;
import com.josh.routes.TransformPayloadToCSVRoute;

//Configure a rest end point (POST)
//Split 'records' element
//Process each record
	//Remove events section
	//Transform to CSV
//Aggregate transformed messages every 1min or when 10 messages are in the batch
//Write each aggregation into a separate file (use consistent file naming convention)
//Configure file end point
	//Output file to the files system

public class Main {
	public static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.start();
		
		context.addRoutes(new OutputToCSVRoute());
		context.addRoutes(new TransformPayloadToCSVRoute());
		
		context.addRoutes(new RouteBuilder() {
			public void configure() throws Exception {
				
				restConfiguration()
			        .component("restlet")
			        .host("localhost")
			        .port("8080");
				
				rest("/api/jsontocsv")
					.post("/post")
						.consumes("application/json")
						.to("direct://transformPayloadToCsv");
            }
		});
		
		
		Thread.sleep(10000);
		context.stop();
	}
}
