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


	//T6 - tests to check for hints functionality - hints remaining after a few letters/words guessed and the hint option used.
    @Test
    public void validateHintsAndGuessesLeft(){
	    GameState gameState = new GameState("apple", 10, 5);
	    gameState.validateGuessedWord("hello");
	    gameState.validateGuessedLetter('a');
	    gameState.hint();
	    gameState.hint();
	    assertEquals(gameState.numberOfHints, 3);
	    assertEquals(gameState.chancesLeft, 9);
    }

    //T7 - test to check hints functionality when hints used exceeds max hints available to the user.
    @Test
    public void hintsAllowedTest(){
        GameState gameState = new GameState("apple", 10, 3);
        gameState.hint();
        gameState.hint();
        gameState.hint();
        gameState.hint();
        assertEquals(gameState.numberOfHints, 0);
    }

    //T8 - when available letters is less than the number of hints remaining " all possible hints delivered "
    @Test
    public void hintsPossibleTest(){
	    GameState gameState = new GameState("apple", 10, 3);
	    gameState.validateGuessedLetter('p');
	    gameState.validateGuessedLetter('l');
	    gameState.hint();
	    gameState.hint();
	    gameState.hint();
	    assertEquals(gameState.isHintsUsed(), true);
    }

    //T9 - game won tests
    @Test
    public void testGameWon(){
        GameState gameState = new GameState("apple", 10, 5);
        gameState.validateGuessedLetter('p');
        gameState.validateGuessedLetter('l');
        gameState.validateGuessedLetter('e');
        gameState.validateGuessedWord("apple");
        assertTrue(gameState.won());
        assertFalse(gameState.lost());
    }

    //T10 - game lost test
    @Test
    public void testGameLost(){
        GameState gameState = new GameState("apple", 3, 5);
        gameState.validateGuessedLetter('q');
        gameState.validateGuessedLetter('f');
        gameState.validateGuessedLetter('b');
        assertTrue(gameState.lost());
        assertFalse(gameState.won());
    }







}
