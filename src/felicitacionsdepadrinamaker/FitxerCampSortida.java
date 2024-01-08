/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Francesc Mut Mollà
 */
public class FitxerCampSortida {
    
    //Atributs
    private final FileWriter file;
    private final BufferedWriter buffer;
    
    //Constants
    private static final char CR = '\r';
    private static final char LF = '\n';
    private static final int NUM = (int) '#';
    private static final char NUL = '\0';
    
    /**
     * Constructor
     * 
     * @param path
     * @throws java.lang.Exception
     */
    public FitxerCampSortida(String path) throws Exception {
        file = new FileWriter(path);
        buffer = new BufferedWriter(file);
    }
    
    /**
     * Constructor amb paràmetre afegeix.
     * S'empra per a afegir al final del fitxer en comptes de sobreescriure.
     * @param path
     * @param append
     * @throws Exception
     */
    public FitxerCampSortida(String path, boolean append) throws Exception {
        file = new FileWriter(path, append);
        buffer = new BufferedWriter(file);
    }
    
    /**
     * Escriu un camp al búfer. Bota els caràcters buids si en troba.
     * @param c
     * @throws java.lang.Exception
     */
    public void escriuCamp(Camp c) throws Exception {
        for(char caracter: c.contingut()){
            if(caracter != NUL){
                buffer.write(caracter);
            }
        }
    }
           
    /**
     * Afegeix una línia nova al fitxer existent.
     * Pareix que no és necessari?
     * @throws java.lang.Exception
     */
    public void afegeixLiniaNova() throws Exception {
        buffer.append(CR);
        buffer.append(LF);
    }
    
    /**
     * Afegeix un separador al fitxer.
     * @throws Exception
     */
    public void afegeixSeparador() throws Exception {
        buffer.write(NUM);
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
