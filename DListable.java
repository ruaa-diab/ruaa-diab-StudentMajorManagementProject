package application;

public interface DListable<T extends Comparable<T>> {
	
	void insert(T data);
	boolean delete (T data);
	public Dnode<T> search(T data) ;
	public T getAt(int index);
	void clear();
	void print();
	int size();

}

