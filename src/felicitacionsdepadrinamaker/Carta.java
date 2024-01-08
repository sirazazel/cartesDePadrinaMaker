/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package felicitacionsdepadrinamaker;

import java.io.File;

/**
 *
 * @author Francesc Mut Mollà
 */
public class Carta {
       
    /**
     * Demanam a l'usuari el nom de la nova carta de distribució, per a qui està
     * destinat i quina plantilla ha d'emprar.
     * Després cercam si els contactes existeixen a la nostra agenda de contactes,
     * i passa per paràmetre a una funció de sortida de fitxers tota la informació.
     * @throws java.lang.Exception
     */
    public static void menuCarta() throws Exception {
        char[] nomCarpeta, sendersUnformatted, plantilla;
        String ruta;
        Directori sendersFormatted;
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
        sendersUnformatted = LT.readLineChar();
        
        if(sendersUnformatted.length == 0){
            preparaCartaAgenda(plantilla, ruta);
        } else {
            // Formatejam entrada en un directori de contactes
            sendersFormatted = Directori.processaDirectori(sendersUnformatted);
            // Preparem carta
            preparaCartaDirectori(sendersFormatted, plantilla, ruta);
        }
    }
    
    private static void generaCarta(Contacte contacte, char[] plantilla, String ruta) throws Exception{
        System.out.println(contacte.name() + " " + contacte.lastName());
        System.out.println("Nom de la carta: " + contacte.email());
        
        FitxerCartaEntrada fitxerPlantilla = new FitxerCartaEntrada("fitxers/plantilles/" + String.valueOf(plantilla) + ".html");
        fitxerPlantilla.generaDesdePlantilla(contacte, ruta);
        fitxerPlantilla.tanca();
    }
    
    private static void preparaCartaDirectori(Directori d, char[] plantilla, String ruta) throws Exception {
        for(int i = 0; i < d.length() ; i++){
            Contacte complet;
            complet = Contacte.cercaContacteCorreu(d.accedirDirectori()[i].email());
            if(complet.email() != null){
                generaCarta(complet, plantilla, ruta);
            } else {    
                System.out.println("No s'ha trobat el contacte amb el correu "
                                + d.accedirDirectori()[i].email());
            }
        }
    }
    
    private static void preparaCartaAgenda(char[] plantilla, String ruta) throws Exception {
        try{
            FitxerCampEntrada agenda = new FitxerCampEntrada("fitxers/agenda.txt");
           
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
