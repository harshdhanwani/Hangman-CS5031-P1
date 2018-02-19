package uk.ac.standrews.cs5031;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
	public String word;
	public int numberOfGuesses;
	public int chancesLeft;
	public int numberOfHints;

	// List of letters guessed
	ArrayList<Character> letterGuessed;
	// List of letters letterNotGuessed guessed
	ArrayList<Character> letterNotGuessed;
	
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
	public GameState(String target, int numberOfGuesses, int numberOfHints) {
		this.word = target;

		// replaced <Character> with <>
		letterNotGuessed = new ArrayList<>();
		   letterGuessed = new ArrayList<>();
		
		for(int i = 0; i < target.length(); ++i) {
			if (!letterNotGuessed.contains(Character.toLowerCase(target.charAt(i))))
			letterNotGuessed.add(Character.toLowerCase(target.charAt(i)));
		}
		//System.out.println(missing);
		
		this.numberOfGuesses = 0;
		chancesLeft = numberOfGuesses;
		this.numberOfHints = numberOfHints;
	}
	
	void showWord() {
		for (int i = 0; i < word.length(); ++i) {
			if (letterGuessed.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
		// System.out.println(missing);
	}
	
	boolean guessLetter() {
		int i;
		char letter;
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String str = sc.next().toLowerCase();
		
		if (str.length() > 1) {
			if (str==word) {
				letterNotGuessed.clear();
				return true;
			} else return false;
		}
		
		letter = str.charAt(0);
		
		if (letter == '?') {
			hint();
			return false;
		}
		
		for(i = 0; i < letterNotGuessed.size(); ++i) { // Loop over the letterNotGuessed letterGuessed
			if (letterNotGuessed.get(i) == letter) {
				letterNotGuessed.remove(i);
				letterGuessed.add(letter);
				numberOfGuesses++;
				return true;
			}
		}

		numberOfGuesses++; // One more guess
		chancesLeft--;
		return false;
	}
	
	boolean won() {
		if (letterNotGuessed.size() == 0) return true; else return false;
	}

	boolean lost() {
		if (letterNotGuessed.size() > 0 && chancesLeft == 0) return true; else return false;
	}

	void hint() {
		if (numberOfHints == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		// INDEX OUT OF BOUND EXCEPTION - fix this.
		System.out.println(letterNotGuessed.get((int)(Math.random()* letterNotGuessed.size())));
	}
}
