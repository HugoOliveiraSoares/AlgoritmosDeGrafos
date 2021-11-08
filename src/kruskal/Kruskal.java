package src.kruskal;

import src.Grafos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Kruskal {

    private Grafos grafo;
    private LinkedList<Integer> r = new LinkedList<>();;
    private List<int[]> arestas =  new ArrayList<>(); // lista de arestas identificadas pelos pares de vértices(int[])
    private int[] p; // Vetor de parents, de onde eu vim
    private int[] t;

    public Kruskal() {
    }

    public Kruskal(Grafos grafo) {
        this.grafo = grafo;
        p = new int[this.grafo.getNumVertices()];
        Arrays.fill(p, -1);
        geraArvoreMinima(this.grafo.getMatrizPesos());
    }

    public void geraArvoreMinima(int[][] matrizPesos){

        int vetorInicial = 0;
        p[vetorInicial] = 0; // Escolha um vértice Vi e marque-o com 0 na posição i em P
        r.add(vetorInicial);

        while (contains(p)) { // Faça enquanto p == -1
            adjacentes(matrizPesos);
            int[] vetices = menorAresta(matrizPesos); // obtenha a menor aresta
            // De modo que não forme circuito
            p[vetices[0]] = vetices[1]; // marque a posição j de p com o valor de i
        }

    }

    private void adjacentes(int[][] matrizPesos) {
        for (int i = 0; i < matrizPesos.length; i++) {
            if (matrizPesos[r.getLast()][i] != 0){
                arestas.add(new int[]{r.getLast(), i});
            }
        }
    }

    private boolean contains(int[] vetor) {
        for (int x : vetor) {
            if (x == -1) {
                return true;
            }
        }
        return false;
    }

    public int[] menorAresta(int[][] matrizPesos){

        // variaveis auxiliares para indentificação dos vertices.
        int vertice1 = 0, vertice2 = 0;
        int verticeX = arestas.get(0)[0], verticeY = arestas.get(0)[1];

        int menor = matrizPesos[verticeX][verticeY]; // menor peso de aresta encontrado

        for (int i = 1; i < arestas.size(); i++) {

            verticeX = arestas.get(i)[0];
            verticeY = arestas.get(i)[1];

            if (matrizPesos[verticeX][verticeY] < menor){

                menor = matrizPesos[arestas.get(i)[0]][arestas.get(i)[1]];
                vertice1 = verticeX;
                vertice2 = verticeY;
            }
        }
        return new int[]{vertice1, vertice2};
    }

    private boolean fechaCircuito(int[][] matrizPesos, int[][] arvore, int vertice1, int vertice2){
        int v1 = vertice1;
        int v2 = vertice2;

        for (int i = v2; i < matrizPesos.length; i++) {
            for (int j = 0; j < matrizPesos.length; j++) {
                if (j != v1){
                    if (estaAdicionado(arvore[v2][j], matrizPesos[v2][j])){
                        v2 = j;
                    }
                }
            }
        }
        return false;
    }

    private boolean estaAdicionado(int x, int y) {
        return x == y;
    }

}