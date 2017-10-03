package appModules;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.IMDB_Page;
import stepDefinition.IMDB_Steps;
import webDriver.Driver;

import java.util.ArrayList;
import java.util.List;


public class IMDB_Search_Action {

	private static WebDriver driver = Driver.getCurrentDriver();
	private static WebDriverWait wait = new WebDriverWait(driver, 10);

	public static void search(String searchCriteria) {

		driver.get("http://www.imdb.com/");
		wait.until(ExpectedConditions.elementToBeClickable(IMDB_Page.searchField()));

		IMDB_Page.searchField().clear();
		IMDB_Page.searchField().sendKeys(searchCriteria);
		IMDB_Page.searchField().sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("findSubHeader"))));

	}

	public static boolean isNamePresent(String name) {
		String nameInElement = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/table/tbody/tr[1]/td[2]/a")).getText();
		return name.equals(nameInElement);
	}

	public static void clickOnLinkFor(String name) {
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/table/tbody/tr[1]/td[2]/a")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"filmo-head-director\"]/a"))));
	}

	public static int getFilmsDirected(String name) {
		String numberWithParensAndCreditWord = driver.findElement(By.xpath("//*[@id=\"filmo-head-director\"]")).getText();
		int numberDirected = Integer.parseInt(numberWithParensAndCreditWord.substring(16,numberWithParensAndCreditWord.length()-9));
		return numberDirected;
	}

	private static void clickOnLinkForDirectorCredits() {
		driver.findElement(By.xpath("//*[@id=\"filmo-head-director\"]/a")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"hide-director\"]"))));
	}

	public static List<IMDB_Steps.FilmNameAndYear> getFilmsDirected() {
		List<IMDB_Steps.FilmNameAndYear> filmsDirected = new ArrayList<>();
		clickOnLinkForDirectorCredits();

		List<WebElement> filmsByDirectorSection = driver.findElements(By.xpath("//*[@id=\"filmography\"]/div[4]/div"));
		for (WebElement filmByDirectorSection : filmsByDirectorSection) {
			WebElement year = filmByDirectorSection.findElement(By.xpath("./span"));
			WebElement film = filmByDirectorSection.findElement(By.xpath("./b/a"));
			IMDB_Steps.FilmNameAndYear filmNameAndYear = new IMDB_Steps.FilmNameAndYear(film.getText(), Integer.parseInt(year.getText().substring(1)));
			filmsDirected.add(filmNameAndYear);
		}

		return  filmsDirected;
	}

}
