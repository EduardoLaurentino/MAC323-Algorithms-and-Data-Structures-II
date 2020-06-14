import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Teste{
	public static void main(String[] args){
		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = i;
		}

		int[] b = new int[10];

		StdOut.println("A:");
		for (int i = 0; i < 10; i++) StdOut.print(a[i] + " ");
		StdOut.println(); StdOut.println();

		StdOut.println("B");
		for (int i = 0; i < 10; i++) StdOut.print(b[i] + " ");
		StdOut.println(); StdOut.println();

		b = a.clone();
		StdOut.println("A:");
		for (int i = 0; i < 10; i++) StdOut.print(a[i] + " ");
		StdOut.println(); StdOut.println();

		StdOut.println("B");
		for (int i = 0; i < 10; i++) StdOut.print(b[i] + " ");
		StdOut.println(); StdOut.println();

		a[5] = 666;
		StdOut.println("A:");
		for (int i = 0; i < 10; i++) StdOut.print(a[i] + " ");
		StdOut.println(); StdOut.println();

		StdOut.println("B");
		for (int i = 0; i < 10; i++) StdOut.print(b[i] + " ");
		StdOut.println(); StdOut.println();
	}
}