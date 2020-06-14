import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackRA<Item> implements Iterable<Item>{
	private Item[] a;
	private int N;

	//construtor
	public StackRA(){
		this.a = (Item[]) new Object[2];
		this.N = 0;
	}

	//a pilha está vazia?
	public boolean isEmpty(){
		return N==0;
	}

	//insere item nesta pilha
	public void push(Item item){
		if (N == a.length)
			resize(2 * a.length);
		a[N++] = item;
	}

	//retira o item mais recente da pilha
	public Item pop(){
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = a[N-1];
		a[N-1] = null;
		N--;
		if (N > 0 && N <= (a.length/4))
			resize(a.length/2);
		return item;
	}

	//retorna o item mais recente sem retirá-lo
	public Item peek(){
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return a[N-1];
	}

	public int size(){
		return N;
	}

	//altera o tamanho do vetor de itens
	public void resize(int max){
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;		
	}

	public Iterator<Item> iterator(){
		return new StackRAIterator();
	}

	private class StackRAIterator implements Iterator<Item>{
		private int current = N;

		public boolean hasNext(){
			return current > N;
		}

		public Item next(){
			if(!hasNext()) throw new NoSuchElementException();
			return a[--current];
		}

		public void remove() {
            throw new UnsupportedOperationException();
        }

	}

	public static void main(String[] args) {
        ResizingArrayStack<String> s = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");

        for (String x : s) StdOut.println(x);
    }
}