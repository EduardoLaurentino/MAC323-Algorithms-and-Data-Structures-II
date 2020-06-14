import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class RandomQueue<Item> implements Iterable<Item>{
	private Item[] queue;
	private int first, last, n;

	//construtor
	public RandomQueue(){
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
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		int k = StdRandom.uniform(last);
		Item item = queue[k];
		queue[k] = queue[n];
		queue[n] = null;
		first++;
		n--;
		if (first == queue.length) first = 0;		
		if (n > 0 && n == (queue.length/4)) resize(queue.length/2);
		return item;
	}

	public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        int k = StdRandom.uniform(n);
        return queue[(first + k) % queue.length];
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
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item>{
		private int current = 0;
		private Item[] b;

		public RandomQueueIterator(){
			b = queue.clone();
		}

		public void Randoming(Item[] a){
			int N = a.length;
            for (int i = 0; i < n; i++) {
                int r = i + StdRandom.uniform(N - i);
                Item temp = a[r];
                a[r] = a[i];
                a[i] = temp;
            }
		}

		public boolean hasNext(){
			return current < n;
		}

		public Item next(){
			if(!hasNext()) throw new NoSuchElementException();
			Item item = b[(first + current) % b.length];
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
            //else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");

        for (String x : q) StdOut.println(x);
    }
}