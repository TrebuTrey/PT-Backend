package week06.week06FinalCodingProject;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import week06.week06FinalCodingProject.Card.Suit;

class CardTest {

	@Test
	void testValue() {
		Card aceSpades = new Card(14, "Spades");
		assertEquals(aceSpades.getName(), "Ace");
		assertEquals(aceSpades.getSuit(), Suit.Spades);
		aceSpades.describe();
//		fail("Not yet implemented");
	}

}
