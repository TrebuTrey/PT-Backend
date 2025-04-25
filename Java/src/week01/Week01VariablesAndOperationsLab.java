//
// Copyright (c) 2023 Promineo Tech
// Author:  Promineo Tech Academic Team
// Subject:  Variables & Operations Lab
// Java Week 01 Lab  
//
package week01;

public class Week01VariablesAndOperationsLab {

	public static void main(String[] args) {
		System.out.println("Week 1 Lab");
		
		// 1. Create a variable to hold the quantity of 
		//		available plane seats left on a flight		
		
		int seatsAvailable = 15;
		
		// 2. Create a variable to hold the cost of groceries at checkout
		double groceryTotal = 45.65;
		
		// 3. Create a variable to hold a person's middle initial
		char middleInitial = 'R';
	
		// 4. Create a variable to hold true if it's hot outside 
		//		and false if it's cold outside
		boolean isHotOutside = false;
		
		// 5. Create a variable to hold a customer's first name
		String firstName = "Trey";
		
		// 6. Create a variable to hold a street address
		String address = "123 Drury Lane";

		// 7. Print all variables to the console
		System.out.println(seatsAvailable);
		System.out.println(groceryTotal);
		System.out.println(middleInitial);
		System.out.println(isHotOutside);
		System.out.println(firstName);
		System.out.println(address);
		
		// 8. A customer booked 2 plane seats, 
		//		remove 2 seats from the available seats variable
		seatsAvailable -= 2;

		// 9. Impulse candy bar purchase, add 2.15 to the grocery total
		// 		costOfGroceries = costOfGroceries + 2.15;
		groceryTotal += 2.15;

		// 10.  The birth certificate was printed incorrectly, 
		//		change the middle initial to something else
		middleInitial = 'Q';

		// 11.  The season has changed, update the hot outside 
		//			variable to be opposite of what it was
		isHotOutside = true;

		// 12.  Create a new variable called full name using the customer's first name, 
		//			the middle initial, and a last name of your choice
		String fullName = firstName + " " + middleInitial + ". Robinson";

		// 13.  Print a line to the console that introduces the customer and says they live
		// 			at the address variable
		
		System.out.println("Hello, this is " + fullName + ", and they live at " + address);
	}

}
