/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.model;

import java.awt.Point;

/**
 *
 * @author Adson Estevesa
 */
public class Cell {
        private int heuristicCost = 0; //Heuristic cost
        private int finalCost = 0; //G+H
        private Point position = new Point();
        private Cell parent; 
     
    public Cell(int i, int j){
        this.position.x=j;
        this.position.y=i;
    }

    @Override
    public String toString(){
        return "["+this.position.y+", "+this.position.x+"]";
    }

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public int getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(int finalCost) {
        this.finalCost = finalCost;
    }

    public Point getPosition() {
        return position;
    }
    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }
        
        
}
