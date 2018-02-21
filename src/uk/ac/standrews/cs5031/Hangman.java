package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {

  /**
   * Main function that would start the game with respective interface and game state declarations.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    GameState game;
    CommandOpts opts;
    boolean correct;

    opts = new CommandOpts(args);

    if (opts.wordsource.isEmpty()) {

      System.out.println("1. Counties \n" +
        "2. Countries \n" +
        "3. Cities \n" +
        "Pick a Category: ");

      game = new GameState(Words.randomWord(sc.nextInt()), opts.maxGuesses, opts.maxHints);
    } else {
      game = new GameState(Words.randomWord(opts.wordsource), opts.maxGuesses, opts.maxHints);
    }

    // Logic to display interface depending on same status.
    while (!(game.won() || game.lost())) {
      game.showWord();
      System.out.println();
      System.out.println("Guesses remaining: " + game.chancesLeft);

      correct = game.guessLetter();

      if (correct) {
        System.out.println("Good Guess!!");
        System.out.println("");
      } else if (game.getHintCondition()) {
        System.out.println("");
      } else {
        System.out.println("Wrong guess, Try again!");
        System.out.println("");
      }
    }

    // Logic to display interface depending on game lost or won.
    if (game.won()) {
      System.out.println("Congratulations! You've won the game. \n" + "The word you guessed was - " + game.word + "\n"
        + "You took " + game.numberOfGuesses + " guesses.");

    } else {
      System.out.println("Sorry, You lost! The word was " + game.word);
    }
  }

}
