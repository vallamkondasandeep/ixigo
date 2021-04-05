package test.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {

	protected WebDriver driver;
	private By From = By.xpath("//div[text()='From']/following-sibling::input");
	private By FromPlaceholder = By
			.xpath("//div[text()='From']/parent::div/following-sibling::div[contains(@class,'clear-input')]");
	private By To = By.xpath("//div[text()='To']/following-sibling::input");
	private By Search = By.xpath("//div[contains(@class,'search')]");
	private By DepartureDate = By.xpath("//div[text() ='Departure']/following-sibling::input[@readonly]");
	private By ReturnDate = By.xpath("//div[text() ='Return']/following-sibling::input[@readonly]");
	private By Travellers = By.xpath("//div[contains(@class ,'passanger-class')]/div/div[@class='input-label']");
	private By FromAutocompleter = By.xpath(
			"//div[text()='From']/parent::div/following-sibling::div[contains(@class,'autocompleter') and @style='display: block;']");
	private By ToAutocompleter = By.xpath(
			"//div[text()='To']/parent::div/following-sibling::div[contains(@class,'autocompleter') and @style='display: block;']");

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void Waits(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}

	public void selectTo(String countrycode) {
		driver.findElement(To).sendKeys(countrycode);
		WebElement toAutocompleter = driver.findElement(ToAutocompleter);
		Waits(toAutocompleter);

		WebElement ele = driver.findElement(By.xpath(
				"//div[text()='To']/parent::div/following-sibling::div//div[contains(@class,'result-row')]//div[contains(text(),'"
						+ countrycode + "')]"));
		Waits(ele);
		ele.click();
	}

	public void selectFrom(String countrycode) {
		driver.findElement(FromPlaceholder).click();
		WebElement from = driver.findElement(From);
		Waits(from);
		from.sendKeys(countrycode);

		WebElement fromAutocompleter = driver.findElement(FromAutocompleter);
		Waits(fromAutocompleter);

		WebElement ele = driver.findElement(By.xpath(
				"//div[text()='From']/parent::div/following-sibling::div//div[contains(@class,'result-row')]//div[contains(text(),'"
						+ countrycode + "')]"));
		Waits(ele);
		ele.click();

	}

	public void DepatureDate(String Date) {

		driver.findElement(DepartureDate).click();
		WebElement ele = driver.findElement(By.xpath("//td[@class ='rd-day-body' and @data-date='" + Date + "']"));
		Waits(ele);
		ele.click();

	}

	public void ReturnDate(String Date) {

		driver.findElement(ReturnDate).click();

		WebElement elew = driver.findElement(By.xpath(
				"//div[@class='rd-container flight-ret-cal extra-bottom rd-container-attachment' and contains(@style,'display: inline-block')]"));

		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOf(elew));

		WebElement ele = driver.findElement(By.xpath(
				"//div[contains(@class,'flight-ret-cal')]//td[@class ='rd-day-body' and @data-date='" + Date + "']"));
		Waits(ele);

		ele.click();

	}

	public void Travellers(String Type, String NoOfPersons) {

		driver.findElement(Travellers).click();

		WebElement ele = driver.findElement(By.xpath("//div[text()='" + Type
				+ "']/parent::div/following-sibling::div/span[@data-val='" + NoOfPersons + "']"));
		Waits(ele);
		ele.click();
	}

	public void Search() {
		driver.findElement(Search).click();
	}

	public void verifyBasePageTitle() {

		String expectedPageTitle = "ixigo - Flights, IRCTC Train Booking, Bus Booking, Air Tickets & Hotels";
		Assert.assertEquals(driver.getTitle(), expectedPageTitle);
	}
}
