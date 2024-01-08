/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package felicitacionsdepadrinamaker;

/**
 *Aquest programa genera cartes de felicitació en format html i les guarda 
 * al directori donat. 
 * 
 * Les cartes es generen de manera dinàmica, agafant les dades del contacte
 * des d'un directori, guardat a un fitxer de text separat per numerals.
 * 
 * Aquest directori és modificable, via una opció al menú principal. Es pot 
 * afegir, eliminar i consultar un contacte, afegir, consultar i eliminar una 
 * llista de distribució, veure els contactes i veure les llistes.
 * 
 * @author Francesc Mut Mollà
 */
public class FelicitacionsDePadrinaMaker {

    private static boolean end = false;
    
    /**
     * Menú principal del programa. 
     * 
     * Selector de funcions. 
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        int fSelect;
        
        while(end == false){
            System.out.println("Menú principal.");
            System.out.println("""
                               1.Generar
                               2.Agenda
                               3.Llistes
                               0.Sortir
                               """);
            try{
                fSelect = LT.readInt();
                switch(fSelect){
                    case 1 -> Carta.menuCarta();
                    case 2 -> administraAgenda();
                    case 3 -> administraLlistes();
                    case 0 -> acaba();
                }
            } catch (NumberFormatException e) {
                System.out.println("Selecció invàlida");
            }
        }
    }
        
    /**
     * Submenús. 
     */
    static void administraAgenda() throws Exception {
        int fSelect;
        
        System.out.println("Administrar agenda.");
        System.out.println("""
                           1.Contacte nou
                           2.Consulta
                           3.Eliminar contacte
                           0.Tornar enrere
                           """);
        try{
            fSelect = LT.readInt();
            switch(fSelect){
               case 1 -> Contacte.nouContacte();
               case 2 -> seleccioContacte();
               case 3 -> Contacte.eliminaContacte();
               case 0 -> enrere();
            }
        } catch (NumberFormatException e) {
            System.out.println("Selecció invàlida");
            administraAgenda();
        }
    }
    
    static void seleccioContacte() throws Exception {
        int fSelect;
        
        System.out.println("Vols mostrar un contacte o la llibreta sencera?");
        System.out.println("""
                           1. Un contacte.
                           2. Tota la llibreta.
                           0. Tornar enrere.
                           """);
        
        try {
            fSelect = LT.readInt();
            switch(fSelect){
                case 1 -> Contacte.mostraContacte();
                case 2 -> Contacte.mostraContactes();
                case 0 -> enrere();
            }  
        } catch (NumberFormatException e) {
            System.out.println("Selecció invàlida"); 
            seleccioContacte();
        }
    }
    
    static void administraLlistes(){
        int fSelect;
        
        System.out.println("Vols mostrar una llista o la llibreta sencera?");
        System.out.println("""
                           1. Una llista.
                           2. Tota la llibreta.
                           0. Tornar enrere.
                           """);
        
        try{
            fSelect = LT.readInt();
            switch(fSelect){
                case 1 -> Contacte.mostraLlista();
                case 2 -> Contacte.mostraLlistes();
                case 0 -> enrere();
            }
        } catch (NumberFormatException e) {
            System.out.println("Selecció invàlida"); 
            administraLlistes();
        }
    }
    
    /**
     * Aquest mètode no fa res. S'empra per a tornar enrere als menús.
     */
    static void enrere(){
    }
    
    /**
     * Marca la variable de final per a acabar l'execució.
     */
    static void acaba(){
        end = true;
    }

}
