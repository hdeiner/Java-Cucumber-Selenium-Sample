package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import java.util.Map;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources/featureFiles" },
		glue = { "webDriver", "stepDefinition", "testRunner" },
		tags = { "~@ignore"},
		plugin = {"pretty"})
public class TestRunner {
	public static Map<String, String> config;
	public static Scenario scenario;
}
