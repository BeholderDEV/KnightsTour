/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.control;

import br.beholder.knightstour.core.algoritmos.BackTracking;
import br.beholder.knightstour.core.algoritmos.BackTracking2;
import br.beholder.knightstour.core.algoritmos.KnightsTour;
import br.beholder.knightstour.core.algoritmos.Warnsdorf;
import br.beholder.knightstour.core.model.Coords;
import br.beholder.knightstour.core.model.Mapa;
import br.beholder.knightstour.ui.MainPanel;
import br.beholder.knightstour.ui.swing.MapRenderer;
import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;

/**
 *
 * @author lite
 */
public class NormalPathController {

    private Mapa mapa;
    private MainPanel mainPanel;
    private String tipo;
    private boolean mapaFinalizado = false;
    private boolean threadExecucao = false;
    
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
    
    public long getTime(){
        return mainPanel.getTime();
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Mapa getMap(){
        return mapa;
    }

    public void calculate() {
        if(this.threadExecucao || mainPanel.getBoardSize() < 5){
            return;
        }
        if(this.mapaFinalizado){
            this.createMap(this.mainPanel.getBoardSize());
        }
        if (mapa != null) {
            KnightsTour kt;
            if(mainPanel.getComboBoxAlgoritmo().getSelectedIndex() == 0){
                kt = new BackTracking(mainPanel.getBoardSize(), this);
            }else if (mainPanel.getComboBoxAlgoritmo().getSelectedIndex() == 1){
                kt = new BackTracking2(mainPanel.getBoardSize(), this);
            }else{
                kt = new Warnsdorf(mainPanel.getBoardSize(), this);
            }
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadExecucao = true;
                    kt.findPath();
                    mapaFinalizado = true;
                    threadExecucao = false;
                }
            });
            t.start();
        }
    }

    public void calculateStep() {
        if (mapa != null) {
            
        }
    }

    public void stepImage(Image imagem) {
        setPathImage(imagem);
    }
    
    public void createMap(Integer size)
    {
        if(this.threadExecucao){
            return;
        }
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
        mapa = new Mapa(new Dimension(size, size), mat, new Point(size / 2, size / 2));
        Image image = MapRenderer.getInstance().getFirstImage(mapa);
        setPathImage(image);
        String str="";
        str = str.concat("Tamanho: " + size + "x" + size);
        mainPanel.getConsoleArea().setText(str);
        this.mapaFinalizado = false;
    }
    
    public void randomMap()
    {
        if(this.threadExecucao){
            return;
        }
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
        mainPanel.setBoardSize(size);
        mapa = new Mapa(new Dimension(size, size), mat, initial);
        Image image = MapRenderer.getInstance().getFirstImage(mapa);
        setPathImage(image);
        String str="";
        str = str.concat("Mapa Criado \n");
        str = str.concat("Tamanho: " + size + "x" + size);
        str = str.concat("\n Inicio: X-" + initial.getX() + " Y-"+initial.getY());
        
        mainPanel.getConsoleArea().setText(str);
        this.mapaFinalizado = false;
    }
    
    public void appendTextConsole(String text){
        this.mainPanel.getConsoleArea().append(text);
    }
}
