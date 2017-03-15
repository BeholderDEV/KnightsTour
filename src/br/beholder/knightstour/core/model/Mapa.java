/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.model;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author lite
 */
public class Mapa {
    private Dimension tamanho;
    private int[][] matrix;
    private int[][] blocked;
    private boolean [][] pathMatrix;
    private Point pontoInicial;
    private Point pontoFinal;

    public Mapa(Dimension tamanho, int[][] matrix, Point pontoInicial, Point pontoFinal) {
        this.tamanho = tamanho;
        this.matrix = matrix;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
    }

    public Mapa(Dimension tamanho, int[][] matrix, int[][] blocked, Point pontoInicial, Point pontoFinal) {
        this.tamanho = tamanho;
        this.matrix = matrix;
        this.blocked = blocked;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
    }

    public int[][] getBlocked() {
        return blocked;
    }

    public void setBlocked(int[][] blocked) {
        this.blocked = blocked;
    }
    
    public boolean [][] getPathMatrix() {
        return pathMatrix;
    }

    public void setPathMatrix(boolean[][] pathMatrix) {
        this.pathMatrix = pathMatrix;
    }

    public Dimension getTamanho() {
        return tamanho;
    }

    public void setTamanho(Dimension tamanho) {
        this.tamanho = tamanho;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Point getPontoInicial() {
        return pontoInicial;
    }

    public void setPontoInicial(Point pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public Point getPontoFinal() {
        return pontoFinal;
    }

    public void setPontoFinal(Point pontoFinal) {
        this.pontoFinal = pontoFinal;
    }

    @Override
    public String toString() {
        String str="";
        str = str.concat("\nTamanho: "+tamanho.toString());
        str = str.concat("\nInicial: "+pontoInicial.toString());
        str = str.concat("\nFinal: "+pontoFinal.toString()+"\n");
        
        for (int i = 0; i < tamanho.height; i++) {
            for (int j = 0; j < tamanho.width; j++) {
                str = str.concat(matrix[i][j]+" ");
            }
            str = str.concat("\n");
        }
        
        return str;
    }
    
}
