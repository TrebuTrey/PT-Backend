package week06.week06FinalCodingProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

	public int startingCardCount = 0;
	private int score;
	private String name;
	private List<Card> hand = new ArrayList<Card>();
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	public void addToHand(Card card) {
		hand.add(card);
		startingCardCount = hand.size();
	}
	
	public void describe() {
		System.out.println("Player name: " + getName());
		System.out.println("Card Count after last draw: " + startingCardCount);
		System.out.println("Cards in hand: ");
		
		for(Card card: hand) {
			card.describe();
		}
	}
	
	public Card flip() {
		Collections.shuffle(hand);
		return hand.removeFirst();
	}

	public void incrementScore() {
		this.score += 1;
	}

	public int finalScore() {
		return score;
	}
	
	public String victoryLap() {
		return getName() + " is the best! No luck here. Total Score: "
				+ finalScore();
	}

	public String getName() {
		return name;
	}

}
