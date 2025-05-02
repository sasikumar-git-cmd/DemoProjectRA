package restAssuredDemo;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.CoursePrice());

//Print no oof courses
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print purchase amount

		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

//print title of first course

	String titlefirstcourse	= js.get("courses[0].title");
		
		System.out.println(titlefirstcourse);
		
		
		//print title of last course

		String titleLastcourse	= js.get("courses[2].title");
			
			System.out.println(titleLastcourse);
			
//Print all course title and its prices
			
			for(int i = 0;i<count;i++) {
				
			String courseTitles = js.get("courses["+i+"].title");
				System.out.println(courseTitles);
				System.out.println(js.get("courses["+i+"].title").toString());
			}
			
		// Print no of copies sold by RPA
			for(int i = 0;i<count;i++) {
				
				String courseTitles = js.get("courses["+i+"].title");
				
				if(courseTitles.equalsIgnoreCase("RPA")) {
					int copies = js.get("courses["+i+"].copies");
					System.out.println(copies);
					break;
				}
			
			}
			
		
			
			
	}
}
