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

/**
 * Implements the JobListADT and defines the container object JobList as a
 * singly linked list of Job.
 * 
 * Provides user with methods to determine the size of the list, check if the
 * list item is empty, add a Job to the list, remove a Job from a particular
 * index position and access a Job at a particular index position.
 *
 * <p>
 * Bugs: None
 *
 * @author Collin Lacy Yuchen Bai Matthew Perry
 */

public class JobList<Job> implements ListADT<Job> {

	// Head Node
	private Listnode<Job> head;
	// number of items stored in the list
	private int numItems;

	/**
	 * Constructor for a new JobList object which defines a new singly linked
	 * list of nodes.
	 * 
	 * Also sets the number of items in the list
	 * 
	 */
	public JobList(Listnode<Job> node) {
		head = new Listnode<Job>(null);
		numItems = 0;

		if (node != null) {
			add(node.getData());
		}

	}

	/**
	 * Constructor for Job iterator
	 * 
	 * @return reference to Job iterator
	 */
	public JobListIterator<Job> iterator() {

		// Create an iterator with direct access to the JobList
		JobListIterator<Job> iterator = new JobListIterator<Job>(head);

		return iterator;

	}

	/**
	 * Adds an item at the end of the list and increments numItems.
	 * 
	 * Implementation:
	 *
	 * Create a new Listnode for Job, pointed to by the "next" field of the last
	 * node in the list (which will be the header node if the list is empty)
	 * 
	 * Increment numItems.
	 * 
	 * 
	 * @param item
	 *            an item to add to the list
	 * @throws IllegalArgumentException
	 *             if item is null
	 */
	@Override
	public void add(Job item) {

		// Assign head to current node
		Listnode<Job> curr = head;

		// Throw Exception
		if (item == null) {
			throw new IllegalArgumentException();
		}

		// create new node with item data, next field left null
		Listnode<Job> newNode = new Listnode<Job>(item);

		// If the list is empty, set dummy header's next field
		// to point to node to be added
		if (isEmpty()) {
			head.setNext(newNode);
			numItems++;
			return;
		}

		// Traverse through list until the
		// link to the next node is null (end of list)
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
	 * 
	 * Implementation:
	 * 
	 * Check for a bad position and if so, throw an exception. Otherwise: If
	 * we're being asked to add to the end of the list, call the "add to the
	 * end" method Otherwise: Find the node n in position pos - 1 (counting the
	 * header node as being in position - 1)
	 * 
	 * Create a new Listnode for item, whose next field points to the node after
	 * n and set n's "next" field to point to the new node
	 *
	 * @param item
	 *            an item to be added to the list
	 * 
	 * @param pos
	 *            position at which the item must be added. Indexing starts from
	 *            0
	 * 
	 * @throws IllegalArgumentException
	 *             if item is null
	 * @throws IndexOutOfBoundsException
	 *             if pos is less than 0 or greater than size() - 1
	 */
	@Override
	public void add(int pos, Job item) {
		// Check for bad position
		if (pos < 0 || pos > size() - 1) {
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

		// Traversal node
		Listnode<Job> curr = head;

		// Create a new Listnode for item
		Listnode<Job> newNode = new Listnode<Job>(item);

		// Special case for the 0 index.
		if (pos == 0) {
			newNode.setNext(curr.getNext());
			curr.setNext(newNode);
			return;
		}

		// Traverse to the listnode before our index.
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		newNode.setNext(curr.getNext());
		curr.setNext(newNode);
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
		// Assign head to current node
		Listnode<Job> curr = head;

		// Throw Exception if null
		if (item == null) {
			throw new IllegalArgumentException();
		}

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
	 * Returns the position of the item to return
	 * 
	 * Implementation:
	 * 
	 * @param pos
	 *            position of the item to be returned
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if position is less than 0 or greater than size() - 1
	 * 
	 */
	@Override
	public Job get(int pos) {
		// Check for bad pos range
		if (pos < 0 || pos > size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		// Assign head to current node
		Listnode<Job> curr = head;

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
	 * @return value is true if the list is empty else false
	 */
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Removes the item at the given position
	 * 
	 * 
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
		Listnode<Job> curr = head;
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
	 * Returns the number of items in the list or zero
	 * 
	 * @return the number of items in this list
	 */
	@Override
	public int size() {
		return numItems;
	}
}
