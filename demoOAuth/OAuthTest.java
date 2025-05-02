package demoOAuth;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String [] courses = {"Selenium","Protractor"};
		
		System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth/identifier?access_type=offline&client_id=587594460880-u53ikl5ast2sup28098ofsm9iku8vvm6.apps.googleusercontent.com&code_challenge=TIPrzlcbgKKkgJOb-EZvya__Is282zwOY_KHGEgb4D0&code_challenge_method=S256&include_granted_scopes=true&prompt=select_account%20consent&redirect_uri=https%3A%2F%2Fsso.teachable.com%2Fidentity%2Fcallbacks%2Fgoogle%2Fcallback&response_type=code&scope=email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile%20openid%20profile&state=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJnb29nbGUiLCJpYXQiOjE2ODg0ODYyNjAsImV4cCI6MTY4ODQ4ODA2MCwianRpIjoiMjU3Mzc0NTgtMmVjYS00MDQyLTg3ODYtZjdiMzU1MzE1NWMwIiwiaXNzIjoic2tfeno4dHc2ZGciLCJzdWIiOiIya01pNHRLWHVRNnQxbF9CR1RSbl9kRkk3bXlOZUNLZGpyd01hWm1GZkJiTzRHTEo2SXNKZnFmYmZkcEwzRm5oQkhjeV9wN0pQUUtkSFYzcUc3SEJSdyJ9.9JO4C6-c7_MLtqyetHst-4eUZGIi3dEB44l56p0ayps&service=lso&o2v=2&flowName=GeneralOAuthFlow");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Imman221@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		  driver.findElement(By.cssSelector("input[type='password']")).sendKeys(
		  "Imman@7531");
		  driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.
		  ENTER); Thread.sleep(2000); String url = driver.getCurrentUrl();
		 

//		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		System.out.println(url);
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println(code);

		String accessTokenResponse = given().urlEncodingEnabled(false).queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		/*
		 * String response = given().queryParam("access_token",
		 * accessToken).when().log().all()
		 * .get("https://rahulshettyacademy.com/getCourse.php").asString();
		 * 
		 * System.out.println(response);
		 */
		
		GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

		System.out.println(gc.getLinkedin());
		System.out.println(gc.getInstructor());
		gc.getCourses().getApi().get(1).getCourseTitle();
		
		List<Api> apiCourses = gc.getCourses().getApi();
		
		for(int i = 0;i<apiCourses.size();i++) {
			
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("Soap UI")) {
				
				System.out.println(apiCourses.get(i).getPrice());
				
				
				
				
			}
			
		}
		// Get the Course name opf WebAutomation
		
		ArrayList<String> a = new ArrayList<String>();
		
		List<WebAutomation> webElementCourses = gc.getCourses().getWebAutomation();
		
		for(int i=0;i<webElementCourses.size();i++) {
			
		a.add(webElementCourses.get(i).getCourseTitle());
			
		List<String> expectedCourse  = Arrays.asList(courses);
		
		Assert.assertTrue(a.equals(expectedCourse));
			
		
		
		}

	}

}
