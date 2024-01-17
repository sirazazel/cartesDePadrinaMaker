/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.File;
import java.io.FileNotFoundException;

public class Contacte {
    //Camps d'un contacte
    private Camp name;
    private Camp lastName;
    private Camp email;
    private Camp phone;
    private boolean existeix, llista;
    
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
        this.existeix = true;
        this.llista = false;
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
       this.existeix = false;
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
    public boolean existeix(){
        return existeix;
    }
    public boolean llista(){
        return llista;
    }
    
    /**
     * Marcam aquest contacte com a llista de distribució.
     */
    public void marcaLlista(){
        this.llista = true;
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
        
        pausa();
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
    public static Contacte cercaContacte(Camp cerca) throws Exception{  
        Contacte c = new Contacte();
        Boolean trobat = false;
        try{
            FitxerEntrada agenda = new FitxerEntrada("fitxers/agenda.txt");
            // System.out.println("Cercant al directori..");
            
            Camp name, lastName, email, phone;
            
            while(agenda.quedenCamps() && !trobat){
                // Llegim una línia
                name = agenda.nouCampFitxer();
                lastName = agenda.nouCampFitxer();
                email = agenda.nouCampFitxer();
                phone = agenda.nouCampFitxer();
                // Verificam correu
                if(cerca.compara(name) || cerca.compara(lastName) || cerca.compara(email) || cerca.compara(phone)){
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
     * Cerca un contacte amb el correu electrònic exactament igual. El primer contacte que 
     * coincideixi es mostra. (Ignora si hi ha més d'un contacte amb el mateix
     * correu)
     * Si no en troba cap, retorna un contacte buid.
     * @param cerca
     * @return Contacte trobat (o buid)
     * @throws java.lang.Exception
     */
    public static Contacte cercaPrecisa(Camp cerca) throws Exception{  
        Contacte c = new Contacte();
        Boolean trobat = false;
        try{
            FitxerEntrada agenda = new FitxerEntrada("fitxers/agenda.txt");
            
            Camp name, lastName, email, phone;
            
            while(agenda.quedenCamps() && !trobat){
                // Llegim una línia
                name = agenda.nouCampFitxer();
                lastName = agenda.nouCampFitxer();
                email = agenda.nouCampFitxer();
                phone = agenda.nouCampFitxer();
                // Verificam correu
                if(cerca.comparaPrecisa(email)){
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
        System.out.println("Escriu el nom, llinatges o correu electrònic del contacte que"
                            + " vols mostrar.");
        System.out.print("Nom, llinatges o correu: ");
        cerca = Camp.nouCampTeclat();
        resultat = cercaContacte(cerca);
        
        if(resultat.existeix()){
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

        pausa();
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

        pausa();
    }
 
    /**
     * Donat un directori passat per paràmetre, mostra el nom dels contactes 
     * guardats.
     * @throws java.lang.Exception
     * @param 
     */
    public static void mostraContactesDirectori(Directori directori) throws Exception {      
        for(Contacte c: directori.accedirDirectori()){
            if(c != null){
                System.out.println("Contacte -> " + c.name + " " + c.lastName);
            }
        }

    }
    
    /**
     * Compara tots els camps d'aquest contacte amb el contacte passat per parametre.
     * @param b
     * @return
     */
    public boolean compara(Contacte b){
        return (name.compara(b.name) && lastName.compara(lastName) && email.compara(b.email) && phone.compara(b.phone));
    }
    
    /**
     * Elimina un contacte del directori.
     *
     * @throws java.lang.Exception
     */
    public static void eliminaContacte() throws Exception {
        Contacte complet;
        Camp cerca;
        String agenda = "fitxers/agenda.txt";
        String llistes = "fitxers/llistes/";
        System.out.println("Escriu el correu electrònic del contacte que"
                            + " vols eliminar.");
        cerca = Camp.nouCampTeclat();
        //Validam que el contacte existeix.
        complet = cercaPrecisa(cerca);
        
        if(!complet.existeix()){
            System.out.println("El contacte " + cerca + "no és a l'agenda.");
            return;
        } else {
            System.out.println("El contacte " + complet.name + " " + complet.lastName 
                                + "s'eliminarà de la llista. (S)i / (N)o");
            try{
                char selector = LT.readChar();
                switch(selector){
                    case 's': 
                    case 'S': selector = LT.readChar(); break;
                    case 'n': 
                    case 'N': selector = LT.readChar(); return;
                    default: {
                        selector = LT.readChar(); 
                        System.out.println("Selecció invàlida."); 
                        eliminaContacte();
                    }                
                }
            } catch (Exception e) {
                char avanca = LT.readChar();
            }    
        }
        
        eliminaContacteDeFitxer(agenda, complet);
        
        //Ara busquem si el contacte estava a alguna llista de distribució
        File dir = new File("fitxers/llistes");
        for(File f: dir.listFiles()){
            String ruta = llistes + f.getName();
            Camp.eliminaCampDeFitxer(ruta, complet.email());
        }
                
        System.out.println("Nota: Tot i que el contacte sí s'ha eliminat de la llista de distribució,"
                + " no s'elimina de l'agenda. No sé per què passa això! ");
        pausa();
    }
          
    /**
     * Elimina un contacte de qualsevol fitxer.
     * @param ruta
     * @param contacte
     * @throws Exception 
     */
    public static void eliminaContacteDeFitxer(String ruta, Contacte contacte) throws Exception {
        FitxerEntrada lectura = new FitxerEntrada(ruta);
        File fitxerOriginal = new File(ruta);
        File fitxerTemporal = new File("fitxers/temp.txt");
        
        lectura.elimina(contacte, fitxerTemporal.getPath());
        lectura.tanca();
        fitxerOriginal.delete();
        fitxerTemporal.renameTo(fitxerOriginal);
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
    
    /**
     * Pausa el programa fins que l'usuari introdueix un enter.
     * Si empleam var, no hauria de tenir cap Exception per tipus explícit de variable
     */
    private static void pausa(){
        System.out.println("Pitja enter per acabar...");
        var pausa = LT.readChar();
    }
}
