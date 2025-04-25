package week06.week06FinalCodingProject;

//import week05.week05Lab.Deck;

public class App {
	public static void main(String[] args) {
		Deck deck = new Deck();
		
		Player p1 = new Player("John");
		Player p2 = new Player("Doe");
		
		Card card1, card2;
		
		String outcome;
		String divider = "_".repeat(50);
		
		deck.shuffle();
		
		while(deck.size() > 1) {
			deck.draw(p1);
			deck.draw(p2);
		}
		
		p1.describe();
		p2.describe();
		
		for(int i = 0; i < p1.startingCardCount; i++) {
			card1 = p1.flip();
			card2 = p2.flip();
			
			if(card1.getValue() > card2.getValue()) {
				p1.incrementScore();
				outcome = card1.getName() + " beats " + 
						card2.getName() + ". Player "
						+ p1.getName() + " wins the point.";
			}else if(card2.getValue() > card1.getValue()) {
				p2.incrementScore();
				outcome = card2.getName() + " beats " + 
						card1.getName() + ". Player "
						+ p2.getName() + " wins the point.";
			}else {
				outcome = "Cards draw, no point awarded.";
			}
			
			System.out.println("Turn " + (i+1) + ": " + outcome);
			System.out.println(divider);
		}
		
		String winner = p1.finalScore() > p2.finalScore() ? p1.victoryLap(): p1.finalScore() > p2.finalScore() ? draw(): p2.victoryLap();
		System.out.println(winner);
	}

	private static String draw() {
		return "The game has ended in a draw";
	}
}
