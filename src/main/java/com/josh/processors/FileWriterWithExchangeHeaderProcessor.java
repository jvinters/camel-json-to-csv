package com.josh.processors;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.josh.helpers.FileHelper;

public class FileWriterWithExchangeHeaderProcessor implements Processor{
	
	private String filePath;
	private String fileExtension;
	private String fileName;
	private Boolean useFileNameAsHeaderLookup;
	
	public FileWriterWithExchangeHeaderProcessor(String filePath, String fileName, String fileExtension, Boolean useFileNameAsHeaderLookup) {
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.useFileNameAsHeaderLookup = useFileNameAsHeaderLookup;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Writer writer = null;
		
		if(useFileNameAsHeaderLookup)
		{
			Object headerObj = exchange.getIn().getHeader(fileName);
			if(headerObj != null) {
				String header = headerObj.toString();
				if(!header.isEmpty())
					fileName = header;
			}
		}
		
		String fn =  FileHelper.CheckIfFileExistsAndIncrement(filePath, fileName, fileExtension);

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filePath + fn), "utf-8"));
		    writer.write(exchange.getIn().getBody().toString());
		} catch (IOException ex) {
		    // Report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	
	
}
