package seleniumPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ParallelExecution {

	WebDriver driver;
	@BeforeTest
	@Parameters ("browser")
	public void browseInitialization(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			
		System.setProperty("webdriver.chrome.driver", "c://ChromeDriver//chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://www.google.com/");
		}else {
			
		}
	}
	
	@Test
	public void perform() {
		System.out.println("Page Title  " + driver.getTitle());
		System.out.println("Thread count is  " +Thread.currentThread().getId());
		System.out.println("Parallel ");
	}
	
	@AfterTest
	
	public void quitBrowser() {
		
		driver.quit();
		
	}
	
}
