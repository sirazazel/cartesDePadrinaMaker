/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.FileNotFoundException;

/**
 *
 * @author Francesc Mut Mollà
 */
public class Directori {
    final static int MAX = 64;
    final static char ESP = ' ';
    
    //Atributs de classe
    private Contacte[] contactes;
    private int index;
    
    
    /**
     * Constructor. Crea un nou directori buit.
     */
    public Directori(){
        contactes = new Contacte[64];
        index = 0;
    }
    
    public Contacte[] accedirDirectori(){
        return contactes;
    }
    
    public int length(){
        return index;
    }
    /**
     * Escriu al directori un nou contacte. Primer llegeix el fitxer, suma un 
     * contacte al final si hi cap, i aumenta l'índex.
     * @param c
     * @throws Exception
     */
    public static void escriuAlDirectori(Contacte c) throws Exception{
        Directori d = llegeixDirectori();
        try{
            if(d.index < MAX){
                boolean afegeix = true;
                FitxerCampSortida agenda = new FitxerCampSortida("fitxers/agenda.txt", afegeix);
                //Funció escriure al fitxer contactes.
                agenda.afegeixLiniaNova();
                agenda.escriuCamp(c.name());
                agenda.afegeixSeparador();
                agenda.escriuCamp(c.lastName());
                agenda.afegeixSeparador();
                agenda.escriuCamp(c.email());
                agenda.afegeixSeparador();
                agenda.escriuCamp(c.phone());
                agenda.tanca();
                System.out.println("Contacte afegit correctament amb índex " 
                                    + d.index);
                d.index++;
            } else {
                System.out.println("Directori ple.");
            }
        } catch (FileNotFoundException e){
            System.out.println("Fitxer no trobat.");
        }
        
    }
        
    /**
     * Llegeix (o refresca) el directori del fitxer a la memòria. (poc eficient, no?)
     * @return d Directori de contactes des del fitxer.
     * @throws java.lang.Exception
     */
    public static Directori llegeixDirectori() throws Exception {
        Directori d = new Directori();
        
        try{
            FitxerCampEntrada agenda = new FitxerCampEntrada("fitxers/agenda.txt");
            //System.out.println("Fitxer trobat. Actualitzant directori..");
            
            // Directori en memòria
            Camp name, lastName, email, phone;
            Contacte contacte;
            
            while(agenda.quedenCamps()){
                //Inicialitzam contacte nou amb nous atributs llegits
                name = agenda.nouCampFitxer();
                lastName = agenda.nouCampFitxer();
                email = agenda.nouCampFitxer();
                phone = agenda.nouCampFitxer();
                contacte = new Contacte(name, lastName, email, phone);
                //Guardam contacte a la memòria, si hi cap.)
                if(d.index < 64){
                    d.contactes[d.index] = contacte;    
                    //Sumam 1 a l'índex
                    d.index++;
                }
            }
            //System.out.println("S'han actualitzat " + d.index + " contactes des del fitxer agenda.");
            agenda.tanca();
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer no trobat.");
        }
        return d;
    }
    
    /**
     * Donada una cerca passada com a array de caràcters, retornam un directori de contactes.
     * @param llista
     * @return
     */
    public static Directori processaDirectori(char[] llista){
        Directori d = new Directori();
        Camp camp = Camp.nouCamp();
        Contacte contacte;
        
        for(char c: llista){
            if(c == ESP){
                //Cream contacte i l'assignam al directori de cerca.
                contacte = new Contacte(camp);
                d.contactes[d.index] = contacte;
                d.index++;
                //Buidam el camp
                camp = Camp.nouCamp();
            } else {
                camp.afegeixLletra(c);
            }
        }        
        
        //Assignam darrer contacte al directori de cerca.
        contacte = new Contacte(camp);
        d.contactes[d.index] = contacte;
        d.index++;
        
        return d;
    }
    
    

    
}
