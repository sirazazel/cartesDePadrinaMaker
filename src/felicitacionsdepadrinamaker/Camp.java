/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

/**
 *
 * @author Francesc Mut Mollà
 */
public class Camp {
    final static int MAX = 30;
    private char[] contingut;
    private int longitud;
    
    /**
     * Constructor.
     * 
     */
    public Camp(){
        contingut = new char[MAX];
        longitud = 0;
    }
    
    public Camp(char[] contingut, int longitud){
        this.contingut = contingut;
        this.longitud = longitud;
    }
    
    public char[] contingut(){
        return contingut;
    }
    
    public int length(){
        return longitud;
    }
    
    /**
     * Retornam char[] com String legible.
     * @return
     */
    @Override
    public String toString(){
        return String.valueOf(contingut);
    }
    
    public static Camp nouCamp(){
        Camp camp = new Camp();
        return camp;
    }
    
    /**
     * Llegeix entrada de teclat i retorna un objecte Camp.
     * @return 
     */
    public static Camp nouCampTeclat(){
        char c;
        Camp camp = new Camp();
        
        c = LT.readChar();
        for(int i = 0; i < MAX; i++){
            if(c != '\n'){
                camp.afegeixLletra(c);
                c = LT.readChar();
            }
        }
                
        return camp;        
    }  
    
    /**
     * Afegeix una lletra a un objecte Camp, si hi cap.
     * 
     * @param c, lletra a afegir
     */
    public void afegeixLletra(char c){
        if(longitud+1 < 30){
            contingut[longitud] = c;
            longitud++;
        }
    }
    
    
    public String retalla(){
        char[] arrayRetallada = new char[longitud];
        
        for(int i = 0; i < length(); i++){
            arrayRetallada[i] = contingut[i];
        }
        
        return String.valueOf(arrayRetallada);
    }
    
    /**
     * Comprova si la paraula actual és igual a la cerca.
     * L'algorisme recorr cada un dels valors de l'array cercant la primera 
     * diferència.
     * @param b
     * @return true o false.
     */
    public boolean compara(Camp b){
        boolean sonIguals = longitud == b.longitud;
        
        for(int i = 0; i < longitud && sonIguals; i++){
            sonIguals = contingut[i] == b.contingut[i];
        }
        return sonIguals;
    }
    
    /**
     * Compara si dues paraules són iguals.
     * @param a
     * @param b
     * @return 
     */
    public static boolean compara(Camp a, Camp b){
        return a.compara(b);
    }
    
}
