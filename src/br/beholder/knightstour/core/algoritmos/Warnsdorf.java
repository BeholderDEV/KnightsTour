/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.algoritmos;

import br.beholder.knightstour.control.NormalPathController;
import br.beholder.knightstour.core.model.Coords;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Augustop
 */

public class Warnsdorf extends KnightsTour{

    private int moveX [] = { -2, -2, -1, 1, 2, 2, -1, 1 };
    private int moveY [] = { 1, -1, 2, 2, 1, -1, -2, -2 };
    private Random rand;
    private int linhaInicial; 
    private int colunaInicial;
    
    
    public Warnsdorf(int n, NormalPathController ctr) {
        super(n, ctr);
    }
    
    public Coords[] findPath() {
        this.rand = new Random(System.nanoTime());
        this.colunaInicial = rand.nextInt(this.solucao.length);
        this.linhaInicial = rand.nextInt(this.solucao.length);
        System.out.println(linhaInicial + ", " + colunaInicial);
        Coords posicaoInicial = new Coords(linhaInicial, colunaInicial);  
        
        if(this.percorrerCaminho(posicaoInicial)){; 
            this.desenharSolucao();
//            this.imprimirSolucao();
        }else{
            System.out.println("Fail"); 
//            this.imprimirSolucao();
            this.resetBoard(); 
            return this.findPath();
        }
        return this.solutionCoord;
    }
    
    private ArrayList<Coords> getVizinhos(Coords posicao){
        ArrayList<Coords> vizinhos = new ArrayList<>();
        Coords posicaoVizinha;
        for (int i = 0; i < 8; i++) {
            posicaoVizinha = new Coords(posicao.getX() + this.moveX[i], posicao.getY() + this.moveY[i]);
            if(this.movimentoValido(posicaoVizinha.getX(), posicaoVizinha.getY()) && !this.posicaoVerificada(posicaoVizinha)){
                vizinhos.add(posicaoVizinha);
            }
        }
        return vizinhos;
    }
    
    private int calcularNumeroVizinhos(Coords posicao){
        int qtd = 0;
        Coords posicaoVizinha;
        for (int i = 0; i < 8; i++) {
            posicaoVizinha = new Coords(posicao.getX() + this.moveX[i], posicao.getY() + this.moveY[i]);
            if(this.movimentoValido(posicaoVizinha.getX(), posicaoVizinha.getY()) && !this.posicaoVerificada(posicaoVizinha)){
                qtd++;
            }
        }
        return qtd;
    }
    
    private boolean percorrerCaminho(Coords posicao){
        int passosRealizados = 1;
        while(passosRealizados != this.solucao.length * this.solucao.length) {
            this.solucao[posicao.getX()][posicao.getY()] = passosRealizados;
            this.solutionCoord[passosRealizados - 1] = new Coords(posicao.getX(), posicao.getY());
            ArrayList<Coords> vizinhos = this.getVizinhos(posicao);
            if(vizinhos.isEmpty()){
                return false;
            }
            Coords novaPosicao = null;
            int menorVizinho = Integer.MAX_VALUE;
            int quantidadeVizinhos;
            for (Coords vizinho : vizinhos) {
                quantidadeVizinhos = this.calcularNumeroVizinhos(vizinho);
                if((quantidadeVizinhos > 0 || passosRealizados == this.solucao.length * this.solucao.length - 1) && quantidadeVizinhos < menorVizinho){ // Decidir o que fazer com empates
                    menorVizinho = quantidadeVizinhos;
                    novaPosicao = vizinho;
                }
            }
            if(novaPosicao == null){
                return false;
            }
            posicao = novaPosicao;
            passosRealizados++;
        }
        this.solucao[posicao.getX()][posicao.getY()] = passosRealizados;
        this.solutionCoord[passosRealizados - 1] = new Coords(posicao.getX(), posicao.getY());
        return true;
    }
    
}
