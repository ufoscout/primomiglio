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
package ufo.primomiglio.backend.usermanagement.provider;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import ufo.primomiglio.common.jwt.JWTService;

public class UMAuthProvider implements AuthProvider {

    private final JWTService jwt;

    public UMAuthProvider(JWTService jwt) {
        this.jwt = jwt;
    }

    @Override
    public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
        try {
            UserContext userContext = jwt.parse(authInfo.getString("jwt"), UserContext.class);
            resultHandler.handle(Future.succeededFuture(new AuthUser(userContext)));
          } catch (RuntimeException e) {
            resultHandler.handle(Future.failedFuture(e));
          }
    }

}
