/******************************************************************************
 *
 * MAC0323 - Algoritmos e Estruturas de Dados II
 * Aluno: Eduardo Rocha Laurentino
 * Numero USP: 8988212
 * Tarefa: EP06 - CircularSuffix.java
 * Data: 12/06/2018
 *
 * referências: 
 *      - http://www.cs.princeton.edu/courses/archive/spring18/cos226/assignments/burrows/checklist.html
 *		- https://algs4.cs.princeton.edu/51radix/Quick3string.java.html
 *		- https://algs4.cs.princeton.edu/63suffix/SuffixArrayX.java.html
 *
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
import java.util.NoSuchElementException;
import java.io.IOException;

public class CircularSuffixArray {
	// cutoff to insertion sort 
	private static final int CUTOFF =  5;   

	private int[] csa;
	private static String text;
	private static int length;

	// circular suffix array of s
   	public CircularSuffixArray(String s){
   		text = s;
   		length = s.length();
   		this.csa = new int[length];
   		for (int i = 0; i < length; i++) {
   			csa[i] = i;
   		}
   		
   		sort(0, length - 1, 0);
   	}

	private char charAt(int i) {
        return text.charAt(i % length);
    }

   	// O SORT ABAIXO, BEM COMO OS MÉTODOS AUXILIARES
   	// FORAM ADAPTADOS DE https://algs4.cs.princeton.edu/51radix/Quick3string.java.html
    private void sort(int lo, int hi, int d) { 

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        char v = charAt(csa[lo] + d);
        int i = lo + 1;
        while (i <= gt) {
            char t = charAt(csa[i] + d);
            if      (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else            i++;
        }

        sort(lo, lt-1, d);
        if (v > 0) sort(lt, gt, d+1);
        sort(gt+1, hi, d);
    }

    private void insertion(int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(csa[j], csa[j-1], d); j--)
                exch(j, j-1);
    }


    private boolean less(int i, int j, int d) {
        if (i == j) return false;
        i = i + d;
        j = j + d;
        while (i < length && j < length) {
            if (charAt(i) < charAt(j)) return true;
            if (charAt(i) > charAt(j)) return false;
            i++;
            j++;
        }
        return i > j;
    }

    // exchange csa[i] and csa[j]
    private void exch(int i, int j) {
    	if (i < 0 || j< 0 || j >= length() || i >= length()) 
    		throw new IllegalArgumentException();
        int swap = csa[i];
        csa[i] = csa[j];
        csa[j] = swap;
    }

   	// length of s
   	public int length(){
   		return length;
   	}                     

   	// returns index of ith sorted suffix
   	public int index(int i){
   		if (i < 0 || i >= length()) throw new IllegalArgumentException();
   		return csa[i];
   	}                

   	// unit testing (required)
   	public static void main(String[] args){
   		String s = StdIn.readAll();
        CircularSuffixArray csa = new CircularSuffixArray(s);

        //for (int i = 0; i < s.length(); i++)
        //	StdOut.println(csa.index(i));
   	}  
}