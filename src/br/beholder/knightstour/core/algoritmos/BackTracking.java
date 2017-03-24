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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Alisson
 */
/**
 * @author Pavel Micka
 */
public class BackTracking extends KnightsTour{


    private static int NOT_VISITED = -1;

    private int xSize;

    private int ySize;

    private int[][] solutionBoard;
    private Coords[] solution;
    private boolean finish = false;
    private NormalPathController controller;

    public static void main(String[] args) {
        //BackTracking kt = new BackTracking(8,8);
        //kt.solve();
    }

    public BackTracking(int n, NormalPathController controller) {
        super(n, controller);
        this.controller = controller;
        this.xSize = n;
        this.ySize = n;
        solution = new Coords[ySize*xSize];
        solutionBoard = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                solutionBoard[i][j] = NOT_VISITED;
            }
        }
    }

    public Coords[] solve() {
        takeTurn(0, 0, 0);
        for (Coords coord : solution) {
            controller.getMap().andar(new Point(coord.getX(), coord.getY()));
            Image imagem = MapRenderer.getInstance().getFirstImage(controller.getMap());
            controller.stepImage(imagem);
            try {
                Thread.sleep(controller.getTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(BackTracking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return solution;
    }

    private List<Coords> getFields(int x, int y) {
        List<Coords> l = new ArrayList<Coords>();
        if (x + 2 < xSize && y - 1 >= 0)
            l.add(new Coords(x + 2, y - 1)); 
        if (x + 1 < xSize && y - 2 >= 0)
            l.add(new Coords(x + 1, y - 2));
        if (x - 1 >= 0 && y - 2 >= 0)
            l.add(new Coords(x - 1, y - 2)); 
        if (x - 2 >= 0 && y - 1 >= 0)
            l.add(new Coords(x - 2, y - 1));
        if (x - 2 >= 0 && y + 1 < ySize)
            l.add(new Coords(x - 2, y + 1));
        if (x - 1 >= 0 && y + 2 < ySize)
            l.add(new Coords(x - 1, y + 2));
        if (x + 1 < xSize && y + 2 < ySize)
            l.add(new Coords(x + 1, y + 2));
        if (x + 2 < xSize && y + 1 < ySize)
            l.add(new Coords(x + 2, y + 1));
        return l;
    }

    private void takeTurn(int x, int y, int turnNr) {
        if(!finish){
            solutionBoard[y][x] = turnNr;
            solution[turnNr]= new Coords(x, y);
            if (turnNr == (xSize * ySize) - 1) {
                //solutionsCount++;
                //printSolution();
                finish=true;
                return;
            } else {
                for (Coords c : getFields(x, y)) {
                    if (solutionBoard[c.getY()][c.getX()] == NOT_VISITED) {
                        takeTurn(c.getX(), c.getY(), turnNr + 1);
                        solutionBoard[c.getY()][c.getX()] = NOT_VISITED; //reset the square
                    }
                }
            }
        }
        
    }

    private void printSolution() {
        //System.out.println("Solution #" + solutionsCount);
        for (int i = 0; i < solutionBoard.length; i++) {
            for (int j = 0; j < solutionBoard[i].length; j++) {
                System.out.print(solutionBoard[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println(" + "+solution.length);
        for (int i = 0; i < solution.length; i++) {
                System.out.print(solution[i].toString()+ " / ");
        }
    }

    @Override
    public Coords[] findPath() {
        this.solve();
        return solution;
    }
    
    
}

