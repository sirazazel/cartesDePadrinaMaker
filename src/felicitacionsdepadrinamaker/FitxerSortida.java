/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Francesc Mut Mollà
 */
public class FitxerSortida {
    
    //Atributs
    private final FileWriter file;
    private final BufferedWriter buffer;
    
    //Constants
    private static final char CR = '\r';
    private static final char LF = '\n';
    private static final int NUM = (int) '#';
    private static final char NUL = '\0';
    
    //Variables de plantilla
    private static final String NAME = "NAME";
    private static final String LASTNAME = "LASTNAME";
    private static final String PHONE = "PHONE";
    private static final String EMAIL = "EMAIL";
    
    private static final Camp NAMEFIELD = new Camp(NAME.toCharArray(), 4);
    private static final Camp LASTNAMEFIELD = new Camp(LASTNAME.toCharArray(), 8);
    private static final Camp PHONEFIELD = new Camp(PHONE.toCharArray(), 5);
    private static final Camp EMAILFIELD = new Camp(EMAIL.toCharArray(), 5);

    /**
     * Constructor
     * 
     * @param path
     * @throws java.lang.Exception
     */
    public FitxerSortida(String path) throws Exception {
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
    public FitxerSortida(String path, boolean append) throws Exception {
        file = new FileWriter(path, append);
        buffer = new BufferedWriter(file);
    }
    
    /*
     *  FUNCIONS
     */
    
    /**
     * Afegeix una línia nova al fitxer existent.
     * 
     * @throws java.lang.Exception
     */
    public void afegeixLiniaNova() throws Exception {
        buffer.newLine();
    }
    
    /**
     * Afegeix un nou caràcter al búfer.
     * @param caracter
     * @throws Exception 
     */
    public void afegeix(int caracter) throws Exception {
        buffer.write(caracter);
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
     * MÈTODES D'ESCRIPTURA DE CAMP
     */
    
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
     * Escriu un contacte nou al fitxer.
     * @param c
     * @throws Exception
     */
    public void escriuContacte(Contacte c) throws Exception {
        escriuCamp(c.name());
        afegeixSeparador();
        escriuCamp(c.lastName());
        afegeixSeparador();
        escriuCamp(c.email());
        afegeixSeparador();
        escriuCamp(c.phone());
        afegeixSeparador();
        afegeixLiniaNova();
    }
    
    /**
     * Afegeix un separador de camp al fitxer.
     * @throws Exception
     */
    public void afegeixSeparador() throws Exception {
        buffer.write(NUM);
    }
    
    public static void eliminaFitxer(String ruta) throws Exception {
        try {
            File file = new File(ruta);
            file.delete();
        } catch (Exception e) {
            System.out.println("Error desconegut eliminant el fitxer.");
        }
    }
    
    public static boolean comprovaFitxer(String ruta) throws Exception {
        File file = new File(ruta);
        return file.exists();
    }
    
    /*
     * MÈTODES D'ESCRIPTURA DE CARTA
     */
    
    public void substitueixVariable(Camp camp, Contacte contacte) throws Exception {
        if (camp.compara(NAMEFIELD)){   
            escriuCamp(contacte.name());
        } else if (camp.compara(LASTNAMEFIELD)) {
            escriuCamp(contacte.lastName());
        } else if (camp.compara(PHONEFIELD)) {
            escriuCamp(contacte.phone());
        } else if (camp.compara(EMAILFIELD)) {
            escriuCamp(contacte.email());
        }
    }
    
    /*
     * Funcions de generació de llistes de distribució.
     */
    
    public void creaLlista(Directori directori) throws Exception {
        for(Contacte c: directori.accedirDirectori()){
            if(c != null){
                escriuCamp(c.email());
                afegeixLiniaNova();
            }
        }
    }
    
    public static void afegeixContacte(String ruta) throws Exception {
        boolean afegeix = true;
        
        System.out.print("Escriu el correu electrònic del contacte que vols afegir: ");
        Camp cerca = Camp.nouCampTeclat();
        Contacte complet = Contacte.cercaPrecisa(cerca);
        
        if(complet.existeix()){ 
            System.out.println("Contacte " + cerca.toString() + " trobat. Afegim...");
            FitxerSortida llista = new FitxerSortida(ruta, afegeix);
            llista.escriuCamp(complet.email());
            llista.afegeixLiniaNova();
            llista.tanca();            
        } else {
            System.out.println("Contacte " + cerca.toString() + " no trobat.");
        }
    }
}
