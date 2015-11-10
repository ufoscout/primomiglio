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
package com.crossover.architect.currency.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.crossover.architect.currency.core.dao.CurrencyReporitory;
import com.crossover.architect.currency.core.dao.entity.Currency;
import com.crossover.architect.currency.core.service.CurrencyService;
import com.crossover.architect.currency.core.service.dto.CurrencyDTO;
import com.crossover.architect.currency.core.service.dto.CurrencyDTOCurrencyConverter;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	@Autowired
	private CurrencyReporitory currencyReporitory;

	@Override
	@Cacheable(value = "CURRENCY", key = "{#root.targetClass,#root.methodName}")
	public List<CurrencyDTO> getAllCurrencies() {
		LOGGER.info("Return all currencies");
		List<CurrencyDTO> result = new ArrayList<>();
		for (Currency currency : currencyReporitory.findAll()) {
			result.add(CurrencyDTOCurrencyConverter.convert(currency));
		}
		return result;
	}

	@Override
	@Cacheable(value = "CURRENCY", key = "{#root.targetClass,#root.methodName, #filter}")
	public List<CurrencyDTO> getAllCurrencies(String filter) {
		LOGGER.info("Return currencies with filter [{}]", filter);
		if (filter.isEmpty()) {
			return getAllCurrencies();
		}

		List<CurrencyDTO> result = new ArrayList<>();
		for (Currency currency : currencyReporitory.findAllFiltered(filter)) {
			result.add(CurrencyDTOCurrencyConverter.convert(currency));
		}
		return result;

	}

}
