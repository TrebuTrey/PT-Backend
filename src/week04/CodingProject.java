package week04;

import java.util.Arrays;
import java.util.Optional;

public class CodingProject {
	public static void main(String[] args) {
		
		// Coding Step 1
		int[] ages = {3, 9, 23, 64, 2, 8, 28, 93};
		System.out.println("Problem 1 ages: " + Arrays.toString(ages));
		System.out.println("Problem 1a: " + Arrays.toString(subtractFirstLast(ages)));
		System.out.println("Problem 1c: Average for ages: " + calculateAverage(ages));
		
		int[] ages2 = {3, 9, 23, 64, 2, 8, 28, 93, 105, 44};
		System.out.println("Problem 1 ages2: " + Arrays.toString(ages2));
		System.out.println("Problem 1b:" + Arrays.toString(subtractFirstLast(ages2)));
		System.out.println("Problem 1c: Average for ages2: " + calculateAverage(ages2));
		System.out.println();
		
		// Coding Step 2
		String[] names = {"Sam", "Tommy", "Tim", "Sally", "Buck", "Bob"};
		System.out.println("Problem 2 names: " + Arrays.toString(names));
		System.out.println("Problem 2a: Average length of name: " + calculateAverage(names));
		System.out.println("Problem 2b:" + concatenateNames(names));
		System.out.println();
		
		// Coding Step 3
		/*
		 *  Elements at the end of the array are retrieved by typing
		 *  
		 *  Array[Array.length - 1]
		 */
		System.out.println("Problem 3: Array[Array.length - 1]");
		System.out.println();
		
		// Coding Step 4
		/*
		 * Elements at the beginning of the array are retrieved by typing
		 * 
		 * Array[0]
		 */
		System.out.println("Problem 4: Array[0]");
		System.out.println();
		
		// Coding Step 5
		int[] nameLengths = new int[names.length];
		for (int i = 0; i < nameLengths.length; i++) {
			nameLengths[i] = names[i].length();
		}
		System.out.println("Problem 5 nameLengths: " + Arrays.toString(nameLengths));
		System.out.println();
		
		// Coding Step 6
		System.out.println("Problem 6: " + calculateAverage(nameLengths) * nameLengths.length); //reverse the division of sum/array_length by multiplying the array_length for the return of sum
		System.out.println();
		
		// Coding Step 7
		System.out.println("Problem 7: " + concatenateItself("Hello", 3));
		System.out.println();
		
		// Coding Step 8
		System.out.println("Problem 8: " + concatenateNames("John", "Doe"));
		System.out.println();
		
		// Coding Step 9
		System.out.println("Problem 9 using ages2 array: " + isGreaterThan100(ages2)); //Expected True
		System.out.println("Problem 9 using nameLengths array:" + isGreaterThan100(nameLengths)); //Expected False
		System.out.println();
		
		// Coding Step 10
		double[] currency = {63, 19, 123, 64, 2, 48, 28, 93};
		System.out.println("Problem 10 currency: " + Arrays.toString(currency));
		System.out.println("Problem 10: The average money in currency: $" + calculateAverage(currency));
		System.out.println();
		
		// Coding Step 11
		double[] currency2 = {63, 19, 123, 64, 12, 48, 28, 93};
		System.out.println("Problem 11 currency2: " + Arrays.toString(currency2));
		System.out.println("Problem 11: Is currency more than currency2? " + isGreaterThan(currency, currency2));
		System.out.println();
		
		// Coding Step 12
		System.out.println("Problem 12: Will I buy a drink today? " + willBuyDrink(true, 10.75));
		System.out.println();
		
		// Coding Step 13
		/*
		 * The method whatCanIBuy takes two params:
		 * 
		 * Boolean billsPaid
		 * double spendingMoney
		 * 
		 * The point of this method was for me to practice my ternary operators
		 * as an alternative to if-else and switch statements
		 * 
		 * If billsPaid are true, then I check the amount of spendingMoney
		 * against 500, 220, and 15 to see what I have available money to 
		 * spend on a Playstation, 3D printing, or a lollipop.
		 */
		whatCanIBuy(true, 412.5);
		
	}
	
	public static int[] subtractFirstLast(int[] nums) {
		// For Step 1
		nums[nums.length - 1] = nums[nums.length - 1] - nums[0];
		
		return nums;
	}
	
	public static double calculateAverage(int[] nums) {
		// For Step 1, 9
		double sum = 0.0;
		for (int num: nums) {
			sum += num;
		}
		return sum/nums.length;
	}
	
	public static double calculateAverage(double[] nums) {
		// For Step 10, 11
		double sum = 0.0;
		for (double num: nums) {
			sum += num;
		}
		return sum/nums.length;
	}
	
	public static double calculateAverage(String[] names) {
		// For Step 2, 6
		double sum = 0.0;
		for (String name: names) {
			sum += name.length();
		}
		return sum/names.length;

	}
	
	public static String concatenateNames(String[] words) {
		// For Step 2, 8
		StringBuilder sb = new StringBuilder();
		for (String word: words) {
			sb.append(word);
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	public static String concatenateNames(String firstName, String lastName) {
		// For Step 8
		String[] bothNames = {firstName, lastName};
		
		return concatenateNames(bothNames);
	}
	
	public static String concatenateItself(String str, int n) {
		// For Step 7
		String returnString = "";
		for (int i = 1; i <= n; i++) {
			returnString += str;
		}
		return returnString;
	}
	
	public static Boolean isGreaterThan100(int[] nums) {
		//For Step 9
		double sum = calculateAverage(nums) * nums.length;
		
		return sum > 100.0;
	}
	
	public static Boolean isGreaterThan(double[] arr1, double[] arr2) {
		// For Step 11
		double avgArr1 = calculateAverage(arr1);
		double avgArr2 = calculateAverage(arr2);
		
		return avgArr1 > avgArr2;
	}
	
	public static Boolean willBuyDrink(Boolean isHotOutside, double moneyInPocket) {
		// For Step 12
		if (isHotOutside && moneyInPocket > 10.5) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void whatCanIBuy(Boolean billsPaid, double spendingMoney) {
		// For Step 13
		String option = "";
		if (billsPaid) {
			option = (spendingMoney >= 500.0) ? "I can buy a Playstation!": (spendingMoney >= 220.0) ? "I can buy some stuff for my 3D printer!" : (spendingMoney >= 15.0) ? "I can buy a lollipop": "I should probably just save what I have";
		} else {
			option = "Let's wait to see how much money I have leftover.";
		}
		
		System.out.println("Problem 13: " + option);
	}
}
