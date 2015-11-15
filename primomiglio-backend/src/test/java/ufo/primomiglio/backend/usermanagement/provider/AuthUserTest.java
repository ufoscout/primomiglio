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

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

import ufo.primomiglio.backend.BaseAbstractTest;


public class AuthUserTest extends BaseAbstractTest {

    @Test
    public void pricipal_should_contain_the_username() {
        UserContext userContext = new UserContext();
        userContext.setUsername(UUID.randomUUID() + "");

        AuthUser authUser = new AuthUser(userContext);
        assertNotNull(authUser.principal());
        assertEquals(userContext.getUsername(), authUser.principal().getString("username"));
    }

    @Test
    public void user_should_have_permission() {
        UserContext userContext = new UserContext();
        userContext.setUsername(UUID.randomUUID() + "");
        userContext.setPermissions(Arrays.asList("RIGHT_0", "RIGHT_1"));

        AuthUser authUser = new AuthUser(userContext);

        AtomicBoolean succeeded = new AtomicBoolean(false);
        authUser.doIsPermitted("RIGHT_0", result -> {
            succeeded.set( result.result() );
        });
        assertTrue(succeeded.get());
    }

    @Test
    public void user_should_not_have_permission() {
        UserContext userContext = new UserContext();
        userContext.setUsername(UUID.randomUUID() + "");
        userContext.setPermissions(Arrays.asList("RIGHT_0", "RIGHT_1"));

        AuthUser authUser = new AuthUser(userContext);

        AtomicBoolean succeeded = new AtomicBoolean(true);
        authUser.doIsPermitted("RIGHT_2", result -> {
            succeeded.set( result.result() );
        });
        assertFalse(succeeded.get());
    }
}
