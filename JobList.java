import java.util.Iterator;

///////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p2
// FILE:             JobList.java
//
// TEAM:    #46 Paras
// Authors: 
// Author1: Udhbhav Gupta, ugupta23@wisc.edu, ugupta23, Lec 002
// Author2: Collin Lacy, clacy@wisc.edu, clacy; Lec 001
// Author3: Yuchen Bai
// Author4: Matthew Perry, mperry3@wisc.edu, mperry3, Lec 002
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Implements the JobListADT and defines the container object JobList as a
 * singly linked list of Job objects.
 * 
 * Provides user with methods to determine the size of the list, check if the
 * list is empty, add a Job to the list, remove a Job from a particular
 * index position and access a Job at a particular index position.
 *
 * <p>
 * Bugs: None
 *
 * @author Collin Lacy 
 * 		   Yuchen Bai 
 * 		   Matthew Perry 
 *         Udhbhav Gupta
 */

public class JobList<Job> implements ListADT<Job> {

	private Listnode<Job> header; //header Node
	private int numItems; //number of items stored in the list

	/**
	 * Constructor for a new JobList object which defines a new singly linked
	 * list of nodes. Also sets the number of items in the list to 0.
	 * 
	 */
	public JobList() {
		header = new Listnode<Job>(null);
		numItems = 0;
	}

	/**
	 * Creates and returns a new iterator for the joblist
	 * 
	 * @return reference to Job iterator
	 */
	public Iterator<Job> iterator() {

		return new JobListIterator(header);
	}

	/**
	 * Adds an item at the end of the list and increments numItems.
	 * 
	 * Implementation:
	 *
	 * Create a new Listnode for Job, pointed to by the next field of the last
	 * node in the list
	 * 
	 * Increment numItems.
	 * 
	 * @param item
	 *            an item to add to the list
	 * @throws IllegalArgumentException
	 *             if item is null
	 */
	@Override
	public void add(Job item) {

		// Throw Exception
		if (item == null) throw new IllegalArgumentException();

		// create new node with item data, next field left null
		Listnode<Job> newNode = new Listnode<Job>(item);

		// Traverse through list until the end of list
		Listnode<Job> curr = header;
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}

		// the last node in the list points to the newNode
		curr.setNext(newNode);

		// Increment numItems
		numItems++;
	}

	/**
	 * Add an item at any position in the list
	 * 
	 * Implementation:
	 * 
	 * Check for a bad position and if so, throw an exception. Otherwise: If
	 * we're being asked to add to the end of the list, call the "add to the
	 * end" method Otherwise: Find the node n in position pos - 1.
	 * 
	 * Create a new Listnode for item, whose next field points to the node 
	 * after n and set n's "next" field to point to the new node
	 *
	 * @param item
	 *            an item to be added to the list
	 * 
	 * @param pos
	 *            position at which the item must be added. Indexing starts
	 *            from 0
	 * 
	 * @throws IllegalArgumentException
	 *             if item is null
	 * @throws IndexOutOfBoundsException
	 *             if pos is less than 0 or greater than size()
	 */
	@Override
	public void add(int pos, Job item) {
		// Check for bad position
		if (pos < 0 || pos > size()) {
			throw new IndexOutOfBoundsException();
		}
		// Throw Exception
		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		// If asked to add to end, let other add method do the work
		if (pos == size()) {
			add(item);
			return;
		}

		// Create a new Listnode for item
		Listnode<Job> newNode = new Listnode<Job>(item);

		// Traversal node
		Listnode<Job> curr = header;
		// Traverse to the listnode before our index.
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		
		newNode.setNext(curr.getNext());
		curr.setNext(newNode);
		numItems++;
	}

	/**
	 * Check if a particular item exists in the list
	 * 
	 * Implementation:
	 * 
	 * @param item
	 *            the item to be checked for in the list
	 * 
	 * @return true if value exists, else false
	 * 
	 * @throws IllegalArgumentException
	 *             if item is null
	 * 
	 */
	@Override
	public boolean contains(Job item) {
		// Throw Exception if null
		if (item == null) {
			throw new IllegalArgumentException();
		}

		// Traversal node
		Listnode<Job> curr = header;
		
		// Traverse through list until the last node,
		// Return true if item equals the current node's data
		while (curr.getNext() != null) {
			if (item.equals(curr.getData())) {
				return true;
			}
			curr = curr.getNext();
		}
		return false;
	}

	/**
	 * Returns the item at required position
	 * 
	 * @param pos
	 *            position of the item to be returned
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if position is less than 0 or greater than size() - 1
	 *             
	 * @return Job at the required position
	 * 
	 */
	@Override
	public Job get(int pos) {
		// Check for bad pos range
		if (pos < 0 || pos > size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		// Traversal node
		Listnode<Job> curr = header;
				
		// Traverse until the node before pos,
		// Get and return node at pos
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		return curr.getNext().getData();
	}

	/**
	 * Returns true if the list is empty
	 * 
	 * @return true if the list is empty else false
	 */
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Removes the item at the given position
	 *
	 * @param pos
	 *            the position of the item to be deleted from the list
	 * 
	 * @return returns the item deleted
	 * @throws IndexOutOfBoundsException
	 *             if the pos value is less than 0 or greater than size() - 1
	 */
	@Override
	public Job remove(int pos) {
		// Check for bad pos range
		if (pos < 0 || pos > size() - 1) {
			throw new IndexOutOfBoundsException();
		}

		// Traversal node
		Listnode<Job> curr = header;
		// Find node before the node to be removed
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		
		// Save node to remove so that we can return
		Listnode<Job> toRemove = curr.getNext();

		// node before set to node after the node to be removed
		curr.setNext(curr.getNext().getNext());

		// Decrement numItems by 1
		numItems--;

		return toRemove.getData();
	}

	/**
	 * Returns the number of items in the list
	 * 
	 * @return the number of items in this list
	 */
	@Override
	public int size() {
		return numItems;
	}
}


