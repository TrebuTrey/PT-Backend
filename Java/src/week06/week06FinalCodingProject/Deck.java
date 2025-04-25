package week06.week06FinalCodingProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import week06.week06FinalCodingProject.Card.Suit;

public class Deck {

	private List<Card> deck = new ArrayList<Card>();
	
	public Deck() {
		// Standard 52-Card Deck
		for(Suit su: Suit.values()) {
			for(int i = 2; i < 15; i++) {
				Card card = new Card(i, su.toString());
				deck.add(card);
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public void display(int index) {
		deck.get(index).describe();
	}

	public void draw(Player player) {
		player.addToHand(deck.removeFirst());
	}

	public int size() {
		return deck.size();
	}

}
