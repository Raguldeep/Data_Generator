package BusinessMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Maven.designAccelartor.ExcelWriter;

public class Login_Page extends ExcelWriter{

	WebDriver driver;
	
	//public static void main(String[] args) throws InterruptedException {
		
		public static void Login() throws Exception {

		System.setProperty("webdriver.gecko.driver", "D://geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://automationpractice.com/index.php");
		Thread.sleep(5000);
		driver.findElement(By.id(""));
		

	}
}
