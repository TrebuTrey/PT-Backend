package week05;

public class Classes {
	public static void main(String[] args) {
		
		Student student1 = new Student();
		student1.setFirstName("Tom");
		// The Below methods no longer work because the variables
		// have been set to private
//		student1.lastName  ="Smith";
//		student1.gradeLevel = 12;
//		student1.phoneNumber = "480-123-4567";
//		student1.introduce();
		
		Student student2 = new Student("Sammy", "Sosa", "123-456-7890", 1);
		student2.introduce();
		
		Student student3 = new Student("John", "Cena");
		student3.introduce();
		
	}
}
