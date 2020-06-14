import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueLL<Item>{
	public Node lo, hi;
	public int n;

	public class Node{
		Item item;
		Node next;
	}

	public QueueLL(){
		this.lo = null;
		this.hi = null;
		this.n = 0;
	}

	public void enqueue(Item item){
		Node oldhi = hi;
		hi = new Node();
		hi.item = item;
		hi.next = null;
		if (isEmpty()) lo = hi; //inserção do primeiro
		else oldhi.next = hi;
		n++;
	}

	public Item dequeue(){
		Item item = lo.item;
		lo = lo.next;
		n--;
		if (isEmpty()) hi = null;
      	return item;
	}

	public boolean isEmpty(){
		return hi == null;
	}

    public static void main(String[] args) {
        LinkedQueue<String> q = new LinkedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }	
}