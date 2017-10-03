package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webDriver.Driver;

public class IMDB_Page {

	private static WebDriver driver = Driver.getCurrentDriver();

	public static WebElement searchField() {
		return driver.findElement(By.id("navbar-query"));
	}

	public static WebElement searchButton() {
		return driver.findElement(By.id("navbar-submit-button"));
	}

}
