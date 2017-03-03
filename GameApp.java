import java.util.Scanner;

public class GameApp {

	private static Game game;
	private Boolean gameOver = false; // True when game is over

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

		String seedPrompt = "Please enter a seed number: ";
		randSeed = getIntegerInput(seedPrompt);

		String durationPrompt = "Please enter the duration: ";
		duration = getIntegerInput(durationPrompt);
		
		// Exit if seed and duration are not positive.
		if (!(randSeed > 0 && duration > 0)) {
			System.out.println("Negative argument, program terminated.");
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
	 * Add Comments as per implementation
	 */
	private static void start() {
		//Keep track of number of iterations
		int iterationCount = 0;
		
		//Display how much time remains in the game
		System.out.println(game.getTimeToPlay());
		
		
		if (iterationCount == 0) {
		//Use the game object to create new jobs
		game.createJobs();
		iterationCount++;
		}
		
		//Use the game object to display jobs left in the game
		System.out.println("You have " + game.getNumberOfJobs()
						   + " left in the game!");
		
		//Use the game object to display all active jobs in the game
		game.displayActiveJobs();
		
		//Prompt the user for an index of a job to work on
		//Time penalty for picking a job that is not at index 0
		System.out.println("Select a job to work on: ");
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
			System.out.print(STDIN.next() + " is not an int.  Please enter an integer.");
		}
		return STDIN.nextInt();
	}
}
