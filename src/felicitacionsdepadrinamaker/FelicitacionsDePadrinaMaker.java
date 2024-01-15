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
               case 1 -> novaLlista();
               case 2 -> mostraLlistes();
               case 3 -> consultaLlista();
               case 4 -> eliminaLlista();               
               case 0 -> enrere();
            }
        } catch (NumberFormatException e) {
            System.out.println("Selecció invàlida");
            administraAgenda();
        }
    }
        
    /*
     *
     *  Funcions de generació de carta
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
        plantilla = seleccionaFitxer("fitxers/plantilles");
        System.out.println("""
                           Escriu els correus dels destinataris de la carta 
                           separats per espai, o be deixa-ho en blanc per a 
                           enviar a tothom.
                           """);
        System.out.print("Correus dels destinataris -> ");
        destinatarisIn = LT.readLineChar();
        
        // Si s'entra més d'un caràcter, s'ha de processar què s'ha entrat.
        if(destinatarisIn.length != 0){
            // Formatejam entrada en un directori de contactes. Comprovam si
            // és llista de distribució.
            destinataris = Directori.processaDirectori(destinatarisIn);
            // Preparem carta
            preparaCartaDirectori(destinataris, plantilla, ruta);
            
        } else {
            preparaCartaAgenda(plantilla, ruta);
        }
        
        pausa();
    }
    
    /**
     * Prepara l'entorn per a crear una carta per a cada contacte que troba al 
     * directori donat. Si és una llista, genera una per cada correu.
     * @param d
     * @param plantilla
     * @param ruta
     * @throws Exception 
     */
    private static void preparaCartaDirectori(Directori directori, String plantilla, String ruta) throws Exception {
        for(Contacte cerca: directori.accedirDirectori()){
            try{
                Contacte complet = Contacte.cercaPrecisa(cerca.email());
                // Si el contacte existeix, es marca quan es crea. Si és buid, es marca fals.
                // Si és llista, s'ha marcat abans quan es processava el directori.
                if(complet.existeix()){
                    generaCarta(complet, plantilla, ruta);                
                } else if (cerca.llista()) {
                    String correuRetallat = cerca.email().retalla();
                    String rutaLlista = "fitxers/llistes/" + correuRetallat + ".txt";
                    System.out.println("S'ha detectat la llista de distribució " + correuRetallat + ".");
                    preparaCartaLlista(rutaLlista, plantilla, ruta);                
                } else {
                    System.out.println("No s'ha trobat el contacte amb el correu donat.");
                }
            } catch (Exception e){
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
     * Prepara l'entorn per a generar una carta per a cada persona en una llista de distribució.
     * @param rutaLlista
     * @param plantilla
     * @param ruta
     * @throws Exception 
     */
    private static void preparaCartaLlista(String rutaLlista, String plantilla, String ruta) throws Exception {
        FitxerEntrada llista = new FitxerEntrada(rutaLlista);
        
        while(llista.quedenCamps()){
            Camp cerca = llista.nouCampFitxer();
            Contacte c = Contacte.cercaPrecisa(cerca);
            generaCarta(c, plantilla, ruta);
        }
        llista.tanca();
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
    
    /* 
     *
     * Funcions de selecció de plantilles
     *
     */
    
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
    
    /**
     * Menú de selecció de plantilla.
     * @param path
     * @return 
     */
    private static String seleccionaFitxer(String path){
        //Mostram directori primer pic
        mostraDirectoriPerPantalla(path);
        //Usuari selecciona número de fitxer
        try{
            System.out.print("Seleccio -> ");
            int seleccio = LT.readInt();
            System.out.println();
            //Guardam fitxer plantilla
            File plantilla = guardaPlantilla(path,seleccio);
            return plantilla.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Selecció incorrecta.");
            seleccionaFitxer(path);
        }
        return "Error desconegut sel·leccionant el fitxer.";
    }

    /**
     * Mostra el directori passat per paràmetre, i hi afegeix un índex.
     * @param path 
     */
    private static void mostraDirectoriPerPantalla(String path){
        File dir = new File(path);
        File[] directori = dir.listFiles();
        int i = 1;
        
        for(File f: directori){
            System.out.println(i + ". " + f);
            i++;
        }
    }
        
    /**
     * Retorna el fitxer de la plantilla seleccionada.
     * @param path
     * @param index
     * @return 
     */
    private static File guardaPlantilla(String path, int index){
        File dir = new File(path);
        File[] plantilles = dir.listFiles();
        // Igual és index -1
        return plantilles[index-1];
    }
    
    /*
     *
     *  Funcions llistes de distribució
     *
     */
    
    private static void novaLlista() throws Exception {
        char[] nomLlista, destinatarisIn;
        Directori destinataris;
        String ruta = "fitxers/llistes";
        
        blanc();
        System.out.println("Escriu el nom de la nova llista de distribució.");
        System.out.print("Nom -> ");
        nomLlista = LT.readLineChar();
        ruta = ruta + "/" + String.valueOf(nomLlista) + ".txt";
        System.out.println("Escriu, separat per espais, cada correu electrònic"
                + "que ha d'estar subscrit a aquesta llista de distribució.");
        System.out.print("Destinataris -> ");
        destinatarisIn = LT.readLineChar();
        
        destinataris = Directori.processaDirectori(destinatarisIn);
        
        // Cream la llista amb el directori de contactes vàlids.
        FitxerSortida novaLlista = new FitxerSortida(ruta);
        novaLlista.creaLlista(destinataris);
        novaLlista.tanca();
        
        System.out.println("S'ha creat la nova llista de distribució " + 
                String.valueOf(nomLlista) + " amb els contactes següents: ");
        Contacte.mostraContactesDirectori(destinataris);
        pausa();
    }
    
    /**
     * Mostra la llista de distribució triada per pantalla.
     * @throws java.lang.Exception
     */
    public static void consultaLlista() throws Exception {
        blanc();
        System.out.println("Selecciona la llista de distribució que vols "
                + "consultar");
        System.out.println("Per seleccionar, escriu el número de la llista.");
        String rutaLlista = seleccionaFitxer("fitxers/llistes");
        System.out.println("Llista seleccionada ->" + rutaLlista);
             
        FitxerEntrada llista = new FitxerEntrada(rutaLlista);
        while(llista.quedenCamps()){
            Camp c = llista.nouCampFitxer();
            System.out.println(c);
        }
        
        llista.tanca();
        
        modificaLlista(rutaLlista);
        
        pausa();
    }
    
    private static void mostraLlistes() throws Exception {
        blanc();
        System.out.println("Llistes disponibles: ");
        mostraDirectoriPerPantalla("fitxers/llistes");
        pausa();
    }
    
    private static void eliminaLlista() throws Exception {
        blanc();
        System.out.println("Quina llista vols eliminar? \n Llistes disponibles: ");
        String rutaLlista = seleccionaFitxer("fitxers/llistes");
        System.out.println(rutaLlista);
        FitxerSortida.eliminaFitxer(rutaLlista);
        if(FitxerSortida.comprovaFitxer(rutaLlista)) {
            System.out.println("Error eliminant la llista. Torna a provar.");
        } else {
            System.out.println("Llista eliminada correctament.");
        }
        pausa();
    }
    
    private static void modificaLlista(String rutaLlista) throws Exception {
        boolean continua = false;
        System.out.println("Vols modificar la llista de distribució seleccionada? (S)i / (N)o");
        try {
            char selector = LT.readChar();
                switch(selector){
                    case 's' -> continua = true;
                    case 'S' -> continua = true;
                    case 'n' -> continua = false;
                    case 'N' -> continua = false;
                }
        } catch (Exception e)  {
            System.out.println("Selecció invàlida.");
        }

        if(continua){
            System.out.println("""
                               1. Afegir un contacte a la llista.
                               2. Eliminar un contacte.
                               0. He canviat d'idea.
                               """);
            try{
                int selectorII = LT.readInt();
                switch (selectorII){
                    case 1 -> FitxerSortida.afegeixContacte(rutaLlista);
                    case 2 -> eliminaDeLlista(rutaLlista);
                    default -> enrere();
                }
            } catch (Exception e) {
                System.out.println("Selecció invàlida.");
            }
        }
    }
    
    /**
     * Demana quin usuari s'ha d'eliminar.. No me mola.
     * @param rutaLlista
     * @throws Exception 
     */
    private static void eliminaDeLlista(String rutaLlista) throws Exception {
        System.out.println("Quin contacte vols eliminar?");
        
        Camp cerca = Camp.nouCampTeclat();
        Contacte complet = Contacte.cercaPrecisa(cerca);
        
        Camp.eliminaCampDeFitxer(rutaLlista, complet.email());
    }
    
    /*
     *
     *  Funcions del menú
     *
     */
    
    /**
     * 
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

    /**
     * Pausa el programa fins que l'usuari introdueix un enter.
     * Si empleam var, no hauria de tenir cap Exception per tipus explícit de variable?
     */
    private static void pausa(){
        System.out.println("Pitja enter per acabar...");
        var pausa = LT.readChar();
    }    
}
