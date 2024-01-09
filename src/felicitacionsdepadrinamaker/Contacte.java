/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.FileNotFoundException;

public class Contacte {
    //Camps d'un contacte
    private Camp name;
    private Camp lastName;
    private Camp email;
    private Camp phone;
    
    /**
     * Constructor objecte Contacte.
     * @param name, nom de pila del contacte
     * @param lastName, llinatges del contacte
     * @param email, correu electrònic del contacte
     * @param phone, telèfon del contacte
     */
    public Contacte(Camp name, Camp lastName, Camp email, Camp phone){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    /**
     * Constructor objecte contacte amb només email.
     * S'empra per a cercar usuaris.
     * @param email
     */
    public Contacte(Camp email){
        this.email = email;
    }

    /**
     * Constructor de contacte no trobat
     * 
     */
    public Contacte(){
       email = new Camp(); 
    }
    
    /**
     * Retorna string formatejada per a mostrar un contacte.
     * @return
     */
    @Override
    public String toString(){

        
        
        
        return "------CONTACTE ------\n" +   
              name + " " + lastName + "\n" + 
              "Tel: " + phone + "\n" + 
              "Correu: \n"+email + "\n" + 
               "---------------------"
               ;
    }
    
    /**
     * Mètodes per a accedir als atributs d'un contacte.
     * @return Objecte Camp de l'atribut demanat 
     */
    public Camp name(){
        return name;
    }
    public Camp lastName(){
        return lastName;
    }
    public Camp email(){
        return email;
    }
    public Camp phone(){
        return phone;
    }
    
    /**
     * Mètode per a crear un nou contacte via informacions passades pel teclat.
     * Static, ja que és una funció que s'ha de cridar sense haver inicialitzat 
     * un nou contacte.
     * @throws java.lang.Exception
     */
    public static void nouContacte() throws Exception{
        // Atributs d'un contacte 
        Camp name, lastName, email, phone;
        
        // Inicialitzam objecte contacte
        Contacte c;
        
        // Menú creació contacte nou
        blanc();
        System.out.println("Menu de creacio de contacte nou.");
        System.out.print("Nom -> ");
        name = Camp.nouCampTeclat();
        System.out.print("Llinatges -> ");
        lastName = Camp.nouCampTeclat();
        System.out.print("Correu electronic -> ");
        email = Camp.nouCampTeclat();
        System.out.print("Telefon -> ");
        phone = Camp.nouCampTeclat();
                
        c = new Contacte(name,lastName,email,phone);
        
        guardaAlFitxer(c);
        
        System.out.println("Pitja enter per acabar...");
        char stop = LT.readChar();

    }    


    /**
     * Escriu al directori un nou contacte. Primer llegeix el fitxer, suma un 
     * contacte al final si hi cap, i aumenta l'índex.
     * @param c
     * @throws Exception
     */
    public static void guardaAlFitxer(Contacte c) throws Exception{
        try{
            boolean afegeix = true;
            FitxerSortida agenda = new FitxerSortida("fitxers/agenda.txt", afegeix);
            agenda.afegeixLiniaNova();
            agenda.escriuCamp(c.name());
            agenda.afegeixSeparador();
            agenda.escriuCamp(c.lastName());
            agenda.afegeixSeparador();
            agenda.escriuCamp(c.email());
            agenda.afegeixSeparador();
            agenda.escriuCamp(c.phone());
            agenda.tanca();
            System.out.print("Contacte creat. ");
        } catch (FileNotFoundException e){
            System.out.println("Fitxer no trobat.");
        }
    }

    /**
     * Cerca un contacte amb un camp coincident. El primer contacte que 
     * coincideixi es mostra. (Ignora si hi ha més d'un contacte amb el mateix
     * correu)
     * Si no en troba cap, retorna un contacte buid.
     * @param cerca
     * @return Contacte trobat (o buid)
     * @throws java.lang.Exception
     */
    public static Contacte cercaContacteCorreu(Camp cerca) throws Exception{  
        Contacte c = new Contacte();
        Boolean trobat = false;
        try{
            FitxerEntrada agenda = new FitxerEntrada("fitxers/agenda.txt");
            System.out.println("Cercant al directori..");
            
            Camp name, lastName, email, phone;
            
            while(agenda.quedenCamps() && !trobat){
                // Llegim una línia
                name = agenda.nouCampFitxer();
                lastName = agenda.nouCampFitxer();
                email = agenda.nouCampFitxer();
                phone = agenda.nouCampFitxer();
                // Verificam correu
                if(cerca.compara(email)){
                    trobat = true;
                    c = new Contacte(name, lastName, email, phone);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("El fitxer directori no està disponible.");
        }
        return c;
    }
    
    /**
     * Mostra el contacte triat per pantalla.
     * 
     * @throws java.lang.Exception
     */
    public static void mostraContacte() throws Exception {
        Camp cerca;
        Contacte resultat;
        char selector;
        System.out.println("Escriu el correu electrònic del contacte que"
                            + " vols mostrar.");
        cerca = Camp.nouCampTeclat();
        System.out.println("CERCA: " + cerca);
        resultat = cercaContacteCorreu(cerca);
        
        if(resultat.email().length() != 0){
            System.out.println("S'ha trobat un contacte coincident amb la cerca.");
            System.out.println(resultat);    
        } else {
            System.out.println("""
                               La cerca no ha retornat resultats coincidents. 
                               Vols provar cercant un altre correu? (S)i / (N)o
                               """);
            selector = LT.readChar();
            switch(selector){
                case 's' -> mostraContacte();
                case 'S' -> mostraContacte();
                case 'n' -> enrere();
                case 'N' -> enrere();
                default -> {
                    System.out.println("Selecció invàlida."); 
                    mostraContacte();
                }
            }
        }
        System.out.println("Pitja enter per acabar...");
        char stop = LT.readChar();

    }
    
    /**
     * Mostra tots els contactes del directori.
     * @throws java.lang.Exception
     * @param 
     */
    public static void mostraContactes() throws Exception {
        Camp name, lastName, email, phone;
        Contacte contacte;

        System.out.println("Directori de contactes:");
        try{
            FitxerEntrada agenda = new FitxerEntrada("fitxers/agenda.txt");
            while(agenda.quedenCamps()){
            //Inicialitzam contacte nou amb nous atributs llegits
            name = agenda.nouCampFitxer();
            lastName = agenda.nouCampFitxer();
            email = agenda.nouCampFitxer();
            phone = agenda.nouCampFitxer();
            contacte = new Contacte(name, lastName, email, phone);
            //Mostram contacte
            System.out.println(contacte);            
            }
        } catch (Exception e){
            System.out.println("Fitxer no trobat.");
        }
        System.out.println("Pitja enter per acabar...");
        char stop = LT.readChar();
    }
    
    /**
     * Elimina tots els contactes del directori.
     *
     * @throws java.lang.Exception
     */
    public static void eliminaContacte() throws Exception {
        Contacte resultat;
        Camp cerca;
        
        System.out.println("Escriu el correu electrònic del contacte que"
                            + " vols eliminar.");
        cerca = Camp.nouCampTeclat();
        resultat = cercaContacteCorreu(cerca);

        System.out.println(resultat);        
    }
    
    /**
     * Mostra el contacte triat per pantalla.
     */
    public static void novaLlista(){
        
    }
    
    /**
     * Mostra el contacte triat per pantalla.
     */
    public static void eliminaLlista(){
        
    }
    /**
     * Mostra tots els contactes del directori.
     */
    public static void mostraLlistes(){
        
    }
    
    /**
     * Mostra el contacte triat per pantalla.
     */
    public static void consultaLlista(){
        
    }
    
    /**
     * Aquest mètode no fa res. S'empra per a tornar enrere als menús.
     */
    private static void enrere(){
        
    }
    /**
     * Fa net la pantalla (entre comilles..) . S'empra per a fer guapos els menús.
     */
    private static void blanc(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}
