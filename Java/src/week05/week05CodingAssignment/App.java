package week05.week05CodingAssignment;

public class App {

	public static void main(String[] args) {
		Logger aLog = new AsteriskLogger();
		
		aLog.log("Hello World and all who Inhabit it");
		System.out.println();
		aLog.error("Is this thing working?");
		System.out.println();
		
		Logger sLog = new SpacedLogger();
		
		sLog.log("Hello World and all who Inhabit it");
		System.out.println();
		sLog.error("Is this thing working?");
	}
}
