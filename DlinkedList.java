package application;

public class DlinkedList<T extends Comparable<T>> implements DListable<T> {

	private Dnode<T> head;

	@Override
	public void insert(T data) {
		Dnode<T> newNode = new Dnode<T>(data), curr = head;
		for (; curr != null && curr.getData().compareTo(data) < 0 && curr.getNext() != null; curr = curr.getNext())
			;

		if (head == null) {
			head = newNode;

		} else {

			if (curr.getData().compareTo(data) >= 0 && curr.getPrev() == null) {
				curr.setPrev(newNode);
				newNode.setNext(curr);
				head = newNode;
			} else if (curr.getData().compareTo(data) < 0 && curr.getNext() == null) {
				curr.setNext(newNode);
				newNode.setPrev(curr);

				// newNode.setNext(null);
			} else {
				newNode.setNext(curr);
				newNode.setPrev(curr.getPrev());
				curr.getPrev().setNext(newNode);
				curr.setPrev(newNode);

			}
		}
	}

	public void insertRec(T data) {
		insertRec(data, head);
	}

	private void insertRec(T data, Dnode<T> curr) {

		if (curr != null & curr.getData().compareTo(data) < 0 && curr.getNext() != null) {
			insertRec(data, curr.getNext());
		} else {
			Dnode<T> newNode = new Dnode<T>(data);

			if (head == null) {
				head = newNode;
			} else if (curr.getData().compareTo(data) >= 0 && curr == head) {
				head.setPrev(newNode);
				newNode.setNext(curr);
				head = newNode;
			} else if (curr.getData().compareTo(data) < 0 && curr.getNext() == null) {
				curr.setNext(newNode);
				newNode.setPrev(curr);
			} else {

				newNode.setNext(curr);
				newNode.setPrev(curr.getPrev());
				curr.setPrev(newNode);
				curr.getPrev().setNext(newNode);
			}

		}

	}

	@Override
	public boolean delete(T data) {

		Dnode<T> curr = head;

		for (; curr != null && curr.getData().compareTo(data) < 0; curr = curr.getNext())
			;
		if (curr.getData().compareTo(data) != 0 || curr == null)
			return false;

		if (curr.getNext() == null && curr.getPrev() == null) {
			head = null;
			return true;
		} else if (curr.getPrev() == null) {
			curr.getNext().setPrev(null);
			head = curr.getNext();
			return true;
		} else if (curr.getNext() == null) {
			curr.getPrev().setNext(null);
			return true;
		}
		curr.getPrev().setNext(curr.getNext());
		curr.getNext().setPrev(curr.getPrev());
		return true;

	}

	private boolean deleterec(T data, Dnode<T> curr) {
		if (curr == null || curr.getData().compareTo(data) > 0) {
			return false;
		}

		if (curr.getData().compareTo(data) == 0) {

			if (curr.getPrev() == null && curr.getNext() == null) {
				head = null;
				return true;
			} else if (curr.getPrev() == null) {
				curr.getNext().setPrev(null);
				head = curr.getNext();
				return true;
			} else if (curr.getNext() == null) {
				curr.getPrev().setNext(null);
				return true;
			} else {
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
				return true;
			}

		}
		return deleterec(data, curr.getNext());

	}

	public boolean deleterec(T data) {
		return deleterec(data, head);
	}

	@Override
	public Dnode<T> search(T data) {

		Dnode<T> curr = head;
		for (; curr.getNext() != null && curr.getData().compareTo(data) < 0; curr = curr.getNext())
			;

		if (curr.getData().compareTo(data) == 0) {
			return curr;
		} else {

			return null;
		}
	}

	private boolean searchrec(T data, Dnode<T> curr) {

		if (curr == null) {
			return false;
		} else {

			if (curr.getData().compareTo(data) == 0) {
				return true;
			} else {
				return searchrec(data, curr.getNext());
			}

		}

	}

	public boolean searchrec(T data) {
		return searchrec(data, head);
	}

	@Override
	public T getAt(int index) {
		if (index < 0) {
			return null;
		}

		Dnode<T> curr = head;
		int c = 0;
		while (c < index && curr != null) {
			c++;
			curr = curr.getNext();
		}
		if (curr == null) {
			return null;
		} else {
			return curr.getData();
		}
	}

	@Override
	public void clear() {
		head = null;

	}

	@Override
	public void print() {
		Dnode<T> curr = head;
		System.out.print("Head --> ");
		while (curr != null) {
			System.out.println(curr.toString() + " --> "+"\n");
			curr = curr.getNext();
		}
		System.out.println("Null");

	}

	private void printrec(Dnode<T> curr) {
		if (curr == null) {
			System.out.println("Null");
		} else {
			System.out.println(curr.getData() + "---->");
			printrec(curr.getNext());
		}

	}

	public void printrec() {
		printrec(head);
	}

	@Override
	public int size() {
		Dnode<T> curr = head;
		int c = 0;
		while (curr != null) {
			c++;
			curr = curr.getNext();
		}
		return c;
	}

	public int sizerec() {
		return sizerec(head);
	}

	private int sizerec(Dnode<T> curr) {
		if (curr == null) {
			return 0;
		} else {
			return 1 + sizerec(curr.getNext());
		}

	}

	@Override
	public String toString() {
		Dnode<T> curr = head;
		String res = "Head --> ";
		while (curr != null) {
			res += curr + " --> ";
			curr = curr.getNext();
		}
		res += "Null";
		return res;
	}

	public int getFreq(T data) {
		Dnode<T> curr = head;
		int c = 0;
		while (curr != null) {
			if (curr.getData().compareTo(data) == 0) {
				c++;
				curr = curr.getNext();
			}
		}
		return c;
	}

	public void removeDuplicate() {
		Dnode<T> curr = head;
		while (curr != null && curr.getNext() != null) {
			if (curr.getData().compareTo(curr.getNext().getData()) == 0) {
				curr.setNext(curr.getNext().getNext());
			} else {

				curr = curr.getNext();
			}
		}
	}

	private void removeduplicaterec(Dnode<T> curr) {
		if (curr == null || curr.getNext() == null) {
			return;
		}

		if (curr.getData().compareTo(curr.getNext().getData()) == 0) {
			curr.setNext(curr.getNext().getNext());
			removeduplicaterec(curr);

		} else {
			removeduplicaterec(curr.getNext());

		}

	}

	public void removeduplicaterec() {
		removeduplicaterec(head);
	}

	public void reverse() {
		Dnode<T> curr = head, tempNext;

		while (curr != null) {
			head = curr;
			tempNext = curr.getNext();

			curr.setNext(curr.getPrev());
			curr.setPrev(tempNext);
			curr = tempNext;
		}

	}

	public void reverserec() {
		if (head != null) {

			reverserec(head);

		}
	}

	private void reverserec(Dnode<T> curr) {

		Dnode<T> tempNext;
		curr = head;
		if (curr == null) {
			return;
		}

		tempNext = curr.getNext();

		curr.setNext(curr.getPrev());
		curr.setPrev(tempNext);

		if (tempNext == null) {
			head = curr;
		} else {
			reverserec(tempNext);
		}

	}

}
