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

import java.util.Collections;
import java.util.List;

public class UserContext {

    private Long userId;
    private List<String> permissions;

    public UserContext() {
        this(-1l, Collections.EMPTY_LIST);
    }

    public UserContext(Long userId, List<String> permissions) {
        this.userId = userId;
        this.permissions = permissions;
    }

    /**
     * @return the permissions
     */
    public List<String> getPermissions() {
        return permissions;
    }
    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
