package com.josh.processors;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josh.helpers.FileHelper;


public class FileWriterWithExchangeHeaderProcessor implements Processor{
	
	private String filePath;
	private String fileExtension;
	private String fileName;
	private Boolean useFileNameAsHeaderLookup;
	
	public FileWriterWithExchangeHeaderProcessor(String filePath, String fileName, String fileExtension, Boolean useFileNameAsHeaderLookup) {
		setFilePath(filePath);
		setFileName(fileName);
		setFileExtension(fileExtension);
		setUseFileNameAsHeaderLookup(useFileNameAsHeaderLookup);
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

	@Autowired
	public String getFilePath() {
		return filePath;
	}

	@Autowired
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Autowired
	public String getFileExtension() {
		return fileExtension;
	}

	@Autowired
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	@Autowired
	public String getFileName() {
		return fileName;
	}

	@Autowired
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Autowired
	public Boolean getUseFileNameAsHeaderLookup() {
		return useFileNameAsHeaderLookup;
	}

	@Autowired
	public void setUseFileNameAsHeaderLookup(Boolean useFileNameAsHeaderLookup) {
		this.useFileNameAsHeaderLookup = useFileNameAsHeaderLookup;
	}
}
