package src;

import src.fila.FilaDinamica;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Grafos {

    private int numVertices;
    private int numArestas;
    private String[] vertices;            //array que armazena o nome de cada vertice
    private String[] arestas;            //array que armazena o nome de cada aresta
    private int[][] matrizAdjacencias;    //matriz de adjacencias numvertices X numvertices
    private int[][] matrizPesos;        //matriz de pesos

    private int[] grausVertices;            //Grau de todos os vertices

    //------------------------------------------------------------------------------

    public Grafos() {
    }

    public Grafos(String nomearquivo) {
        this.lerArquivo(nomearquivo);
    }

    //----------------------------------------

    private void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumVertices() {
        return (this.numVertices);
    }

    private void setNumArestas(int numArestas) {
        this.numArestas = numArestas;
    }

    public int getNumArestas() {
        return (this.numArestas);
    }

    private void setVertices(String[] vertices) {
        this.vertices = vertices;
    }

    public String[] getVertices() {
        return (this.vertices);
    }

    private void setArestas(String[] arestas) {
        this.arestas = arestas;
    }

    public String[] getArestas() {
        return (this.arestas);
    }

    public int[][] getMatrizAdjacencias() {
        return (this.matrizAdjacencias);
    }

    public int[][] getMatrizPesos() {
        return this.matrizPesos;
    }

    public int getGrauVertice(int vertice) {
        int grau = 0;
        for (int i = 0; i < this.vertices.length; i++) {
            if (this.matrizAdjacencias[vertice][i] == 1) {
                grau++;
            }
        }
        return (grau);
    }

    //Grau de todos os vertices - OK!
    public int[] getGrausVertices() {
        this.grausVertices = new int[this.getNumVertices()];
        if (this.getNumVertices() > 0) {
            for (int i = 0; i < this.getNumVertices(); i++) {
                this.grausVertices[i] = this.getGrauVertice(i);
            }
        } else {
            System.out.println("ERRO: Nao existem vertices no grafo!");
        }
        return (this.grausVertices);
    }

    //------------------------------------------------------------------------------

    public void lerArquivo(String nomearquivo) {
        try {
            RandomAccessFile arqEntrada = new RandomAccessFile(new File("resources/" + nomearquivo), "r");

            // Rotulo dos vertices
            String linha = arqEntrada.readLine();
            String[] vertices = linha.split(";");
            this.setVertices(vertices);

            this.setNumVertices(vertices.length); // Qtde de vertices

            this.matrizAdjacencias = new int[this.numVertices][this.numVertices];
            this.matrizPesos = new int[this.numVertices][this.numVertices];

            linha = arqEntrada.readLine();
            int nLin = 0; // Primeia linha da matriz
            while (linha != null) {
                String[] lin = linha.split(";");
                for (int j = 0; j < lin.length; j++) {
                    if (lin[j].equals("0")) {
                        this.matrizPesos[nLin][j] = 0;
                        this.matrizAdjacencias[nLin][j] = 0;
                    } else {
                        this.matrizPesos[nLin][j] = Integer.parseInt(lin[j]);
                        this.matrizAdjacencias[nLin][j] = 1;
                        this.numArestas++;
                    }
                }
                nLin++;
                linha = arqEntrada.readLine();
            }
            this.setNumArestas(this.numArestas); // Qtde de arestas
            this.grausVertices = this.getGrausVertices(); // Grau de todos os vertices

            arqEntrada.close();

        } catch (IOException e) {
            System.out.println("ERRO: Leitura de arquivo invalida!");
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Formato de numero invalido!");
        }
    }

    //------------------------------------------------------------------------------
    // Busca em Amplitude e Busca em Profundidade
    //------------------------------------------------------------------------------

    public Boolean buscaAmplitude(int verticeInicial) {

        boolean[] marcado = new boolean[getNumVertices()]; // A variável numVertices é da classe
        int[] antecessor = new int[getNumVertices()]; // A variável numVertices é da classe
        FilaDinamica fila = new FilaDinamica();

        marcado[verticeInicial] = true;
        fila.enfileirar(verticeInicial);

        if (!fila.vazia()) {
            int coluna = (int) fila.desenfileirar();
            for (int z = 0; z <= marcado.length - 1; z++) {
                if (z == coluna && !marcado[z]) {
                    antecessor[z] = coluna;
                    fila.enfileirar(z);
                }
            }
        }

        return marcado[verticeInicial];
    }

    public boolean buscaProfundidade(int vertice) {

        boolean[] marcado = new boolean[getNumVertices()]; // A variável numVertices é da classe
        int[] antecessor = new int[getNumVertices()]; // A variável numVertices é da classe

        marcado[vertice] = true;

        for (int coluna = 0; coluna <= marcado.length - 1; coluna++) {

            if (vertice == coluna && !marcado[coluna]) {
                antecessor[coluna] = vertice;
                DFS(coluna);
            }
        }
        return marcado[vertice];
    }

    public void DFS(int vertice) {

        boolean[] marcado = new boolean[0];
        int[] antecessor = new int[marcado.length];
        Stack<Object> pilha = new Stack<>(); // instanciar a pilha

        marcado[vertice] = true;
        pilha.push(vertice);// inserir na pilha

        if (!pilha.isEmpty()) { // ver ser ta vazia

            int coluna = (int) pilha.pop();
            for (int z = 0; z <= marcado.length; z++) {
                if (vertice == coluna && !marcado[coluna]) {
                    antecessor[z] = coluna;
                    marcado[z] = true;
                    pilha.push(z);
                }
            }
        }

    }

    //------------------------------------------------------------------------------
    // Algoritmo de Kruskal
    //------------------------------------------------------------------------------

    public int[] kruskal(){

        LinkedList<Integer> r = new LinkedList<>();
        List<List<Integer>> arestas =  new ArrayList<>(); // lista de arestas identificadas pelos pares de vértices(int[])
        int[] p = new int[getNumVertices()]; // Vetor de parents, de onde eu vim
        Arrays.fill(p, -1); // n posições inicializadas com -1
        int anterior;

        p[3] = -2; // Escolha um vértice Vi e marque-o com 0 na posição i em P
        r.add(3);
        anterior = r.getLast();

        adjacentes(anterior, arestas, p);

        List<Integer> vertices;
        while (contemMenos1(p)) {// Faça enquanto p == -1
            if (anterior != r.getLast() ) {
                adjacentes(r.getLast(), arestas, p); // obtem as arestas candidatas a próxima escolha
                anterior = r.getLast();
            }

            vertices = menorAresta(arestas); // obtenha a aresta de menor peso
            if (r.size() == 1){ // se é a primeira iteração não há arestas para formar circuitos
                p[vertices.get(1)] = vertices.get(0); // marque a posição j de p com o valor de i
                r.add(vertices.get(1));
            }else {
                if (!fechaCircuito(vertices.get(0), vertices.get(1), p)){
                    p[vertices.get(1)] = vertices.get(0); // marque a posição j de p com o valor de i
                    r.add(vertices.get(1));
                }
            }
            arestas.remove(vertices);
        }

        return p;
    }

    private void adjacentes(int v, List<List<Integer>> arestas, int[] p) {
        for (int i = 0; i < getNumVertices(); i++) {
            if (matrizPesos[v][i] != 0){
                if (!estaAdicionado(v, i, p) || !containsAresta(v, i, arestas)){
                    arestas.add(List.of(v, i));
                }
            }
        }
    }

    public List<Integer> menorAresta(List<List<Integer>> arestas){

        // variaveis auxiliares para indentificação das arestas.
        int vertice1 = arestas.get(0).get(0), vertice2 = arestas.get(0).get(1);

        int vertices = 0;
        int menor = matrizPesos[vertice1][vertice2]; // menor peso de aresta encontrado

        for (int i = 1; i < arestas.size(); i++) {
            vertice1 = arestas.get(i).get(0);
            vertice2 = arestas.get(i).get(1);

            if (matrizPesos[vertice1][vertice2] < menor){
                menor = matrizPesos[vertice1][vertice2];
                vertices = i;
            }
        }
        return arestas.get(vertices);
    }

    public boolean fechaCircuito(int vertice, int buscado, int[] p) {

        int n = p.length;
        boolean[] marcado = new boolean[n];
        int[] antecessor = new int[n];
        marcado[vertice] = true;

        for (int w = 0; w < n; w++) {
            if (eAdjacente(vertice, w, p) && !marcado[w]){
                antecessor[w] = vertice;
                DFS(w, marcado, antecessor, n, p);
            }
        }
        return marcado[buscado];
    }

    // Parte de busca e profundidade modificado para verificar se fecha circuito
    public void DFS(int vertice, boolean[] marcado, int[] antecessor, int n, int[] p) {

        Stack<Integer> pilha = new Stack<>(); // instanciar a pilha

        marcado[vertice] = true;
        pilha.push(vertice);// inserir na pilha

        while(!pilha.isEmpty()) { // ver ser ta vazia
            int w = pilha.pop();
            for (int z = 0; z < n; z++) {
                if (eAdjacente(w, z, p) && !marcado[z]) {
                    antecessor[z] = w;
                    marcado[z] = true;
                    pilha.push(z);
                }
            }
        }

    }

    // Verifica se dois vertices são adjacentes na arvore (no vetor p)
    private boolean eAdjacente(int v, int w, int[] vetor) {
        return vetor[v] == w || vetor[w] == v;
    }

    // Verifica se já está adicionado na arvore (no vetor p)
    private boolean estaAdicionado(int x, int y, int[] p) {
        return p[x] == y || p[y] == x;
    }

    private boolean containsAresta(int vertice1, int vertice2, List<List<Integer>> arestas) {
        return arestas.contains(List.of(vertice1, vertice2));
    }

    //------------------------------------------------------------------------------
    // Algoritmo de Dikstra
    //------------------------------------------------------------------------------

    public HashMap<String, Integer> dijkstra(int v0){

        List<Integer> borda = new ArrayList<>(); // proximos vértices candidatos a próxima escolha
        borda.add(v0);

        List<Integer> incluidos = new ArrayList<>(); // menores caminhos a partir do inicial

        int[] custo = new int[getNumVertices()]; // Custo do caminho até x
        Arrays.fill(custo, Integer.MAX_VALUE); // custo[x] = infinito d
        custo[v0] = 0;

        int v = 0;
        while (!borda.isEmpty()){

            v = menorCusto(borda, custo); // Vértice da borda com o menor custo
            incluidos.add(v); // insira v em incluidos
            borda.remove((Integer) v); // retire v da borda

            // todos vertices de G não pertencentes em incluidos
            for (int x = 0; x < getNumVertices(); x++){
                if (!incluidos.contains(x)){
                    if ( ( eAdjacente(v,x, matrizAdjacencias) ) && ( custo[x] > custo[v] + pesoXparaY(v,x) ) ){
                        custo[x] = custo[v] + pesoXparaY(v,x);
                        borda.add(x); // insira x na borda
                    }
                }
            }
        }

        HashMap<String, Integer> solucao = new HashMap<>();
        for (int i = 0; i < custo.length; i++) {
            solucao.put(vertices[i], custo[i]);
        }

        return solucao; // Retorna os vertices e os custos para chegar em nele a partir do inicial
    }

    // Vértice da borda com o menor custo
    private int menorCusto(List<Integer> borda, int[] custo) {

        int menorPeso = Integer.MAX_VALUE;
        int vertice = 0;

        for (int i : borda) {
            if (custo[i] < menorPeso){
                menorPeso = custo[i];
                vertice = i;
            }
        }

        return vertice;
    }

    // W(x, y): peso da aresta de x para y
    private int pesoXparaY(int v, int x){
        return matrizPesos[v][x];
    }

    // E(x, y): aresta de x para y
    private boolean eAdjacente(int v, int w, int[][] vetor) {
        return vetor[v][w] != 0;
    }

    //------------------------------------------------------------------------------
    // METODOS AUXILIARES
    //------------------------------------------------------------------------------

    public String exibirVetor(int[] vet) {
        String res = "";
        for (int i = 0; i < vet.length; i++) {
            res += ((i < (vet.length - 1)) ? vet[i] + "-" : vet[i]);
        }
        return (res);
    }

    public String exibirVetor(String[] vet) {
        String res = "";
        for (int i = 0; i < vet.length; i++) {
            res += ((i < (vet.length - 1)) ? vet[i] + "-" : vet[i]);
        }
        return (res);
    }

    public String exibirMatrix(int[][] mat) {
        String res = "";
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                res += ((j < (mat[0].length - 1)) ? mat[i][j] + "  " : mat[i][j]);
            }
            res += ((i < (mat.length - 1)) ? "\n" : "");
        }
        return (res);
    }

    private boolean contemMenos1(int[] vetor) {
        for (int x : vetor) {
            if (x == -1) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return (">>> Classe src.Grafos (G) <<<" + "\n\n" +
                "Matriz de adjacencias  :\n" + this.exibirMatrix(this.getMatrizAdjacencias()) + "\n" +
                "Numero de vertices (n) : " + this.getNumVertices() + "\n" +
                "Numero de arestas (e)  : " + this.getNumArestas() + "\n" +
                "Graus dos vertices de G: " + this.exibirVetor(this.getGrausVertices()) + "\n"
        );
    }
}