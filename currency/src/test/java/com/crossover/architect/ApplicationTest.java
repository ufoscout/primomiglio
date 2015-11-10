package com.crossover.architect;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ApplicationTest extends IntegrationTestBase {

	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		assertNotNull(context);
	}

}
