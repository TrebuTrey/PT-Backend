package week01;

public class Application {

	public static void main(String[] args) {

		/*
		 * Two Types of Data in Java
		 * 
		 * Primitive:
		 * int - integer
		 * double - decimal places
		 * float - precision decimal data type
		 * long - like int; numbers can be higher
		 * short - line an int; less number range
		 * byte - 8 bits of data
		 * char - a single character
		 * boolean - true/false
		 * 
		 * Object:
		 * String - textual data, a string of characters
		 */
		
		//variable declaration - type, identifier, assignment operator, vale, semicolon
		
		int age = 67;
		double accountBalance = 34.67;	//camelCase
		char middleInitial = 'C';		//char is 'single quote'
		boolean isHotOutside = true;	
		String firstName = "Sam";		//String type is "double quote"
		
		System.out.println(age);		//Print method
		
		//operator performs actions on operands
		// + - * /
		
		// + on numbers is simple addition
		// + on Strings is called concatenation
		
		boolean isAge30 = age == 30;	// comparator equals is ==
	}

}
