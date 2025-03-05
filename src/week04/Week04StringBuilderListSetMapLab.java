//
// Copyright (c) 2023 Promineo Tech
// Author:  Promineo Tech Academic Team
// Subject:  StringBuilders, Lists, Sets, & Maps
// Java Week 04 Lab  
//
package week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Week04StringBuilderListSetMapLab {

	public static void main(String[] args) {

		// 1. Why would we use a StringBuilder instead of a String?
		// 		a. Instantiate a new StringBuilder
		//		b. Append the characters 0 through 9 to it separated by dashes
		// 				Note:  make sure no dash appears at the end of the StringBuilder
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(i);
			if (i != 9) {
				sb.append('-');
			}
		}	
//		System.out.println(sb);
		
		// 2. List of String:
		//		a. Create a list of Strings 
		//		b. Add 5 Strings to it, each with a different length
		List<String> strings = new ArrayList<String>();
		strings.add("Hello");
		strings.add("my");
		strings.add("name");
		strings.add("translates");
		strings.add("spinach");
//		System.out.println(strings.toString());
		
		// 3. Write and test a method that takes a list of strings 
		//			and returns the shortest string
		String shortest = returnShortestString(strings);
//		System.out.println(shortest);
		
		// 4. Write and test a method that takes a list of strings 
		//			and returns the list with the first and last element switched
		manipulateFirstLast(strings);
//		System.out.println(strings);
		
		// 5. Write and test a method that takes a list of strings 
		//			and returns a string with all the list elements concatenated to each other,
		// 			separated by a comma
		String concatenate = returnConcatString(strings);
//		System.out.println(concatenate);
		
		// 6. Write and test a method that takes a list of strings and a string 
		//			and returns a new list with all strings from the original list
		// 			containing the second string parameter (i.e. like a search method)
		List<String> containsNa = searchList(strings, "na");
//		System.out.println(containsNa.toString());
		
		// 7. Write and test a method that takes a list of integers 
		//			and returns a List<List<Integer>> with the following conditions specified
		//			for the return value:
		//		a. The first List in the returned value contains any number from the input list 
		//			that is divisible by 2
		//		b. The second List contains values from the input list that are divisible by 3
		//		c. The third containing values divisible by 5, and 
		//		d. The fourth all numbers from the input List not divisible by 2, 3, or 5
		List<Integer> numbers = Arrays.asList(1, 3, 5, 18, 12349, 3, 5, 73, 45);
		List<List<Integer>> checkedNums = checkDivisibility(numbers);
//		System.out.println("Number /2: " + checkedNums.get(0).toString());
//		System.out.println("Number /3: " + checkedNums.get(1).toString());
//		System.out.println("Number /5: " + checkedNums.get(2).toString());
//		System.out.println("Number !/: " + checkedNums.get(3).toString());
		
		// 8. Write and test a method that takes a list of strings 
		//			and returns a list of integers that contains the length of each string
		List<Integer> stringLengths = lengthOfString(strings);
//		System.out.println(stringLengths.toString());
		
		// 9. Create a set of strings and add 5 values
		Set<String> stringSet = new HashSet<String>();
		stringSet.addAll(strings);
//		System.out.println(stringSet.toString());
		
		// 10. Write and test a method that takes a set of strings and a character 
		//			and returns a set of strings consisting of all the strings in the
		// 			input set that start with the character parameter.
		Set<String> startsWith_S = startsWith(stringSet, "s");
//		System.out.println(startsWith_S.toString());
		
		// 11. Write and test a method that takes a set of strings 
		//			and returns a list of the same strings
		List<String> setToList = convertSetToList(stringSet);
//		System.out.println(setToList.toString());

		// 12. Write and test a method that takes a set of integers 
		//			and returns a new set of integers containing only even numbers 
		//			from the original set
		Set<Integer> numSet = new HashSet<>(Arrays.asList(1, 3, 5, 18, 12349, 3, 5, 73, 45, 24));
		Set<Integer> evenSet = onlyEvenNumbers(numSet);
//		System.out.println(evenSet.toString());
		
		// 13. Create a map of string and string and add 3 items to it where the key of each
		// 			is a word and the value is the definition of the word
		Map<String, String> baseballTeams = new HashMap<String, String>(){{
			put("angels", "los angeles");
			put("dodgers", "los angeles");
			put("yankees", "new york");
		}};
//		for (String team: baseballTeams.keySet()) {
//			System.out.println(baseballTeams.get(team) + " " + team);
//		}
		
		
		// 14. Write and test a method that takes a Map<String, String> and a string 
		// 			and returns the value for a key in the map that matches the
		// 			string parameter (i.e. like a language dictionary lookup)
		String value = returnValue(baseballTeams, "yankees");
//		System.out.println(value);
		
		// 15. Write and test a method that takes a List<String> 
		//			and returns a Map<Character, Integer> containing a count of 
		//			all the strings that start with a given character
		Map<Character, Integer> firstLetterCounter = beginsWithCounter(strings);
		System.out.println(firstLetterCounter.toString());

	}
	
	
	// Method 15:
	public static Map<Character, Integer> beginsWithCounter(List<String> lst){
		Map<Character, Integer> counter = new HashMap<Character, Integer>();
		for (String str: lst) {
			if (counter.containsKey(str.charAt(0))) {
				counter.put(str.charAt(0), counter.get(str.charAt(0)) + 1);
			} else {
				counter.put(str.charAt(0), 1);
			}
		}
		
		return counter;
	}
	
	
	// Method 14:
	public static String returnValue(Map<String, String> map, String keySearch) {
		return map.get(keySearch);
	}

	
	// Method 12:
	public static Set<Integer> onlyEvenNumbers(Set<Integer> set){
		Set<Integer> evens = new HashSet<Integer>();
		
		for (int num: set) {
			if (num % 2 == 0) {
				evens.add(num);
			}
		}
		
		return evens;
	}

	
	// Method 11:
	public static List<String> convertSetToList(Set<String> set){
		List<String> lst = new ArrayList<String>();
		
		for (String str: set) {
			lst.add(str);
		}
		
		return lst;
	}


	// Method 10:
	public static Set<String> startsWith(Set<String> strSet, String start){
		Set<String> returnSet = new HashSet<String>();
		for (String str: strSet) {
			if (str.startsWith(start)) {
				returnSet.add(str);
			}
		}
		
		return returnSet;
	}

	
	// Method 8:
	public static List<Integer> lengthOfString(List<String> lst){
		List<Integer> nums = new ArrayList<Integer>();
		for (String str: lst) {
			nums.add(str.length());
		}
		
		return nums;
	}

	
	// Method 7:
	public static List<List<Integer>> checkDivisibility(List<Integer> input){
		List<List<Integer>> returnList = new ArrayList<List<Integer>>();
		List<Integer> divisible2 = new ArrayList<Integer>();
		List<Integer> divisible3 = new ArrayList<Integer>();
		List<Integer> divisible5 = new ArrayList<Integer>();
		List<Integer> notDivisible = new ArrayList<Integer>();
		
		for (int num: input) {
			if (num % 2 == 0) {
				divisible2.add(num);
			}
			if (num % 3 == 0) {
				divisible3.add(num);
			}
			if (num % 5 == 0) {
				divisible5.add(num);
			}
			if (num % 2 != 0 && num % 3 != 0 && num % 5 != 0) {
				notDivisible.add(num);
			}
		}
		
		returnList.add(divisible2);
		returnList.add(divisible3);
		returnList.add(divisible5);
		returnList.add(notDivisible);
		
		return returnList;
	}

	
	// Method 6:
	public static List<String> searchList(List<String> lst, String search){
		List<String> returnList = new ArrayList<String>();
		
		for (String str: lst) {
			if (str.contains(search)) {
				returnList.add(str);
			}
		}
		
		return returnList;
	}

	
	// Method 5:
	public static String returnConcatString(List<String> lst) {
		StringBuilder concat = new StringBuilder();
		for (String str: lst) {
			concat.append(str);
			if (str != lst.getLast()) {
				concat.append(',');
			}
		}
		
		return concat.toString();
	}
	
	
	// Method 4:
	public static void manipulateFirstLast(List<String> lst){
		String first = lst.getFirst();
		String last = lst.getLast();
		
		lst.set(0, last);
		lst.set(lst.size() - 1, first);
	}
	
	
	// Method 3:
	public static String returnShortestString(List<String> lst) {
		StringBuilder shortest = new StringBuilder();
		for (String str: lst) {
			if (shortest.isEmpty()) {
				shortest.append(str);
			}
			if (str.length() < shortest.length()) {
				shortest.replace(0, shortest.length(), str);
			}
		}
		
		return shortest.toString();
	}
	

}