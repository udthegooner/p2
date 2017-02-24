/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p2
// FILE:             Game.java
//
// TEAM:    #46 Paras
// Authors: 
// Author1: Udhbhav Gupta, ugupta23@wisc.edu, ugupta23, Lec 002
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Maintains the active list of jobs and utilizes the JobSimulator class to 
 * create new jobs to be added to the end of the job listing. Provides accessor
 * methods for time currently left in the game, number of jobs on JobList and
 * total score. Updates jobs chosen by the user and also displays active jobs
 * and the scoreBoard.
 *
 * <p>Bugs: none
 *
 * @author Udhbhav
 */
public class Game{

    /**
     * A list of all jobs currently in the queue.
     */
    private ListADT<Job> list;
    /**
     * Whenever a Job is completed it is added to the scoreboard
     */
    private ScoreboardADT scoreBoard;
    private int timeToPlay;
    private JobSimulator jobSimulator;

    /**
     * Constructor. Initializes all variables.
     * @param seed
     * seed used to seed the random number generator in the Jobsimulator class.
     * @param timeToPlay
     * duration used to determine the length of the game.
     */
    public Game(int seed, int timeToPlay){
        jobSimulator = new JobSimulator(seed);
        this.timeToPlay = timeToPlay;
        scoreBoard = new Scoreboard();
    }

    /**
     * Returns the amount of time currently left in the game.
     * @returns the amount of time left in the game.
     */
    public int getTimeToPlay() {
        return timeToPlay;
    }

    /**
     * Sets the amount of time that the game is to be executed for.
     * Can be used to update the amount of time remaining.
     * @param timeToPlay
     *        the remaining duration of the game
     */
    public void setTimeToPlay(int timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    /**
     * States whether or not the game is over yet.
     * @returns true if the amount of time remaining in
     * the game is less than or equal to 0,
     * else returns false
     */
    public boolean isOver(){
        if (timeToPlay <= 0)
        	return true;
        return false;
    }
    
    /**
     * This method simply invokes the simulateJobs method
     * in the JobSimulator object.
     */
    public void createJobs(){
        jobSimulator.simulateJobs(list, timeToPlay);
    }

    /**
     * @returns the length of the Joblist.
     */
    public int getNumberOfJobs(){
        return list.size();
    }

    /**
     * Adds a job to a given position in the joblist.
     * Also requires to calculate the time Penalty involved in
     * adding a job back into the list and update the timeToPlay
     * accordingly
     * @param pos
     *      The position that the given job is to be added to in the list.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(int pos, Job item){
        list.add(pos, item);
    }

    /**
     * Adds a job to the joblist.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(Job item){
        list.add(item);
    }

    /**
     * Given a valid index and duration,
     * executes the given job for the given duration.
     *
     * This function should remove the job from the list and
     * return it after applying the duration.
     *
     * This function should set duration equal to the
     * amount of time remaining if duration exceeds it prior
     * to executing the job.
     * After executing the job for a given amount of time,
     * check if it is completed or not. If it is, then
     * it must be inserted into the scoreBoard.
     * This method should also calculate the time penalty involved in
     * executing the job and update the timeToPlay value accordingly
     * @param index
     *      The job to be inserted in the list.
     * @param duration
     *      The amount of time the given job is to be worked on for.
     */
    public Job updateJob(int index, int duration){
        Job currJob = list.remove(index); //job to be updated
        
        //checking that duration isn't greater than total timeunits for the job
        try{
        	if (duration > currJob.getTimeUnits())
        		throw new IllegalArgumentException();
        } catch (IllegalArgumentException e){
        	System.out.println("Incorrect duration entered.");
        	System.exit(0);
        }
 
        //checking if duration is greater than timeToPlay
        if (duration > timeToPlay)
        	duration = timeToPlay;
        
        //setting steps for the job
        int newSteps = currJob.getSteps() + duration;
        currJob.setSteps(newSteps);
        
        if (currJob.isCompleted())
        	scoreBoard.updateScoreBoard(currJob);
        
        timeToPlay -= duration;
        return currJob;
    }

    /**
     * This method produces the output for the initial Job Listing, IE:
     * "Job Listing
     *  At position: job.toString()
     *  At position: job.toString()
     *  ..."
     *
     */
    public void displayActiveJobs(){
        System.out.println("Job Listing");
        for (int i=0; i<list.size(); i++)
        	System.out.println("At position: " + i + " " 
        						+ list.get(i).toString());

    }

    /**
     * This function simply invokes the displayScoreBoard method in the 
     * ScoreBoard class.
     */
    public void displayCompletedJobs(){
    	scoreBoard.displayScoreBoard();
    }

    /**
     * This function simply invokes the getTotalScore method of the 
     * ScoreBoard class.
     * @return the value calculated by getTotalScore
     */
    public int getTotalScore(){
        return scoreBoard.getTotalScore();
    }
}