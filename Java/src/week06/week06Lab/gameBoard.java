package week06.week06Lab;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class gameBoard {
	private Map<String, Team> board = new HashMap<String, Team>(){{
		put("tl", null);
		put("tc", null);
		put("tr", null);
		put("ml", null);
		put("mc", null);
		put("mr", null);
		put("bl", null);
		put("bc", null);
		put("br", null);
	}};
	
	private Scanner scanner = new Scanner(System.in);
	
	private int turnCount = 0;
	
	private String winner = null;
	
	private boolean catGame = false;
	
	public int getTurnCount() {
		return turnCount;
	}

	public String getWinner() {
		return winner;
	}
	
	public void checkForWin(Player player) {
		if(player.returnWinStatus()) {
			setWinner(player.getTeam().toString());
		}
	}
	
	public void displayBoard() {
		System.out.println(outSys("tl") + outSys("tc") + outSys("tr"));
		System.out.println(outSys("ml") + outSys("mc") + outSys("mr"));
		System.out.println(outSys("bl") + outSys("bc") + outSys("br"));
	}
	
	public void takeTurn(Player player) {
		if(getWinner() != null || isCatGame()) {
			return;
		}
		
		if(turnCount <= 1) {
			printOptions();
		}
		System.out.println("Player " + player.getTeam().toString() + ", where would you like to go?");
		String position = scanner.next().trim();
		while(checkIfVacant(position) == false || checkIfValid(position) == false) {
			System.out.println("This spot is already taken or doesn't exist. Please choose a different spot.");
			position = scanner.next().trim();
		}
		
		board.put(position, player.getTeam());
		player.addMove(position);
		
		turnCount += 1;
		
		if(turnCount > 4) {
			checkForWin(player);
		}
		
		checkForDraw();
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	@SuppressWarnings("finally")
	private String outSys(String placement) {
		String isFilled = null;
		
		try {
			isFilled = board.get(placement).toString();
		}catch(Exception e) {
			isFilled = " ";
		}finally {
			return "|" + isFilled + "|";
		}
	}
	
	private void checkForDraw() {
		if(board.containsValue(null) == false && getWinner() == null) {
			setCatGame(true);
		}
	}
	
	private boolean checkIfVacant(String placement) {
		return board.get(placement) != null ? false: true;
	}
	
	private boolean checkIfValid(String placement) {
		return board.keySet().contains(placement)? true: false;
	}
	
	private void printOptions() {
		System.out.println("Options must be typed as rc for row-column");
		System.out.println("rows are top(t), middle(m), bottom(b)");
		System.out.println("columns are left(l), center(c), right(r)");
	}

	public boolean isCatGame() {
		return catGame;
	}

	public void setCatGame(boolean catGame) {
		this.catGame = catGame;
	}
}
