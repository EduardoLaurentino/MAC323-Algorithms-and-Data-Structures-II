public class Beti{
	public static void main (String[] args){
		int N = Integer.parseInt(args[0]);
		int pareamentos = 0, cont = 0, contesp = 0;
		int[] freq = new int[N];
		int[] freqF = new int[N];

		//int k = (int) (Math.random() * N);

		for (int j = 0; j < 100; j++){
			for (int i = 0; i < N; i++){
				int k = (int) (Math.random() * (N));
				if (k == i) {pareamentos++; contesp++;}
				//contesp++;
				cont++;
			}
			freqF[contesp]++;
			contesp = 0;

			/*for(int p = 0; p < N; p++){
				if (p == ) freq[p]++;
			}*/
			//System.out.println();
		}

		double contd = (double) cont;
		System.out.println("Quantidade de pareamentos (X)				 Frequencia absoluta				frequencia relativa");
		for (int i = 0; i < N; i++) {
			System.out.println((i+1) + "						" + freqF[i] + "						" + (freqF[i]/100.0));
		}
		System.out.println();
		System.out.println("Frequencia = " + pareamentos + "/" + cont + " = " + (pareamentos/contd));
	}
}