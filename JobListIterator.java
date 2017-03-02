
/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          P2
// FILE:             JobListIterator.java
//
// TEAM:    46 Paras
// Authors: 
// Author1: (Daniel Jones,djones39,lecture 2)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.NoSuchElementException;

/**
 * Defines the iterator for a JobList by providing access to next and hasNext
 * methods.
 *
 * <p>
 * Bugs: none
 *
 * @author Daniel Jones
 * 		   Matthew Perry
 */
public class JobListIterator<Job> implements java.util.Iterator {
	// Current Listnode where the iterator points.
	private Listnode<Job> curr;

	/**
	 * Constructor for the iterator which sets curr to the header node which is
	 * the beginning of the list.
	 *
	 * @param head
	 *            header node of the list
	 */
	public JobListIterator(Listnode<Job> head) {
		curr = head;
	}

	/**
	 * Returns the next node in the list
	 *
	 * PRECONDITIONS: the list being iterated has a next node
	 * 
	 * @return the next Listnode in the list
	 * @throws NoSuchElementException
	 *             if no further node exists in the list
	 */
	public Listnode<Job> next() {
		if (hasNext() == false) {
			throw new NoSuchElementException();
		}
		curr = curr.getNext();
		return curr;
	}

	/**
	 * Checks if there is another node in the list
	 * 
	 * @return true if a next node exists in the list; false otherwise
	 */
	public boolean hasNext() {
		if (curr.getNext() == null) {
			return false;
		}
		return true;
	}
}
