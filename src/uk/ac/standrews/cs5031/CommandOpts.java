package uk.ac.standrews.cs5031;

public class CommandOpts {


  public int maxGuesses;
  public int maxHints;

  String wordsource;

  // to avoid magic numbers
  private static final int DEFAULT_MAXIMUM_GUESSES = 10;
  private static final int DEFAULT_MAXIMUM_HINTS = 5;

  CommandOpts(String[] args) {
    setMaxGuesses(DEFAULT_MAXIMUM_GUESSES);
    setMaxHints(DEFAULT_MAXIMUM_HINTS);
    wordsource = "";
    commandLineOptions(args);
  }

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

  public int getMaxGuesses() {
    return maxGuesses;
  }

  private void setMaxGuesses(int maxGuesses) {
    this.maxGuesses = maxGuesses;
  }

  public int getMaxHints() {
    return maxHints;
  }

  private void setMaxHints(int maxHints) {
    this.maxHints = maxHints;
  }

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

  private void setMaxGuessesValue(int maxGuesses) {
    if (maxGuesses < 0) {
      System.out.println("Please enter a valid input. Value should be greater than 0");
    } else {
      setMaxGuesses(maxGuesses);
    }
  }

  private void setMaxHintsValue(int maxHints) {
    if (maxHints < 0) {
      System.out.println("Please enter a valid input. Value should be greater than 0");
    } else {
      setMaxHints(maxHints);
    }
  }
}
