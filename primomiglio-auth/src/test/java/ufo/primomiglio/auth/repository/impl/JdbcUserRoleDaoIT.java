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
package ufo.primomiglio.auth.repository.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ufo.primomiglio.auth.BaseIT;
import ufo.primomiglio.auth.repository.UserRole;
import ufo.primomiglio.auth.repository.UserRoleDao;

public class JdbcUserRoleDaoIT extends BaseIT {

    @Autowired
    private UserRoleDao userRoleDao;

    @Test
    public void test() {
        jpo.transaction()
        .readOnly(true)
        .executeVoid(session -> {

            Long userId = new Random().nextLong();
            String roleName = UUID.randomUUID().toString();

            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleName(roleName);
            userRoleDao.save(session, userRole);

            List<String> roles = userRoleDao.getRolesByUserId(session, userId);
            assertNotNull(roles);
            assertFalse(roles.isEmpty());
            assertTrue(roles.contains(roleName));

        });

    }

}
