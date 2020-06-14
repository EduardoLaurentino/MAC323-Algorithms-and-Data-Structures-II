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
        if (n < 1) throw new IllegalArgumentException("tamanho inválido");
		this.n = n;
		this.grid = new boolean[n][n];
		this.status = new WeightedQuickUnionUF(n*n + 2); //cada elemento do grid + topo e base
		this.topo = n*n; //sitio virtual
		this.base = n*n+1; //sitio virtual 
		this.open_sites = 0;
	}

	// opens the site (row, col) if it is not open already
    public void open(int row, int col){
    	if (row < 0 || row >= n || col < 0 || col >= n) throw new IllegalArgumentException("site inválido");

    	if (grid[row][col] == false){
    		open_sites++;
    		grid[row][col] = true;

            //FORMULA GERAL DE CORREPONDENCIA DE COORDENDA: row*n + col
            //sitios do topo
            if (row == 0) status.union(topo, row * n + col);
            else if (isOpen(row - 1, col)) status.union((row-1) * n + col, row * n + col);

            //sitios intermediarios
            if (col + 1 < n && isOpen(row, col + 1)) status.union(row * n + col + 1, row * n + col);
            if (col > 0 && isOpen(row, col - 1)) status.union(row * n + col - 1, row * n + col);

            //sitios da base
            if (row == n - 1) status.union(row * n + col, base);
            else if (isOpen(row + 1, col)) status.union((row+1) * n + col, row * n + col);

    	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
    	return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
    	if (row < 0 || row >= n || col < 0 || col >= n) throw new IllegalArgumentException("site inválido");
    	return status.connected(topo, n * row + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
    	return open_sites;
    }

    // does the system percolate?
    public boolean percolates(){
    	return status.connected(topo, base);
    }
}