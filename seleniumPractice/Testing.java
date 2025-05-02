package seleniumPractice;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Testing {
	
	public static WebDriver driver;
	public Properties p;
	@BeforeTest
    @Parameters({"os","browser"})
	public void browseInitialization(String os, String br) throws IOException {
		
		
		
		FileReader file=new FileReader("C:\\Users\\Sasikumar\\eclipse-workspace\\DemoProjectRA\\resources\\config.properties");	
		 p=new Properties();
			p.load(file);
			
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}else if (os.equalsIgnoreCase("Linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}else {
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browsers"); return;
			}
			
			
				driver =new RemoteWebDriver(new URL("http://192.168.0.103:4444"),capabilities );
			System.out.println("Session running in selenium Grid");
			
		}
			
		// Local
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			 System.setProperty("webdriver.chrome.driver",
			 "c://ChromeDriver//chromedriver.exe"); 
			
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default: System.out.println("No matching browsers"); return;
			}
			
			
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		System.out.println("code running in local");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println("Page Title  " + driver.getTitle());
		System.out.println("Thread count is  " +Thread.currentThread().getId());
		driver.quit();
		
		
	}
	
	
}
