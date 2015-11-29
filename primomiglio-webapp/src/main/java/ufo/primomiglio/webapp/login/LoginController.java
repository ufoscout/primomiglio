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
package ufo.primomiglio.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import reactor.rx.Promise;
import reactor.rx.Promises;
import ufo.primomiglio.auth.client.AuthApi;
import ufo.primomiglio.auth.client.UserContext;
import ufo.primomiglio.common.jwt.JWTService;
import ufo.primomiglio.um.client.UserManagementApi;
import ufo.primomiglio.webapp.RestConstants;
import ufo.primomiglio.webapp.util.DeferredResultUtil;

@RestController
@RequestMapping(RestConstants.REST_BASE_URL)
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AuthApi securityApi;
    private final UserManagementApi umApi;
    private final JWTService jwtService;

    @Autowired
    public LoginController(UserManagementApi umApi, AuthApi securityApi, JWTService jwtService) {
        this.umApi = umApi;
        this.securityApi = securityApi;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DeferredResult<String> login(@RequestBody LoginDTO loginDto) {
        logger.debug("Attempted login with username [{}]", loginDto.username);
        Promise<String> promise = umApi.getUserByUsername(loginDto.username).flatMap(user -> securityApi.getUserContextByUserId(user.getId()))
                .map(userContext -> jwtService.generate(userContext));

        return DeferredResultUtil.fromPromise(promise);
    }

    @RequestMapping(value = "/protected", method = RequestMethod.GET)
    public DeferredResult<String> login(UserContext userContext) {
        logger.warn("UserContext has roles [{}]", userContext.getPermissions());

        Promise<String> promise = Promises
        .syncTask(() -> AuthApi.neededPermisison(userContext, "ADMIN"))
        .map(context -> context.getPermissions().toString());

        return DeferredResultUtil.fromPromise(promise);
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    public DeferredResult<String> forbidden(UserContext userContext) {

        Promise<String> promise = Promises
        .syncTask(() -> AuthApi.neededPermisison(userContext, "GOD!!!"))
        .map(context -> "Are you a God?!!");

        return DeferredResultUtil.fromPromise(promise);

    }

}
