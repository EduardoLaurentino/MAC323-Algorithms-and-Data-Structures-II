import edu.princeton.cs.algs4.*;
import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
	private CircularSuffix[] csa;
	private static String text;
	private static int length;

	// circular suffix array of s
   	public CircularSuffixArray(String s){
   		text = s;
   		length = s.length();
   		this.csa = new CircularSuffix[length];
   		for (int i = 0; i < length; i++) {
   			csa[i] = new CircularSuffix(i, i);
   		}
   		Arrays.sort(csa);
   	}

	private static class CircularSuffix implements Comparable<CircularSuffix> {
		private final int beginning;
		private final int index;


		private CircularSuffix(int beginning, int index){
			this.beginning = beginning;
			this.index = index;
		}

		private int beginning(){
			return this.beginning;
		}

		private int index(){
			return this.index;
		}

		private char charAt(int i){
			if ((this.beginning + i) < length)
				return text.charAt(this.beginning + i);
			else 
				return text.charAt(length - i);
		}

		public int compareTo(CircularSuffix that){
			if (this == that) return 0; 
            for (int i = 0; i < length; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return 0;
		}

		public String toString() {
			return (text.substring(this.beginning) + text.substring(0, beginning));
		}
	}

	public String CSufixString(int index){
		return this.csa[index].toString();
   	}

   	// length of s
   	public int length(){
   		return length;
   	}                     

   	// returns index of ith sorted suffix
   	public int index(int i){
   		if (i < 0 || i >= length()) throw new IllegalArgumentException();
   		return csa[i].index();
   	}                

   	// unit testing (required)
   	public static void main(String[] args){
   		String s = StdIn.readAll();
        CircularSuffixArray csa = new CircularSuffixArray(s);
 		/*StdOut.println("  i       Sorted       index[i]\n");
        for (int i = 0; i < s.length(); i++) {
            int index = csa.index(i);
			StdOut.printf("%3d    %3s    %3d\n", i, csa.CSufixString(i), index);
        }
        StdOut.println("\n length: " + csa.length());*/
   	}  
}