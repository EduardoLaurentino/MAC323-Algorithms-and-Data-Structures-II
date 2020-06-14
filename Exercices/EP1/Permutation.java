import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Permutation{
	public static void main(String[] args){
		int k = Integer.parseInt(args[0]);

		RandomizedQueue<String> s = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            s.enqueue(item);
        }

        /*Iterator<String> it = s.iterator();
        for(int i = 0; i < k; i++)
        	StdOut.println(it.next());*/

        for(String x : s) StdOut.println(x);
	}
}