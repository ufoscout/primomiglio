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
package ufo.primomiglio.auth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.core.Dispatcher;
import reactor.core.reactivestreams.SubscriberWithContext;
import reactor.rx.Stream;
import reactor.rx.Streams;

@Repository
public class JdbcRolesDao implements RolesDao {

    private final Dispatcher dispatcher;

    @Autowired
    public JdbcRolesDao(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Stream<String> getRoles() {
        return Streams.createWith((Long demand, SubscriberWithContext<String, Void> sub) -> {
            System.out.println("Demand: " + demand + ". Thread: " + Thread.currentThread().getName());
            sub.onNext("ADMIN");
            sub.onNext("USER");
            sub.onNext("OTHER");
            sub.onComplete();
        }).subscribeOn(dispatcher);

    }

}