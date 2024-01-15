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
public class FitxerEntrada {
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
    public FitxerEntrada(String path) throws Exception {
        file = new FileReader(path);
        buffer = new BufferedReader(file);
    }
    
    /*
     * Mètodes útils
     */
    
    /**
     * Detecta si és el final del fitxer
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
    
    /*
     * MÈTODES DE GENERACIÓ DE CAMP
     */
    
    /**
     * Genera un camp a partir de la lectura d'un fitxer.
     * @return camp Camp creat a partir de teclat
     * @throws Exception 
     */
    public Camp nouCampFitxer() throws Exception {
        Camp camp = new Camp();
        //Posicionam al primer caràcter vàlid.
        posicionaSeguentCamp();
        
        while(caracter != NUM && caracter != LF && caracter != CR && caracter != EOF){
            camp.afegeixLletra((char) caracter);
            caracter = buffer.read();
        }
        
        return camp;
    }   
    
    /**
     * Posa el caràcter punter a la primera lletra del següent camp.
     * @throws Exception 
     */
    private void posicionaSeguentCamp() throws Exception {
        while(caracter == NUM || caracter == LF || caracter == CR || caracter == SPA){
            caracter = buffer.read();
        }
    }
    
    /**
     * Indica si queden objectes Camp al fitxer.
     * @return boolean true si el caràcter és diferent de '-1' i comença un camp
     * @throws Exception
     */
    public boolean quedenCamps() throws Exception {
        posicionaSeguentCamp();
        return (caracter != EOF);
    }
    
    /*
     * MÈTODES DE GENERACIÓ DE CARTA  
     */
    
    /**
     * Llegeix una carta de la plantilla passada per paràmetre i l'envia al 
     * búfer de sortida.
     * 
     * Si troba un numeral, entra en mode Variable per detectar què s'ha 
     * insertat i canviar-ho pel camp conseqüent del contacte passat per 
     * paràmetre.
     * @param c
     * @param ruta
     * @throws Exception
     */
    public void generaDesdePlantilla(Contacte c, String ruta) throws Exception {
        char[] contingutVariable = null;
        boolean modeVariable = false;
        int longitudVariable = 0;
        Camp campVariable;
        String email = c.email().retalla();
        String rutaFitxer;
        
        caracter = buffer.read();
        try{
            //Obrim fitxer d'escriptura.
            rutaFitxer = ruta + email + ".html";            
            FitxerSortida cardOut = new FitxerSortida(rutaFitxer);
                        
            while (quedenLletres()){
                if (caracter == NUM && modeVariable == false){
                    contingutVariable = new char[30];
                    modeVariable = true;
                    caracter = buffer.read();
                } else  if (caracter != NUM && modeVariable == true) {
                    contingutVariable[longitudVariable] = (char) caracter;
                    longitudVariable++;
                    caracter = buffer.read();
                } else if (caracter == NUM && modeVariable == true) {
                    campVariable = new Camp(contingutVariable, longitudVariable);
                    modeVariable = false;
                    cardOut.substitueixVariable(campVariable, c);
                    //Reiniciam
                    caracter = buffer.read();
                    contingutVariable = new char[30];
                    longitudVariable = 0;
                } else {
                    cardOut.afegeix(caracter);
                    caracter = buffer.read();
                }
            }
            cardOut.tanca();
            System.out.println("Carta per a " + c.name() + " " +  c.lastName() 
                                + " generada correctament.");
        } catch (NumberFormatException e) {
            System.out.println("Error");
        }
    }
    
    /*
     * Mètodes per a eliminar contactes.
     */

    /**
     *
     * @param complet
     * @throws Exception
     */
    public void elimina(Contacte complet, String sortida) throws Exception{
        Camp name, lastName, email, phone;
        FitxerSortida agendaTemporal = new FitxerSortida(sortida);
        
        //Llegim línia fitxer i generam contacte.
        while(quedenLletres()){
            name = nouCampFitxer();
            lastName = nouCampFitxer();
            email = nouCampFitxer();
            phone = nouCampFitxer();
            Contacte temporal = new Contacte(name, lastName, email, phone);
            
            if(!temporal.compara(complet)){
                agendaTemporal.escriuContacte(temporal);
            }
        }
        agendaTemporal.tanca();
    }
    
    /**
     *
     * @param complet
     * @throws Exception
     */
    public void eliminaCamp(Camp correu, String sortida) throws Exception{
        Camp campLlegit;
        FitxerSortida agendaTemporal = new FitxerSortida(sortida);
        
        //Llegim línia fitxer i generam contacte.
        while(quedenLletres()){
            campLlegit = nouCampFitxer();
            
            if(!campLlegit.compara(correu)){
                agendaTemporal.escriuCamp(campLlegit);
                agendaTemporal.afegeixLiniaNova();
            }
        }
        agendaTemporal.tanca();
    }
}