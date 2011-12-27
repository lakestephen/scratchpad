package com.lake.mycollections;

import java.util.Iterator;

public interface List<T> extends Iterable<T> {

	void add(T item);
	void remove(T item);
	T get(int index);
	int size();
	Iterator<T> iterator();
}
