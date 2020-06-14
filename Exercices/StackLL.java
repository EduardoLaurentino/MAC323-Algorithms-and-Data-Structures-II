import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackLL<Item> implements Iterable<Item>{
	private Node first;
	private int n;

	public class Node{
		Item item;
		Node next;
	}

	public StackLL(){
		this.first = null;
		this.n = 0;
	}

	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		n++;
	}

	public Item pop(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}

	public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

	public boolean isEmpty(){
		return first == null; //N == 0;
	}

	public int size(){
		return n;
	}

	public Iterator<Item> iterator(){
		return new StackLLIterator();
	}

	private class StackLLIterator implements Iterator<Item>{
		private Node current = first;

		public boolean hasNext() { 
            return current != null;
        }

		public Item next(){
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

	}

	public static void main(String[] args) {
        StackLL<String> s = new StackLL<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}