import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>{
	private Item[] a;
	private int N;

	//construtor
	public RandomizedQueue(){
		this.a = (Item[]) new Object[2];
		this.N = 0;
	}

	//a pilha está vazia?
	public boolean isEmpty(){
		return N == 0;
	}

	public int size(){
		return N;
	}

	//altera o tamanho do vetor de itens
	private void resize(int max){
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;		
	}

	public void enqueue(Item item){
		if (item == null) throw new IllegalArgumentException("Item inválido");
		if (N == a.length)
			resize(2 * a.length);
		a[N++] = item;
	}

	public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        int k = StdRandom.uniform(N);
		Item item = a[k];
		a[k] = a[N-1];
		a[N-1] = null;
		N--;
		if (N > 0 && N <= (a.length/4))
			resize(a.length/2);
		return item;
	}

	public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        int k = StdRandom.uniform(N);
        return a[k];
    }

	public Iterator<Item> iterator(){
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item>{
		private int current = 0;
				private Item[] b;

				public RandomizedQueueIterator(){
					b = a.clone();
					Randoming(b);
				}

				public void Randoming(Item[] a){
					int T = a.length;
		            for (int i = 0; i < N; i++) {
		                int r = i + StdRandom.uniform(T - i);
		                Item temp = a[r];
		                a[r] = a[i];
		                a[i] = temp;
		            }
				}

				public boolean hasNext(){
					return current < N;
				}

				public Item next(){
					if(!hasNext()) throw new NoSuchElementException();
					Item item = b[current++];
					return item;
				}

				public void remove() {
		            throw new UnsupportedOperationException();
		        }
	}

	public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            //StdOut.println(item);
            if (!item.equals("-")) s.enqueue(item);
            else if (!s.isEmpty()) StdOut.print(s.dequeue() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");

        for (String x : s) StdOut.println(x);
    }
}