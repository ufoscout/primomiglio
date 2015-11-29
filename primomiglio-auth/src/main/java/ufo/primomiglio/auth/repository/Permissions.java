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

public interface Permissions {

    String USER_PROFILE_OWN_READ = "USER_PROFILE_OWN_READ";
    String USER_PROFILE_OWN_EDIT = "USER_PROFILE_OWN_EDIT";

    String USER_PROFILE_OTHERS_READ = "USER_PROFILE_OTHERS_READ";
    String USER_PROFILE_OTHERS_EDIT = "USER_PROFILE_OTHERS_EDIT";

    String APPLICATION_CONFIGURATION_READ = "APPLICATION_CONFIGURATION_READ";
    String APPLICATION_CONFIGURATION_EDIT = "APPLICATION_CONFIGURATION_EDIT";

}
