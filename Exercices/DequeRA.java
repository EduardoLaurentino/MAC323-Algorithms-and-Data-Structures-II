import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeRA<Item> implements Iterable<Item>{
	private Item[] deque;
	private int n, first, last;

	//construtor
	public DequeRA(){
		this.deque = (Item[]) new Object[2];
		this.n = 0;
		this.first = 1;
		this.last = 0;
	}

	public boolean isEmpty(){
		return n == 0;
	}

	public int size(){
		return n;
	}

	public void addFirst(Item item){
		if (n == deque.length) resize(2*deque.length);
		deque[first++] = item;
		if (first == deque.length) first = 0;
		n++;
	}

	public Item removeFirst(){
		if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		Item item = deque[first--];
		if (first < 0) first = deque.length;
		if (n > 0 && n <= (deque.length/4))
			resize(deque.length/2);
		n--;
		return item;
	}

	public void addLast(Item item){
		if (n == deque.length) resize(2*deque.length);
		deque[last--] = item;
		if (last < 0) last = deque.length;
		n++;
	}

	public Item removeLast(){
		if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		Item item = deque[last++];
		if (last == deque.length) last = 0;
		if (n > 0 && n <= (deque.length/4))
			resize(deque.length/2);
		n--;
		return item;
	}

	private void resize(int max){
		assert max >= n;
		Item[] b = (Item[]) new Object[max];
		for (int i = 0; i < deque.length; i++)
			b[i] = deque[(last + i) % deque.length]; //circular
		deque = b;
        last = 0;
        first  = n;
	}

	public Iterator<Item> iterator(){
		return new DequeRAIterator();
	}

	private class DequeRAIterator implements Iterator<Item>{
		private int current = n;

		public boolean hasNext(){
			return current > 0;
		}

		public Item next(){
			if(!hasNext()) throw new NoSuchElementException();
			Item item = deque[(last + current) % deque.length];
			current++;
			return item;
		}
	}


	public static void main(String[] args) {
        DequeRA<String> q = new DequeRA<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.addFirst(item);
            else if (!q.isEmpty()) StdOut.print(q.removeLast() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}