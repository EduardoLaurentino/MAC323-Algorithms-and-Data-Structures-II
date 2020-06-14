import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

   private Item[] q;
   private int N;
   private int first;
   private int last;
    
   public RandomizedQueue()           // construct an empty randomized
                                      // queue
       {
           q = (Item[]) new Object[4];
       }

       // resize the underlying array
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }

   public boolean isEmpty()           // is the queue empty?
   {
       return N == 0;
   }
   
   public int size()                  // return the number of items on
                                      // the queue
   {
       return N;
   }


   private void swap(int i, int j)
   {
       Item t = q[j];
       q[j] = q[i];
       q[i] = t;
   }
   
   public void enqueue(Item item)     // add the item
   {
       // double size of array if necessary and recopy to front of array

       if (item == null)
            throw new NullPointerException();
       
       if (N == q.length) resize(2*q.length);   // double size of array if necessary
       q[last++] = item;                        // add item
       if (last == q.length) last = 0;          // wrap-around
       N++;

       // swap the choosen on with the last one.
       if (N > 2) {
           int choosen = StdRandom.uniform(N);

           int index = (first + choosen) % q.length;

           //   StdOut.printf("swap between :last:%d index:%d first:%d
           //   choosen:%d\n", last, index, first, choosen);

           if (last == 0)
               swap(q.length - 1, index);
           else
               swap(last - 1, index);
       }
   }

   // public void dump()
   //     {
   //         StdOut.printf("----------------------\n");
   //      for (Item i : q)
   //          StdOut.printf("ARRAY: %s\n", i);
   //  }


   public Item dequeue()              // delete and return a random item
   {
       if (isEmpty())
            throw new NoSuchElementException();

       Item item = q[first];
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2); 
        return item;
   }
   public Item sample()               // return (but do not delete) a
                                      // random item
   {
       int choosen = StdRandom.uniform(N);
       int index = (first + choosen) % q.length;
       return q[index];
   }

   public Iterator<Item> iterator() { return new RandomQueueIterator(); }
    // an iterator, doesn't implement remove() since it's optional
    private class RandomQueueIterator  implements Iterator<Item> {
        private int i;

        public boolean hasNext()  { return i < N; }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            StdOut.println(item);
            if (!item.equals("-")) s.enqueue(item);
            //else if (!s.isEmpty()) StdOut.print(s.dequeue() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");

        for (String x : s) StdOut.println(x);
    }

}