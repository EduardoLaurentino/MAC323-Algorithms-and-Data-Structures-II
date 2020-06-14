import edu.princeton.cs.algs4.*;

public class Stats{
	
	public static void main(String[] args){
		Bag<Integer> bag = new Bag<Integer>();
		while (!StdIn.isEmpty()) 
			bag.add(StdIn.readInt());

		int N = bag.size();
		double soma = 0.0;

		for(int x : bag){
			soma += x;
		}

		double media = soma/N;
		soma = 0.0;

		for (int x : bag){
			soma += (x - media)*(x - media);
		}

		double desvpad = Math.sqrt(soma/(N-1));
		StdOut.printf("Media: %.2f\n", media);
		StdOut.printf("Desvio padrão: %.2f\n", desvpad);
	}
}