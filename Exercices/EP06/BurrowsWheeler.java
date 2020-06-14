/******************************************************************************
 *
 * MAC0323 - Algoritmos e Estruturas de Dados II
 * Aluno: Eduardo Rocha Laurentino
 * Numero USP: 8988212
 * Tarefa: EP06 - BurrowsWheeler.java
 * Data: 12/06/2018
 *
 * referências: 
        - http://www.cs.princeton.edu/courses/archive/spring18/cos226/assignments/burrows/checklist.html
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/



import edu.princeton.cs.algs4.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform, reading from 
    // standard input and writing to standard output
    public static void transform(){
	    String s = BinaryStdIn.readString();
	    CircularSuffixArray csa = new CircularSuffixArray(s);
        boolean continua = true;

	    for (int i = 0; i < csa.length() && continua; i++)
	      	if (csa.index(i) == 0) {BinaryStdOut.write(i); continua = false;}
	    for (int j = 0; j < (csa.length()); j++) 
			BinaryStdOut.write(s.charAt(((csa.index(j) + s.length() - 1)) % s.length()));

        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform, reading 
    //from standard input and writing to standard output
    public static void inverseTransform(){
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        int length = t.length();

        int[] freq = new int[257]; //extended ASCII: 256 (+1 for corner)
        int[] next = new int[length];

        for (int i = 0; i < length; i++)
            freq[t.charAt(i) + 1]++;
        for (int i = 1; i < 257; i++)
            freq[i] += freq[i - 1];
        for (int i = 0; i < length; i++)
            next[freq[t.charAt(i)]++] = i;

        int i = next[first];
        for (int j = 0; j < length; j++){
            BinaryStdOut.write(t.charAt(i));
            i = next[i];
        }

        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args){
    	if      (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Operação inválida");
    }
}