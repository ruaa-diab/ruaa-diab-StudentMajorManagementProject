package application;

public class LinkedList<T extends Comparable<T>> implements Listable<T> {

	private Node<T> head;

	@Override
	public void insert(T data) {
		Node<T> newNode = new Node<>(data);

		if (head == null) {
			head = newNode;
			return;
		}

		Node<T> prev = null, curr = head;
		for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
			;

		if (prev == null /* or curr == head */) { // insert first
			newNode.setNext(head);
			head = newNode;
		} else if (curr == null) // insert last
			prev.setNext(newNode);
		else { // insert between
			newNode.setNext(curr);
			prev.setNext(newNode);
		}
	}

	public void insertrec(T data) {
		insertrec(data, null, head);

	}

	private void insertrec(T data, Node<T> prev, Node<T> curr) {
		if (curr == null || curr.getData().compareTo(data) >= 0) {

			Node<T> newNode = new Node<T>(data);

			if (prev == null) {
				newNode.setNext(head);
			} else if (curr == null) {
				prev.setNext(newNode);
			} else {
				prev.setNext(newNode);
				newNode.setNext(curr);
			}

		}

		insertrec(data, curr, curr.getNext());

	}

	@Override
	public boolean delete(T data) {

		Node<T> prev = null, curr = head;
		for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
			;

		if (curr != null && curr.getData().compareTo(data) == 0) {
			if (prev == null) {
				head = curr.getNext();
				return true;
			} else {
				prev.setNext(curr.getNext());
				return true;

			}
		}

		return false;
	}

	public boolean deleterec(T data) {
		return deleterec(data, null, head);

	}

	private boolean deleterec(T data, Node<T> prev, Node<T> curr) {

		if (curr == null || curr.getData().compareTo(data) > 0) {
			return false;
		}

		if (curr != null && curr.getData().compareTo(data) == 0) {
			if (prev == null) {
				head = curr.getNext();
				return true;

			} else {
				prev.setNext(curr.getNext());
				return true;
			}
		}
		return deleterec(data, curr, curr.getNext());

	}

	@Override
	public Node<T> search(T data) {

		Node<T> curr = head;
		for (; curr != null && curr.getData().compareTo(data) < 0; curr = curr.getNext())
			;

		if (curr != null && curr.getData().compareTo(data) == 0)
			return curr;
		return null;
	}

	public boolean searchrec(T data) {
		return searchrec(data, head);
	}

	private boolean searchrec(T data, Node<T> curr) {

		if (curr == null || curr.getData().compareTo(data) > 0) {
			return false;
		}
		if (curr.getData().compareTo(data) == 0) {
			return true;
		} else {
			return searchrec(data, curr.getNext());
		}

	}

	@Override
	public void clear() {
		head=null;

	}

	@Override
	public void print() {
		Node<T> curr = head;

		String l = "head--->";
		while (curr != null) {
			l += curr + "-->"+"\n";

			curr = curr.getNext();
		}
		System.out.println(l + "null");

	}

	@Override
	public String toString() {
		Node<T> curr = head;

		String l = "head--->";
		while (curr != null) {
			l += curr + "-->";

			curr = curr.getNext();
		}
		return (l + "null");

	}

	@Override
	public int size() {
		int c = 0;
		Node<T> curr = head;
		while (curr != null) {
			c++;

			curr = curr.getNext();
		}
		return c;

	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	
	
	public T getAtrec(int index) {
		if(head==null||index>size()||index<0) {
			return null;
		}
		
		return getAtrec(index,head,0);
	}
	
	
	
	private T getAtrec(int index,Node<T> curr,int cIndex) {
		if(curr!=null) {
			if(index==cIndex){
				return curr.getData();
				
			}else {
				return getAtrec(index,curr.getNext(),cIndex+1);
			}
		}
		return null;
	}
	
	
	
	@Override
	public T getAt(int index) {
		
		if(head==null||index>size()||index<0) {
			return null;
		}
		Node<T> curr=head;
		
		int c=0;
		while(curr!=null) {
			if(index==c) {
				return curr.getData();
			}else {
				c++;
				curr=curr.getNext();
			}
		}
		return null;
		
	}
	
	public void removeDuplicate() {
		Node<T> curr = head;
		while (curr != null && curr.getNext() != null) {
			if (curr.getData().compareTo(curr.getNext().getData()) == 0) {
				curr.setNext(curr.getNext().getNext());
			} else {

				curr = curr.getNext();
			}
		}
	}

	public int getFreq(T data) {
		Node<T> curr = head;
		int c = 0;
		while (curr != null) {
			if (curr.getData().compareTo(data) == 0) {
				c++;
				curr = curr.getNext();
			}
		}
		return c;
	}
	
	
	
	
	
	
	
	

}
