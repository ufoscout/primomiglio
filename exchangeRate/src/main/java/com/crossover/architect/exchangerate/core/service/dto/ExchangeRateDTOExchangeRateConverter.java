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
package com.crossover.architect.exchangerate.core.service.dto;

import com.crossover.architect.exchangerate.core.dao.entity.ExchangeRate;

public class ExchangeRateDTOExchangeRateConverter {

	public static ExchangeRateDTO convert(ExchangeRate exchangeRate) {
		return new ExchangeRateDTO(exchangeRate.getFromCurrency(), exchangeRate.getToCurrency(), exchangeRate.getExchangeRate());
	}

}
