package com.josh.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.josh.main.Main;

public class LogRecordProcessor implements Processor{
	
	public String LogMessage;
	public LogRecordProcessor(String logMessage) {
		this.LogMessage = logMessage;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			String batchId = (String) exchange.getProperty("batchId");
			String prefix = "Batch(" + batchId + "):\t";
			
			Main.logger.info(prefix + LogMessage);
		} catch (Exception e) {
			//TODO
		}
	}
}
