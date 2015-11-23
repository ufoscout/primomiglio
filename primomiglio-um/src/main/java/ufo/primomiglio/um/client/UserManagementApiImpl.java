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
package ufo.primomiglio.um.client;

import org.springframework.stereotype.Service;

import reactor.rx.Promise;
import reactor.rx.Promises;

@Service
public class UserManagementApiImpl implements UserManagementApi {

    @Override
    public Promise<User> getUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("ufoscout");
        user.setFirstName("Francesco");
        user.setLastName("Cina");
        return Promises.success(user);
    }

    @Override
    public Promise<User> getUserByUsername(String username) {
        return getUserById(0l);
    }

}
