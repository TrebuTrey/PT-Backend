package week06.week06FinalCodingProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {
	Deck deck = new Deck();
	
	@Test
	void testDeckCreate() {
		assertEquals(deck.size(), 52);
//		fail("Not yet implemented");
	}
	
	@Test
	void testShuffle() {
		deck.shuffle();
		deck.display(0);
	}
	
	@Test
	void testDraw() {
		Player player = new Player("John");
		deck.display(0);
		deck.draw(player);
		deck.display(0);
	}

}
