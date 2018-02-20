package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTest {

    // T1
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

	// T2 - test to validate letter guessed.
	@Test
	public void validateGuessLetterTest(){
		GameState gameState = new GameState("apple", 10, 5);
		assertFalse(gameState.validateGuessedLetter('d'));
		assertTrue(gameState.validateGuessedLetter('p'));
	}

	// T3 - test to validate word guessed.
	@Test
	public void validateGuessWordTest(){
		GameState gameState = new GameState("apple", 10, 5);
		assertFalse(gameState.validateGuessedWord("addle"));
		assertTrue(gameState.validateGuessedWord("apple"));
	}

	// T4 - validate remaining guesses after letter is entered ( one correct guess)
	@Test
	public void validateRemainingGuesses(){
		GameState gameState = new GameState("apple", 10, 5);
		gameState.validateGuessedLetter('w');
		gameState.validateGuessedLetter('g');
		gameState.validateGuessedLetter('p');
		assertEquals(gameState.chancesLeft, 8);
	}

	// T5 - validate remaining guesses after a word is entered (one correct word guess)
	@Test
	public void validateRemainingGuessesWord(){
		GameState gameState = new GameState("apple", 10, 5);
		gameState.validateGuessedWord("hello");
		gameState.validateGuessedWord("america");
		gameState.validateGuessedWord("ball");
		gameState.validateGuessedWord("apple");
		assertEquals(gameState.chancesLeft, 7);
	}





}
