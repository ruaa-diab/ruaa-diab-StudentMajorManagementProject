package application;

public interface Listable<T extends Comparable<T>>  {
	
	void insert(T data);
	boolean delete (T data);
	public Node<T> search(T data) ;
	public T getAt(int index);
	void clear();
	void print();
	int size();

}

