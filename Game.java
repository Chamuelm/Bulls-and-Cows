import java.util.Collections;
import java.util.ArrayList;

/**
 * This class maintains logics of Bulls and Cows game.
 * It's generate code and check for guesses
 * 
 * @author Moshe Hamiel - 308238716
 */
public class Game {
	// Instance variables
	private char[] code;	// Code of current game round
	

	/**
	 * Game no-argument constructor initialize instance variables 
	 * and generate random code
	 */
	public Game() {
		code = newCode();
	}
	
	   /**
	    * Return new valid code which is 4 different digits.
	    * Adds all digits to array list and shuffle it, then take the first four.
	    * 
	    *  @return returnCode char[4] with valid code which is 
	    *  different 4 digits
	    */
	private char[] newCode() {
		char[] returnCode = new char[Guess.CODE_LENGTH];				// return chars array
		ArrayList<Integer> digits = new ArrayList<Integer>();		// Contain digits to shuffle
		
		// Add digits to list
		for (int i='0'; i<='9'; i++ ) {
			digits.add(i);
		}
		
		// Shuffle the list
		Collections.shuffle(digits);
		
		// Fill returnCode with different shuffled elements
		for(int i=0; i<returnCode.length; i++) {
			returnCode[i] = (char)(int)(digits.get(i));
		}
		
		return returnCode;
	}
	
	   /**
	    *  Check guess g for bulls and cows.
	    *  Assumes g's code is array with length of Guess.CODE_LENGH contains digits only
	    *  Puts number bulls and cows in Guess's members 
	    *  @param g guess to check
	    *  
	    */
	public void checkGuess(Guess g) {
		int bulls = 0;	// Bulls counter
		int cows = 0;		// Cows counter
		char[] gArr = g.getCode();	// Guess code
		
		for(int i=0; i< gArr.length; i++) {
			// For each digit in guess check if exist in code
			// If exist - check if bull and increment relevant counter
			if (isExist(gArr[i], code)) {
				if (gArr[i] == code[i])
					bulls++;
				else
					cows++;
			}
		} // End of for
		
		g.setBulls(bulls);
		g.setCows(cows);
	} // End of method
	
	   /**
	    *  Check if c is in arr
	    *  @param c char to check
	    *  @param arr array to look in
	    *  @return true if c exist in arr
	    */
	private boolean isExist(char c, char[] arr) {
		for (char x : arr) {
			if (x == c)
				return true;
		}
		
		return false;
	}
}
