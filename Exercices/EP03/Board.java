import edu.princeton.cs.algs4.*;

public class Board {
    private int[][] tabuleiro;
    private int n;
    private int space = 0;
    private int space_row = -1;
    private int space_col = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        this.n = tiles.length;
        tabuleiro = clona_matriz(tiles);
    }

    //retorna uma nova matrix, cópia da passada como argumento
    private int[][] clona_matriz(int[][] matriz){
        int[][] clone = new int[matriz.length][matriz.length];
        for (int row = 0; row < matriz.length; row++)
            for (int col = 0; col < matriz.length; col++)
                clone[row][col] = matriz[row][col];

        return clone;
    }

    // string representation of this board
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                s.append(String.format("%2d ", tileAt(row, col)));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col){
        if (row < 0 || row >= n || col < 0 || col >= n) throw new IllegalArgumentException("tile inválido");
        return tabuleiro[row][col];
    }    

    // board size n
    public int size(){
        return n;
    }    

    // number of tiles out of place              
    public int hamming(){
        int h = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int tile = tileAt(i, j);
                if (tile != space && tile != tileGoal(i, j))
                    h++;
            }
        }
        return h;
    }

    //retorna a lajota que deveria preencher a posição indicada
    private int tileGoal(int row, int col){
        return row*n + col + 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int m = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){                
                if (tileAt(i, j) != space) m += manhattan_distance(i, j);
            }
        }
        return m;
    }   

    // calcula uma distancia manhattan de um elemento especifico
    private int manhattan_distance(int row, int col){
        int tile = tileAt(row, col);
        int dist_row = 0, dist_col = 0;

        dist_row = (tile - 1)/size();
        dist_row = row - dist_row;
        dist_col = (tile - 1)%size();
        dist_col = col - dist_col;

        return Math.abs(dist_col) + Math.abs(dist_row);
    }



    // is this board the goal board?         
    public boolean isGoal(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){   
                if (tileAt(i, j) != space && tileAt(i, j) != tileGoal(i, j))
                    return false;
            }
        }
        return true;
    }              

    // does this board equal y?
    public boolean equals(Object y) {
        // self check
        if (this == y)
            return true;
        // null check
        if (y == null)
            return false;
        // type check and cast
        if (getClass() != y.getClass())
            return false;

        Board board = (Board) y;

        if (n != board.n) return false;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){   
                if (tileAt(i, j) != board.tileAt(i, j))
                    return false;
            }
        }

        return true;
    }

    //atualiza as coordenadas do space, que são variáveis globais
    private void findSpaceCoord(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (tileAt(i, j) == space){
                    space_row = i;
                    space_col = j;
                    break;
                }
            }
        }
    }

    // all neighboring boards  
    public Iterable<Board> neighbors(){
        Stack<Board> stack_board = new Stack<Board>();

        findSpaceCoord();

        //vizinho de cima
        if (space_col - 1 >= 0) {
            int[][] neighbor = clona_matriz(tabuleiro);
            slidingSpace(neighbor, space_row, space_col - 1);
            stack_board.push(new Board(neighbor));
        }

        //vizinho de baixo
        if (space_col + 1 < n) {
            int[][] neighbor = clona_matriz(tabuleiro);
            slidingSpace(neighbor, space_row, space_col + 1);
            stack_board.push(new Board(neighbor));
        }

        //vizinho da esquerda
        if (space_row - 1 >= 0) {
            int[][] neighbor = clona_matriz(tabuleiro);
            slidingSpace(neighbor, space_row - 1, space_col);
            stack_board.push(new Board(neighbor));
        }

        //vizinho da direita
        if (space_row + 1 < n) {
            int[][] neighbor = clona_matriz(tabuleiro);
            slidingSpace(neighbor, space_row + 1, space_col);
            stack_board.push(new Board(neighbor));
        }

        return stack_board;
    } 

    //Movimenta o espaço do puzzle para as coordenadas (row,col)
    //que idealmente serão coordenadas vizinhas de space
    private void slidingSpace(int[][] matriz, int row, int col){
        matriz[space_row][space_col] = matriz[row][col];
        matriz[row][col] = space;
    }

    // is this board solvable?   
    public boolean isSolvable(){
        findSpaceCoord();
        int inversions = numberOfInversions();
        if (((n%2) != 0) && ((inversions%2) == 0)) return true;
        if (((n%2) == 0) && (((inversions + space_row)%2) != 0)) return true;
        return false;
    }  

    //calcula e retorna o número de inversões no puzzle como definido no enunciado
    private int numberOfInversions(){
        int tam = n*n;
        int[] rowMajorOrder = new int[tam];
        int count = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                rowMajorOrder[count++] = tileAt(i, j);
            }
        }

        count = 0;

        for(int i = 0; i < tam; i++){
            for(int j = i + 1; j < tam; j++){
                if (rowMajorOrder[i] != space && 
                    rowMajorOrder[j] != space && 
                    rowMajorOrder[i] > rowMajorOrder[j]) 
                        count++;
            }
        }

        return count;
    }         

    // unit testing (required)
    public static void main(String[] args){
        //O CÓDIGO ABAIXO QUE COMPÕE A MAIN FOI RETIRADO DO SITE
        //https://pastebin.com/YZHHrg6w PARA FINS DE DEBUG

        int[][] t = { {1, 2, 3}, {5, 7, 6}, {4, 8, 0}};
        Board b = new Board(t);
        StdOut.printf("\ntoString():\n");
        StdOut.println(b.toString());
        StdOut.printf("\nhamming():\n");
        StdOut.println(b.hamming());
        StdOut.printf("\nmanhattan():\n");
        StdOut.println(b.manhattan());
        StdOut.printf("\nisGoal():\n");
        StdOut.println(b.isGoal());
        StdOut.printf("\nequals():\n");
        int[][] t2 = { {0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board b2 = new Board(t2);
        StdOut.println(b2.toString());
        StdOut.println(b.equals(b2));
        StdOut.printf("\nneighbors():\n");
        for (Board brd : b.neighbors())
            StdOut.println(brd.toString());
        StdOut.printf("\nisSolvable():\n");
        StdOut.println(b.isSolvable());
        StdOut.println("\nNumberOfInversions():");
        StdOut.println(b.numberOfInversions());
        //
    }
}