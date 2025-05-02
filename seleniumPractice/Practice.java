package seleniumPractice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practice {

	public static void main(String[] args) {

		try {
			WebDriver driver;
			System.setProperty("webdriver.chrome.driver", "c://ChromeDriver//chromedriver.exe");
			driver = new ChromeDriver();
			
			driver.get("https://www.google.com/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				
				driver.manage().window().maximize();
				
				
				
			} catch (Exception e) {
				
				System.out.println(e);
			}
		
		

	}

}
