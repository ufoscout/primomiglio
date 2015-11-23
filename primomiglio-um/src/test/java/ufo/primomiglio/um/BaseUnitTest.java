/*******************************************************************************
 * Copyright 2013 Francesco Cina'
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
package ufo.primomiglio.um;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Francesco Cina
 *
 *         20/mag/2011
 */
@RunWith(BlockJUnit4ClassRunner.class)
public abstract class BaseUnitTest {

    static {
        //System.setProperty("derby.stream.error.field", DerbyNullOutputUtil.NULL_DERBY_LOG);
    }

    @Rule
    public final TestName name = new TestName();

    private long startTime;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Logger getLogger() {
        return logger;
    }

    @Before
    public void setUpBeforeTest() {

        startTime = System.currentTimeMillis();

        logger.info("==================================================================="); //$NON-NLS-1$
        logger.info("BEGIN TEST " + name.getMethodName()); //$NON-NLS-1$
        logger.info("==================================================================="); //$NON-NLS-1$

    }

    @After
    public void tearDownAfterTest() {

        final String time = new BigDecimal(System.currentTimeMillis() - startTime).divide(new BigDecimal(1000)).toString();

        logger.info("==================================================================="); //$NON-NLS-1$
        logger.info("END TEST " + name.getMethodName()); //$NON-NLS-1$
        logger.info("Execution time: " + time + " seconds"); //$NON-NLS-1$ //$NON-NLS-2$
        logger.info("==================================================================="); //$NON-NLS-1$

    }

}
