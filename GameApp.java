import java.util.Scanner;

public class GameApp {

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
		// True when game is over.
		Boolean gameOver = false;

		try {
			randSeed = Integer.parseInt(args[0]);
			duration = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			// End the program if arguments are not int.
			System.out.println("Invalid argument, program terminated.");
			System.exit(0);
		}

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
			main_menu_loop(game);
		}
	}

	/**
	 * Add Comments as per implementation
	 */
	private static void start() {
		// TODO: The interactive game logic goes here
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

	private static void main_menu_loop(Game game) {

		// The job to work on.
		int jobToWork = 0;
		// Time to work on job.
		int timeToWork = 0;
		// Worked job.
		Job jobWorked;

		System.out.println("You have " + game.getTimeToPlay() + " left in the game!");

		// Create the jobs then display active jobs.
		game.createJobs();
		game.displayActiveJobs();

		// Wait for user input on which job to do. (NOTE, NO ERROR HANDLING).
		System.out.print("\nSelect a job to work on: ");
		jobToWork = STDIN.nextInt();
		System.out.print("For how long would you like to work on this job?: ");
		timeToWork = STDIN.nextInt();
		
		jobWorked = game.updateJob(jobToWork, timeToWork);

	}
}