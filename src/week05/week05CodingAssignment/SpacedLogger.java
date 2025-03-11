package week05.week05CodingAssignment;

public class SpacedLogger implements Logger{

	@Override
	public void log(String log) {
		StringBuilder logSpace = new StringBuilder();
		
		for (int i = 0; i < log.length(); i++) {
			char nextChar = log.charAt(i);
			logSpace.append(nextChar);
			if (nextChar != ' ') {
				logSpace.append(" ");
			}
		}
		
		logSpace.deleteCharAt(logSpace.length()-1);
		
		System.out.println(logSpace.toString());
	}

	@Override
	public void error(String error) {
		StringBuilder errSpace = new StringBuilder();
		
		errSpace.append("ERROR: ");
		
		for (int i = 0; i < error.length(); i++) {
			char nextChar = error.charAt(i);
			errSpace.append(nextChar);
			if (nextChar != ' ') {
				errSpace.append(" ");
			}
			
		}
		
		errSpace.deleteCharAt(errSpace.length()-1);
		
		System.out.println(errSpace.toString());
		
	}

}
