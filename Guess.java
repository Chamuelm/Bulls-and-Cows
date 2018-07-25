/**
 * This class maintains guess object and guess input checks
 * 
 * @author Moshe Hamiel - 308238716
 */
public class Guess {
	// Instance variables
	char[] code;		// Holds guess
	int bulls;
	int cows;
	
	// Static variables
	final static int CODE_LENGTH = 4; 	// Length of valid guess
	
	static enum guessError {
		INVALID_LEN, // Length of guess is invalid
		INVALID_CHAR, // Guess contains invalid characters
		REPEATED_DIGIT, // Guess contains repeated digit
		NO_ERROR // No error in guess
	}

	static char[] emptyCode = { '0', '0', '0', '0' };

	/**
	 * Guess no-argument constructor initialize instance variables 
	 */
	public Guess() {
		code = new char[CODE_LENGTH];
		copyGuess(emptyCode);
		
		bulls = cows = 0;
	}
	
	   /**
	    *  @return code current guess code
	    */
	public char[] getCode() {
		return code;
	}
	
	   /**
	    * Set a new guess after validity check
	    * 
	    *  @param	g guess code to set
	    *  @return err NO_ERROR if successfully set or relevant error code
	    */
	public guessError setCode(String g) {
		guessError err = checkGuessValidity(g);
		if (err == guessError.NO_ERROR)
			copyGuess(g);
		return err;
	}
	
	   /**
	    *  @return bulls number of successful hits in current guess
	    */
	public int getBulls() {
		return bulls;
	}
	
	   /**
	    * Set a number of bulls in current guess
	    * 
	    *  @param	b number to set
	    */
	public void setBulls(int b) {
		bulls = b;
	}
	
	   /**
	    *  @return cows number of hits in wrong location in current guess
	    */
	public int getCows() {
		return cows;
	}
	
	   /**
	    * Set a number of cows in current guess
	    * 
	    *  @param	c number to set
	    */
	public void setCows(int c) {
		cows = c;
	}

	   /**
	    * Check if string g is valid guess string
	    *  @param	g guess string to check
	    *  @return ret NO_ERROR or relevant error code from enum guessError
	    */
	guessError checkGuessValidity(String g) {
		guessError ret = guessError.NO_ERROR;

		if (g.length() != CODE_LENGTH)
			ret = guessError.INVALID_LEN; // String length is not valid
		else {
			for (int i = 0; i < CODE_LENGTH; i++) {
				// Check for invalid characters
				if (!Character.isDigit(g.charAt(i)))
					ret = guessError.INVALID_CHAR;
				else {
					// Check for repeated digits
					for (int j = i + 1; j < CODE_LENGTH; j++) {
						if (g.charAt(i) == g.charAt(j))
							ret = guessError.REPEATED_DIGIT;
					} // End of repeted character check
				}
			} // End of isDigit check
		} // End of length check

		return ret;
	}

	   /**
	    * copy guess from char array
	    *  @param	toCopy guess code copy
	    */
	void copyGuess(char[] toCopy) {
		for (int i = 0; i < CODE_LENGTH; i++)
			code[i] = toCopy[i];
	}
	
	   /**
	    * copy guess from String. Assume validity
	    *  @param	toCopy guess code copy
	    */	
	void copyGuess(String toCopy) {
		for (int i = 0; i < CODE_LENGTH; i++)
			code[i] = toCopy.charAt(i);
	}

	   /**
	    * @return	str string contains guess
	    */
	public String toString() {
		String str = "";
		for (int i=0; i<CODE_LENGTH; i++)
			str += code[i];
		
		return str;
	}
	
}