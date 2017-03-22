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
        
    int path = 1;
    
    public BackTracking2(int n, NormalPathController ctr) {
        super(n, ctr);
    }
    
    @Override
    public Coords[] findPath() {
        Random rand = new Random(System.nanoTime());
        int colunaInicial = rand.nextInt(this.solucao.length);
        int linhaInicial = rand.nextInt(this.solucao.length);
//        System.out.println(colunaInicial);
//        System.out.println(linhaInicial);
        if(this.percorrerCaminho(0, 0, 1)){; // Dando problemas quando o início é no meio
            this.desenharSolucao();
//            this.imprimirSolucao();
        }else{
            System.out.println("Fail");
            return this.solutionCoord;
        }
        return this.solutionCoord;
    
    }
    
    private boolean percorrerCaminho(int linha, int coluna, int index){
        if(this.solucao[linha][coluna] != 0) {
            return false;
        }
        this.solutionCoord[this.path - 1] = new Coords(linha, coluna);
        this.solucao[linha][coluna] = this.path++;
        if(index == this.solucao.length * this.solucao.length) {
            return true;
        }
        if(this.movimentoValido(linha + 2, coluna + 1) && this.percorrerCaminho(linha + 2, coluna + 1, index + 1)) {
            return true;
        }
        if(this.movimentoValido(linha + 1, coluna + 2) && this.percorrerCaminho(linha + 1, coluna + 2, index + 1)) {
            return true;
        }
        if(this.movimentoValido(linha - 1, coluna + 2) && this.percorrerCaminho(linha - 1, coluna + 2, index + 1)) {
            return true;
        }
        if(this.movimentoValido(linha - 2, coluna + 1) && this.percorrerCaminho(linha - 2, coluna + 1, index + 1)) {
            return true;
        }
        if(this.movimentoValido(linha - 2, coluna - 1) && this.percorrerCaminho(linha - 2, coluna - 1, index + 1)) {
            return true;
        }
        if(this.movimentoValido(linha - 1, coluna - 2) && this.percorrerCaminho(linha - 1, coluna - 2, index + 1)) {
            return true;
        }
        if(this.movimentoValido(linha + 1, coluna - 2) && this.percorrerCaminho(linha + 1, coluna - 2, index + 1)) {
            return true;
        }
        if(this.movimentoValido(linha + 2, coluna - 1) && this.percorrerCaminho(linha + 2, coluna - 1, index + 1)) {
            return true;
        }
        this.solucao[linha][coluna] = 0;
        this.path--;
        return false;
    }
    
    
}
