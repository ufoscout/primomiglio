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
package ufo.primomiglio.auth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.rx.Promise;
import reactor.rx.Stream;
import ufo.primomiglio.auth.repository.RolesDao;

@Service
public class SecurityApiImpl implements SecurityApi {

    private final RolesDao rolesDao;

    @Autowired
    public SecurityApiImpl(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    @Override
    public Stream<String> getRolesByUserId(Long id) {
        return rolesDao.getRoles();
    }

    @Override
    public Promise<UserContext> getUserContextByUserId(Long id) {
        return getRolesByUserId(id)
        .buffer()
        .next()
        .map(roles -> new UserContext(id, roles));
    }

}
