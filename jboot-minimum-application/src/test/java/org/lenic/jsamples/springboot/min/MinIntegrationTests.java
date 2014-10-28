package org.lenic.jsamples.springboot.min;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lenic.jboot.minimum.Application;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MinIntegrationTests {
	RestTemplate	template	= new TestRestTemplate();

	@Test
	public void testRequest() throws Exception {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080", String.class);
		assertEquals(response.getStatusCode().value(), 200);
		assertEquals(response.getBody(), "hi,spring boot");
	}
}