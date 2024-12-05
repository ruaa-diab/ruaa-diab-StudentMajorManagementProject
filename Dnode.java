package application;

public class Dnode<T extends Comparable<T>> {
	
	private T data;
	private Dnode<T> prev, next;

	public Dnode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Dnode<T> getPrev() {
		return prev;
	}

	public void setPrev(Dnode<T> prev) {
		this.prev = prev;
	}

	public Dnode<T> getNext() {
		return next;
	}

	public void setNext(Dnode<T> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
