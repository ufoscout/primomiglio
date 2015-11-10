/*******************************************************************************
 * Copyright 2015 Francesco Cina'
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.crossover.architect.exchangerate.web.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.crossover.architect.WebTestBase;
import com.crossover.architect.exchangerate.core.service.ExchangeRateService;
import com.crossover.architect.exchangerate.core.service.dto.ExchangeRateDTO;

public class ExchangeRateControllerTest extends WebTestBase {

	@Autowired
	private ExchangeRateService exchangeRateService;

    @Test
    public void testGetExchangeRate() throws Exception {
		RestTemplate restTemplate = new TestRestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(serverUrl + "/rest/exchangeRate/rates?currencies=USD,EUR", String.class);
	    assertEquals( HttpStatus.OK, response.getStatusCode());

	    getLogger().info("Received: [{}]", response.getBody());

	    List<ExchangeRateDTO> responseBody = fromJson(response.getBody(), List.class, ExchangeRateDTO.class);
	    assertFalse(responseBody.isEmpty());
    }

    @Test
    public void testGetConvert() throws Exception {
		RestTemplate restTemplate = new TestRestTemplate();
	    ResponseEntity<BigDecimal> response = restTemplate.getForEntity(serverUrl + "/rest/exchangeRate/convert/USD/EUR/100", BigDecimal.class);
	    assertEquals( HttpStatus.OK, response.getStatusCode());

	    getLogger().info("Received: [{}]", response.getBody());

	    assertEquals(exchangeRateService.convert("USD", "EUR", BigDecimal.valueOf(100)), response.getBody());
    }

}
