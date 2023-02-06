package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitHelper {

		public WebDriver driver;
		
		public WaitHelper(WebDriver driver) {
			this.driver = driver;
		}
		
		// Explicit wait
		// Wait for element till its visible in web page
		public void WaitForElement(WebElement element, long timeOutInSeconds) {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
					wait.until(ExpectedConditions.visibilityOf(element));
		}
}

// implicit wait is applicable to all elements in same class
// explicit wait is specific to element and condition-based not time-based