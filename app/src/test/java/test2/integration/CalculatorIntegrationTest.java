package test2.integration;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import test2.integration.step.FirstStepOfIntegrationTest;
import test2.integration.step.SecondStepOfIntegrationTest;
import test2.integration.step.ThirdStepOfIntegrationTest;

@Suite
@SelectClasses({
    FirstStepOfIntegrationTest.class,
    SecondStepOfIntegrationTest.class,
    ThirdStepOfIntegrationTest.class
})
class CalculatorIntegrationTest {
}
