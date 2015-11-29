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

import reactor.rx.Promise;
import reactor.rx.Stream;

public interface AuthApi {

    /**
     * Throws {@link UnauthorizedException} if the {@link UserContext} does not have the
     * required permission
     * @param userContext
     * @param permissionName
     * @return the {@link UserContext}
     */
    static UserContext neededPermisison(UserContext userContext, String permissionName) {
        return neededAnyPermisison(userContext, permissionName);
    }

    /**
     * Throws {@link UnauthorizedException} if the {@link UserContext} does not have at least
     * one of the required permissions
     * @param userContext
     * @param permissionName
     * @return the {@link UserContext}
     */
    static UserContext neededAnyPermisison(UserContext userContext, String... permissionsName) {
        if (!hasAnyPermission(userContext, permissionsName)) {
            throw new UnauthorizedException();
        }
        return userContext;
    }

    /**
     * Returns whether the {@link UserContext} has the required permission
     * @param userContext
     * @param permissionName
     * @return
     */
    static boolean hasPermission(UserContext userContext, String permissionName) {
        return hasAnyPermission(userContext, permissionName);
    }

    /**
     * Returns whether the {@link UserContext} has at least one of the required permissions
     * @param userContext
     * @param permissionsName
     * @return
     */
    static boolean hasAnyPermission(UserContext userContext, String... permissionsName) {
        for (String permissionName : permissionsName) {
            if (userContext.getPermissions().contains(permissionName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the roles of a user by id
     * @param id
     * @return
     */
    Stream<String> getRolesByUserId(Long id);

    Promise<UserContext> getUserContextByUserId(Long id);

}
