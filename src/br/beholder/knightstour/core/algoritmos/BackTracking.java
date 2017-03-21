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
 * Backtracking Knight's tour solver
 * @author Pavel Micka
 */
public class BackTracking {

    /**
     * Indicator that the square was not visited yet
     */
    private static int NOT_VISITED = -1;
    /**
     * Width of the chessboard
     */
    private int xSize;
    /**
     * Height of the chessboard
     */
    private int ySize;
    /**
     * Solution board
     * 0 -> Initial position of the knight
     * 1 -> first move
     * 2 -> second move
     * .
     * .
     * .
     * n -> n-th move
     */
    private int[][] solutionBoard;
    private Coords[] solution;
    private boolean finish=false;
    private NormalPathController controller;

    public static void main(String[] args) {
        //BackTracking kt = new BackTracking(8,8);
        //kt.solve();
    }

    /**
     * Constructor
     * @param xSize width of the chessboard
     * @param ySize height of the chessboard
     */
    public BackTracking(int xSize, int ySize, NormalPathController controller) {
        this.controller=controller;
        this.xSize = xSize;
        this.ySize = ySize;
        solution = new Coords[ySize*xSize];
        solutionBoard = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                solutionBoard[i][j] = NOT_VISITED;
            }
        }
    }

    /**
     * Solve the knight's tour
     */
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

    /**
     * Return possible destinations of the knight
     * @param x x coord of the knight
     * @param y y coord of the knight
     * @return possible destinations of the knight
     */
    private List<Coords> getFields(int x, int y) {
        List<Coords> l = new ArrayList<Coords>();
        if (x + 2 < xSize && y - 1 >= 0)
            l.add(new Coords(x + 2, y - 1)); //right and upward
        if (x + 1 < xSize && y - 2 >= 0)
            l.add(new Coords(x + 1, y - 2)); //upward and right
        if (x - 1 >= 0 && y - 2 >= 0)
            l.add(new Coords(x - 1, y - 2)); //upward and left
        if (x - 2 >= 0 && y - 1 >= 0)
            l.add(new Coords(x - 2, y - 1)); //left and upward
        if (x - 2 >= 0 && y + 1 < ySize)
            l.add(new Coords(x - 2, y + 1)); //left and downward
        if (x - 1 >= 0 && y + 2 < ySize)
            l.add(new Coords(x - 1, y + 2)); //downward and left
        if (x + 1 < xSize && y + 2 < ySize)
            l.add(new Coords(x + 1, y + 2)); //downward and right
        if (x + 2 < xSize && y + 1 < ySize)
            l.add(new Coords(x + 2, y + 1)); //right and downward
        return l;
    }

    /**
     * Perform the move
     * @param x destination x coord
     * @param y destination y coord
     * @param turnNr number of the move
     */
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

    /**
     * Print out the solution
     */
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
    
    
}

