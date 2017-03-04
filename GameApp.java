import java.util.Scanner;

/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (project name)
// FILE:             (file name)
//
// TEAM:    #46 Paras
// Authors: (Be sure to check if programming teams are allowed)
// Author1: Collin Lacy
// Author2: Matthew Perry
//
// ---------------- OTHER ASSISTANCE CREDITS 
//
//
//
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class runs the game. The game takes the command line arguments as 
 * random seed and game duration.  the game ends when time runs out. Follows
 * the flow of the Game and generates jobs to be complete
 * 
 * <p> Bugs; None known
 *
 * @author 
 */
public class GameApp {

	// True when game is over.
	static boolean gameOver = false;
	// Stores our game to be played.
	public static Game game;

	/**
	 * Scanner instance for reading input from console
	 */
	private static final Scanner STDIN = new Scanner(System.in);

	/**
	 * Constructor for instantiating game class
	 * 
	 * @param seed:
	 *            Seed value as processed in command line
	 * @param timeToPlay:
	 *            Total time to play from command line
	 */
	public GameApp(int seed, int timeToPlay) {
		game = new Game(seed, timeToPlay);
	}

	/**
	 * Main function which takes the command line arguments and instantiate the
	 * GameApp class. The main function terminates when the game ends. Use the
	 * getIntegerInput function to read inputs from console
	 *
	 * @param args:
	 *            Command line arguments <seed> <timeToPlay>
	 */
	public static void main(String[] args) {

		// Stores the random number generator seed as String.
		int randSeed = -1;
		// Stores the positive integer indication duration as String.
		int duration = -1;

		try {
			if (args.length > 0) {
				randSeed = Integer.parseInt(args[0]);
				duration = Integer.parseInt(args[1]);
			}
		} catch (NumberFormatException e) {
			// End the program if arguments are not int.
			System.out.println("Invalid argument, program terminated.");
			System.exit(0);
		}

		// Exit if seed and duration are not positive.
		if (!(randSeed > 0 && duration > 0)) {
			System.out.println("Invalid argument, program terminated.");
			System.exit(0);
		}

		// Create a new game.
		GameApp gameApp = new GameApp(randSeed, duration);

		// Intro message.
		System.out.println("Welcome to the Job Market!");

		// As long as the game is not over, go through the gameplay loop.
		while (!gameOver) {
			gameApp.start();
		}
	}

	/**
	 * Starts the game and prompts requests for jobs. The meathod also follows 
	 * when the game has runout of time, the meathod ends the game and displays 
	 * the player's final score.
	 */
	private static void start() {
		// The job to work on.
		int jobToWork = 0;
		// Time to work on job.
		int timeToWork = 0;
		// Where user wants to reinsert unfinished job.
		int reinsert = 0;
		// Prompt for selecting job.
		String whichJobPrompt = "Select a job to work on: ";
		// Prompt for how long to work on job
		String jobTimePrompt = "For how long would you like to work on this job?: ";
		// Prompt for reinserting unfinished job.
		String reinsertPrompt = "At what position would you like to insert the job back into the list?\n";
		// Worked job.
		Job jobWorked;
		// Checks if valid index
		boolean jobIsValid = false;
		// Checks if valid time to work
		boolean timeIsValid = false;
		// Checks if the insert is valid
		boolean insertValid = false;

		// Check if the game is over.
		if (game.getTimeToPlay() <= 0) {
			gameOver = true;
			System.out.println("Game Over!");
			System.out.println("Your final score: " + game.getTotalScore());
			return;
		}

		// Display time.
		System.out.println("You have " + game.getTimeToPlay() + " left in the game!");

		// Create the jobs then display active jobs.
		game.createJobs();
		game.displayActiveJobs();
		System.out.println("");

		while (!jobIsValid) {
			// Wait for user input on which job to do.
			jobToWork = getIntegerInput(whichJobPrompt);
			if (jobToWork < 0 || jobToWork > game.getNumberOfJobs()) {
				jobIsValid = false;
			}
			else {
				jobIsValid = true;
			}
		}

		// Check the index of the user's job choice, penalize by index
		// if not 0.
		if (jobToWork != 0) {
			game.setTimeToPlay(game.getTimeToPlay() - jobToWork);
		}

		// Length of time to work.
		while (!timeIsValid) {
			// Wait for user input on steps for job.
			timeToWork = getIntegerInput(jobTimePrompt);
			timeIsValid = true;
		}

		// Do the job.
		jobWorked = game.updateJob(jobToWork, timeToWork);

		// If job was not finished, ask which position to reinsert it.
		if (!jobWorked.isCompleted()) {
			while (!insertValid) {
				reinsert = getIntegerInput(reinsertPrompt);
				insertValid = true;
			}
			// Calculate the time penalty.
			if (reinsert > game.getNumberOfJobs() || reinsert < 0) {
				game.setTimeToPlay(game.getTimeToPlay() - game.getNumberOfJobs());
				reinsert = game.getNumberOfJobs();
			} else {
				game.setTimeToPlay(game.getTimeToPlay() - reinsert);
			}

			// Reinsert the job at given index and create jobs.
			game.addJob(reinsert, jobWorked);
			game.createJobs();
		}

	}

	/**
	 * Displays the prompt and returns the integer entered by the user to the
	 * standard input stream.
	 *
	 * Does not return until the user enters an integer. Does not check the
	 * integer value in any way.
	 * 
	 * @param prompt
	 *            The user prompt to display before waiting for this integer.
	 */
	public static int getIntegerInput(String prompt) {
		System.out.print(prompt);
		while (!STDIN.hasNextInt()) {
			System.out.print(STDIN.next() + " is not an int.  Please enter an integer: ");
		}
		return STDIN.nextInt();
	}
}
