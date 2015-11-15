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
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;

public class AuthUser extends AbstractUser {

    private final UserContext userContext;
    private final JsonObject principal;

    public AuthUser(UserContext userContext) {
        this.userContext = userContext;
        principal = new JsonObject();
        principal.put("username", userContext.getUsername());
    }

    @Override
    public JsonObject principal() {
        return principal;
    }

    @Override
    public void setAuthProvider(AuthProvider authProvider) {
    }

    @Override
    protected void doIsPermitted(String permission, Handler<AsyncResult<Boolean>> resultHandler) {
        if (userContext().getPermissions().contains(permission)) {
            resultHandler.handle(Future.succeededFuture(true));
            return;
        }
        // log.debug("User has no permission [" + permission + "]");
        resultHandler.handle(Future.succeededFuture(false));

    }

    /**
     * @return the userContext
     */
    public UserContext userContext() {
        return userContext;
    }

}
