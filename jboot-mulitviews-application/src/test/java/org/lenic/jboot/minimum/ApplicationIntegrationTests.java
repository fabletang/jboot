package org.lenic.jboot.minimum;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lenic.jboot.response.Application;
import org.lenic.jboot.response.Application.User;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApplicationIntegrationTests {

	@Test
	public void testUserJSON() throws Exception {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		RestTemplate template = new RestTemplate(messageConverters);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/user.json", String.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(mapper.writeValueAsString(new User("张三", 16, "男")), response.getBody());
	}

	@Test
	public void testUserXML() throws Exception {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
		RestTemplate template = new RestTemplate(messageConverters);
		ResponseEntity<User> response = template.getForEntity("http://localhost:8080/user.xml", User.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(new User("张三", 16, "男"), response.getBody());
	}

	@Test
	public void testUserHtml() throws Exception {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		RestTemplate template = new RestTemplate(messageConverters);
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/user.html", String.class);
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("张三"));
		assertTrue(response.getBody().contains("16"));
		assertTrue(response.getBody().contains("男"));
	}
}