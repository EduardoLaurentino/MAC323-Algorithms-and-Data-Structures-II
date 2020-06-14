import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeLL<Item> implements Iterable<Item>{
	private Node first, last;
	private int n;

	public class Node{
		Item item;
		Node decrescente, crescente;
	}

	public DequeLL(){
		this.first = null;
		this.last = null;
		this.n = 0;
	}

	public boolean isEmpty(){
		return n == 0; 
	}

	public int size(){
		return n;
	}

	public void addFirst(Item item){
		if (item == null) throw new IllegalArgumentException("Item inválido");
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.decrescente = oldfirst;
		first.crescente = null;
		if (isEmpty()) last = first;
		else first.decrescente.crescente = first;
		n++;
	}

	public Item removeFirst(){
		if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		Item item = first.item;
		first = first.decrescente;
		first.crescente = null;
		n--;
		return item;
	}

	public void addLast(Item item){
		if (item == null) throw new IllegalArgumentException("Item inválido");
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.decrescente = null;
		last.crescente = oldlast;
		if (isEmpty()) first = last; //inserção do primeiro
		else last.crescente.decrescente = last;
		n++;
	}

	public Item removeLast(){
		if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		Item item = last.item;
		last = last.crescente;
		last.decrescente = null;
		n--;
		return item;
	}

	public Iterator<Item> iterator(){
		return new DequeLLIterator();
	}

	public class DequeLLIterator implements Iterator<Item>{
		private Node current = first;

		public boolean hasNext() { 
            return current != null;
        }

		public Item next(){
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.decrescente;
			return item;
		}

		public void remove() {
            throw new UnsupportedOperationException();
        }

	}

	public static void main(String[] args) {
        DequeLL<String> q = new DequeLL<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.addLast(item);
            else if (!q.isEmpty()) StdOut.print(q.removeFirst() + " ");
        }
        StdOut.println("(" + q.size() + " left on deque)");

        for (String x : q) StdOut.println(x);
    }
}