package com.josh.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.josh.main.Main;

public class LogRecordProcessor implements Processor{
	
	public String LogMessage;
	Boolean logBody;
	
	public LogRecordProcessor(String logMessage) {
		this.LogMessage = logMessage;
		this.logBody = false;
	}
	
	public LogRecordProcessor(Boolean logBody) {
		this.LogMessage = "";
		this.logBody = logBody;
	}
	
	public LogRecordProcessor(String logMessage, Boolean logBody) {
		this.LogMessage = logMessage;
		this.logBody = logBody;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			String batchId = (String) exchange.getProperty("batchId");
			String prefix = "Batch(" + batchId + "):\t";
			
			if(!LogMessage.isEmpty())
				Main.logger.info(prefix + LogMessage);
			if(logBody)
				Main.logger.info(prefix + exchange.getIn().getBody());
			
		} catch (Exception e) {
			//TODO
		}
	}
}
