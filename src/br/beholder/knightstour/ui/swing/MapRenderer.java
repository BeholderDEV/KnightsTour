/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.ui.swing;

import br.beholder.knightstour.core.model.Mapa;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author lite
 */
public class MapRenderer extends JComponent{

    static Image tile_set;
    static Image cat_sprite;
    static ImageObserver observer;
    static int tile_size = 64;
    static int base = 500;
    static private MapRenderer mapRenderer;

    private MapRenderer() {
        try {
            tile_set =  ImageIO.read(getClass().getResource("/br/beholder/knightstour/ui/resources/tileset.png"));
            cat_sprite =  ImageIO.read(getClass().getResource("/br/beholder/knightstour/ui/resources/cat.png"));
            observer= new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return true;
                }
            };
        } catch (Exception ex) {
            Logger.getLogger(MapRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MapRenderer getInstance() {
        if(mapRenderer==null){
            mapRenderer=new MapRenderer();
        }
        return mapRenderer;
    }
    
    public Image getFirstImage(Mapa mapa) {
        int maxcx = mapa.getMatrix()[0].length*tile_size/2;
        int maxcy = mapa.getMatrix().length*tile_size/2;
        int maxix = maxcx - maxcy;
        int maxiy = Math.round((maxcx + maxcy) / 2f);
        int alt = maxiy+tile_size;
        maxcx = mapa.getMatrix()[0].length*tile_size/2;
        maxcy = 0*tile_size/2;
        maxix = maxcx - maxcy;
        maxiy = Math.round((maxcx + maxcy) / 2f);
        base = mapa.getMatrix()[0].length*tile_size/2;
        int larg = base+maxix+tile_size;
        BufferedImage bi = new BufferedImage(larg,alt, BufferedImage.TYPE_4BYTE_ABGR);
        
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        
        g2d.setColor(Color.DARK_GRAY);
        int[][] matrix = mapa.getMatrix();
        for (int i = 0; i < mapa.getTamanho().height; i++) {
            for (int j = 0; j < mapa.getTamanho().width; j++) {
                int cartX = Math.round(j*tile_size/2f);
                int cartY =  Math.round(i*tile_size/2f);
                int isoX = cartX - cartY;
                int isoY = Math.round((cartX + cartY) / 2f);                
                g2d.drawImage(tile_set, base+isoX, isoY, base+isoX+tile_size, isoY+tile_size, matrix[i][j]*tile_size, 0, matrix[i][j]*tile_size+tile_size, tile_size, observer);
                
                
                if(i==mapa.getPontoInicial().y && j==mapa.getPontoInicial().x){
                    g2d.drawImage(tile_set, base+isoX, isoY, base+isoX+tile_size, isoY+tile_size, 2*tile_size, 0, 2*tile_size+tile_size, tile_size, observer);
                }
                
//                g2d.drawRect(j*tile_size, i*tile_size, tile_size, tile_size);
            }
        }
        //JOptionPane.showMessageDialog(null,new ImageIcon(bi));
        return bi;
    }
    public Image getStepImage(Mapa mapa) {
        int maxcx = mapa.getMatrix()[0].length*tile_size/2;
        int maxcy = mapa.getMatrix().length*tile_size/2;
        int maxix = maxcx - maxcy;
        int maxiy = Math.round((maxcx + maxcy) / 2f);
        int alt = maxiy+tile_size;
        maxcx = mapa.getMatrix()[0].length*tile_size/2;
        maxcy = 0*tile_size/2;
        maxix = maxcx - maxcy;
        maxiy = Math.round((maxcx + maxcy) / 2f);
        base = mapa.getMatrix()[0].length*tile_size/2;
        int larg = base+maxix+tile_size;
        BufferedImage bi = new BufferedImage(larg,alt, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        
        g2d.setColor(Color.DARK_GRAY);
        int[][] matrix = mapa.getMatrix();
        for (int i = 0; i < mapa.getTamanho().height; i++) {
            for (int j = 0; j < mapa.getTamanho().width; j++) {
                int cartX = Math.round(j*tile_size/2f);
                int cartY =  Math.round(i*tile_size/2f);
                int isoX = cartX - cartY;
                int isoY = Math.round((cartX + cartY) / 2f);

            }
        }
        JOptionPane.showMessageDialog(null,new ImageIcon(bi));
        return bi;
    }
    
    public Image getPathedImage(Mapa mapa) {
        int maxcx = mapa.getMatrix()[0].length*tile_size/2;
        int maxcy = mapa.getMatrix().length*tile_size/2;
        int maxix = maxcx - maxcy;
        int maxiy = Math.round((maxcx + maxcy) / 2f);
        int alt = maxiy+tile_size;
        maxcx = mapa.getMatrix()[0].length*tile_size/2;
        maxcy = 0*tile_size/2;
        maxix = maxcx - maxcy;
        maxiy = Math.round((maxcx + maxcy) / 2f);
        base = mapa.getMatrix()[0].length*tile_size/2;
        int larg = base+maxix+tile_size;
        BufferedImage bi = new BufferedImage(larg,alt, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        
        g2d.setColor(Color.DARK_GRAY);
        int[][] matrix = mapa.getMatrix();
        for (int i = 0; i < mapa.getTamanho().height; i++) {
            for (int j = 0; j < mapa.getTamanho().width; j++) {
                int cartX = Math.round(j*tile_size/2f);
                int cartY =  Math.round(i*tile_size/2f);
                int isoX = cartX - cartY;
                int isoY = Math.round((cartX + cartY) / 2f);
            }
        }
        return bi;
    }
    
}
