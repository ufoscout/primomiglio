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
package ufo.primomiglio.common.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.Environment;
import reactor.core.Dispatcher;
import reactor.core.dispatch.ThreadPoolExecutorDispatcher;

@Configuration
public class ReactiveConfig {

    @Bean
    public Environment env() {
        return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    public Dispatcher dispatcher(Environment env) {
        int poolSize = (int) (Runtime.getRuntime().availableProcessors() * 1.5);
        int backlog = 5000;
        return new ThreadPoolExecutorDispatcher(poolSize, backlog);
    }

    @Bean
    public reactor.bus.EventBus createEventBus(Environment env, Dispatcher dispatcher) {
        return reactor.bus.EventBus.create(env, dispatcher);
    }

}
