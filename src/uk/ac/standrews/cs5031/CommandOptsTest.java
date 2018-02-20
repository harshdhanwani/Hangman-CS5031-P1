package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandOptsTest {

	@Test
	public void optionsTest() {
		String[] args = { "--guesses", "12", "--hints", "5", "words.txt" };
		CommandOpts opts = new CommandOpts(args);
		assertEquals(opts.maxGuesses, 12);
		assertEquals(opts.maxHints, 5);
		assertEquals(opts.wordsource, "words.txt");
	}



}
