/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          P2
// FILE:             (file name)
//
// TEAM:    46 Paras
// Authors: 
// Author1: (Daniel Jones,djones39,lecture 2)
import java.util.NoSuchElementException;
public class JoblistIterator implements java.util.Iterator{
	Listnode<Job> curr;

	public JoblistIterator(Listnode<Job> head) {
		curr = head;
	}
	
	public Listnode<Job> next(){
		if(hasNext() == false){
			throw new NoSuchElementException();
		}
		curr = curr.getNext();
		return curr;
	}
	
	public boolean hasNext(){
		if(curr.getNext() == null){
			return false;
		}
		return true;
	}
}
