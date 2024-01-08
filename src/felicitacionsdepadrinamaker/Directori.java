/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

/**
 *
 * @author Francesc Mut Mollà
 */
public class Directori {
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
