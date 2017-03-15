/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.knightstour.core.reader;

import br.beholder.knightstour.core.model.Mapa;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author lite
 */
public class PFReader {
    
    public static Mapa lerPF(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Arquivo PF", "pf");
        fileChooser.setFileFilter(extensionFilter);
        File file = null;
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader (file));
                String line = null;
                line = reader.readLine();
                int linhas = Integer.parseInt(line);
                line = reader.readLine();
                int colunas = Integer.parseInt(line);
                Dimension tamanho = new Dimension(colunas, linhas);
                line = reader.readLine();
                String[] pos = line.split(",");
                Point inicial = new Point(Integer.parseInt(pos[1]), Integer.parseInt(pos[0]));
                line = reader.readLine();
                pos = line.split(",");
                Point fina = new Point(Integer.parseInt(pos[1]), Integer.parseInt(pos[0]));
                String[] linha;
                int blockedsCount=0;
                int[][] matriz = new int[linhas][colunas];
                for (int i = 0; i < linhas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        matriz[i][j]=0;
                    }
                }
                for (int i = 0; i < linhas; i++) {
                    line = reader.readLine();
                    linha = line.split(" ");
                    for (int j = 0; j < colunas; j++) {
                        if(Integer.parseInt(linha[j])==1){
                            blockedsCount++;
                        }
                        matriz[i][j]=Integer.parseInt(linha[j]);
//                        blocked[i][j]=Boolean.parseBoolean(linha[j]);
                    }
                }
                int[][] blocked = new int[blockedsCount][2];
                int k=0;
                for (int i = 0; i < linhas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        if(matriz[i][j]==1){
                            blocked[k][0]=i;
                            blocked[k][1]=j;
                            k++;
                        }
                    }
                }
                Mapa mapa= new Mapa(tamanho, matriz, blocked, inicial, fina);
                return mapa;
            } catch (IOException ex) {
                Logger.getLogger(PFReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
