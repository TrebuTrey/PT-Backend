package week04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DifferentCollections {

	public static void main(String[] args) {
		
		/*
		 * List
		 * 
		 * Allows Duplicates
		 * 
		 * Keeps the order of Index constant
		 * Allows null values
		 * Common Implementations: ArrayList, LinkedList, Vector
		 */
		
		List<String> students = new ArrayList<String>();
		students.add("Rob");	// always students[0]
		students.add("Rob");	// always students[1]
		students.add("Sam");	// always students[2]
		
		
		/*
		 * Set
		 * 
		 * No Duplicates
		 * 
		 * Unordered
		 * Allows null value
		 * Common Implementations: HashSet, LinkedHashSet, TreeSet
		 */
		
		Set<String> states = new HashSet<String>();
		states.add("Alabama");
		states.add("Alabama");	// This will not be added.
		System.out.println(states.size()); // Only prints 1
		
		/*
		 * Map
		 * 
		 * key-value pairs (dict)
		 * 
		 * Duplicate values
		 * Non-Duplicate keys
		 * 
		 * Unordered
		 * Common Implementations: HashMap, LinkedHashMap, TreeMap, Hashtable
		 */
		
		Map<Integer, String> racerPlacements = new HashMap<Integer, String>();
		racerPlacements.put(1,  "Tommy");
		racerPlacements.put(2, "Sally");
		racerPlacements.put(3,  "John");
		
		System.out.println(racerPlacements.get(3)); // Retrieves values based on key
		
		Set<Integer> racerKeys = racerPlacements.keySet();	// Returns the set of keys from racerPlacements
		
		for (int key: racerKeys) {
			System.out.println("Key: " + key + " Val: " + racerPlacements.get(key));
		}
		
	}
}
