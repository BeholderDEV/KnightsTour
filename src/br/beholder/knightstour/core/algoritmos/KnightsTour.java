/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.algoritmos;

import br.beholder.knightstour.control.NormalPathController;
import br.beholder.knightstour.core.model.Coords;
import br.beholder.knightstour.ui.swing.MapRenderer;
import java.awt.Image;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Augustop
 */
public abstract class KnightsTour {

    protected NormalPathController controller;
    protected Coords[] solutionCoord;
    protected int solucao[][];
    
    public KnightsTour(int n, NormalPathController ctr) {
        this.solutionCoord = new Coords[n * n];
        this.controller = ctr;
        solucao = new int[n][n];
    }
    
    public abstract Coords[] findPath();
    
    public void desenharSolucao(){
        for (Coords coord : this.solutionCoord) {
            this.controller.getMap().andar(new Point(coord.getX(), coord.getY()));
            Image imagem = MapRenderer.getInstance().getFirstImage(this.controller.getMap());
            this.controller.stepImage(imagem);
            try {
                Thread.sleep(this.controller.getTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(BackTracking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean movimentoValido(int l, int c){
        if (l >= 0 && c >= 0 && l < this.solucao.length && c < this.solucao.length) {
                return true;
        }
        return false;
    }
    
    public boolean posicaoVerificada(Coords c){
        return this.solucao[c.getX()][c.getY()] != 0;
    }
    
    public void resetBoard(){
        for (int i = 0; i < this.solucao.length; i++) {
            for (int j = 0; j < this.solucao.length; j++) {
                solucao[i][j] = 0;
            }
        }
    }
    
    public void imprimirSolucao(){
        DecimalFormat df = new DecimalFormat("00");
        for (int i = 0; i < this.solucao.length; i++) {;;
            for (int j = 0; j < this.solucao.length; j++) {
                System.out.print(df.format(solucao[i][j]) + " ");
            }
            System.out.println("");
        }
    }
    
}
