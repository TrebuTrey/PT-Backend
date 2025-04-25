package week05.week05Lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import week05.week05Lab.Card.Suit;

public class Deck {
	private List<Card> cards = new ArrayList<Card>();

	public Deck() {
		createNewDeck();
	}
	
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	private void createNewDeck() {
		String[] values  = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
		for (Suit suit: Suit.values()) {
			for (String val: values) {
				Card card = new Card(val, suit);
				cards.add(card);
			}
		}
	}
	
	public void describe() {
		for (Card card: cards) {
			card.describe();
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public List<Card> draw(int count) {
		List<Card> hand = new ArrayList<Card>();
		do {
			if (cards.size() > 0) {
				hand.add(cards.get(0));
				cards.remove(0);
			}
		}while(hand.size() < count && cards.size() > 0);
		
		return hand;
	}

}
