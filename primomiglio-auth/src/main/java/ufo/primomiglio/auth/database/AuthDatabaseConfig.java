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
package ufo.primomiglio.auth.database;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthDatabaseConfig {

	/**
	 * Creates or updates the database for the auth module
	 * @return
	 * @throws SQLException
	 */
//	@Bean
//	public SpringLiquibase authLiquibase(DataSource dataSource) throws SQLException {
//		SpringLiquibase liquibase = new SpringLiquibase();
//		liquibase.setDataSource(dataSource);
//		liquibase.setChangeLog("classpath:auth/db/changelog/db.changelog-master.xml");
//		return liquibase;
//	}

}
