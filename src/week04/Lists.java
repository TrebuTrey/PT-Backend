package week04;

import java.util.ArrayList;
import java.util.List;

public class Lists {

	public static void main(String[] args) {
		//Arrays are constrained by defined sizes at assignment
		
		//Lists are not beholden to the same requirements
		//List<E> where E is the generic for the collection
		List<String> sports = new ArrayList<String>();
		sports.add("wrestling");
		sports.add("soccer");
		sports.add("football");
		
		//For Loops must be dealt with slightly different
		for (int i = 0; i < sports.size(); i++) {
			System.out.println(sports.get(i));
		}
		
		//Enhanced Loops are superior for coding simplicity
		for (String sport: sports) {
			System.out.println(sport);
		}
		
		//Remove can be done both by index and by value
		sports.remove(0);
		sports.remove("football");
		
		List<Integer> numbers = new ArrayList<Integer>();
		if (numbers.isEmpty()) {
			System.out.println("No numbers added");
		}
	}

}
