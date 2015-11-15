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
package ufo.primomiglio.backend.config;

import co.paralleluniverse.fibers.Suspendable;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ufo.primomiglio.backend.web.WebServerVerticle;
import ufo.primomiglio.common.context.Context;
import ufo.primomiglio.common.util.RuntimeUtils;

public class WebConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    @Suspendable
    public static void configureWeb(Context context) {
        LOGGER.info("Configure Backend Web application");

        Vertx vertx = context.getComponent(Vertx.class);

        int instances = RuntimeUtils.numberOfCores() / 4;

        vertx.deployVerticle(WebServerVerticle.class.getName(), new DeploymentOptions().setInstances(instances));

    }

}
