/******************************************************************************
 *
 * MAC0323 - Algoritmos e Estruturas de Dados II
 * Aluno: Eduardo Rocha Laurentino
 * Numero USP: 8988212
 * Tarefa: EP0r - SeamCarver.java
 * Data: 07/07/2018
 *
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
import java.awt.Color;

public class SeamCarver {
   private Picture fotinha;
   private int largura, altura;
   private double[][] matriz_de_energia;
   private double[][] distTo;
   private int[][] edgeTo;
   IndexMinPQ<Double> pq;
   ST<Integer, Integer> st = new ST<Integer, Integer>();

   //create a seam carver object based on the given picture
   public SeamCarver(Picture picture){
      if (picture == null)
         throw new IllegalArgumentException("imagem inválida");

      this.fotinha = picture;
      this.largura = picture.width();
      this.altura  = picture.height();
   }              
   
   // current picture
   public Picture picture(){
      return this.fotinha;
   }                          
   
   // width of current picture
   public int width(){
      return this.largura;
   }
   
   // height of current picture
   public int height(){
      return this.altura;
   } 

   private double delta_ao_quadrado(int x, int y, boolean deltaX){
      Color pixel_esquerdo_acima = new Color(0, 0, 0);
      Color pixel_direito_abaixo = new Color(0, 0, 0);

      if (deltaX){
         if (x > 0 && x < (largura - 1)){
            pixel_esquerdo_acima = fotinha.get(x - 1, y);
            pixel_direito_abaixo  = fotinha.get(x + 1, y);
         }
         else if (x == 0){
            pixel_esquerdo_acima = fotinha.get(largura - 1, y);
            pixel_direito_abaixo = fotinha.get(1, y);
         }
         else if (x == (largura - 1)){
            pixel_esquerdo_acima = fotinha.get(x - 1, y);
            pixel_direito_abaixo = fotinha.get(0, y);
         }
      }
      else { //se não for deltaX é o deltaY
         if (y > 0 && y < (altura - 1)){
            pixel_esquerdo_acima = fotinha.get(x, y - 1);
            pixel_direito_abaixo  = fotinha.get(x, y + 1);
         }
         else if (y == 0){
            pixel_esquerdo_acima = fotinha.get(x, altura - 1);
            pixel_direito_abaixo = fotinha.get(x, 1);
         }
         else if (y == (altura - 1)){
            pixel_esquerdo_acima = fotinha.get(x, 0);
            pixel_direito_abaixo = fotinha.get(x, y - 1);
         }
      }

      double R = pixel_direito_abaixo.getRed() - pixel_esquerdo_acima.getRed();
      double G = pixel_direito_abaixo.getGreen() - pixel_esquerdo_acima.getGreen();
      double B = pixel_direito_abaixo.getBlue() - pixel_esquerdo_acima.getBlue();

      double delta_ao_quadrado = R*R + G*G + B*B;
      return delta_ao_quadrado;
   }                          
   
   // energy of pixel at column x and row y
   public double energy(int x, int y){
      if (x < 0 || x >= largura || y < 0 || y > altura)
         throw new IllegalArgumentException("pixel inválido");

      double delta_ao_quadradoX = delta_ao_quadrado(x, y, true);
      double delta_ao_quadradoY = delta_ao_quadrado(x, y, false);
      return Math.sqrt(delta_ao_quadradoX + delta_ao_quadradoY);
   }              
   
   // sequence of indices for horizontal seam
   public int[] findHorizontalSeam(){
      transpose();
      int[] seam = findVerticalSeam();
      transpose();
      return seam;
   }   

   private void resetaDist(){      
      for (int lin = 0; lin < altura; lin++)
         for (int col = 0; col < largura; col++)
            distTo[col][lin] = Double.POSITIVE_INFINITY;
   }            
   
   // sequence of indices for vertical seam
   public int[] findVerticalSeam(){
      int[] corte = new int[altura];
      this.matriz_de_energia = new double[largura][altura];
      this.distTo  = new double[largura][altura];
      this.edgeTo  = new int[largura][altura];
      this.pq = new IndexMinPQ<Double>(largura*altura);

      for (int lin = 0; lin < altura; lin++)
         for (int col = 0; col < largura; col++)
            matriz_de_energia[col][lin] = energy(col, lin);

      resetaDist();

      for (int col = 0; col < largura; col++){
         rodaBFS(col, 0);
      }

      double energia_minima_final = Double.POSITIVE_INFINITY;
      int col_energia_minima_final = 0;
      for (int col = 0; col < largura; col++)
         if (distTo[col][altura - 1] < energia_minima_final){
            energia_minima_final = distTo[col][altura - 1];
            col_energia_minima_final = col;
         }

      corte[altura - 1] = col_energia_minima_final;
      for (int lin = altura - 1 - 1; lin >= 0; lin --){
         corte[lin] = edgeTo[corte[lin+1]][lin+1];
      }

      return corte;
   }

   private void rodaBFS(int x, int y){
      distTo[x][y] = matriz_de_energia[x][y];
      int id = y*largura + x;
      pq.insert(id, distTo[x][y]);
      st.put(id, y);
      int max_id = largura;

      while (!pq.isEmpty() ){
         id = pq.delMin();
         if (id > max_id){
            y++; 
            max_id = (y+1)*(largura) - 1;
         }
         int v = id - st.get(id)*largura;
         relax(v, y);
      }
   }

   private void relax(int v, int y) {
      boolean tem_direita  = (v < (largura - 1));
      boolean tem_esquerda = (v > 0);
      boolean tem_abaixo = (y < (altura - 1));
      double energia;

      if (tem_esquerda && tem_abaixo){
         energia = distTo[v][y] + matriz_de_energia[v - 1][y+1];
         relax2(v - 1, y + 1, energia, v);
      }
        
      if (tem_abaixo){
         energia = distTo[v][y] + matriz_de_energia[v][y+1];
         relax2(v, y + 1, energia, v);
      }
      
      if (tem_direita && tem_abaixo){
         energia = distTo[v][y] + matriz_de_energia[v + 1][y+1];
         relax2(v + 1, y + 1, energia, v);
      }
   }

   private void relax2(int w, int y, double energia, int v){
        if (distTo[w][y] > energia){
            edgeTo[w][y] = v;            
            distTo[w][y] = energia;
            int id = y*largura + w;
            if (pq.contains(id))
               pq.decreaseKey(id, energia);
            else pq.insert(id, energia);
            st.put(id, y);
        }
   }

   private void transpose(){
      Picture foto_temporaria = new Picture(altura, largura);
      for (int lin = 0; lin < largura; lin++) {
         for (int col = 0; col < altura; col++) {
            foto_temporaria.setRGB(col, lin, fotinha.getRGB(lin, col));
         }
      } 

      this.fotinha = foto_temporaria;
      int tmp = this.altura;
      this.altura = this.largura;
      this.largura = tmp;
   }
   
   // remove horizontal seam from current picture
   public void removeHorizontalSeam(int[] seam){
      if (seam == null || altura == 1 || seam.length != largura)
         throw new IllegalArgumentException("corte inválido");
      transpose();
      removeVerticalSeam(seam);
      transpose();
   }      

   // remove vertical seam from current picture
   public void removeVerticalSeam(int[] seam){
      if (seam == null || largura == 1 || seam.length != altura)
         throw new IllegalArgumentException("corte inválido");

      largura--;
      Picture nova_fotinha = new Picture(largura, altura);
      for (int y = 0; y < altura; y++) {
         int k = 0;
         for (int x = 0; x < largura+1; x++) {
            if (x != seam[y])
               nova_fotinha.set(k++, y, fotinha.get(x, y));
         }
      }
      fotinha = nova_fotinha;
      largura = fotinha.width();
   }     

   //  unit testing 
   public static void main(String[] args){

   }           
}