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
package ufo.primomiglio.backend.security.repository;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import reactor.core.dispatch.ThreadPoolExecutorDispatcher;
import reactor.rx.Stream;
import ufo.primomiglio.backend.BaseUnitTest;
import ufo.primomiglio.backend.security.repository.JdbcRolesDao;
import ufo.primomiglio.backend.security.repository.RolesDao;

public class JdbcRolesDaoTest extends BaseUnitTest {

    private RolesDao rolesDao = new JdbcRolesDao(new ThreadPoolExecutorDispatcher(10,10));

    @Test
    public void should_return_list_of_roles() throws InterruptedException {
        Stream<List<String>> roleStream = rolesDao.getRoles().buffer();
        assertNotNull(roleStream);
        List<String> queue = roleStream.toBlockingQueue().poll(1, TimeUnit.SECONDS);
        assertTrue(queue.contains("ADMIN"));
        assertEquals(3, queue.size());
    }

}
