package week05.week05Lab;

public class Card {
		enum Suit{
			CLUBS,
			DIAMONDS,
			HEARTS,
			SPADES
		}
		

		private String name;
		private Suit suit;
		private int value;
		
		//Empty Constructor
		public Card() {}
		
		public Card(String name, Suit suit) {
			this.setName(name);
			this.suit = suit;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
			switch (this.name) {
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "10":
				this.value = Integer.parseInt(this.name);
				break;
			case "Jack":
				this.value = 11;
				break;
			case "Queen":
				this.value = 12;
				break;
			case "King":
				this.value = 13;
				break;
			case "Ace":
				this.value = 14;
				break;
			default:
				System.out.println("Invalid input for card name. Please input 2-10 as Strings or Jack, Queen, King, Ace");
			}
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
		
		public void describe() {
			System.out.println(name + " of " + suit + "\t Value: " + value);
		}
		
}
