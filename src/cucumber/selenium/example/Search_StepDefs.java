package cucumber.selenium.example;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search_StepDefs {
    WebDriver webDriver;
    WebDriverWait driverWait;
    int timeoutInSeconds = 30;

    @Given("^I open Google$")
    public void i_open_Google() throws Throwable {
        webDriver = new FirefoxDriver();
        webDriver.get("http://www.google.com/ncr");  // no country redirect
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
    }

    @Given("^I query on \"([^\"]*)\"$")
    public void i_query_on(String arg1) throws Throwable {
        WebElement queryText = webDriver.findElement(By.name("q"));
        queryText.sendKeys(arg1 + Keys.RETURN);
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("resultStats")));
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void i_should_see(String arg1) throws Throwable {
        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        assertThat(bodyText, containsString(arg1));
        webDriver.close();
    }
}
