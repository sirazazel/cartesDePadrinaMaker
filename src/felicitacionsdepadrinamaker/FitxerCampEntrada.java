/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Francesc Mut Mollà
 */
public class FitxerCampEntrada {
    //Atributs
    private static final int EOF = -1;
    private static final int CR = (int) '\r';
    private static final int LF = (int) '\n';
    private static final int SPA = (int) ' ';
    private static final int NUM = (int) '#';
    
    private int caracter = SPA;
    private final FileReader file;
    private final BufferedReader buffer;
    
    /**
     * Constructor
     * 
     * @param path Ruta del fitxer a l'ordinador
     * @throws Exception
     */
    public FitxerCampEntrada(String path) throws Exception {
        file = new FileReader(path);
        buffer = new BufferedReader(file);
    }
    
    /**
     * Genera un camp a partir de la lectura d'un fitxer.
     * @return camp Camp creat a partir de teclat
     * @throws Exception 
     */
    public Camp nouCampFitxer() throws Exception {
        Camp camp = new Camp();
        //Posicionam al primer caràcter vàlid.
        posiciona();
        
        while(caracter != NUM && caracter != LF && caracter != CR && caracter != EOF){
            camp.afegeixLletra((char) caracter);
            caracter = buffer.read();
        }
        
        return camp;
    }   
    
    /**
     * Posa el caràcter punter a la primera lletra del fitxer.
     * @throws Exception 
     */
    private void posiciona() throws Exception {
        while(caracter == NUM || caracter == LF || caracter == CR){
            caracter = buffer.read();
        }
    }
    
    /**
     *
     * @return boolean true si el caràcter és diferent de '-1'
     * @throws Exception
     */
    public boolean quedenCamps() throws Exception {
        posiciona();
        return (caracter != EOF);
    }
    
    /**
     * Tanca el fitxer.
     * @throws Exception 
     */
    public void tanca() throws Exception {
        buffer.close();
        file.close();
    }
}
