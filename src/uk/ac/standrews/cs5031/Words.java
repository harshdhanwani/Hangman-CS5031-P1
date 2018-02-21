package uk.ac.standrews.cs5031;

import java.io.*;
import java.util.ArrayList;

public class Words {

  // Changed variable names, access made private.
  private static String[] counties = {"Argyll and Bute",
    "Caithness",
    "Kingdom of Fife",
    "East Lothian",
    "Highland",
    "Dumfries and Galloway",
    "Renfrewshire",
    "Scottish Borders",
    "Perth and Kinross"};

  private static String[] countries = {"Scotland",
    "England",
    "Wales",
    "Northern Ireland",
    "Ireland",
    "France",
    "Germany",
    "Netherlands",
    "Spain",
    "Portugal",
    "Belgium",
    "Luxembourg",
    "Switzerland",
    "Italy",
    "Greece"};

  private static String[] cities = {"St Andrews",
    "Edinburgh",
    "Glasgow",
    "Kirkcaldy",
    "Perth",
    "Dundee",
    "Stirling",
    "Inverness",
    "Aberdeen",
    "Falkirk"};

  /**
   * Method returning a random word from the respective array list
   *
   * @param category category in terms of countries, counties or cities.
   * @return returns a random word
   */
  public static String randomWord(int category) {

    if (category == 1) {
      return counties[(int) (Math.random() * 9)];
    } else if (category == 2) {
      return countries[(int) (Math.random() * 15)];
    } else {
      return cities[(int) (Math.random() * 10)];
    }
  }

  /**
   * Method returning custom random word from the respective array list.
   *
   * @param wordsource the word source entered/selected by the user
   * @return returns a custom word. Returns Error if file not found or if any other exception is found.
   */
  public static String randomWord(String wordsource) {
    String line;
    // replaced <String> to <>, converted field to local variable.
    ArrayList<String> customWordsFromList = new ArrayList<>();

    try {
      FileReader file = new FileReader(wordsource);
      BufferedReader reader = new BufferedReader(file);
      while ((line = reader.readLine()) != null) {
        customWordsFromList.add(line);
      }
      // added reader.close(), issue reported by findbugs plugin.
      reader.close();
      // returns a random word from the words list
      return customWordsFromList.get((int) (Math.random() * customWordsFromList.size()));

    } catch (FileNotFoundException e) {
      System.out.println("File error");
      return "";
    } catch (IOException e) {
      System.out.println("IO error");
      return "";
    }
  }
}
