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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.architect.exchangerate.core.service.ExchangeRateService;
import com.crossover.architect.exchangerate.core.service.dto.ExchangeRateDTO;

@RestController
@RequestMapping("/rest/exchangeRate")
public class ExchangeRateController {

	@Autowired
	private ExchangeRateService exchangeRateService;

	@RequestMapping("/rates")
	public List<ExchangeRateDTO> home(@RequestParam(value = "currencies", defaultValue="") String currencies) {
		List<String> currencyList = new ArrayList<>();
		if (!currencies.isEmpty()) {
			currencyList = Arrays.asList(currencies.split(","));
		}
		return exchangeRateService.getExchangeRate(currencyList);
	}

	@RequestMapping("/convert/{from}/{to}/{amount}")
	public BigDecimal convert(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {
		return exchangeRateService.convert(from, to, amount);
	}

}
