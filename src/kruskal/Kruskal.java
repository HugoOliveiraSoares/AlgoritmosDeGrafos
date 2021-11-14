package src.kruskal;

import src.Grafos;

import java.util.*;

public class Kruskal {

    private Grafos grafo;
    private LinkedList<Integer> r = new LinkedList<>();
    private List<int[]> arestas =  new ArrayList<>(); // lista de arestas identificadas pelos pares de vértices(int[])
    private int[] p; // Vetor de parents, de onde eu vim

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
        int anterior;

        p[vetorInicial] = 0; // Escolha um vértice Vi e marque-o com 0 na posição i em P
        r.add(vetorInicial);
        anterior = r.getLast();

        adjacentes(matrizPesos, anterior);

        while (containsmenos1P()) {// Faça enquanto p == -1
            if (anterior != r.getLast() ) {
                adjacentes(matrizPesos, r.getLast());
                anterior = r.getLast();
            }

            int[] vertices = menorAresta(matrizPesos); // obtenha a menor aresta
            if (!fechaCircuito(vertices[0], vertices[1])){
                p[vertices[1]] = vertices[0]; // marque a posição j de p com o valor de i
                r.add(vertices[1]);
            }
            arestas.remove(vertices);
        }
    }

    private void adjacentes(int[][] matrizPesos, int v) {
        for (int i = 0; i < matrizPesos.length; i++) {
            if (matrizPesos[v][i] != 0){
                if (!estaAdicionado(v, i) || !containsAresta(v, i)){
                    arestas.add(new int[]{v, i});
                }
            }
        }
    }

    public int[] menorAresta(int[][] matrizPesos){

        // variaveis auxiliares para indentificação das arestas.
        int vertice1 = arestas.get(0)[0], vertice2 = arestas.get(0)[1];

        int vertices = 0;
        int menor = matrizPesos[vertice1][vertice2]; // menor peso de aresta encontrado

        for (int i = 1; i < arestas.size(); i++) {
            vertice1 = arestas.get(i)[0];
            vertice2 = arestas.get(i)[1];

            if (matrizPesos[vertice1][vertice2] < menor){
                menor = matrizPesos[vertice1][vertice2];
                vertices = i;
            }
        }
        return arestas.get(vertices);
    }

    public boolean fechaCircuito(int vertice, int buscado) {

        int n = p.length;
        boolean[] marcado = new boolean[n];
        int[] antecessor = new int[n];
        marcado[vertice] = true;

        for (int w = 0; w < n; w++) {
            if (eAdjacente(vertice, w) && !marcado[w]){
                antecessor[w] = vertice;
                DFS(w, marcado, antecessor, n);
            }
        }

//        System.out.println(marcado[buscado]);
        return marcado[buscado];
    }

    public void DFS(int vertice, boolean[] marcado, int[] antecessor, int n) {

        Stack<Integer> pilha = new Stack<>(); // instanciar a pilha

        marcado[vertice] = true;
        pilha.push(vertice);// inserir na pilha

        while(!pilha.isEmpty()) { // ver ser ta vazia
            int w = pilha.pop();
            for (int z = 0; z < n; z++) {
                if (eAdjacente(w, z) && !marcado[z]) {
                    antecessor[z] = w;
                    marcado[z] = true;
                    pilha.push(z);
                }
            }
        }

    }

    private boolean eAdjacente(int v, int w) {
        return p[v] == w || p[w] == v;
    }

    //Verifica se já está adicionado na arvore (no vetor p)
    private boolean estaAdicionado(int x, int y) {
        return p[x] == y || p[y] == x;
    }

    private boolean containsmenos1P() {
        for (int x : p) {
            if (x == -1) {
                return true;
            }
        }
        return false;
    }

    private boolean containsAresta(int vertice1, int vertice2) {

        for (int[] vertices : arestas) {
            if ((vertices[0] == vertice1 && vertices[1] == vertice2) ||
                    (vertices[1] == vertice1 && vertices[0] == vertice2)) {
                return true;
            }
        }
        return false;
    }

    public int[] getP() {
        return p;
    }
}