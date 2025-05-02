package files;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="http://localhost:8080";
//Login scenario
		SessionFilter session = new SessionFilter();
		//Launch
		String response = given().relaxedHTTPSValidation().header("Content-Type","application/json").body("{ \"username\": \"Imman221\", \"password\": \"Jira@123\" }")
		.log().all().filter(session).when().post("http://localhost:8080/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
		String expectedMessage = "Hi How are you";
		
		// Add Comments
		
		String  addCommentsResponse = given().pathParam("key", "10005").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \""+expectedMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(addCommentsResponse);
		String commentID = js.get("id");
		
		
		
		//Add attachement
		
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key", "10005").
		header("Content-Type","multipart/form-data").
		multiPart("file",new File("jira.txt")).when().post("rest/api/2/issue/{key}/attachments").then().log().all()
		.assertThat().statusCode(200);
		
		//Get issue
		
		String issueDetails = given().filter(session).pathParam("key", "10005").queryParam("fields", "comment")
				.log().all().when().get("rest/api/2/issue/{key}")
		.then().log().all().extract().response().asString();
		System.out.println(issueDetails);
		
		JsonPath js1 = new JsonPath(issueDetails);
		int commentCount = js1.get("fields.comment.comments.size()");
		
		for(int i=0;i<commentCount;i++) {
			
		String commentIDisuue = js1.get("fields.comment.comments["+i+"].id").toString();
			
		if(commentIDisuue.equalsIgnoreCase(commentID)) {
			
		String message = js1.get("fields.comment.comments["+i+"].body").toString();
			System.out.println(message);
			Assert.assertEquals(message, expectedMessage);
		}
		
		}
		
		
		
	}

}
