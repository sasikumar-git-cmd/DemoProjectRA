package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class Browserstack_Jenkins {

public RemoteWebDriver driver;

    public static final String USERNAME = "imman_edrfvf";
    public static final String ACCESS_KEY = "unXqgsrRGECYmPMNW9sP";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    
   @BeforeClass
    	public  void initial_Cap() throws MalformedURLException {
    		 ChromeOptions options = new ChromeOptions();

 	        // W3C-style capabilities for BrowserStack
 	        HashMap<String, Object> browserstackOptions = new HashMap<>();
 	        browserstackOptions.put("os", "Windows");
 	        browserstackOptions.put("osVersion", "10");
 	        browserstackOptions.put("sessionName", "My W3C Chrome Test"); // Optional
 	        browserstackOptions.put("buildName", "Build #1"); // Optional

 	        options.setCapability("bstack:options", browserstackOptions);
 	        options.setCapability("browserName", "Chrome");
 	        options.setCapability("browserVersion", "latest");

 	         driver = new RemoteWebDriver(new URL(URL), options);
 	      
    	}
    	@Test       
    	public  void validateUI() {
    	        try {
    	        	 driver.get("https://tutorialsninja.com/demo/");
					 System.out.println("Page Title: " +driver.getTitle());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	       

	
    	}
	@AfterClass
    	public  void tearBrowser() {
    		 driver.quit();
    		 System.out.println("Driver closed");
    	}
    	

}
