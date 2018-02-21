package uk.ac.standrews.cs5031;

public class CommandOpts {

  public int maxGuesses;
  public int maxHints;
  String wordsource;
  // to avoid magic numbers
  private static final int DEFAULT_MAXIMUM_GUESSES = 10;
  private static final int DEFAULT_MAXIMUM_HINTS = 5;

  /**
   * Constructor method to initialise and assign maximum guesses, hints allowed and the word. Call to function that
   * parses the command line input.
   *
   * @param args arguments passed in
   */
  CommandOpts(String[] args) {
    setMaxGuesses(DEFAULT_MAXIMUM_GUESSES);
    setMaxHints(DEFAULT_MAXIMUM_HINTS);
    wordsource = "";
    commandLineOptions(args);
  }

  /**
   * Method to parse the options typed in via the command line for guesses, hints and the word source.
   *
   * @param args arguments to be parsed. Logic simplified in order to prevent Number Format Exception found while
   *             testing.
   */
  // to avoid number format exception.
  private void commandLineOptions(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("--guesses")) {
        int maxGuessesArgs = maxGuessesInput(args[i + 1]);
        setMaxGuessesValue(maxGuessesArgs);
        if (maxGuessesArgs != -1) {
          i++;
        }
      } else if (args[i].equals("--hints")) {
        int maxHintsArgs = maxHintsInput(args[i + 1]);
        setMaxHintsValue(maxHintsArgs);
        if (maxHintsArgs != -1) {
          i++;
        }
      } else {
        wordsource = args[i];
      }
    }
  }

  /**
   * Method to return the maximum number of guesses.
   *
   * @return returns max guesses
   */
  public int getMaxGuesses() {
    return maxGuesses;
  }

  /**
   * Method to set the maximum number of guesses.
   *
   * @param maxGuesses maximum guesses
   */
  private void setMaxGuesses(int maxGuesses) {
    this.maxGuesses = maxGuesses;
  }

  /**
   * Method to return the maximum number of hints.
   *
   * @return maximum hints allowed
   */
  public int getMaxHints() {
    return maxHints;
  }

  /**
   * Method to set the Maximum number of hints.
   *
   * @param maxHints input for number of maximum hints.
   */
  private void setMaxHints(int maxHints) {
    this.maxHints = maxHints;
  }

  /**
   * Method to check the input if its valid or not and prevent NumberFormatException error.
   *
   * @param guessInput input to be checked.
   * @return returns the input depending on the validation of the input.
   */
  private int maxGuessesInput(String guessInput) {
    try {
      int input = Integer.parseInt(guessInput);
      if (input >= 0) {
        return input;
      } else {
        return -1;
      }
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * Method to check the input if its valid or not and prevent NumberFormatException error.
   *
   * @param hintInput input to be checked.
   * @return returns the input depending on the validation of the input.
   */
  private int maxHintsInput(String hintInput) {
    try {
      int input = Integer.parseInt(hintInput);
      if (input >= 0) {
        return input;
      } else {
        return -1;
      }
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * Method to set maximum guesses value
   *
   * @param maxGuesses input value
   */
  private void setMaxGuessesValue(int maxGuesses) {
    if (maxGuesses < 0) {
      System.out.println("Please enter a valid input. Value should be greater than 0");
    } else {
      setMaxGuesses(maxGuesses);
    }
  }

  /**
   * Method to set maximum hints value
   *
   * @param maxHints input value
   */
  private void setMaxHintsValue(int maxHints) {
    if (maxHints < 0) {
      System.out.println("Please enter a valid input. Value should be greater than 0");
    } else {
      setMaxHints(maxHints);
    }
  }
}
