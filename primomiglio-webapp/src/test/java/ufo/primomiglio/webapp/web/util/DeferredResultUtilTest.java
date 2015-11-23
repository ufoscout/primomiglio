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
package ufo.primomiglio.webapp.web.util;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResult.DeferredResultHandler;

import reactor.rx.Promise;
import reactor.rx.Promises;
import ufo.primomiglio.webapp.BaseUnitTest;
import ufo.primomiglio.webapp.util.DeferredResultUtil;

public class DeferredResultUtilTest extends BaseUnitTest {

    @Test
    public void deferred_should_be_resolved_on_successful_promise() {

        Promise<String> promise = Promises.success("hello");
        DeferredResult<String> deferredResult = DeferredResultUtil.fromPromise(promise);
        assertNotNull(deferredResult);

        AtomicReference<Object> reference = new AtomicReference<>();
        deferredResult.setResultHandler(new DeferredResultHandler() {
            @Override
            public void handleResult(Object result) {
                reference.set(result);
            }
        });

        assertEquals("hello", reference.get());

    }


    @Test
    public void deferred_should_fail_on_error_promise() {

        Throwable exception = new RuntimeException("Hello from exception");
        Promise<String> promise = Promises.error(exception);
        DeferredResult<String> deferredResult = DeferredResultUtil.fromPromise(promise);
        assertNotNull(deferredResult);

        AtomicReference<Object> reference = new AtomicReference<>();
        deferredResult.setResultHandler(new DeferredResultHandler() {
            @Override
            public void handleResult(Object result) {
                reference.set(result);
            }
        });

        assertEquals(exception, reference.get());

    }
}
