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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jporm.rm.session.Session;
import com.jporm.types.io.ResultSetRowReader;

import ufo.primomiglio.auth.repository.UserRole;
import ufo.primomiglio.auth.repository.UserRoleDao;

@Repository
public class JdbcUserRoleDao implements UserRoleDao {

    @Override
    public List<String> getRolesByUserId(final Session session, Long userId) {
        return session.find("ur.roleName").from(UserRole.class, "ur").where().eq("ur.userId", userId)
                .fetch((ResultSetRowReader<String>) (rs, rowNum) -> rs.getString("ur.roleName"));

    }

}
