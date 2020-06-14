import edu.princeton.cs.algs4.*;
import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>{
	private int n;
	private Node first;
	private Node current;

	private class Node{
		Item item;
		Node next;
	}

	public Bag(){
		this.first = null;
		this.n = 0;
	}

	public void add(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		this.n++;
	}

	public int size(){
		return this.n;
	}

	public boolean isEmpty(){
		return n==0;
	}

	/*
	public void startIterator(){
		current = first;
	}

	public boolean hasNext(){
		return != null;
	}

	public Item next(){
		Integer item = current.item;
		current = current.next;
		return item;
	}
	*/

	public Iterator<Item> iterator(){
		return new BagIterator();
	}

	private class BagIterator implements Iterator<Item>{
		private Node current = first;
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}

		public boolean hasNext(){
			return current != null;
		}
	}


}