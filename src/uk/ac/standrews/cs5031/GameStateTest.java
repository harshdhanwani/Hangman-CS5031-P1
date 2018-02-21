package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTest {

  /**
   * Test case to test the functionality of the game with the respective parameters.
   */
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

  /**
   * Test case to test the validation of letter guessed
   */
  @Test
  public void validateGuessLetterTest() {
    GameState gameState = new GameState("apple", 10, 5);
    assertFalse(gameState.validateGuessedLetter('d'));
    assertTrue(gameState.validateGuessedLetter('p'));
  }

  /**
   * Test case to test the validation of word guessed.
   */
  @Test
  public void validateGuessWordTest() {
    GameState gameState = new GameState("apple", 10, 5);
    assertFalse(gameState.validateGuessedWord("addle"));
    assertTrue(gameState.validateGuessedWord("apple"));
  }

  /**
   * Test case to test the validation of number of remaining guesses after a letter is entered.
   */
  @Test
  public void validateRemainingGuesses() {
    GameState gameState = new GameState("apple", 10, 5);
    gameState.validateGuessedLetter('w');
    gameState.validateGuessedLetter('g');
    gameState.validateGuessedLetter('p');
    assertEquals(gameState.chancesLeft, 8);
  }

  /**
   * Test case to test the validation of number of remaining guesses after a word is entered.
   */
  @Test
  public void validateRemainingGuessesWord() {
    GameState gameState = new GameState("apple", 10, 5);
    gameState.validateGuessedWord("hello");
    gameState.validateGuessedWord("america");
    gameState.validateGuessedWord("ball");
    gameState.validateGuessedWord("apple");
    assertEquals(gameState.chancesLeft, 7);
  }


  /**
   * Test case to test the validation of hints functionality, i.e. hints remaining after a few letters/words guessed
   * and the hints option used.
   */
  @Test
  public void validateHintsAndGuessesLeft() {
    GameState gameState = new GameState("apple", 10, 5);
    gameState.validateGuessedWord("hello");
    gameState.validateGuessedLetter('a');
    gameState.hint();
    gameState.hint();
    assertEquals(gameState.numberOfHints, 3);
    assertEquals(gameState.chancesLeft, 9);
  }

  /**
   * Test case to test the validation of hints functionality when user exceeds maximum hints allowed to the user.
   */
  @Test
  public void hintsAllowedTest() {
    GameState gameState = new GameState("apple", 10, 3);
    gameState.hint();
    gameState.hint();
    gameState.hint();
    gameState.hint();
    assertEquals(gameState.numberOfHints, 0);
  }

  /**
   * Test case to test the validation of hints if the number of letters remaining in the word is less than the number
   * of hints remaining to the user.
   */
  @Test
  public void hintsPossibleTest() {
    GameState gameState = new GameState("apple", 10, 3);
    gameState.validateGuessedLetter('p');
    gameState.validateGuessedLetter('l');
    gameState.hint();
    gameState.hint();
    gameState.hint();
    assertEquals(gameState.isHintsUsed(), true);
  }

  /**
   * Test case to test the validation of the game status i.e. if the game is won.
   */
  @Test
  public void testGameWon() {
    GameState gameState = new GameState("apple", 10, 5);
    gameState.validateGuessedLetter('p');
    gameState.validateGuessedLetter('l');
    gameState.validateGuessedLetter('e');
    gameState.validateGuessedWord("apple");
    assertTrue(gameState.won());
    assertFalse(gameState.lost());
  }

  /**
   * Test case to test the validation of the game status i.e. if the game is lost.
   */
  @Test
  public void testGameLost() {
    GameState gameState = new GameState("apple", 3, 5);
    gameState.validateGuessedLetter('q');
    gameState.validateGuessedLetter('f');
    gameState.validateGuessedLetter('b');
    assertTrue(gameState.lost());
    assertFalse(gameState.won());
  }

}
