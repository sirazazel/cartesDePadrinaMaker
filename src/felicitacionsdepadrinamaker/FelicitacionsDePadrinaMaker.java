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
            blanc();
            System.out.println("""
                                  _______________________________________________________
                                 /\\                                                      \\ 
                             (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><>)==(O)
                                \\/''''''''''''''''''''''''''''''''''''''''''''''''''''''/
                                 (                                                      (
                                  )              -----------------------                 )
                                 (               |   Menu  principal   |                (
                                  )              -----------------------                 )
                                 (     1 -> Generar una carta nova des d'una plantilla. (
                                  )    2 -> Consulta l'agenda de contactes.              )
                                 (     3 -> Consulta les llistes de distribució.        (
                                  )    0 -> Surt del programa.                           )
                                 (                                                      (
                                  )'''''''''''''''''''''''''''''''''''''''''''''''''''''')    
                             (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><)==(O)
                                 \\/______________________________________________________/
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
                System.out.println("\n Selecció invàlida");
            }
        }
    }
        
    /**
     * Submenús. 
     */
    private static void administraAgenda() throws Exception {
        int fSelect;
        blanc();
        System.out.println("""
                                 _______________________________________________________
                                /\\                                                      \\ 
                            (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><>)==(O)
                               \\/''''''''''''''''''''''''''''''''''''''''''''''''''''''/
                                (                                                      (
                                 )              -----------------------                 )
                                (               | Agenda de contactes |                (
                                 )              -----------------------                 )
                                (     1 -> Crear un contacte nou.                      (
                                 )    2 -> Consulta l'agenda de contactes.              )
                                (     3 -> Elimina un contacte de l'agenda.            (
                                 )    0 -> Torna al menú principal.                    )
                                (                                                      (
                                 )'''''''''''''''''''''''''''''''''''''''''''''''''''''')    
                            (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><)==(O)
                                \\/______________________________________________________/
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
    
    private static void seleccioContacte() throws Exception {
        int fSelect;
        blanc();
        System.out.println("""
                                  _______________________________________________________
                                /\\                                                      \\ 
                            (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><>)==(O)
                               \\/''''''''''''''''''''''''''''''''''''''''''''''''''''''/
                                (                                                      (
                                 )              -----------------------                 )
                                (               | Agenda de contactes |                (
                                 )              -----------------------                 )
                                (     Vols mostrar un contacte o la llibreta sencera?  (
                                 )    1 -> Consulta un contacte de l'agenda             )
                                (     2 -> Mostra la llibreta de contactes             (
                                 )    0 -> Torna al menú principal.                     )
                                (                                                      (
                                 )'''''''''''''''''''''''''''''''''''''''''''''''''''''')    
                            (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><)==(O)
                                \\/______________________________________________________/
                            """);        
        try {
            fSelect = LT.readInt();
            switch(fSelect){
                case 1 -> Contacte.mostraContacte();
                case 2 -> Contacte.mostraContactes();
                case 0 -> administraAgenda();
            }  
        } catch (NumberFormatException e) {
            System.out.println("Selecció invàlida"); 
            seleccioContacte();
        }
    }
    
    
    private static void administraLlistes() throws Exception {
        int fSelect;
        blanc();
        System.out.println("""
                                 _______________________________________________________
                                /\\                                                      \\
                            (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><>)==(O)
                               \\/''''''''''''''''''''''''''''''''''''''''''''''''''''''/
                                (                                                      (
                                 )              -----------------------                 )
                                (               | Llistes distribució |                (
                                 )              -----------------------                 )
                                (     1 -> Crea una llista nova.                       (
                                 )    2 -> Consulta les llistes de l'agenda.            )
                                (     3 -> Consulta els contactes d'una llista         (
                                 )    4 -> Elimina una llista de distribució            )
                                (     0 -> Torna al menú principal.                    (
                                 )'''''''''''''''''''''''''''''''''''''''''''''''''''''')
                            (O)===)><><><><><><><><><><><><><><><><><><><><><><><><><><><)==(O)
                                \\/______________________________________________________/
                            """);
        try{
            fSelect = LT.readInt();
            switch(fSelect){
               case 1 -> Contacte.novaLlista();
               case 2 -> mostraLlistes();
               case 3 -> Contacte.consultaLlista();
               case 4 -> Contacte.eliminaLlista();
               case 0 -> enrere();
            }
        } catch (NumberFormatException e) {
            System.out.println("Selecció invàlida");
            administraAgenda();
        }
    }

    private static void mostraLlistes() throws Exception {
        mostraDirectoriPerPantalla("fitxers/llistes");
    }
    
    /**
     * Fa net la pantalla (entre comilles..) . S'empra per a fer guapos els menús.
     */
    private static void blanc(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    
    /**
     * Aquest mètode no fa res. S'empra per a tornar enrere als menús.
     */
    private static void enrere(){
    }
    
    /**
     * Marca la variable de final per a acabar l'execució.
     */
    private static void acaba(){
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
    private static void menuCarta() throws Exception {
        blanc();
        char[] nomCarpeta, destinatarisIn;
        String ruta, plantilla;
        Directori destinataris;
        System.out.println("""
                           Indica el nom de la carta. 
                           La carta generada es trobara a una carpeta\s
                           amb el mateix nom, dins del directori 'generats'.
                            """);
        System.out.print("Nom de la carta -> ");
        nomCarpeta = LT.readLineChar();
        ruta = creaDirectori(nomCarpeta);
        System.out.println("Ruta dels fitxers generats: " + ruta);
        System.out.println("\nEscriu el numero de la plantilla que vols emprar.");
        plantilla = seleccionaPlantilla("fitxers/plantilles");
        System.out.print("Numero de plantilla -> ");
        System.out.println(plantilla);
        System.out.println("""
                           Escriu els correus dels destinataris de la carta 
                           separats per espai, o be deixa-ho en blanc per a 
                           enviar a tothom.
                           """);
        System.out.print("Correus dels destinataris -> ");
        destinatarisIn = LT.readLineChar();
        
        if(destinatarisIn.length == 0){
            preparaCartaAgenda(plantilla, ruta);
        } else {
            // Formatejam entrada en un directori de contactes
            destinataris = Directori.processaDirectori(destinatarisIn);
            // Preparem carta
            preparaCartaDirectori(destinataris, plantilla, ruta);
        }
        
        System.out.println("Pitja enter per acabar...");
        char stop = LT.readChar();

    }
    
    /**
     * Prepara l'entorn per a crear una carta per a cada contacte que troba al 
     * directori donat.
     * @param d
     * @param plantilla
     * @param ruta
     * @throws Exception 
     */
    private static void preparaCartaDirectori(Directori directori, String plantilla, String ruta) throws Exception {
        for(int i = 0; i < directori.length() ; i++){
            Contacte contacte;
            contacte = Contacte.cercaContacteCorreu(directori.accedirDirectori()[i].email());
            if(contacte.email().length() != 0){
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
    private static void preparaCartaAgenda(String plantilla, String ruta) throws Exception {
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
    private static void generaCarta(Contacte contacte, String plantilla, String ruta) throws Exception{        
        FitxerEntrada fitxerPlantilla = new FitxerEntrada(plantilla);
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
    
    private static void mostraDirectoriPerPantalla(String path){
        File dir = new File(path);
        File[] directori = dir.listFiles();
        int i = 1;
        
        for(File f: directori){
            System.out.println(i + ". " + f);
            i++;
        }
    }
    
    private static File guardaPlantilla(String path, int index){
        File dir = new File(path);
        File[] plantilles = dir.listFiles();
        // Igual és index -1
        return plantilles[index-1];
    }
    
    private static String seleccionaPlantilla(String path){
        //Mostram directori primer pic
        mostraDirectoriPerPantalla(path);
        //Usuari selecciona número de plantilla
        try{
            int seleccio = LT.readInt();
            //Guardam fitxer plantilla
            File plantilla = guardaPlantilla(path,seleccio);
            return plantilla.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Selecció incorrecta.");
            seleccionaPlantilla(path);
        }
        return "Error desconegut sel·leccionant la plantilla. No es generarà cap carta.";
    }
}
