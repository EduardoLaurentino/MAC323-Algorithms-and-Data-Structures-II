import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
	private int n;
	private boolean[][] grid;
	private WeightedQuickUnionUF status;
	private int topo, base;
	private int open_sites;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n){
		this.n = n;
		this.grid = new[n][n];
		this.status = new WeightedQuickUnionUF(n*n + 2) //cada elemento do grid + topo e base
		this.topo = n*n;
		this.base = 0;
		this.open_sites = 0;
	}

	// opens the site (row, col) if it is not open already
    public void open(int row, int col){
    	if (row < 0 || row >= n || col < 0 || col >= n) throw new IllegalArgumentException("site inválido");

    	if (grid[row][col] == false){
    		open_sites++;
    		grid[row][col] == true;

    		int i = row+1, j = col+1; //auxiliares pra match de indices entre grid e status

    		if (row == 0) 						 status.union(topo, n * (i - 1) + j);
    		if (row > 0 && grid[row-1][col]) 	 status.union(n * (i - 1 - 1) + j, n * (i - 1) + j);
    		if (col < (n-1) && grid[row][col+1]) status.union(n * (i - 1) + j+1, n * (i - 1) + j);
    		if (row < (n-1) && grid[row+1][col]) status.union(n * (i) + j, n * (i - 1) + j);
    		if (col > 0 && grid[row][col-1])	 status.union(n * (i - 1) + j-1, n * (i - 1) + j);
    		if (row == n-1) 					 status.union(base, n * (i - 1) + j);

    	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
    	return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
    	if (row < 0 || row >= n || col < 0 || col >= n) throw new IllegalArgumentException("site inválido");
    	return status.conected(topo, n * i + col + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
    	return open_sites;
    }

    // does the system percolate?
    public boolean percolates(){
    	return status.conected(topo, base);
    }


}