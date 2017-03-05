///////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          p2
// FILE:             Scoreboard.java
//
// TEAM:    #46 Paras
// Author1: (Daniel Jones, djones39@wisc.edu, lecture 2)
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * The Scoreboard class holds a Joblist of all the completed jobs.
 *  This class allows one to get the total score earned so far, 
 *  update the score board, and also display the Score board.
 *
 * <p>Bugs: None known
 *
 * @author Daniel Jones
 */
public class Scoreboard implements ScoreboardADT{
	
	private ListADT<Job> list = new JobList(); //list to hold completed jobs
	private int total = 0; //total score of all completed jobs
	
	/**
	 * This method adds a completed Job to the list and adds the score of that
	 * job to total.
	 *
	 * PRECONDITIONS: A job was completed
	 * 
	 * @param job A Job object to be added to list
	 */
	public void updateScoreBoard(Job job){
		list.add(job);
		total += job.getPoints();
	}
	
	/**
	 * This method returns the total score earned by the player.
	 *
	 * @return total score
	 */
	public int getTotalScore(){
		return total;
	}
	
	/**
	 * This method prints information about all completed jobs in list.
	 */
	public void displayScoreBoard(){
		System.out.println("Total Score: " + total + "\nThe jobs completed:");
		for(int i = 0; i < list.size(); ++i){
			System.out.println("Job Name: " + list.get(i).getJobName());
			System.out.println("Points earned for this job: " 
								+ list.get(i).getPoints());
			System.out.println("--------------------------------------------");
		}
	}

}
