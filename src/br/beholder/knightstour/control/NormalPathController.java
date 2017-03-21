/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.control;

import br.beholder.knightstour.core.model.Cell;
import br.beholder.knightstour.core.model.Mapa;
import br.beholder.knightstour.ui.MainPanel;
import br.beholder.knightstour.ui.swing.MapRenderer;
import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;

/**
 *
 * @author lite
 */
public class NormalPathController {

    Mapa mapa;
    MainPanel mainPanel;
    String tipo;

    private void setPathImage(Image imagem) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WebImage webImage = new WebImage(imagem);
                webImage.setDisplayType(DisplayType.fitComponent);
                mainPanel.getImagePane().removeAll();
                mainPanel.getImagePane().add(webImage);
                mainPanel.getImagePane().revalidate();
                mainPanel.getImagePane().repaint();
            }
        });

    }

    public NormalPathController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void calculate() {
        if (mapa != null) {
            if (mapa.getPathMatrix() != null) {
                Image image = MapRenderer.getInstance().getPathedImage(mapa);
                setPathImage(image);
            }
        }
    }

    public void calculateStep() {
        if (mapa != null) {
            
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    if(mapa.getPathMatrix()!=null){
                        Image image= MapRenderer.getInstance().getPathedImage(mapa);
                        setPathImage(image);
                    }
                }
            });
            t.start();
            
        }
    }

    public void stepImage(boolean[][] grid) {
        Image imagem = MapRenderer.getInstance().getStepImage(mapa, grid);
        setPathImage(imagem);
    }
    public void stepImage(boolean[][] closed, Cell[][] grid) {
        Image imagem = MapRenderer.getInstance().getStepImage(mapa, closed,grid);
        setPathImage(imagem);
    }
    
    public void createMap(Integer size, Point initial)
    {
        int[][] mat = new int[size][size];        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if((i + j) % 2 == 0){
                   mat[i][j] = 4;
                }
                else{
                    mat[i][j] = 5;
                }
            }
        }        
        mapa = new Mapa(new Dimension(size, size), mat, initial);
        Image image = MapRenderer.getInstance().getFirstImage(mapa);
        setPathImage(image);
        String str="";
        str = str.concat("Mapa Criado \n");
        str = str.concat("Tamanho: " + size + "x" + size);
        str = str.concat("\n Inicio: X-" + initial.getX() + " Y-"+initial.getY());
        
        mainPanel.getConsoleArea().setText(str);
    }
    
    public void randomMap()
    {
        int size = ThreadLocalRandom.current().nextInt(5, 50);
        int[][] mat = new int[size][size];        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if((i + j) % 2 == 0){
                   mat[i][j] = 4;
                }
                else{
                    mat[i][j] = 5;
                }
            }
        }
        
        Point initial = new Point(ThreadLocalRandom.current().nextInt(size), ThreadLocalRandom.current().nextInt(size));
        
        mapa = new Mapa(new Dimension(size, size), mat, initial);
        Image image = MapRenderer.getInstance().getFirstImage(mapa);
        setPathImage(image);
        String str="";
        str = str.concat("Mapa Criado \n");
        str = str.concat("Tamanho: " + size + "x" + size);
        str = str.concat("\n Inicio: X-" + initial.getX() + " Y-"+initial.getY());
        
        mainPanel.getConsoleArea().setText(str);
    }
}
