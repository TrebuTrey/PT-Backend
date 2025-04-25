package week06.week06FinalCodingProject;

public class Card {
	enum Suit{
		Spades,
		Hearts,
		Clubs,
		Diamonds
	}
	
	private int value;
	private String name;
	private Suit suit;
	
	public Card(int value, String suit) {
		setValue(value);
		setSuit(Suit.valueOf(suit));
		setName(convertToCard(value));
	}
	
	private String convertToCard(int value) {
		switch(value) {
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			return String.valueOf(value);
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		case 14:
			return "Ace";
		default:
			return "Not a valid card";
		}
	}
	
	public void describe() {
		System.out.println("Card: " + getName() + " of " + getSuit());
		System.out.println("Card Value: " + value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
