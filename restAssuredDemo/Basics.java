package restAssuredDemo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



import files.Payload;
import files.ReUsableMethod;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Validate add place is working
		
				//Given - input details all API's
				//When - submit the API's - resources and http method
				//Then - Validate the response 
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response()
		.asString();


		System.out.println(response);
		
		//Add place > Update place with new address ? Get place validate new address changed to present
		
		JsonPath js = new JsonPath(response); // for parsing Json
		
		String placeID = js.get("place_id");
		
		System.out.println(placeID);
		
		
		//Update Place
		String addNewaddress = "Summer walk, affrica";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+addNewaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then()
		.assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		
		//Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().put("maps/api/place/get/json").
		then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReUsableMethod.rawToJson(getPlaceResponse);
		
		String actualAddress = js1.getString("address");
		
		System.out.println(actualAddress);
		
		
		//TestNg
		
			
		
	}

}
