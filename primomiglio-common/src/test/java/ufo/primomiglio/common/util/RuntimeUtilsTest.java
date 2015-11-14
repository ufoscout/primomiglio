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
package ufo.primomiglio.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ufo.primomiglio.common.BaseAbstractTest;

public class RuntimeUtilsTest extends BaseAbstractTest {

    @Test
    public void CPU_cores_should_be_more_than_zero() {

        int cores = RuntimeUtils.numberOfCores();
        getLogger().info("CPU available cores: {}", cores);
        assertTrue(cores > 0);

    }

}
