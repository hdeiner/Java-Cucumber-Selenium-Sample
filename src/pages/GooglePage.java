package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
    private WebDriver webDriver;
    private int timeoutInSeconds = 30;

    public GooglePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void load(){
        webDriver.get("http://www.google.com/ncr");  // no country redirect
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
    }

    public void searchFor(String searchTerms){
        WebElement queryText = webDriver.findElement(By.name("q"));
        queryText.sendKeys(searchTerms + Keys.RETURN);
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("resultStats")));
    }

    public String getBodyText(){
        return webDriver.findElement(By.tagName("body")).getText();
    }
}
