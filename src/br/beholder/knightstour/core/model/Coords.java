/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.model;

/**
 *
 * @author Alisson
 */
/**
* Represents coordinates
*/
public class Coords {
   private int x;
   private int y;

   public Coords(int x, int y) {
       this.x = x;
       this.y = y;
   }

   /**
    * @return the x
    */
   public int getX() {
       return x;
   }

   /**
    * @param x the x to set
    */
   public void setX(int x) {
       this.x = x;
   }

   /**
    * @return the y
    */
   public int getY() {
       return y;
   }

   /**
    * @param y the y to set
    */
   public void setY(int y) {
       this.y = y;
   }

    @Override
    public String toString() {
        return x+" : "+y;
    }
   
}
