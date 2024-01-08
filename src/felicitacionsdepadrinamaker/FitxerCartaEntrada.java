/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Francesc Mut Mollà
 */
public class FitxerCartaEntrada {
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
    public FitxerCartaEntrada(String path) throws Exception {
        file = new FileReader(path);
        buffer = new BufferedReader(file);
    }
    
    /**
     *
     * @param c
     * @throws Exception
     */
    public void generaDesdePlantilla(Contacte c, String ruta) throws Exception {
        char[] contingutVariable = null;
        boolean modeVariable = false;
        int longitudVariable = 0;
        Camp campVariable;
        String rutaFitxer, email;
        
        caracter = buffer.read();
        email = String.valueOf(c.email().contingut());
        
        try{
            //Obrim fitxer d'escriptura.
            rutaFitxer = ruta + email + ".html";
            System.out.println(rutaFitxer);
            File fileOut = new File ("test.html");
            
            FitxerCartaSortida cardOut = new FitxerCartaSortida("test.html");
                        
            while (quedenLletres()){
                if (caracter == NUM && modeVariable == false){
                    contingutVariable = new char[30];
                    modeVariable = true;
                    caracter = buffer.read();
                    System.out.println("\nMODE VARIABLE:" + modeVariable);
                } else  if (caracter != NUM && modeVariable == true) {
                    contingutVariable[longitudVariable] = (char) caracter;
                    longitudVariable++;
                    caracter = buffer.read();
                } else if (caracter == NUM && modeVariable == true) {
                    campVariable = new Camp(contingutVariable, longitudVariable);
                    System.out.print(campVariable);
                    modeVariable = false;
                    System.out.println("\n MODE VARIABLE:" + modeVariable);
                    cardOut.substitueix(campVariable, c);
                    //Reiniciam
                    caracter = buffer.read();
                    contingutVariable = new char[30];
                    longitudVariable = 0;
                } else {
                    System.out.print((char)caracter);
                    cardOut.afegeix(caracter);
                    caracter = buffer.read();
                }
            }
            cardOut.tanca();
        } catch (NumberFormatException e) {
            System.out.println("Error");
        }
    }
    
    /**
     *
     * @return True o false si no queden caràcters al fitxer.
     */
    public boolean quedenLletres(){
        return(caracter != EOF);
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
