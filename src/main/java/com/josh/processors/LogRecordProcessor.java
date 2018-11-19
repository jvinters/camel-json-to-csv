package com.josh.processors;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josh.main.Application;


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
			String batchId = (String) exchange.getIn().getHeader("batchId");
			String prefix = "Batch(" + batchId + "):\t";
			
			if(!LogMessage.isEmpty())
				Application.logger.info(prefix + LogMessage);
			if(logBody)
				Application.logger.info(prefix + exchange.getIn().getBody());
			
		} catch (Exception e) {
			//TODO
		}
	}

	@Autowired
	public String getLogMessage() {
		return LogMessage;
	}

	@Autowired
	public void setLogMessage(String logMessage) {
		LogMessage = logMessage;
	}

	@Autowired
	public Boolean getLogBody() {
		return logBody;
	}

	@Autowired
	public void setLogBody(Boolean logBody) {
		this.logBody = logBody;
	}
}
