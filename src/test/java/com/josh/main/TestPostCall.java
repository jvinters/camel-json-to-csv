package com.josh.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class TestPostCall extends CamelTestSupport {

	@Test
	public void Test1() throws Exception {
		MockEndpoint mock = getMockEndpoint("mock:split");
		mock.expectedBodiesReceived("A", "B", "C");
		
		List<String> body = new ArrayList<String>();
		body.add("A");
		body.add("B");
		body.add("C");
		template.sendBody("direct:testpoint", body);
		
		assertMockEndpointsSatisfied();
	}
}
