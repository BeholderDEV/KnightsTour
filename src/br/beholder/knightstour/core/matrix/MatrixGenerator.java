/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.matrix;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author lite
 */
public class MatrixGenerator {
    private Point init;
    private Point fin;
    private int size;
    private boolean [][] matrix;

    public MatrixGenerator(int size) {
        this.size = size;
        Noise noise = new Noise(null, 1.0f, size, size);
        noise.initialise();
        matrix = noise.getAsBool();
        generateInitial();
        generateFinal();
    }
    
    private void generateInitial(){
        Random r = new Random();
        int i= Math.abs(r.nextInt()%size);
        int j= Math.abs(r.nextInt()%size);
        if(!matrix[i][j]){
            init=new Point(j, i);
        }
        else{
            generateInitial();
        }
    }
    private void generateFinal(){
        Random r = new Random();
        int i= Math.abs(r.nextInt()%size);
        int j= Math.abs(r.nextInt()%size);
        if(!matrix[i][j] && (i!=init.y || j!=init.x)){
            fin=new Point(j, i);
        }
        else{
            generateFinal();
        }
    }
    
    public String getMatrixText(){
        String answ ="";
        answ = answ.concat(size+"\n");
        answ = answ.concat(size+"\n");
        answ = answ.concat(init.y+","+init.x+"\n");
        answ = answ.concat(fin.y+","+fin.x+"\n");
        for(int i=0; i<size; i++){
            for (int j = 0; j < size; j++) {
                if(matrix[i][j]){
                    answ = answ.concat("1 ");
                }
                else{
                    answ = answ.concat("0 ");
                }
            }
            answ = answ.concat("\n");
        }
        return answ;
    }
    
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            MatrixGenerator generator = new MatrixGenerator(15);
            System.out.println(generator.getMatrixText());
        }
        
    }
}
