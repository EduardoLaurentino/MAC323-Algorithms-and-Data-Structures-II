import edu.princeton.cs.algs4.*;

public class Solver{
	private Board initialBoard;
	private Node searchedNode;
    private boolean isSolvable = true;
    private Stack<Board> solution = null;

    // min number of moves to solve initial board        
    public int moves(){
    	return 0;
    }  

    // sequence of boards in a shortest solution                  
    public Iterable<Board> solution(){
		 return null;
	}

/*f (isSolvable) {
		    return StackSolution();
		 }
		    return null;
	}

    private Stack<Board> StackSolution() {
        if (solution == null) {
            solution = new Stack<Board>();

            while (searchedNode != null) {
                solution.push(searchedNode.board);
                searchedNode = searchedNode.previous;
            }
        }
        return solution;
    }

    public boolean isSolvable() {
        return isSolvable;
    }*/

	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
    	if (initial == null) throw new IllegalArgumentException("tabuleiro nulo");
    	this.initialBoard = initial;
    	this.searchedNode = new Node(initialBoard, null);
    	this.isSolvable = initial.isSolvable();
    	if (!isSolvable) throw new IllegalArgumentException("sem solução");
    	SET<Node> Vistos = new SET<Node>();
    	MinPQ<Node> Vistos_NE = new MinPQ<Node>();        

        /*
        while (true) {
        	searchedNode = Vistos_NE.delMin();

            if(searchedNode.board.isGoal()){
                break;
            }

	        Iterable<Board> neighbors = searchedNode.board.neighbors();

	        for (Board brd : neighbors) {
	        	Node neighbor = new Node(brd, searchedNode);
	        	if (!Vistos.contains(neighbor)){
	        		Vistos.add(neighbor);
	        		Vistos_NE.insert(neighbor);
	        	}
	        }
	    }
	    */
    }  

    

   	// test client (see below)      
    public static void main(String[] args){
    	/*
    	In in = new In(args[0]);
        int n = in.readInt();
        int[][] tabuleiro = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tabuleiro[i][j] = in.readInt();
        Board initial = new Board(tabuleiro);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        */
    } 

    private class Node implements Comparable<Node> {
        private Board board;
        private Node previous;
        private int moves;
        

        private Node(Board board, Node previous) {
            this.board = board;
            this.previous = previous;
            if (previous == null) this.moves = 0;
            else this.moves = previous.moves + 1;
            //this.priority = board.manhattan() + moves; //usando o critério manhattan de prioridade
        }

        public int compareTo(Node node) {
            return (this.board.manhattan() + this.moves) - (node.board.manhattan() + node.moves);
        }
	}
}