import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 * This class maintains Bulls and Cows game.
 * Includes receiving data from user and prints dialogs to user. 
 * 
 * @author Moshe Hamiel - 308238716
 */
public class BullsAndCows {

	final static int BULLS_TO_WIN = 4; // Number of Bulls needs to win

	   /**
	    * Manage games instances for multiple rounds 
	    * 
	    */
	public static void main(String[] args) {
		Game game;					// Game instance
		boolean operaion;		// Games loop boolean expression
		int keepPlay;				// Holds answer from user if want to play again

		operaion = true;
		while (operaion) {
			game = new Game();					// Initialize new game round
			keepPlay = playGame(game);	// Play games until player refuse to continue

			if (keepPlay == JOptionPane.YES_OPTION)
				operaion = true;
			else
				operaion = false;
		}
	}

	   /**
	    * Manage a whole game round
	    * 
	    *  @param	game a Game instance 
	    *  @return userResponse JOptionPane.YES_OPTION if user want to keep play
	    *  another round
	    */
	static int playGame(Game game) {
		int count;											// Guesses counter
		String guessTryStr;							// Holds guess try string
		boolean operation;							// Loop exit variable
		int userResponse;								// Holds answer from user if want to continue play
		Guess.guessError err;						// Holds error type after guess validity check
		Guess guessTry;									// Guess object contains guess data, cows and bulls
		ArrayList<Guess> guessesList;		// List of guesses tried

		// Variables Initializing
		count = 0;
		userResponse = JOptionPane.NO_OPTION;
		guessesList = new ArrayList<>();

		// Game information for user in start of game and ask for first guess
		guessTryStr = JOptionPane
				.showInputDialog("We are playing Bulls and Cows.\n" + "You have to guess a code of 4 varied digits.\n"
						+ "For each guess you will get a feedback of number of \n"
						+ "Bulls for correct hits, and Cows for correct digits in wrong place.\n\n"
						+ "Please enter your first guess: ");
		if (guessTryStr != null)
			operation = true;
		else
			operation = false;

		// Loop irritate until user find the right code or press cancel
		while (operation) {
			guessTry = new Guess();											// Guess object initialize
			err = guessTry.setCode(guessTryStr);				// Insert data from user to Guess object

			if (err != Guess.guessError.NO_ERROR) {
				// Invalid input - show error message and skip next loop irritation
				showError(err);
			} else {
				// Valid Input, increment counter, calculate bulls and cows 
				// and add to guesses list
				count++;
				game.checkGuess(guessTry);
				guessesList.add(guessTry);
			}

			if (guessTry.getBulls() == BULLS_TO_WIN) {
				// Player won
				operation = false;										// Stop loop

				userResponse = JOptionPane.showConfirmDialog(null,
						"You won!\n" + "Guess needed: " + count + "\nDo you want to play again?.\n", "You Won!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			} else {
				// Continue play
				// Print dialog with data and request next input
				guessTryStr = getGuessString(guessesList);
				if (guessTryStr == null) {
					// User canceled prompt so exit
					operation = false;
					userResponse = JOptionPane.NO_OPTION;
				}
			}
		} // End of guesses loop
		return userResponse;
	}

	   /**
	    * Print error dialog to user
	    * 
	    *  @param	err error code of error to display 
	    */
	static void showError(Guess.guessError err) {
		String message;

		switch (err) {
		case INVALID_CHAR:
			message = "Invalid character in input.\nPlease try again.\n";
			break;
		case INVALID_LEN:
			message = "Invalid length of input guess.\nPlease try again.\n";
			break;
		case REPEATED_DIGIT:
			message = "Guess have repeated digits.\nPlease try again.\n";
			break;
		default:
			message = "Invalid input.\nPlease try again.\n";
			break;
		}

		JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.ERROR_MESSAGE);
	}

	   /**
	    * Show input dialog to user with list of all guesses tried before
	    * and ask user to enter new guess
	    * 
	    *  @param	list list of tried guesses 
	    *  @return input input string from user with next guess
	    */
	static String getGuessString(ArrayList<Guess> list) {
		String input;
		String guessesStr = "";
		
		// Generate guesses string:
		guessesStr += "Guess    Bulls    Cows\n";	// Title
		
		for (int i = 0; i < list.size(); i++)
			guessesStr += 
					list.get(i).toString() + "       " + 
					list.get(i).getBulls() + "           " +
					list.get(i).getCows() + "\n";
		
		// Show dialog
		input = JOptionPane
				.showInputDialog("Last guesses: \n" + guessesStr + "\nPlease try again: \n");
		
		return input;
	}

}
