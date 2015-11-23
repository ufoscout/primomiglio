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
package ufo.primomiglio.backend.web.login;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import reactor.rx.Promise;
import ufo.primomiglio.backend.security.client.SecurityApi;
import ufo.primomiglio.backend.security.client.UserContext;
import ufo.primomiglio.backend.usermanagement.client.UserManagementApi;
import ufo.primomiglio.backend.web.RestConstants;
import ufo.primomiglio.backend.web.util.DeferredResultUtil;
import ufo.primomiglio.common.jwt.JWTService;

@RestController
@RequestMapping(RestConstants.REST_BASE_URL)
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SecurityApi securityApi;
    private final UserManagementApi umApi;
    private final JWTService jwtService;

    @Autowired
    public LoginController(UserManagementApi umApi, SecurityApi securityApi, JWTService jwtService) {
        this.umApi = umApi;
        this.securityApi = securityApi;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DeferredResult<String> login(@RequestBody LoginDTO loginDto) {
        logger.debug("Attempted login with username [{}]", loginDto.username);
        Promise<String> promise = umApi.getUserByUsername(loginDto.username).flatMap(user -> securityApi.getUserContextByUserId(user.getId()))
                .map(userContext -> jwtService.generate(userContext));

        return DeferredResultUtil.toDeferredResult(promise);
    }

    @RequestMapping(value = "/protected", method = RequestMethod.GET)
    public String login(UserContext userContext) {
        logger.warn("UserContext has roles [{}]", userContext.getPermissions());
        return userContext.getPermissions().toString();
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    public String login() {
        throw new MyExc();
    }

    public class MyExc extends RuntimeException {

    }

    @ExceptionHandler(MyExc.class)
    public ResponseEntity<?> rulesForCustomerNotFound(HttpServletRequest req, Exception e) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
