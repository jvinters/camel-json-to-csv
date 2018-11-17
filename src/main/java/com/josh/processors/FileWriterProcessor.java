package com.josh.processors;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileWriterProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("F:\\_dev\\Randoli\\test\\filename.csv"), "utf-8"));
		    writer.write(exchange.getIn().getBody().toString());
		} catch (IOException ex) {
		    // Report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}
