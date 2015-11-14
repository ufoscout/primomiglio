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
package ufo.primomiglio.common.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import io.vertx.core.Vertx;
import ufo.primomiglio.common.BaseAbstractTest;

public class ContextTest extends BaseAbstractTest {

    @Test
    public void context_should_not_be_null() {
        assertNotNull(Context.newContext());
    }

    @Test
    public void context_should_contain_vertx() throws InterruptedException {
        assertNotNull(Context.newContext().container().getComponent(Vertx.class));
    }
}
