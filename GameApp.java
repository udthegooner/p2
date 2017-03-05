///////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          p2
// FILE:             GameApp.java
//
// TEAM:    #46 Paras
// Authors: 
// Author1: Udhbhav Gupta, ugupta23@wisc.edu, ugupta23, Lec 002
// Author2: Collin Lacy, clacy@wisc.edu, clacy; Lec 001
// Author3: Matthew Perry, mperry3@wisc.edu, mperry3, Lec 002
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Scanner;

/**
 * This class runs the game. The game takes command line arguments for random
 * seed and game duration. The game starts, creating jobs with priorities,
 * prompts user for a job to work on and awards score for completed jobs.
 * The game runs for the specified duration.
 * 
 * <p>
 * Bugs; None known
 *
 * @author Matthew, Udhbhav, Collin
 */
public class GameApp {

	private static Game game; //game instance

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
		
		int randSeed = -1; // Stores the random number generator seed
		int duration = -1; // Stores the duration of the game
		
		//parsing randSeed and duration from command line arguments
		try {
			if (args.length > 0){
				randSeed = Integer.parseInt(args[0]);
				duration = Integer.parseInt(args[1]);
			}	
		} catch (NumberFormatException e){
			//End the program if arguments are not int
			System.out.println("Invalid argument, program terminated.");
			System.exit(0);
		}
		
		// Exit if seed and duration are not positive.
		if (!(randSeed >= 0 && duration > 0)) {
			System.out.println("Negative/Zero argument, program terminated.");
			System.exit(0);
		}

		// Create a new game.
		GameApp gameApp = new GameApp(randSeed, duration);

		// Intro message.
		System.out.println("Welcome to the Job Market!");
		
		//running the game
		gameApp.start();
		
		//game over message
		System.out.print("Game Over!\nYour final score: " 
						+ game.getTotalScore());
	}
	

	/**
	 * Method that starts and runs the game until duration = 0. Displays active
	 * jobs, prompts user for the job to complete, updates the job chosen, 
	 * creates additional jobs and shows scoreboard.
	 */
	private static void start() {
		game.createJobs();
		
		// loop that runs until game is over 
		while (!game.isOver()) {
			
			System.out.printf("You have %d left in the game!\n", 
							game.getTimeToPlay());
			game.displayActiveJobs();
			
			//choosing job
			String jobPrompt = "\nSelect a job to work on: ";
			int jobNum = getIntegerInput(jobPrompt);
			if (jobNum < 0 || jobNum >= game.getNumberOfJobs()) {
				System.out.println("Invalid argument, program terminated.");
				System.exit(0);
			}
			
			//choosing duration
			String durationPrompt = "For how long would you like to work "
									+ "in this job?: ";
			int jobDuration = getIntegerInput(durationPrompt);
			if (jobDuration <= 0 ) {
				System.out.println("Invalid argument, program terminated.");
				System.exit(0);
			}
			
			Job chosenJob = game.updateJob(jobNum, jobDuration);
			
			//completed job
			if (chosenJob.isCompleted()){
				System.out.println("Job completed! Current Score: " 
						+ game.getTotalScore());
				game.createJobs();
				game.displayCompletedJobs();
			}
			//incomplete job	
			else {
				String insertPrompt = "At what position would you like to "
									+ "insert the job back into the list?\n";
				int insertPos = getIntegerInput(insertPrompt);
				game.addJob(insertPos, chosenJob);
				game.createJobs();
			}
				
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
			System.out.print(STDIN.next() + " is not an int.  "
					+ "Please enter an integer.");
		}
		return STDIN.nextInt();
	}

}

