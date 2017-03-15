/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.reader;

import br.beholder.knightstour.core.model.Mapa;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author lite
 */
public class XMLReader {
    
    public static Mapa lerXML(){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
        fileChooser.setFileFilter(extensionFilter);
        File file = null;
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();                
                NodeList nodes = doc.getElementsByTagName("LINHAS");
                Node node = nodes.item(0);
                int linhas = Integer.parseInt(node.getTextContent());
                nodes = doc.getElementsByTagName("COLUNAS");
                node = nodes.item(0);
                int colunas = Integer.parseInt(node.getTextContent());
                Dimension tamanho = new Dimension(colunas, linhas);
                nodes = doc.getElementsByTagName("INICIAL");
                node = nodes.item(0);
                String ponto = node.getTextContent();
                String[] pos = ponto.split(",");
                Point inicial = new Point(Integer.parseInt(pos[1])-1, Integer.parseInt(pos[0])-1);
                nodes = doc.getElementsByTagName("FINAL");
                node = nodes.item(0);
                ponto = node.getTextContent();
                pos = ponto.split(",");
                Point fina = new Point(Integer.parseInt(pos[1])-1, Integer.parseInt(pos[0])-1);
                
                int[][] matriz = new int[linhas][colunas];
                for (int i = 0; i < linhas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        matriz[i][j]=0;
                    }
                }
                
                nodes = doc.getElementsByTagName("MURO");
                int [][] blocked = new int[nodes.getLength()][2];
                for (int i = 0; i < nodes.getLength(); i++) {
                    node = nodes.item(i);
                    ponto = node.getTextContent();
                    pos = ponto.split(",");
                    matriz[Integer.parseInt(pos[0])-1][Integer.parseInt(pos[1])-1]=1;
                    blocked[i][0]=Integer.parseInt(pos[0])-1;
                    blocked[i][1]=Integer.parseInt(pos[1])-1;
                }
                
                Mapa mapa = new Mapa(tamanho, matriz,blocked, inicial, fina);
                return mapa;
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return null;
    }
}
