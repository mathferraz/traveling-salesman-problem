/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajante;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Instancia {

    /**
     * numero de elementos
     */
    private int n;
    /**
     * matriz distância
     */
    private int d[][];
    /**
     * gerador de números aleatorios
     */
    private Random r = new Random(1);
    /**
     * maior distancia aleatoria
     */
    private int maxRandom;

    /**
     *
     * @param n - int - número de elementos.
     * @param max - int - valores aleatorios serão no máximo max
     */
    public Instancia(int n, int max) {
        this.n = n;
        d = new int[n][n];
        this.maxRandom = max;
    }

    /**
     * Gera uma solução aleatória. A distância entre dois pontos é um valor
     * inteiro aleatório entre 0 e 1000. A distância de a para b pode ser
     * diferente da distância de b para a.
     */
    public void geraAl() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = (i == j) ? 0 : r.nextInt(this.maxRandom);
            }
        }
    }

    public Instancia(String arq) {

        File f = new File(arq);
        FileReader fin = null;
        try {
            fin = new FileReader(f);
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao abrir arquivo " + f.getAbsolutePath());
            Logger.getLogger(Instancia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            BufferedReader br = new BufferedReader(fin);
            String linha = br.readLine();
            this.n = Integer.parseInt(linha);
            d = new int[n][n];
            linha = br.readLine();
            int lc = 0;
            while (linha != null && linha.length() > 1) {
                String[] lista = linha.split(" ");
                for (int i = 0; i < n; i++) {
                    d[lc][i] = Integer.parseInt(lista[i]);
                }
                lc++;
                linha = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Instancia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gera uma matriz de distâncias aleatória, porém simétria. A distância
     * entre a e b é igual a distância entre b e a.
     */
    public void geraAlSim() {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                d[i][j] = (i == j) ? 0 : r.nextInt(this.maxRandom);
                d[j][i] = d[i][j];
            }
        }
    }

    /**
     * verifica se a matriz é simétrica. Se não for, altera no sentido de
     * torna-la mais próxima do simétrica.
     *
     * @return verdadeiro se e somente se ela for simétrica.
     */
    private boolean isMetrica(boolean alterar) {
        boolean metrica = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        metrica = false;
                        if (alterar) {
                            d[i][j] = d[i][k] + d[k][j];
                            d[j][i] = d[i][j];

                        }
                    }
                }
            }
        }
        return metrica;
    }

    public boolean isMetrica() {
        boolean alterar = false;
        return this.isMetrica(alterar);
    }

    /**
     * Gera uma matriz aleatória métrica.
     */
    public void geraAlMet() {
        this.geraAlSim();
        boolean metrica = false;
        while (!metrica) {
            metrica = this.isMetrica(true);

        }
        assert (this.isMetrica());
    }

    @Override
    public String toString() {
        String ret = "Matriz Distancia\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret += d[i][j] + "\t";
            }
            ret += "\n";
        }
        return ret;
    }

    /**
     * metodo get
     *
     * @param i
     * @param j
     * @return distancia entre i e j.
     */
    public int d(int i, int j) {
        return d[i][j];
    }

    /**
     * metodo set. d[i][j] recebe valor.
     *
     * @param i
     * @param j
     * @param valor
     */
    public void d(int i, int j, int valor) {
        d[i][j] = valor;
    }

    public int size() {
        return n;
    }
}
