package cucumber.selenium.example;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search_StepDefs {
    static boolean hasHookBeenAdded = false;
    static String browserInUse = null;
    static WebDriver webDriver;
    WebDriverWait driverWait;
    int timeoutInSeconds = 30;

    @Before
    public void setUp() {
        if(!hasHookBeenAdded) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    if (webDriver != null) {
                        webDriver.close();
                        webDriver.quit();
                    }
                }
            });
            hasHookBeenAdded = true;
        }

    }

    @Given("^I am using \"([^\"]*)\" and open Google$")
    public void i_am_using_and_open_Google(String browserToUse) throws Throwable {
        if (browserInUse != null) {
            if ((!(browserToUse.equals(browserInUse)))) {
                webDriver.close();
                webDriver.quit();
            }
        }

        if (!(browserToUse.equals(browserInUse))) {
            if (browserToUse.equals("Firefox")) {
                webDriver = new FirefoxDriver();
            }

            if (browserToUse.equals("Chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\lib\\" + "chromedriver.exe");
                webDriver = new ChromeDriver();
            }

            if (browserToUse.equals("Internet Explorer")) {
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\lib\\" + "IEDriverServer.exe");
                webDriver = new InternetExplorerDriver();
            }

            browserInUse = browserToUse;
        }

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
    }

    @After
    public void cleanUp() {
    }
}