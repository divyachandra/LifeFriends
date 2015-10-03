package com.csuf;

import org.junit.*;
import org.junit.rules.TestName;

import java.util.logging.Logger;

public abstract class BaseTestCase extends Assert {
    protected static Logger log;
    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void setUpClass() throws Exception {
        //
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // no-op
    }

    @Before
    public void setUp() throws Exception {
        log = Logger.getLogger(BaseTestCase.class.getName());
        log.info("JUNIT: Starting test " + getName());
    }

    @After
    public void tearDown() throws Exception {
        log.info("JUNIT: Ending test " + getName());
    }

    private String getName() {
        return name.getMethodName();
    }
}
