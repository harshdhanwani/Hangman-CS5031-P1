package uk.ac.standrews.cs5031;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class GameState {

  public String word;
  public int numberOfGuesses;
  public int chancesLeft;
  public int numberOfHints; // allowed hints
  private boolean hintCondition;

  // List of letters guessed
  ArrayList<Character> letterGuessed;
  // List of letters letterNotGuessed guessed
  ArrayList<Character> letterNotGuessed;

  private HashSet<Character> hintLetters;

  /**
   * getters function for is number of hints used
   *
   * @return
   */
  public boolean isHintsUsed() {
    return hintsUsed;
  }

  /**
   * Setters for number of hints used
   *
   * @param hintsUsed
   */
  public void setHintsUsed(boolean hintsUsed) {
    this.hintsUsed = hintsUsed;
  }

  private boolean hintsUsed = false;

  private Scanner sc = new Scanner(System.in).useDelimiter("\n");

  /**
   * Constructor method to initialise game state with respected parameters.
   *
   * @param target          the target word
   * @param numberOfGuesses number of guesses allowed
   * @param numberOfHints   number of hints allowed
   */
  GameState(String target, int numberOfGuesses, int numberOfHints) {
    this.word = target;

    // replaced <Character> with <>
    letterNotGuessed = new ArrayList<>();
    letterGuessed = new ArrayList<>();

    for (int i = 0; i < target.length(); i++) {
      if (!letterNotGuessed.contains(Character.toLowerCase(target.charAt(i)))) {
        letterNotGuessed.add(Character.toLowerCase(target.charAt(i)));
      }
    }

    System.out.println();
    hintLetters = new HashSet<>(letterNotGuessed);

    for (int i = 0; i < letterNotGuessed.size(); i++) {
      hintLetters.add(Character.toLowerCase(target.charAt(i)));
    }

    this.numberOfGuesses = 0;
    chancesLeft = numberOfGuesses;
    this.numberOfHints = numberOfHints;

  }

  /**
   * Method to show the word or print out the right letter guessed into the word format or just "-".
   *
   * @return returns the word.
   */
  String showWord() {
    for (int i = 0; i < word.length(); i++) {
      if (letterGuessed.contains(Character.toLowerCase(word.charAt(i)))) {
        System.out.print(word.charAt(i));
      } else {
        System.out.print("-");
      }
    }
    return word;

  }

  /**
   * Boolean method to guess the letter or word entered.
   *
   * @return returns appropriate functions to guess letter/word.
   */
  boolean guessLetter() {

    setHintCondition(false);
    String input ="";
    while(input.isEmpty()){

      System.out.print("Guess a letter or word (? for a hint): ");
      input = sc.next().toLowerCase();
    }

    char letter = input.charAt(0);

    if (input.length() > 1) {
      return validateGuessedWord(input);
    }

    if (letter == '?') {
      hint();
      setHintCondition(true);
      return false;
    } else {
      return validateGuessedLetter(letter);
    }

  }

  /**
   * Method to validate the letter guessed.
   *
   * @param letter letter character entered by user input.
   * @return returns the letter if guessed right or false if guessed wrong.
   */
  boolean validateGuessedLetter(char letter) {
    numberOfGuesses++;
    for (int i = 0; i < letterNotGuessed.size(); i++) {
      if (letterNotGuessed.get(i) == letter) {
        hintLetters.remove(letterNotGuessed.get(i));
        letterNotGuessed.remove(i);
        letterGuessed.add(letter);
        return true;
      }
    }
    chancesLeft--;
    return false;
  }

  /**
   * Method to validate the word guessed.
   *
   * @param input input word entered by the user.
   * @return returns the validated word
   */
  boolean validateGuessedWord(String input) {
    numberOfGuesses++; // One more guess

    if (input.compareToIgnoreCase(word) == 0) {
      letterNotGuessed.clear();
      return true;
    } else {
      chancesLeft--;
      return false;
    }
  }

  /**
   * Method to set the hint status.
   *
   * @param inputVar the input variable
   */
  private void setHintCondition(boolean inputVar) {
    hintCondition = inputVar;
  }

  /**
   * Method to get the hint status.
   *
   * @return returns hint status.
   */
  boolean getHintCondition() {
    return hintCondition;
  }

  /**
   * Boolean method to check if the game is won.
   *
   * @return returns true if game won
   */
  boolean won() {
    return letterNotGuessed.size() == 0;
  }

  /**
   * Method to check if the game is lost.
   *
   * @return returns true if user doesn't win the game
   */
  boolean lost() {
    return letterNotGuessed.size() > 0 && chancesLeft == 0;
  }

  /**
   * Method containing the logic to perform hints functionality.
   */
  void hint() {

    char hintLetter = letterNotGuessed.get((int) (Math.random() * letterNotGuessed.size()));

    if (numberOfHints == 0) {
      System.out.println("No more hints allowed!");
    } else if (hintLetters.contains(hintLetter)) {
      System.out.println("Try: " + hintLetter);
      hintLetters.remove(hintLetter);
      numberOfHints--;
    } else if (hintLetters.isEmpty()) {
      hintsUsed = true;
      System.out.println("All possible hints have been given!");
    } else {
      hint();
    }

  }
}
