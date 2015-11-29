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
package ufo.primomiglio.auth.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import reactor.rx.Promise;
import ufo.primomiglio.auth.BaseUnitTest;
import ufo.primomiglio.auth.repository.impl.InMemoryRolesDao;

public class InMemoryRolesDaoTest extends BaseUnitTest {

    private RolesDao rolesDao = new InMemoryRolesDao();

    @Test
    public void unknown_role_should_not_contain_other_roles() throws InterruptedException {
        Promise<List<String>> roles = rolesDao.getRelatedRoles("unknown").toList();
        roles.await();
    }

    @Test
    public void user_role_should_not_contain_other_roles() throws InterruptedException {
        List<String> roles = rolesDao.getRelatedRoles(Roles.USER).toList().await();
        assertNotNull(roles);
        assertEquals(1, roles.size());
        assertTrue(roles.contains(Roles.USER));
    }

    @Test
    public void admin_role_should_contain_user_role() throws InterruptedException {
        List<String> roles = rolesDao.getRelatedRoles(Roles.ADMIN).toList().await();
        assertNotNull(roles);
        assertEquals(2, roles.size());
        assertTrue(roles.contains(Roles.ADMIN));
        assertTrue(roles.contains(Roles.USER));
    }

    @Test
    public void application_admin_role_should_contain_user_role() throws InterruptedException {
        List<String> roles = rolesDao.getRelatedRoles(Roles.APPLICATION_ADMIN).toList().await();
        assertNotNull(roles);
        assertEquals(2, roles.size());
        assertTrue(roles.contains(Roles.APPLICATION_ADMIN));
        assertTrue(roles.contains(Roles.USER));
    }

    @Test
    public void user_role_should_have_associated_permissions() throws InterruptedException {
        List<String> permissions = rolesDao.getPermissionsByRole(Roles.USER).toList().await();
        assertNotNull(permissions);
        assertTrue(permissions.contains(Permissions.USER_PROFILE_OWN_READ));
        assertTrue(permissions.contains(Permissions.USER_PROFILE_OWN_EDIT));
        assertFalse(permissions.contains(Permissions.USER_PROFILE_OTHERS_EDIT));
    }

    @Test
    public void admin_role_should_have_associated_permissions() throws InterruptedException {
        List<String> permissions = rolesDao.getPermissionsByRole(Roles.ADMIN).toList().await();
        assertNotNull(permissions);
        assertTrue(permissions.contains(Permissions.USER_PROFILE_OTHERS_READ));
        assertTrue(permissions.contains(Permissions.USER_PROFILE_OTHERS_EDIT));
    }


    @Test
    public void admin_role_should_have_transitive_user_permissions() throws InterruptedException {
        List<String> permissions = rolesDao.getPermissionsByRoleRecursively(Roles.ADMIN).toList().await();
        assertNotNull(permissions);
        assertTrue(permissions.containsAll(rolesDao.getPermissionsByRole(Roles.ADMIN).toList().await()));
        assertTrue(permissions.containsAll(rolesDao.getPermissionsByRole(Roles.USER).toList().await()));
    }

    @Test
    public void application_admin_role_should_have_associated_permissions() throws InterruptedException {
        List<String> permissions = rolesDao.getPermissionsByRole(Roles.APPLICATION_ADMIN).toList().await();
        assertNotNull(permissions);
        assertTrue(permissions.contains(Permissions.APPLICATION_CONFIGURATION_READ));
        assertTrue(permissions.contains(Permissions.APPLICATION_CONFIGURATION_EDIT));
        assertFalse(permissions.contains(Permissions.USER_PROFILE_OTHERS_EDIT));
    }


    @Test
    public void applicarion_admin_role_should_have_transitive_user_permissions() throws InterruptedException {
        List<String> permissions = rolesDao.getPermissionsByRoleRecursively(Roles.APPLICATION_ADMIN).toList().await();
        assertNotNull(permissions);
        assertTrue(permissions.containsAll(rolesDao.getPermissionsByRole(Roles.APPLICATION_ADMIN).toList().await()));
        assertTrue(permissions.containsAll(rolesDao.getPermissionsByRole(Roles.USER).toList().await()));
    }

}
