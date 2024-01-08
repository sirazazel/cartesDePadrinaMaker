/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.File;

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
                    case 1 -> menuCarta();
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
    
    /*
     *
     *  MÈTODES DE CARTA
     *
     */
    
    /**
     * Demanam a l'usuari el nom de la nova carta de distribució, per a qui està
     * destinat i quina plantilla ha d'emprar.
     * Després cercam si els contactes existeixen a la nostra agenda de contactes,
     * i passa per paràmetre a una funció de sortida de fitxers tota la informació.
     * @throws java.lang.Exception
     */
    public static void menuCarta() throws Exception {
        char[] nomCarpeta, destinatarisIn, plantilla;
        String ruta;
        Directori destinataris;
        System.out.println("""
                           Escriu el nom de la carta. La carta generada es 
                           trobarà a una carpeta anomenada igual.
                            """);
        nomCarpeta = LT.readLineChar();
        ruta = creaDirectori(nomCarpeta);
        System.out.println("Ruta dels fitxers generats: " + ruta);
        System.out.println("Escriu el nom de la plantilla que vols emprar.");
        plantilla = LT.readLineChar();
        System.out.println("""
                           Escriu els correus dels destinataris de la carta 
                           separats per espai, o bé deixa-ho en blanc per a 
                           enviar a tothom.
                           """);
        destinatarisIn = LT.readLineChar();
        
        if(destinatarisIn.length == 0){
            preparaCartaAgenda(plantilla, ruta);
        } else {
            // Formatejam entrada en un directori de contactes
            destinataris = Directori.processaDirectori(destinatarisIn);
            // Preparem carta
            preparaCartaDirectori(destinataris, plantilla, ruta);
        }
    }
    
    /**
     * Prepara l'entorn per a crear una carta per a cada contacte que troba al 
     * directori donat.
     * @param d
     * @param plantilla
     * @param ruta
     * @throws Exception 
     */
    private static void preparaCartaDirectori(Directori directori, char[] plantilla, String ruta) throws Exception {
        for(int i = 0; i < directori.length() ; i++){
            Contacte contacte;
            contacte = Contacte.cercaContacteCorreu(directori.accedirDirectori()[i].email());
            if(contacte.email() != null){
                generaCarta(contacte, plantilla, ruta);
            } else {    
                System.out.println("No s'ha trobat el contacte amb el correu "
                                + directori.accedirDirectori()[i].email());
            }
        }
    }
    
    /**
     * Prepara l'entorn per a crear una carta per a cada contacte que troba 
     * al fitxer agenda.
     * @param plantilla
     * @param ruta
     * @throws Exception 
     */
    private static void preparaCartaAgenda(char[] plantilla, String ruta) throws Exception {
        try{
            FitxerEntrada agenda = new FitxerEntrada("fitxers/agenda.txt");
           
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
                //Enviam una carta a generar.
                generaCarta(contacte, plantilla, ruta);
                }
            agenda.tanca();
            } catch (Exception e){
        }
    }
    
    /**
     * Genera una carta a partir d'un contacte, una plantilla, i una ruta al 
     * sistema de fitxers.
     * @param contacte Objecte "Contacte"
     * @param plantilla
     * @param ruta
     * @throws Exception 
     */
    private static void generaCarta(Contacte contacte, char[] plantilla, String ruta) throws Exception{
        System.out.println(contacte.name() + " " + contacte.lastName());
        System.out.println("Nom de la carta: " + contacte.email());
        
        FitxerEntrada fitxerPlantilla = new FitxerEntrada(
                "fitxers/plantilles/" + String.valueOf(plantilla) + ".html");
        fitxerPlantilla.generaDesdePlantilla(contacte, ruta);
        fitxerPlantilla.tanca();
    }
    
    /**
     * Crea un nou directori a la ruta donada. Si ja està creada, posiciona.
     * @param path
     * @return 
     */
    private static String creaDirectori(char[] path){
        String rutaAbsoluta = "";
        try {
            String dir = "fitxers/generats/"+String.valueOf(path);
            File file = new File(dir);
            if(!file.exists()){
                file.mkdir();
            }
            rutaAbsoluta = file.getAbsolutePath();
            System.out.println("Directori creat correctament.");
        } catch(Exception e) {
            System.out.println("El directori no s'ha creat correctament.");
        }
        return rutaAbsoluta + "\\";        
    }    
}
