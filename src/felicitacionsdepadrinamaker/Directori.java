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
    private final static char ESP = ' ';
    
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
    
    /**
     * Retorna array de contactes d'aquest directori.
     * @return 
     */
    public Contacte[] accedirDirectori(){
        return contactes;
    }
    
    /**
     * Retorna el nombre de contactes (Comença per 0)
     * @return 
     */
    public int length(){
        return index;
    }
    
    /**
     * Afegeix el contacte donat a aquest directori.
     * @param c Contacte donat
     */
    public void afegeixContacte(Contacte c){
        this.contactes[index] = c;
        index++;
    }
              
    /**
     * Donada una cerca passada com a array de caràcters, retornam un directori de contactes.
     * @param llista Array de caràcters entrada per l'usuari a processar.
     * @return validat Directori de contactes validat.
     */
    public static Directori processaDirectori(char[] llista) {
        Directori d = new Directori();
        Directori validat;
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
        
        //Validam que tots els contactes existeixin al nostre fitxer agenda.
        validat = validaDirectori(d);
        
        return validat;
    }   
    
    /**
     * Cerca tots els contactes d'un directori donat i valida si existeixen a la 
     * llista de contactes, o bé son una llista de distribució.
     * Si no existeixen, continua.
     * @param d
     * @return
     */
    public static Directori validaDirectori(Directori d) {
        Directori validat = new Directori();
        Contacte cerca;

        for(Contacte c: d.contactes){
            try{
                cerca = Contacte.cercaPrecisa(c.email());
                if(cerca.existeix()){
                    validat.afegeixContacte(cerca);
                } else if(c.email().esLlista()) {
                    c.marcaLlista();
                    validat.afegeixContacte(c);
                }
            } catch (Exception e) {
                // Fitxer agenda no trobat.
            }
        }
        return validat;
    }
}
