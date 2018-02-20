package uk.ac.standrews.cs5031;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class GameState {
	public String word;
	public int numberOfGuesses;
	public int chancesLeft;
	public int numberOfHints; // allowed hints
	boolean hintCondition;
    int lettersLeftCount;

	// List of letters guessed
	ArrayList<Character> letterGuessed;
	// List of letters letterNotGuessed guessed
	ArrayList<Character> letterNotGuessed;

HashSet<Character> hintLetters;
    char hintLetter;

	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
	public GameState(String target, int numberOfGuesses, int numberOfHints) {
		this.word = target;

		// replaced <Character> with <>
		letterNotGuessed = new ArrayList<>();
		   letterGuessed = new ArrayList<>();

		for(int i = 0; i < target.length(); i++) {
			if (!letterNotGuessed.contains(Character.toLowerCase(target.charAt(i))))
			letterNotGuessed.add(Character.toLowerCase(target.charAt(i)));
		}
//        System.out.println(letterNotGuessed);

		for (Character ch : letterNotGuessed){
            System.out.print(ch);
        }

        System.out.println();
        hintLetters = new HashSet<>(letterNotGuessed);

		for (int i = 0; i <letterNotGuessed.size() ; i++){
		    hintLetters.add(Character.toLowerCase(target.charAt(i)));

        }


        for (Character chaar : hintLetters){
            System.out.print(chaar);
        }
//        System.out.println(hintLetters);
        lettersLeftCount = hintLetters.size();

		System.out.println(lettersLeftCount);
		
		this.numberOfGuesses = 0;
		chancesLeft = numberOfGuesses;
		this.numberOfHints = numberOfHints;

	}
	
	void showWord() {
		for (int i = 0; i < word.length(); i++) {
			if (letterGuessed.contains(Character.toLowerCase(word.charAt(i)))) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
		// System.out.println(missing);
	}
	
	boolean guessLetter() {

	    setHintCondition(false);

		System.out.print("Guess a letter or word (? for a hint): ");

        String input = sc.next().toLowerCase();
        System.out.println("Guess number:" + numberOfGuesses);
        char  letter = input.charAt(0);

		if (input.length() > 1) {
//            if (input.compareToIgnoreCase(word) == 0) {
//                letterNotGuessed.clear();
//                System.out.println("chances left:2 " + chancesLeft);
////                numberOfGuesses++;
//                return true;
//            } else return false;

            return validateGuessedWord(input);
		}

		if (letter == '?') {
			hint();
			setHintCondition(true);
			return false;
		} else {
            return validateGuessedLetter(letter);
        }

//		for(int i = 0; i < letterNotGuessed.size(); i++) { // Loop over the letterNotGuessed letterGuessed
//            if (letterNotGuessed.get(i) == letter) {
//                letterNotGuessed.remove(i);
//                letterGuessed.add(letter);
//                numberOfGuesses++;
////                chancesLeft--;
//                System.out.println("chances left:3 " + chancesLeft);
//
//                return true;
//            }
//        }




	}


	boolean validateGuessedLetter(char letter){
        numberOfGuesses++;
        for(int i = 0; i < letterNotGuessed.size(); i++) { // Loop over the letterNotGuessed letterGuessed
            if (letterNotGuessed.get(i) == letter) {
                hintLetters.remove(letterNotGuessed.get(i));
                letterNotGuessed.remove(i);
                letterGuessed.add(letter);
//                numberOfGuesses++;
                return true;
            }
        }
         // One more guess
        chancesLeft--;
            return false;
    }

    boolean validateGuessedWord(String input){
        numberOfGuesses++; // One more guess

            if (input.compareToIgnoreCase(word) == 0) {
                letterNotGuessed.clear();
                return true;
            } else {
                chancesLeft--;
                return false;
            }


    }

    void setHintCondition(boolean inputVar){
	    hintCondition = inputVar;
    }

    boolean getHintCondition(){
	    return hintCondition;
    }
	
	boolean won() {
		if (letterNotGuessed.size() == 0) return true; else return false;
	}

	boolean lost() {
		if (letterNotGuessed.size() > 0 && chancesLeft == 0) return true; else return false;
	}

	void hint() {

		// INDEX OUT OF BOUND EXCEPTION - fix this.

        System.out.println("Hint letters size : " + hintLetters.size() + "   " + hintLetters);

		hintLetter = letterNotGuessed.get((int)(Math.random()* letterNotGuessed.size()));

        if (numberOfHints == 0) {
            System.out.println("No more hints allowed");
        } else if (hintLetters.contains(hintLetter)){
                System.out.print("Try: ");
                System.out.println(hintLetter + " no. of hints left : " + numberOfHints);
                hintLetters.remove(hintLetter);
                numberOfHints--;
                lettersLeftCount--;
            } else if (hintLetters.isEmpty()){
             System.out.println(" all possible hints have been given.");
             }
             else {
             hint();
         }

        }
}
