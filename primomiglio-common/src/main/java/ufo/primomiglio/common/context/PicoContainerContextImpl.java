/*******************************************************************************
 * Copyright 2015 Francesco Cina'
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ufo.primomiglio.common.context;

import java.util.function.Consumer;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class PicoContainerContextImpl implements Context {

    private final MutablePicoContainer container = new DefaultPicoContainer();


    @Override
    public <T> T getComponent(Class<T> componentClass) {
        return container.getComponent(componentClass);
    }

    @Override
    public <T> void addComponent(Class<T> componentClass) {
        container.addComponent(componentClass);
    }

    @Override
    public <T> void addComponent(T componentInstance) {
        container.addComponent(componentInstance);
    }

    @Override
    public Context addToContext(Consumer<Context> consumer) {
        consumer.accept(this);
        return this;
    }
}
