package test.company.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchResultsPage {

	protected WebDriver driver;
	String[] expectedStops = { "Non stop", "1 stop", "1+ stops" };
	String[] expectedDepartures = { "Early Morning", "Morning", "Mid Day", "Night" };
	private By FilterStops = By.xpath("//div[@class='stops']//div[@class='stop-info']");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectFilters(String stops) {

		WebElement Filter_Stops = driver
				.findElement(By.xpath("//div[@class='stops']//div[@class='stop-info' and text()='" + stops + "']"));
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOf(Filter_Stops));
		Filter_Stops.click();

	}

	public void validateStopsFilterOptions() {

		List<WebElement> list = driver.findElements(FilterStops);

		for (WebElement ele : list) {
			{
				boolean match = false;
				for (int i = 0; i < expectedStops.length; i++) {
					if (ele.getText().equals(expectedStops[i])) {
						match = true;
					}
				}
				Assert.assertTrue(match);
			}
		}
	}

	public void validateDepatureFilterOptions(String CountryCode) {

		List<WebElement> list = driver.findElements(
				By.xpath("//div[text()='Departure from " + CountryCode + "']/parent::div//div[@class='tmng-btn']/div"));

		for (WebElement ele : list) {
			{
				boolean match = false;
				for (int i = 0; i < expectedDepartures.length; i++) {
					if (ele.getText().equals(expectedDepartures[i])) {
						match = true;
					}
				}
				Assert.assertTrue(match);
			}
		}
	}

	public void printAirlines(int fare) {

		List<WebElement> Airline = driver
				.findElements(By.xpath("//div[@class='result-col outr']//div[@class='airline-text']/div"));
		for (int i = 1; i <= Airline.size(); i++) {

			WebElement PriceElement = driver
					.findElement(By.xpath("//div[@class='result-col outr']//div[contains(@class,'c-flight')][" + i
							+ "]//div[contains(@class,'c-price-display')]/span[@class='']"));

			String price = PriceElement.getText();
			if (Integer.parseInt(price) < fare) {

				WebElement AirlineNumber = driver
						.findElement(By.xpath("//div[@class='result-col outr']//div[contains(@class,'c-flight')][" + i
								+ "]//div[@class='airline-text']/div"));
				WebElement DepatureTime = driver
						.findElement(By.xpath("//div[@class='result-col outr']//div[contains(@class,'c-flight')][" + i
								+ "]//div[@class='time-group']/div[@class='time'][1]"));

				System.out.println("Airline Number : " + AirlineNumber.getText() + "               Departure Time : "
						+ DepatureTime.getText() + "             Fare : " + price);

			}

		}

	}

}
