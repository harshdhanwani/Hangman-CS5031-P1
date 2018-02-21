package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandOptsTest {
  /**
   * Test case to test the arguments entered via the command line.
   */
  @Test
  public void optionsTest() {
    String[] args = {"--guesses", "12", "--hints", "5", "words.txt"};
    CommandOpts opts = new CommandOpts(args);
    assertEquals(opts.maxGuesses, 12);
    assertEquals(opts.maxHints, 5);
    assertEquals(opts.wordsource, "words.txt");
  }

  /**
   * Test case to test the state if some arguments are missing while running the application via the command line.
   */
  // if some arguments are missing
  @Test
  public void missingOptionsTest() {
    String[] args = {"--guesses", "--hints", "5", "words.txt"};
    CommandOpts opts = new CommandOpts(args);
    // default number of guesses set to set in the program.
    assertEquals(opts.maxGuesses, 10);
    assertEquals(opts.maxHints, 5);
    assertEquals(opts.wordsource, "words.txt");
  }

  /**
   * Test case to test the state if no arguments are entered. Default values then considered.
   */
  @Test
  public void noOptionsTest() {
    String[] args = {};
    CommandOpts opts = new CommandOpts(args);
    // default number of guesses set to set in the program.
    assertEquals(opts.maxGuesses, 10);
    // default number of hints set to set in the program.
    assertEquals(opts.maxHints, 5);
    assertEquals(opts.wordsource, "");
  }


}
