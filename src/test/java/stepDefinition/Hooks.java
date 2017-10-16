package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import supportMethods.FileRead;
import testRunner.TestRunner;
import webDriver.Driver;

import java.io.IOException;

public class Hooks {

	@Before
	public void beforeAll() throws IOException, InterruptedException {
		Boolean runOnce = false;
		if (!runOnce) {
			TestRunner.config = FileRead.readProperties();
		}
	}
	
	@Before
	public void before(Scenario scenario) {
		TestRunner.scenario = scenario;
	}

	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {
			Driver.embedScreenshot();
		}
	}

}
