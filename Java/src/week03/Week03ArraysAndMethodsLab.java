//
// Copyright (c) 2023 Promineo Tech
// Author:  Promineo Tech Academic Team
// Subject: Arrays & Methods
// Java Week 03 Lab
//
package week03;

public class Week03ArraysAndMethodsLab {

	public static void main(String[] args) {
			
		//
		// Arrays:
		//
		
		// 1. Create an array with the following values 1, 5, 2, 8, 13, 6
		
		int[] array1 = new int[6];
		array1[0] = 1; array1[1] = 5; array1[2] = 2; array1[3] = 8; array1[4] = 13; array1[5] = 6;
		
		// 2. Print out the first element in the array
		System.out.println(array1[0]);
		
		// 3. Print out the last element in the array without using the number 5
		System.out.println(array1[array1.length - 1]);
		
		// 4. Print out the element in the array at position 6, what happens?
//		System.out.println(array1[6]);
		
		// 5. Print out the element in the array at position -1, what happens?
//		System.out.println(array1[-1]);
			
		// 6. Write a traditional for loop that prints out each element in the array
		for (int i = 0; i < array1.length; i++) {
			System.out.println(array1[i]);
		}
			
		// 7. Write an enhanced for loop that prints out each element in the array
		for (int num: array1) {
			System.out.println(num);
		}
		
		// 8. Create a new variable called sum, write a loop that adds 
		//			each element in the array to the sum
		int sum = 0;
		for (int num: array1) {
			sum += num;
//			System.out.println(sum);
		}
			
		// 9. Create a new variable called average and assign the average value of the array to it
		float average = sum/array1.length;
//		System.out.println(average);
		
		// 10. Write an enhanced for loop that prints out each number in the array 
		//			only if the number is odd
		for (int num: array1) {
			if (num % 2 == 1) {
				System.out.println(num);
			}
		}
		
		// 11. Create an array that contains the values "Sam", "Sally", "Thomas", and "Robert"
		String[] array2 = new String[4];
		array2[0] = "Sam"; array2[1] = "Sally"; array2[2] = "Thomas"; array2[3] = "Robert";
		
		// 12. Calculate the sum of all the letters in the new array
		int letterSum = 0;
		for (String name: array2) {
			letterSum += name.length();
		}
		

		//
		// Methods:
		//
		
		// 13. Write and test a method that takes a String name and prints out a greeting. 
		//			This method returns nothing.
		greeting("Trey");
		
		// 14. Write and test a method that takes a String name and  
		//			returns a greeting.  Do not print in the method.
		String greeting = greetingNoPrint("Trey");
		
		// Compare method 13 and method 14:  
		//		a. Analyze the difference between these two methods.
		//		b. What do you find? 
		//		c. How are they different?
		
		
		// 15. Write and test a method that takes a String and an int and 
		//			returns true if the number of letters in the string is greater than the int
		System.out.println(isLengthGreater("Sam", 2));
		
		// 16. Write and test a method that takes an array of string and a string and 
		//			returns true if the string passed in exists in the array
		System.out.println(isFound(array2, "Sally"));
		
		// 17. Write and test a method that takes an array of int and 
		//			returns the smallest number in the array
		int[] arr3 = {1, 2, 5, 3, 1, 0, -3};
		System.out.println(returnSmallestNum(arr3));
		
		// 18. Write and test a method that takes an array of double and 
		//			returns the average
		double[] arr4 = {1.51, 2.51, 3.86, 4.94, 6.2};
		System.out.println(average(arr4));

		// 19. Write and test a method that takes an array of Strings and 
		//			returns an array of int where each element
		//			matches the length of the string at that position
		String[] arr5 = {"Take", "Me", "Out", "To", "The", "Ballgame"};
		int[] lengths = stringLengths(arr5);
//		for (int num: lengths) { System.out.println(num);}
				
		// 20. Write and test a method that takes an array of strings and 
		//			returns true if the sum of letters for all strings with an even amount of letters
		//			is greater than the sum of those with an odd amount of letters.
		System.out.println(evenOrOddStrings(arr5));
	
		// 21. Write and test a method that takes a string and 
		//			returns true if the string is a palindrome
		System.out.println(isPalindrome("Racecar"));
		
		
	}
	

	
	// Method 13:
	public static void greeting(String name) {
		System.out.println("How are you doing " + name);
	}

	// Method 14:
	public static String greetingNoPrint(String name) {
		return "How are you doing " + name;
	}
	
	// Method 15:
	public static Boolean isLengthGreater(String string, int num) {
		return string.length() > num;
	}
	
	// Method 16:
	public static Boolean isFound(String[] arr, String strToCheck) {
		for (String str: arr) {
			if (strToCheck == str) {
				return true;
			}
		}
		
		return false;
	}
	
	// Method 17:
	public static int returnSmallestNum(int[] nums) {
		int smallest = nums[0];
		for (int num: nums) {
			if (num < smallest) {
				smallest = num;
			}
		}
		return smallest;
	}
	
	// Method 18:
	public static double average(double[] nums) {
		double sum = 0;
		for (double num: nums) {
			sum += num;
		}
		
		return sum/nums.length;
	}
	
	// Method 19:
	public static int[] stringLengths(String[] arr) {
		int[] returnArr = new int[arr.length];
		
		for (int i = 0; i < returnArr.length; i++) {
			returnArr[i] = arr[i].length();
		}
		
		return returnArr;
	}
	
	// Method 20:
	public static Boolean evenOrOddStrings(String[] arr) {
		int oddCount = 0;
		int evenCount = 0;
		for (String str: arr) {
			if (str.length() % 2 == 0) {
				evenCount += str.length();
			} else {
				oddCount += str.length();
			}
		}
		
		return evenCount > oddCount;
	}
	
	// Method 21:
	public static Boolean isPalindrome(String str) {
		char[] charArray = str.toCharArray();
		String backwardsString = "";
		
		for (int i = str.length() - 1; i >= 0; i--) {
			backwardsString += charArray[i];
		}
//		System.out.println(backwardsString);
		return str.equalsIgnoreCase(backwardsString);
	}

}