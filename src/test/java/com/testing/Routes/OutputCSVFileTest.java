package com.testing.Routes;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.josh.helpers.FileHelper;
import com.josh.main.Application;
import com.josh.routes.OutputToCSVRoute;

public class OutputCSVFileTest extends CamelTestSupport{
	
	@Override
    public RouteBuilder createRouteBuilder() throws Exception
    {
        return new OutputToCSVRoute();
    }

	@Test
	public void TestRoute() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "a");
		map.put("B", "b");
		map.put("C", "c");
		
		template.sendBody("direct://outputToCsv", map);
		Thread.sleep(3000);
		
		File directory = new File(Application.OutputFilePath);
		assertTrue(directory.isDirectory());
		
		File csvFile = new File(Application.OutputFilePath + "batchId.csv");
		assertTrue(csvFile.exists());
	}
	
}
