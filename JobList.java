///////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Lec001 Spring 2017 
//                   CS367 Lec002 Spring 2017
// PROJECT:          p2
// FILE:             JobList.java
//
// Author: Collin Lacy
//         Yuchen Bai
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;

/**
 * Implements the JobListADT and defines the container object JobList as 
 * a singly linked list of Job.
 * 
 * Provides user with methods to determine 
 * the size of the list, check if the list item is empty,
 * add a Job to the list, remove a Job from a particular
 * index position and access a Job at a particular index position.
 *
 * <p>Bugs: None
 *
 * @author Collin Lacy
 *         Yuchen Bai
 */

public class JobList implements ListADT<Job> {

	//Head Node
	private Listnode<Job> head;
	//number of items stored in the list
	private int numItems;

	/**
	 * Constructor for a new JobList object which defines
	 * a new singly linked list of nodes. 
	 * 
	 * Also sets the number of items in the list
	 * 
	 */
	public JobList (Listnode<Job> head, int numItems){
		head = new Listnode<Job>(null);
		numItems = 0;
	}

	/**
	 * Constructor for Job iterator 
	 * 
	 * @return reference to Job iterator
	 */
	public Iterator<Job> iterator() {
		return this.iterator();
	}

	/**
	 * Adds an item at the end of the list
	 * and increments numItems.
	 *  
	 * Implementation: 
	 *
	 * Create a new Listnode for Job, pointed to by the "next" field
	 * of the last node in the list (which will be the header node if
	 * the list is empty)
	 * 
	 * Increment numItems.
	 * 
	 * 
	 * @param item                          an item to add to the list
	 * @throws IllegalArgumentException     if item is null
	 */
	@Override
	public void add(Job item) {

		//Assign head to current node
		Listnode<Job> curr = head;

		//Throw Exception
		if (item == null) {
			throw new IllegalArgumentException();
		}

		//Base case, head is null. 
		if (curr == null) {
			head = new Listnode<Job>(item);
			head.setNext(null);
		}

		//Traverse through list until the
		//link to the next node is null (end of list)
		while(curr.getNext()!=null)
			curr = curr.getNext();

		//Create new Listnode, pointed by "next" field of
		//the last node in the list (head if empty)
		curr.setNext(new Listnode<Job>(item));

		//Create new Listnode, pointed by "next" field of
		//the last node in the list (head if empty)
		numItems++;
	}

	/** 
	 * Add an item at any position in the list
	 * 
	 * 
	 * Implementation: 
	 * 
	 * Check for a bad position and if so, throw an exception.
	 * Otherwise:
	 * 		If we're being asked to add to the end of the list,
	 * 		call the "add to the end" method 
	 * Otherwise:
	 * 		Find the node n in position pos - 1 (counting the header
	 * 		node as being in position - 1)
	 * 
	 * 		Create a new Listnode for item, whose next field points to the
	 * 		node after n and set n's "next" field to point to the new node
	 *
	 * @param item     an item to be added to the list
	 *              
	 * @param pos      position at which the item must be added.
	 *                 Indexing starts from 0
	 *                 
	 * @throws IllegalArgumentException     
	 *              if item is null
	 * @throws IndexOutOfBoundsException
	 *              if pos is less than 0 or greater than size() - 1
	 */
	@Override
	public void add(int pos, Job item) {
		//Check for bad position
		if (pos < 0 || pos > numItems) {
			throw new IndexOutOfBoundsException();
		}
		//Throw Exception
		if (item == null) {
			throw new IllegalArgumentException();
		}
		//If asked to add to end, let other add method do the work
		if (pos == numItems) {
			add(item);
			return;
		}

		//Assign head to current node
		Listnode<Job> curr = head;

		//Create a new Listnode for item
		Listnode<Job> newNode = new Listnode<Job>(item);

		//Find node n in position pos - 1
		//Counting the header as being in position - 1
		for (int i = 0; i < pos - 2; i++) {
			curr = curr.getNext();
		}

		//set newNode's next field to point to node after n
		newNode.setNext(curr.getNext());

		//set n's "next" field to point to the new node
		curr.setNext(newNode);

		//Increment numItems
		numItems++;
	}

	/** 
	 * Check if a particular item exists in the list
	 * 
	 * Implementation:
	 * 
	 * @param item                       the item to be checked for in the list
	 *              
	 * @return true                      if value exists, else false
	 *              
	 * @throws IllegalArgumentException  if item is null
	 *             
	 */
	@Override
	public boolean contains(Job item) {
		//Assign head to current node
		Listnode<Job> curr = head;

		//Throw Exception if null
		if (item == null) {
			throw new IllegalArgumentException();
		}
		//Traverse through list until the last node,
		//Return true if item equals the current node's data
		while (curr.getNext() != null) {
			curr = curr.getNext();
			if (item.equals(curr.getData())) {
				return true;
			}
		}
		return false;
	}

	/** 
	 * Returns the position of the item to return
	 * 
	 * Implementation:
	 * 
	 * @param pos                         position of the item to be returned
	 *              
	 * @throws IndexOutOfBoundsException  if position is less than 0 or greater than size() - 1
	 *              
	 */
	@Override
	public Job get(int pos){
		//Check for bad pos range 
		if (pos < 0 || pos >= numItems) {
			throw new IndexOutOfBoundsException();
		}
		//Assign head to current node
		Listnode<Job> curr = head;

		//Traverse until the node before pos,
		//Get and return node at pos
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		return curr.getData();
	}


	/** 
	 * Returns true if the list is empty
	 * 
	 * @return value is true if the list is empty else false            
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (head.getData() == null && head.getNext() == null);
	}

	/** 
	 * Removes the item at the given position
	 * 
	 * @param pos  the position of the item to be deleted from the list
	 *          
	 * @return     returns the item deleted
	 * @throws IndexOutOfBoundsException  
	 * 				if the pos value is less than 0 or greater than size() - 1
	 */
	@Override
	public Job remove(int pos) {
		//Check for bad pos range
		if (pos < 0 || pos >= size() - 1) {
			throw new IndexOutOfBoundsException();
		}

		//Assign head to current node
		Listnode<Job> curr = head;
		for (int i = 0; i < pos - 2; i++) {
			curr = curr.getNext();
		}

		Job removed = curr.getNext().getData();
		curr.setNext(curr.getNext().getNext());

		//Decrement numItems by 1
		numItems--;

		return removed;
	}

	/** 
	 * Returns the number of items in the list or zero
	 * 
	 * @return the number of items in this list
	 */
	@Override
	public int size() {
		//Keep track of number of items in list
		int count = 0;
		
		//Assign head to current node
		Listnode<Job> curr = head;
		
		//Traverse linked list until last node
		while(curr!=null){
			//Increment numItems
			count++;
			curr = curr.getNext();
		}
		return count;
	}



}
