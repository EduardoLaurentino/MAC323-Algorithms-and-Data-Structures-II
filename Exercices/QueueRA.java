import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueRA<Item> implements Iterable<Item>{
	private Item[] queue;
	private int first, last, n;

	//construtor
	public QueueRA(){
		this.queue = (Item[]) new Object[2];
		this.n = 0;
		this.first = 0;
		this.last = 0;
	}

	public void enqueue(Item item){
		if (n == queue.length) resize(2*queue.length);
		queue[last++] = item;
		if (last == queue.length) last = 0;
		n++;
	}

	public Item dequeue(){
		if (isEmpty()) throw new NoSuchElementException("Queue underfw");
		Item item = queue[first];
		queue[first] = null;
		first++;
		n--;
		if (first == queue.length) first = 0;		
		if (n > 0 && n == (queue.length/4)) resize(queue.length/2);
		return item;
	}

	public boolean isEmpty(){
		return n == 0;
	}

	public int size(){
		return n;
	}

	private void resize(int max){
		assert max >= n;
		Item[] b = (Item[]) new Object[max];
		for (int i = 0; i < queue.length; i++)
			b[i] = queue[(first + i) % queue.length]; //circular
		queue = b;
        first = 0;
        last  = n;
	}

	public Iterator<Item> iterator(){
		return new QueueRAIterator();
	}

	private class QueueRAIterator implements Iterator<Item>{
		private int current = 0;

		public boolean hasNext(){
			return current < n;
		}

		public Item next(){
			if(!hasNext()) throw new NoSuchElementException();
			Item item = queue[(first + current) % queue.length];
			current++;
			return item;
		}

		public void remove() {
            throw new UnsupportedOperationException();
        }
	}

	public static void main(String[] args) {
        ResizingArrayQueue<String> q = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");

        for (String x : q) StdOut.println(x);
    }
}