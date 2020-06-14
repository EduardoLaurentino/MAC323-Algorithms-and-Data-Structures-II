/******************************************************************************
 *
 * MAC0323 - Algoritmos e Estruturas de Dados II
 * Aluno: Eduardo Rocha Laurentino
 * Numero USP: 8988212
 * Tarefa: EP06 - MoveToFront.java
 * Data: 12/06/2018
 *
 * referências: 
        - http://www.cs.princeton.edu/courses/archive/spring18/cos226/assignments/burrows/checklist.html
        - https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
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
import java.util.LinkedList;

public class MoveToFront {

    // apply move-to-front encoding, 
    // reading from standard input
    // and writing to standard output
    public static void encode(){
    	LinkedList<Integer> mtf = new LinkedList<>();
    	for (int i = 0; i < 256; i++) mtf.add(i);

    	while (!BinaryStdIn.isEmpty()) {
            int in = BinaryStdIn.readChar();
            int out = mtf.indexOf(in);
            mtf.remove(out);
            mtf.add(0, in);

            BinaryStdOut.write(out, 8);
        }

        BinaryStdOut.close();
    }

    // apply move-to-front decoding, 
    // reading from standard input 
    // and writing to standard output
    public static void decode(){
    	LinkedList<Integer> mtf = new LinkedList<>();
    	for (int i = 0; i < 256; i++) mtf.add(i);

    	while (!BinaryStdIn.isEmpty()) {
    		int in = BinaryStdIn.readChar();
    		int out = mtf.get(in);
    		mtf.remove(in);
    		mtf.add(0, out);

    		BinaryStdOut.write(out, 8);
    	}

    	BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args){
    	if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Operação inválida");
    }
}