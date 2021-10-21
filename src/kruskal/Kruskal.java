package src.kruskal;

import src.Grafos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kruskal {

    private Grafos grafos;
    private List<Integer> pesoArestas = new ArrayList<>();
    private List<int[]> vertices =  new ArrayList<>();
    private int[][] arvore;

    public Kruskal() {
    }

    public Kruskal(Grafos grafos) {
        this.grafos = grafos;
        geraArvoreMinima(this.grafos.getMatrizPesos());
    }

    public void geraArvoreMinima(int[][] matrizPesos){
        ordenaArestas(matrizPesos);
        arvore = new int[matrizPesos.length][matrizPesos.length];

        int vertice1, vertice2;
        for (int i = 0; i < vertices.size(); i++) {
            vertice1 = vertices.get(i)[0];
            vertice2 = vertices.get(i)[1];
            if (!fechaCircuito(arvore, vertice2)){
                arvore[vertice1][vertice2] = matrizPesos[vertice1][vertice2];
                arvore[vertice2][vertice1] = matrizPesos[vertice2][vertice1];
            }
        }

        String res = "";
        for (int i = 0; i < arvore.length; i++) {
            for (int j = 0; j < arvore[0].length; j++) {
                res += ((j < (arvore[0].length - 1)) ? arvore[i][j] + "  " : arvore[i][j]);
            }
            res += ((i < (arvore.length - 1)) ? "\n" : "");
        }
        System.out.println(res);

    }

    public void ordenaArestas(int[][] matrizPesos){

        //extrai os pesos das arestas junto com as suas conexões
        for (int i = 0; i < matrizPesos.length; i++) {
            for (int j = 0; j < matrizPesos.length; j++) {
                if (matrizPesos[i][j] != 0) {
                    vertices.add(new int[]{i,j});
                }
            }
        }

        System.out.println(">>> Grafo Ordenado <<< ");
        System.out.println();
        vertices.forEach(vertice ->{
            for (int i = 0; i < vertice.length; i++) {
                System.out.print(vertice[i] + "");
            }
            System.out.print(" ");
        });
        System.out.println();

    }

    private boolean fechaCircuito(int[][] arvore, int vertice2){

        for (int i = vertice2 - 1; i >= 0 ; i--) {
            if (arvore[i][vertice2] != 0){
                return true;
            }
        }
        return false;
    }

    public Grafos getGrafos() {
        return grafos;
    }

    public void setGrafos(Grafos grafos) {
        this.grafos = grafos;
    }

    public List<int[]> getVertices() {
        return vertices;
    }

    public void setVertices(List<int[]> vertices) {
        this.vertices = vertices;
    }

    public int[][] getArvore() {
        return arvore;
    }

    public void setArvore(int[][] arvore) {
        this.arvore = arvore;
    }
}