/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          P2
// FILE:             Scoreboard.java
//
// TEAM:    46 Paras
// Author1: (Daniel Jones, djones39@wisc.edu, lecture 2)
/**
 * The Scoreboard class holds a ListADT<Job> of all the completed jobs.
 *  This class allows one to get the total score earned so far, update the score board,
 *  and thus score, and also print out a display of the Score board.
 *
 * <p>Bugs: None known
 *
 * @author Daniel Jones
 */
public class Scoreboard implements ScoreboardADT{
	JobList list;
	int total;
	
	/**
	 * This method allows one to add a Job object to list after the job was completed.
	 *
	 * PRECONDITIONS: A job was completed
	 * 
	 * POSTCONDITIONS: The list has one new job at the end
	 *
	 * @param job A Job object to be added to list
	 * @return void
	 */
	public void updateScoreBoard(Job job){
		list.add(job);
	}
	
	/**
	 * This method returns the total score earned by the player by adding the score int of every
	 * job in list.
	 *
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	 *
	 * @return The int total, which is the number of earned points.
	 */
	public int getTotalScore(){
		total = 0;
		for(int i = 0; i < list.size(); ++i){
			total = total + list.get(i).getPoints();
		}
		return total;
	}
	
	/**
	 * This method allows one to print information about all completed jobs in a premade format.
	 *
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: Scoreboard is printed
	 *
	 * @return void
	 */
	public void displayScoreBoard(){
		System.out.println("Total Score: " + getTotalScore() + "\nThe jobs completed:");
		for(int i = 0; i < list.size(); ++i){
			System.out.println("Job Name: " + list.get(i).getJobName());
			System.out.println("Points earned for this job: " + list.get(i).getPoints());
			System.out.println("--------------------------------------------");
		}
	}

}
