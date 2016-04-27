package cucumber.selenium.example;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)

@CucumberOptions(   monochrome = false,
                    tags     = "",
//                    features = { "src/test/resources/features" },
//                    glue     = { "src/cucumber/selenium/example" },
                    format   = { "pretty","html: cucumber-html-reports","json: cucumber-html-reports/cucumber.json" },
                    dryRun   = false
                )

public class CucumberTests {
}
