/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.algoritmos;

import br.beholder.knightstour.control.NormalPathController;
import br.beholder.knightstour.core.model.Coords;
import java.util.Random;

/**
 *
 * @author Augustop
 */
public class BackTracking2 extends KnightsTour{
        
    private int path = 1;
    private int moveX [] = { 2, 1, -1, -2, -2, -1,  1,  2 };
    private int moveY [] = { 1, 2,  2,  1, -1, -2, -2, -1 };

    public BackTracking2(int n, NormalPathController ctr) {
        super(n, ctr);
    }
    
    @Override
    public Coords[] findPath() {
        Random rand = new Random(System.nanoTime());
        int colunaInicial = rand.nextInt(this.solucao.length);
        int linhaInicial = rand.nextInt(this.solucao.length);
//        System.out.println(linhaInicial + ", " + colunaInicial);
        if(this.percorrerCaminho(0, 0)){; // Dando problemas quando o início é no meio
            this.desenharSolucao();
//            this.imprimirSolucao();
        }else{
            System.out.println("Fail");
//            this.path = 1;
//            this.resetBoard();
//            return this.findPath();
            return this.solutionCoord;
        }
        return this.solutionCoord;
    
    }
    
    private boolean percorrerCaminho(int linha, int coluna){
        if(this.solucao[linha][coluna] != 0) {
            return false;
        }
        this.solutionCoord[this.path - 1] = new Coords(linha, coluna);
        this.solucao[linha][coluna] = this.path++;
        if(path == this.solucao.length * this.solucao.length + 1) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            if(this.movimentoValido(linha + this.moveX[i], coluna + this.moveY[i]) && this.percorrerCaminho(linha + this.moveX[i], coluna + this.moveY[i])){
                return true;
            }
        }
        
        this.solucao[linha][coluna] = 0;
        this.path--;
        return false;
    }
    
    
}
