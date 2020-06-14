import edu.princeton.cs.algs4.*;

public class KdTreeST<Value>{

	 
    private class Node {
       private Point2D p;      // the point
       private Value value;    // the symbol table maps the point to this value
       private RectHV rect;    // the axis-aligned rectangle corresponding to this node
       private Node lb;        // the left/bottom subtree
       private Node rt;        // the right/top subtree
    }

   	// unit testing (required)
   	public static void main(String[] args){
   		KdTreeST<Integer> st = new KdTreeST<Integer>();
        for (int i = 1; i <= 50; i++) {
            Point2D p = new Point2D((double) 2*i, (double) 3*i);
            //if (i == 21) p = null;
            st.put(p, i);
        }
        for (Point2D p : st.points())
            StdOut.println(p.toString() + " " + st.get(p));

        StdOut.println();

        RectHV retangulo = new RectHV(20.0, 27.0, 42.0, 63.0);
        for (Point2D p : st.range(retangulo)){
        	StdOut.println(p.toString() + " " + st.get(p));
        	StdOut.println("nearest neighbor of " + p.toString() + " = " + st.nearest(p));
        	StdOut.println();
        }
   	}      

}