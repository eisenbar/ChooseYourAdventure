import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Adventure1 {

	/*
	 * TODO: Support for input adventure JSON file TODO: make function for
	 * gameplay loop independent of # of rounds
	 *
	 */

	public static ParseAudio parser = new ParseAudio();
	public static String question = "";
	public static String hypothesis = "";
	public static boolean gameOver = false;

	// Constructor
	public Adventure1() {

		//this.parser = new ParseAudio();
		this.question = "";
		this.hypothesis = "";
		this.gameOver = false;
		
		return;
	}

	// Main Method that is called
	public static void main(String args[]) {

		String input = "";

		try {

			File file = new File("riddles.json");
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\0");
			input = sc.next();
			sc.close();

			LevelObject level = new LevelObject(input);
			Adventure1 adv = new Adventure1();

			adv.startGame(level);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return;
	}

	private static int startGame(LevelObject level) {

		String endResult = "";

		System.out.println("Welcome to the Game!");
		question = "Welcome to the Game!";
		//promptEnterKey();
		try {
			endResult = gamePlayLoop(level, level.getRound(0));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("This is the end result: " + endResult);
		question = "This is the end result: " + endResult;
		gameOver = true;
		

		return 0;
	}

	private static String gamePlayLoop(LevelObject level, Round currentRound) throws InterruptedException {

		String result = "";

		// print text and question
		System.out.println(currentRound.text);
		question = currentRound.text;
		TimeUnit.SECONDS.sleep(1);
		System.out.println(currentRound.question);
		question = currentRound.question;

		// check if last round
		if (currentRound.option1.equalsIgnoreCase("end") || currentRound.result1 == -1) {
			return "end";
		}

		// grab audio input
		try {
			result = parser.parseInputStream(currentRound.option1, currentRound.option2);

		} catch (Exception e) {
			return "error";
		}
		// resolve if option1
		if (result.equalsIgnoreCase(currentRound.option1)) {
			return gamePlayLoop(level, level.getRound(currentRound.result1 - 1));
		}

		// resolve if option2
		else if (result.equalsIgnoreCase(currentRound.option2)) {
			return gamePlayLoop(level, level.getRound(currentRound.result2 - 1));
		}

		// resolve if stop
		else if (result.equalsIgnoreCase("stop")) {
			return "stop";
		}

		return "";
	}

	/*
	 * Takes in two options and grabs the input from the user. Supports keyboard
	 * input and 2 decisions. Currently not being used
	 */
	@SuppressWarnings("unused")
	private static int getInput(String option1, String option2) {
		String d = "";
		Scanner input = new Scanner(System.in);
		boolean loop = true;

		System.out.print("(" + option1 + " or " + option2 + "): ");

		while (loop) {
			d = input.nextLine();

			// option1 chosen
			if (d.equalsIgnoreCase(option1)) {
				input.close();
				return 0;
			}
			// option2 chosen
			else if (d.equalsIgnoreCase(option2)) {
				input.close();
				return 1;
			}

			// quits
			else if (d.equalsIgnoreCase("quit")) {
				loop = false;
			}

			// bad input
			else {
				System.out.println("Try Again");
				System.out.print("(" + option1 + " or " + option2 + "): ");
			}
		}

		input.close();
		return -1;
	}

	/*
	 * HELPER METHODS BELOW
	 */

	// Got this one from Stack Overflow
	private static void promptEnterKey() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		return;
	}

}