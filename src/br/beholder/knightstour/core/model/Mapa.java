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
    private Point pontoInicial;

    public Mapa(Dimension tamanho, int[][] matrix, Point pontoInicial) {
        this.tamanho = tamanho;
        this.matrix = matrix;
        this.pontoInicial = pontoInicial;
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
    
    public void andar(Point ponto){
        matrix[ponto.y][ponto.x]=6;
        setPontoInicial(ponto);
    }
    
    public void setPontoInicial(Point pontoInicial) {
        this.pontoInicial = pontoInicial;
    }
    
    @Override
    public String toString() {
        String str="";
        str = str.concat("\nTamanho: "+tamanho.toString());
        str = str.concat("\nInicial: "+pontoInicial.toString());
        
        for (int i = 0; i < tamanho.height; i++) {
            for (int j = 0; j < tamanho.width; j++) {
                str = str.concat(matrix[i][j]+" ");
            }
            str = str.concat("\n");
        }
        
        return str;
    }
    
}
