package seleniumPractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Sample {

	public static void main(String[] args) {

		/*
		 * int[] ar = new int[5]; 
		 * ar [0]= 12;
		 */
		
		//remove Duplicates Array*********************************************************************************
		/*
		 * int[] arr = {4,5,9,9,3,4,5};
		 * 
		 * Set<Integer> set = new HashSet<Integer>(); 
		 * for(int i=0;i<arr.length;i++) {
		 * 
		 * set.add(arr[i]); }
		 * 
		 * Integer[] b = set.toArray(new Integer[set.size()]);
		 * 
		 * System.out.println(Arrays.toString(b));
		 */
	//****************************************************************************************************************************	
		//Remove Duplicates in String
		
		
		//Approach 1
		/*
		 * String str = "programming";
		 * 
		 * StringBuilder sb = new StringBuilder(); 
		 * str.chars().distinct().forEach(c -> sb.append((char) c));
		 * 
		 * 
		 * System.out.println(sb);
		 */
		
		//Approach 4
		String str1 = "Java Programming";
		StringBuilder sb1 = new StringBuilder(); //Step 1
		Set<Character> set = new LinkedHashSet<Character>(); //Step 2
		for(int i =0;i<str1.length();i++) { //Step 3
			set.add(str1.charAt(i)); //Step 4
		}
		
		for(Character c : set) {//Step 5
			sb1.append(c); //Step 6
		}
		
		System.out.println(sb1);
//***************************************************************************************************************************************
	//	String occurrence By single Character
		
	   String strr = "Hello Sasi";
		char ch = 'l';
	   int count = 0;
	   
       for(int i=0;i<=strr.length()-1;i++) {
    	  if(strr.charAt(i)== ch ){
    		  
    		  count++;
    		  
    	  }
    	   
    	   }
       
      System.out.println(count);
 //*************************************************************************************************************************************
      //String occurrence
       
      String stri = "Programming";
      
      Map<Character,Integer> mapcount = new HashMap<>();
      
      for(char ch1 : stri.toCharArray()) {
    	  
    	  if(mapcount.containsKey(ch1)) {
    		  mapcount.put(ch1, mapcount.get(ch1)+1);
    	  }else {
    		  mapcount.put(ch1, 1);
    	  }
      }
      
      System.out.println("Occurrence count is " + mapcount);
		
	}

	//****************************************************************************************************************************
	public int dropdown(WebElement element, int count) {
	
	
	Select select = new Select(element);
	select.selectByIndex(count);
	select.selectByVisibleText("");
	select.selectByValue("");
	return count;
	
	
	
	
	}
}
