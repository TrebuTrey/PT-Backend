package week05.week05CodingAssignment;

public class AsteriskLogger implements Logger{

	@Override
	public void log(String log) {
		System.out.println("***" + log + "***");
	}

	@Override
	public void error(String error) {
		String errMessage = "***Error: " + error + "***";
		StringBuilder astLine = new StringBuilder();
		
		for (int i = 0; i < errMessage.length(); i++) {
			astLine.append("*");
		}
		
		System.out.println(astLine.toString());
		System.out.println(errMessage);
		System.out.println(astLine.toString());
		
	}

	
}
