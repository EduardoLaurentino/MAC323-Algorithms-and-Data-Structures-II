//package edu.princeton.cs.algs4;
import edu.princeton.cs.algs4.*;

public class PointST<Value>{

	private RedBlackBST<Point2D, Value> st;
	private int n;

	// construct an empty symbol table of points 
	public PointST(){
		st = new RedBlackBST<Point2D, Value>();
	}    

	// number of points     
	public int size(){
		return st.size();
	}                                        

	// is the symbol table empty? 
	public boolean isEmpty(){
		return size() == 0;
	}               

	// associate the value val with point p
	public void put(Point2D p, Value val){
		if (p == null || val == null) throw new IllegalArgumentException("calls put() with null key");
        //if (val == null) st.delete(p);
        else             st.put(p, val);
	} 

	// value associated with point p 
   	public Value get(Point2D p){
   		if (p == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(p);
   	} 

   	// does the symbol table contain point p? 
   	public boolean contains(Point2D p){
   		if (p == null) throw new IllegalArgumentException("calls contains() with null key");
        return st.contains(p);
   	} 

   	// all points in the symbol table 
   	public Iterable<Point2D> points(){
   		return st.keys();
   	}  

   	// all points that are inside the rectangle (or on the boundary) 
   	public Iterable<Point2D> range(RectHV rect){
   		if (rect == null) throw new IllegalArgumentException("calls range(rect) with null rect");
   		Queue<Point2D> queue = new Queue<Point2D>();

   		for (Point2D p : points())
        	if (rect.contains(p))
        		queue.enqueue(p);

        return queue;
   	} 

   	// a nearest neighbor of point p; null if the symbol table is empty 
   	public Point2D nearest(Point2D p){
   		if (p == null) throw new IllegalArgumentException("calls nearest(p) with null p");
   		if (isEmpty()) return null;
   		Point2D neighbor = null;
   		double dist = Double.POSITIVE_INFINITY;
   		double dist_temp = 0.0;

   		for(Point2D n : st.keys()){
   			dist_temp = n.distanceSquaredTo(p);
   			//StdOut.println(dist_temp);
   			if (neighbor == null || dist_temp <= dist){
   				//StdOut.println("!");
   				dist = dist_temp;
   				neighbor = n;
   			}
   		}

   		return neighbor;
   	}

   	public Iterable<Point2D> nearest(Point2D p, int k){
      if (p == null) throw new IllegalArgumentException("calls nearest(p, k) with null p");
      if (isEmpty()) return null;

      	Queue<Point2D> nearestKPoints = new Queue<Point2D>();
      	//Queue<Point2D> queueTemp = new Queue<Point2D>();

      	//Point2D neighbor = null;
   		double dist = Double.POSITIVE_INFINITY;
   		double dist_temp = 0.0;

   		ST<Double, Point2D> pq = new ST<Double, Point2D>();
   		for(int i = 1; i <= k; i++){
   			Point2D x = new Point2D((double) 2*i, (double) 3*i);
   			pq.put(i*1000000.0, x);
   		}

   		int cont = 0;
   		//while (cont < k){
	   	for(Point2D n : points()){
	   		dist_temp = p.distanceSquaredTo(n);
	   		if (dist_temp <= pq.max()){
	   			//nearestKPoints.dequeue(pq.get(pq.max()));
	   			pq.delete(pq.max());
	   			pq.put(dist_temp, n);
	   			//dist = dist_temp;
	   			//neighbor = n;
	   			//nearestKPoints.enqueue(neighbor);
	   		}
	   	}
	   		
	   	for (Double s : pq.keys())
            nearestKPoints.enqueue(pq.get(s));

	   	return nearestKPoints;
    }

   	// unit testing (required)
   	public static void main(String[] args){
   		PointST<Integer> st = new PointST<Integer>();
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