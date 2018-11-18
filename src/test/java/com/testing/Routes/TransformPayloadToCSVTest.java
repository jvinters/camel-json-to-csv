package com.testing.Routes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.routes.TransformPayloadToCSVRoute;

public class TransformPayloadToCSVTest extends CamelTestSupport {

	@Override
    public RouteBuilder createRouteBuilder() throws Exception
    {
        return new TransformPayloadToCSVRoute();
    }
	
	@Test
	public void TestRoute() throws Exception{
		
		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("A", "a");
		map.put("B", "b");
		map.put("C", "c");
		
		template.sendBody("direct://transformPayloadToCsv", new ObjectMapper().writeValueAsString(map));
		Thread.sleep(3000);
		
		//TODO
		assertTrue(true);
	}
}
