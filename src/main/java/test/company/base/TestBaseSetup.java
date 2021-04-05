package test.company.base;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBaseSetup {

	public WebDriver driver;
	public String appUrl = "https://www.ixigo.com/";
	public String browserType = "chrome";
	static Path driverPath = Paths.get("src/test/resources");

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.setProperty("webdriver.chrome.driver", driverPath.toAbsolutePath() + "\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		return driver;
	}

	@BeforeClass
	public void initializeTestBaseSetup() {
		try {
			setDriver(browserType, appUrl);

		} catch (Exception e) {
			System.out.println("Error" + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
