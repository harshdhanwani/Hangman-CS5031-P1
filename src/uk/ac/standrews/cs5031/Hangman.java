package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    GameState game;
    CommandOpts opts;
    boolean correct;

    opts = new CommandOpts(args);

    // Strings cannot be checked/compared using if (opts.wordsource == ""), .equals should be used instead or .isEmpty() function.
    if (opts.wordsource.isEmpty()) {

      System.out.println("1. Counties \n" +
        "2. Countries \n" +
        "3. Cities \n" +
        "Pick a Category: ");

      // Concatenating strings is a better option than spanning multiple println statements. Check reference: https://softwareengineering.stackexchange.com/questions/246534/how-bad-is-it-calling-println-often-than-concatenating-strings-together-and-ca
//		System.out.println("  1. Counties");
//		System.out.println("  2. Countries");
//		System.out.println("  3. Cities");
//		System.out.print("Pick a category:");


      game = new GameState(Words.randomWord(sc.nextInt()), opts.maxGuesses, opts.maxHints);
    } else {
      game = new GameState(Words.randomWord(opts.wordsource), opts.maxGuesses, opts.maxHints);
    }

    // while(!game.won() && !game.lost()) changed to and && changed to || for better functionality.
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

      // bad practice of if statements, extra if statement used un necessary, decreases performance.
//			if (correct) System.out.println("Good guess!");
//			if (!correct) System.out.println("Wrong guess!");
    }

    if (game.won()) {
      System.out.println("Congratulations! You've won the game. \n" + "The word you guessed was - " + game.word + "\n"
        + "You took " + game.numberOfGuesses + " guesses.");

//			System.out.println("Well done!");
//			System.out.println("You took " + game.numberOfGuesses + " guesses");
    } else {
      System.out.println("Sorry, You lost! The word was " + game.word);
    }
  }

}
