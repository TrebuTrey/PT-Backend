package week05;

public class Student {
	/*
	 * Creation of our first Class
	 * 
	 * Classes are written in PascalCase, not camelCase
	 */
	
	static int numberOfStudents;
	
	String firstName;
	String lastName;
	String phoneNumber;
	int gradeLevel;
	
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
}
