package com.concurrentperformance.mycollections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> implements List<T> {

	Node head;
	Node tail;
	int size = 0;
	
	{
		head = new Node();
		tail = new Node();
		head.nextNode = tail;
	}
	
	@Override
	public void add(T item) {
		tail.nextNode = new Node();
		tail.item = item;
		tail = tail.nextNode;
		size++;
	}

	@Override
	public void remove(T item) {
		Node node = head;
		
		do {
			if (node.nextNode != null && 
					node.nextNode.item != null &&
					node.nextNode.item.equals(item)) {
				
				removeNextNode(node);
				break;
			}
			
			node = node.nextNode;
			
		} while (node != tail);		
	}

	@Override
	public T get(int index) {
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Node node = head;
		int position = 0;
		while (node != tail) {
			node = node.nextNode;
			if (position == index){
				return node.item;
			}
			position++;
		}
		return null;
	}	
	
	private void removeNextNode(Node node) {
		node.nextNode = node.nextNode.nextNode;
		size--;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Iterator<T> iterator() {		
		return new SingleLinkedListIterator(head);
	}

	class Node {
		T item;
		Node nextNode;
	}
	
	private class SingleLinkedListIterator implements Iterator<T>{

		Node curentNode;
		
		SingleLinkedListIterator(Node head) {
			this.curentNode = head.nextNode;
		}
		
		@Override
		public boolean hasNext() {
			return curentNode != tail;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T item = curentNode.item;
			curentNode = curentNode.nextNode;
			
			return item;
		}

		@Override
		public void remove() {		
			removeNextNode(curentNode);
		}		
	}

}
