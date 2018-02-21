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
  private int lettersLeftCount;

  // List of letters guessed
  ArrayList<Character> letterGuessed;
  // List of letters letterNotGuessed guessed
  ArrayList<Character> letterNotGuessed;

  private HashSet<Character> hintLetters;

  public boolean isHintsUsed() {
    return hintsUsed;
  }

  public void setHintsUsed(boolean hintsUsed) {
    this.hintsUsed = hintsUsed;
  }

  private boolean hintsUsed = false;

  private Scanner sc = new Scanner(System.in).useDelimiter("\n");

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

    lettersLeftCount = hintLetters.size();

    this.numberOfGuesses = 0;
    chancesLeft = numberOfGuesses;
    this.numberOfHints = numberOfHints;

  }

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

  boolean guessLetter() {

    setHintCondition(false);

    System.out.print("Guess a letter or word (? for a hint): ");

    String input = sc.next().toLowerCase();
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

  private void setHintCondition(boolean inputVar) {
    hintCondition = inputVar;
  }

  boolean getHintCondition() {
    return hintCondition;
  }

  // simplified won and lost functions.
  boolean won() {
    return letterNotGuessed.size() == 0;
  }

  boolean lost() {
    return letterNotGuessed.size() > 0 && chancesLeft == 0;
  }

  void hint() {

    char hintLetter = letterNotGuessed.get((int) (Math.random() * letterNotGuessed.size()));

    if (numberOfHints == 0) {
      System.out.println("No more hints allowed!");
    } else if (hintLetters.contains(hintLetter)) {
      System.out.println("Try: " + hintLetter);
      hintLetters.remove(hintLetter);
      numberOfHints--;
      lettersLeftCount--;
    } else if (hintLetters.isEmpty()) {
      hintsUsed = true;
      System.out.println("All possible hints have been given!");
    } else {
      hint();
    }

  }
}
