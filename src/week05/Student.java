package week05;

public class Student {
	/*
	 * Creation of our first Class
	 * 
	 * Classes are written in PascalCase, not camelCase
	 */
	
	static int numberOfStudents;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	int gradeLevel;
	
	/*
	 * Constructors are the only instance where there is not a 
	 * name after the modifiers
	 * 
	 * It will always be public/private/protected ClassName()
	 */
	
	public Student() {} //method overload, allows nothing to be input
	
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Student(String firstName, String lastName, String phoneNumber, int gradeLevel) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.gradeLevel = gradeLevel;
	}
	
	public void introduce() {
		System.out.println("My name is " + firstName + " " + lastName);
		System.out.println("My grade level is " + gradeLevel);
		System.out.println("My phone number is " + phoneNumber);
	}
	
	public void setFirstName(String firstName) {
		// Setters are good because they add the ability for logic
		// to validate information
		if (firstName.length() > 1) {
			this.firstName = firstName;
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
}
