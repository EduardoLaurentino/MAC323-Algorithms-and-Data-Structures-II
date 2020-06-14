import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.*;

public class PercolationStats{
	private Percolation grid;
	private double threshold;
	private double[] amostra;
	private int T;
	
	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials){
		if (n <= 0 || trials <= 0) throw new IllegalArgumentException("tamanho inválido");
		this.T = trials;
		amostra = new double[trials];
		int dimensao = n*n;
		

		for(int i = 0; i < trials; i++){
			grid = new Percolation(n);
			while (!grid.percolates()){
				int row = StdRandom.uniform(n);
				int col = StdRandom.uniform(n);
				if (!grid.isOpen(row, col)) grid.open(row, col);
			}
			
			double limiar = grid.numberOfOpenSites()/(dimensao * 1.0);
			amostra[i] = limiar;
		}

		//for(int i = 0; i < T; i++) StdOut.println("amostra[" + i + "] = " + amostra[i]);
	}


	// sample mean of percolation threshold
	public double mean(){
		return StdStats.mean(amostra);
	}

	// sample standard deviation of percolation threshold
	public double stddev(){
		return StdStats.stddev(amostra);
	}


	// low endpoint of 95% confidence interval
	public double confidenceLow(){
		double regiao = (1.96*StdStats.stddev(amostra))/Math.sqrt(T);
		return StdStats.mean(amostra) - regiao;
	}


	// high endpoint of 95% confidence interval
	public double confidenceHigh(){
		double regiao = (1.96*StdStats.stddev(amostra))/Math.sqrt(T);
		return StdStats.mean(amostra) + regiao;

	}

	// test client
	public static void main(String[] args){
		Stopwatch time = new Stopwatch();
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);

		PercolationStats estatisticas = new PercolationStats(n, trials);

		StdOut.println("mean()            = " + estatisticas.mean());
		StdOut.println("stddev()          = " + estatisticas.stddev());
		StdOut.println("confidenceLow()   = " + estatisticas.confidenceLow());
		StdOut.println("confidenceHigh()  = " + estatisticas.confidenceHigh());
		StdOut.println("elapsed time	  = " + time.elapsedTime());
	}
}	