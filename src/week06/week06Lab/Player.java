package week06.week06Lab;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private Team team;
	private boolean hasWon = false;
	private List<String> moves = new ArrayList<String>();
	
	public Player(String team) {
		this.setTeam(Team.valueOf(team));
	}
	
	public void addMove(String placement) {
		moves.add(placement);
	}
	
	private void checkForWin() {
		int tCount, mCount, bCount, lCount, cCount, rCount, diagonalDown, diagonalUp;
		tCount = mCount = bCount = lCount = cCount = rCount = diagonalDown = diagonalUp = 0;
		
		for(String move: moves) {
			switch(move.charAt(0)){
			case 't':
				tCount += 1;
				break;
			case 'm':
				mCount += 1;
				break;
			case 'b':
				bCount += 1;
				break;
			}
			
			switch(move.charAt(1)){
			case 'l':
				lCount += 1;
				break;
			case 'c':
				cCount += 1;
				break;
			case 'r':
				rCount += 1;
				break;
			}
			
			if(move == "tl" || move == "mc" || move == "br") {
				diagonalDown += 1;
			}
			
			if(move == "tr" || move == "mc" || move == "bl") {
				diagonalUp += 1;
			}
		}
		
		if(tCount == 3 || mCount == 3 || bCount == 3 || lCount == 3 ||
				cCount == 3 || rCount == 3 || 
				diagonalDown == 3 || diagonalUp == 3) {
			hasWon = true;
		}
	}
	
	public boolean returnWinStatus() {
		checkForWin();
		return hasWon;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	
}
