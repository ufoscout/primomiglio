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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import reactor.rx.Stream;
import reactor.rx.Streams;

@Repository
public class InMemoryRolesDao implements RolesDao {

    private final Map<String, List<String>> roleHierarchy = new HashMap<>();
    private final Map<String, List<String>> permissionsByRole = new HashMap<>();

    public InMemoryRolesDao() {

        roleHierarchy.put(Roles.USER, Arrays.asList(Roles.USER));
        roleHierarchy.put(Roles.ADMIN, Arrays.asList(Roles.USER, Roles.ADMIN));
        roleHierarchy.put(Roles.APPLICATION_ADMIN, Arrays.asList(Roles.USER, Roles.APPLICATION_ADMIN));

        permissionsByRole.put(Roles.USER, Arrays.asList(
                            Permissions.USER_PROFILE_OWN_READ,
                            Permissions.USER_PROFILE_OWN_EDIT
                         ));

        permissionsByRole.put(Roles.ADMIN, Arrays.asList(
                            Permissions.USER_PROFILE_OTHERS_READ,
                            Permissions.USER_PROFILE_OTHERS_EDIT
                        ));

        permissionsByRole.put(Roles.APPLICATION_ADMIN, Arrays.asList(
                            Permissions.APPLICATION_CONFIGURATION_READ,
                            Permissions.APPLICATION_CONFIGURATION_EDIT
                        ));
    }

    @Override
    public Stream<String> getPermissionsByRole(String roleName) {
        return Streams.createWith((count, sub) -> {
            Optional.ofNullable(permissionsByRole.get(roleName))
            .ifPresent(permissions -> {
                permissions.forEach(permission -> sub.onNext(permission));
            });
            sub.onComplete();
        });
    }

    @Override
    public Stream<String> getRelatedRoles(String roleName) {
        return Streams.createWith((count, sub) -> {
            Optional.ofNullable(roleHierarchy.get(roleName))
            .ifPresent(roles -> {
                roles.forEach(role -> sub.onNext(role));
            });
            sub.onComplete();
        });
    }

    @Override
    public Stream<String> getPermissionsByRoleRecursively(String roleName) {
        return getRelatedRoles(roleName)
        .flatMap(role -> getPermissionsByRole(role));
    }

//    @Override
//    public Stream<String> getRoles() {
//        return Streams.createWith((Long demand, SubscriberWithContext<String, Void> sub) -> {
//            System.out.println("Demand: " + demand + ". Thread: " + Thread.currentThread().getName());
//            sub.onNext("ADMIN");
//            sub.onNext("USER");
//            sub.onNext("OTHER");
//            sub.onComplete();
//        }).subscribeOn(dispatcher);
//
//    }

}