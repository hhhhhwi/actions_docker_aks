package com.hwiyeon.actions_docker_aks;

import io.restassured.RestAssured;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

@TestConfiguration
public class RestAssuredTestConfig extends AbstractTestExecutionListener {
    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        RestAssured.port = testContext.getApplicationContext().getEnvironment().getProperty("local.server.port", Integer.class, 8080);
    }
}
