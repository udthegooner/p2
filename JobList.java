import java.util.Iterator;

public class JobList implements ListADT<Job> {
	private Listnode<Job> head;
	private int numItems;

	public JobList (Listnode<Job> head, int numItems){
		this.head = head;
		this.numItems = numItems;
	}

	@Override
	public Iterator<Job> iterator() {
		// TODO Auto-generated method stub
		
				return itr;
	}

	@Override
	public void add(Job item) {
		// TODO Auto-generated method stub
		Listnode<Job> curr = head;
		while(curr.getNext()!=null)
			curr = curr.getNext();
		curr.setNext(new Listnode<Job>(item));
		numItems++;
	}

	@Override
	public void add(int pos, Job item) {
		// TODO Auto-generated method stub
		Listnode<Job> curr = head;
		Listnode<Job> newnode = new Listnode<Job>(item,null);
		for(int p = 0; p<pos-2; p++)
			curr = curr.getNext();
		newnode.setNext(curr.getNext());
		curr.setNext(newnode);
	}

	@Override
	public boolean contains(Job item) {
		// TODO Auto-generated method stub
		Listnode<Job> curr = head;
		if(item == null)
			throw new IllegalArgumentException();
		while(curr.getNext()!=null){
			curr = curr.getNext();
			if (item.equals(curr.getData()))
				return true;
			}
		return false;
	}

	@Override
	public Job get(int pos){
		// TODO Auto-generated method stub
		if(pos<0 || pos>=numItems)
			throw new IndexOutOfBoundsException();

		Listnode<Job> curr = head;
		for(int p = 0;p<pos;p++)
			curr=curr.getNext();
		return curr.getData();
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (head.getData() == null && head.getNext() == null);
	}

	@Override
	public Job remove(int pos) {
		// TODO Auto-generated method stub
		Listnode<Job> curr = head;
		if(pos<0 || pos>=numItems)
			throw new IndexOutOfBoundsException();
		for(int p = 0; p<pos-2; p++)
			curr = curr.getNext();
		Job removed = curr.getNext().getData();
		curr.setNext(curr.getNext().getNext());
		return removed;
	}
 
	@Override
	public int size() {
		// TODO Auto-generated method stub
		int count = 0;
		Listnode<Job> curr = head;
		while(curr!=null){
			count++;
			curr = curr.getNext();
		}
		return count;
	}



}
