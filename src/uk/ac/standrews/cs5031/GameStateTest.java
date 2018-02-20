package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTest {

	@Test
	public void gameStateTest() {
			GameState gameState = new GameState("apple", 10, 5);
			// number of guesses taken already.
			assertEquals(gameState.numberOfGuesses, 0);
			assertEquals(gameState.word, "apple");
			assertEquals(gameState.letterNotGuessed.size(), 4);
			assertEquals(gameState.letterGuessed.size(), 0);
			assertEquals(gameState.numberOfHints, 5);
	}


}
