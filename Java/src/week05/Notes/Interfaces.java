package week05.Notes;

public interface Interfaces {
	
	public void info(String info); // methods in an interface are presumed abstract
	public void warning(String warning);
	public void error(String error);
	public void fatal(String fatal);
	
	
	public static void main(String[] args) {
		/*
		 * in Java, multiple classes can be extended or inherited
		 * to child classes. To get around this, we can implement
		 * Abstract Classes which are called Interfaces
		 * 
		 * All methods of an interface must be in the body of an
		 * implemented class
		 * 
		 * example:
		 * 
		 * public class SomeClass implements Interfaces {
		 * 	// Must include a method for info(), warning(), error(),
		 * 	// and fatal()
		 * }
		 */
	}
}
