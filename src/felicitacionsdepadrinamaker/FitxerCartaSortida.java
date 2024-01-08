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
public class FitxerCartaSortida {
    //Atributs
    private final FileWriter file;
    private final BufferedWriter buffer;
    
    //Constants
    private static final char CR = '\r';
    private static final char LF = '\n';
    private static final int NUM = (int) '#';
    private static final char NUL = '\0';
    
    private static final String NAME = "NAME";
    private static final String LASTNAME = "LASTNAME";
    private static final String PHONE = "PHONE";
    private static final String EMAIL = "EMAIL";
    
    private static final Camp NAMEFIELD = new Camp(NAME.toCharArray(), 4);
    private static final Camp LASTNAMEFIELD = new Camp(LASTNAME.toCharArray(), 4);
    private static final Camp PHONEFIELD = new Camp(PHONE.toCharArray(), 4);
    private static final Camp EMAILFIELD = new Camp(EMAIL.toCharArray(), 4);
    
    /**
     * Constructor
     * 
     * @param path
     * @throws java.lang.Exception
     */
    public FitxerCartaSortida(String path) throws Exception {
        file = new FileWriter(path);
        buffer = new BufferedWriter(file);
    }
    
    public void afegeix(int caracter) throws Exception {
        buffer.write(caracter);
    }
    
    public void substitueix(Camp camp, Contacte contacte) throws Exception {
        if (camp.compara(NAMEFIELD)){
            for(char c: contacte.name().contingut()){
                this.afegeix((int) c);
            }
        } else if (camp.compara(LASTNAMEFIELD)) {
            for(char c: contacte.lastName().contingut()){
                this.afegeix((int) c);
            }
        } else if (camp.compara(PHONEFIELD)) {
            for(char c: contacte.phone().contingut()){
                this.afegeix((int) c);
            }
        } else if (camp.compara(EMAILFIELD)) {
            for(char c: contacte.email().contingut()){
                this.afegeix((int) c);
            }
        }
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
