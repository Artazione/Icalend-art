package component;
import java.io.File;  // Import the File class
import java.io.FileWriter;  
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;
import java.io.FileNotFoundException;

import property.*;
import value.*;

//import component;
/**  La classe Ical creer le String que l'on va implementer dans notre fichier .ics
 @author Jules
 @author Bavin
 @version 1.0
*/
public class ICal
{
	/** Constante sous forme de string permettant le retour a la ligne */
	public static final String n = "\r\n";//notre CRLF en une variable
	/** String qui comptient le texte que l'on mettera dans le fichier ics */
	private String textResult = "";//String qui comptient le texte que l'on mettera dans le fichier ics
	/**String pour quand on import, ce String correspond au ligne dont la propriete est inconnu ou la valeur est inconnu ou autre */
	private String textInconnu = "";//String pour quand on import, ce String correspond au ligne dont la propriete est inconnu ou la valeur est inconnu ou autre
	/**Tableau de VEventComponent contenant tous les events que l'on possede */
	private VEventComponent[] vEventComponent;//contient tous les events que l'on possede
	/**Recupere l'objet textResult
	 * @return le String du texte
	  */
	public String getTextResult() { return textResult; }
	/**Recupere l'objet vEventComponent
	 * @return le tableau qui contient nos events
	  */
	public VEventComponent[] getVEventComponent() { return vEventComponent; };
	/**Constructeur par defaut de note ics */
	public ICal()//creation de notre ics
	{
		textResult = "BEGIN:VCALENDAR" + n;
		textResult += "PRODID:-//ABC Corporation//NONSGML My Product//FR"+n;//propriete PRODID
		textResult += "VERSION:2.0" + n;//propiete VERSION
		//textResult += "CALSCALE:GREGORIAN" + n;//propriete CALSCALE
		vEventComponent = new VEventComponent[0];
	}
	/**Procedure ajoutant un event dans notre ics
	  */
	public void addVEvent()//ATTENION D INITIALISE LES VEVENT QUE L'ON CREER
	{
		VEventComponent[] vEventComponentResult = new VEventComponent[vEventComponent.length + 1];//on augmente la taille de vEventComponent
		for(int i = 0; i < vEventComponent.length; i++)
		{
			vEventComponentResult[i] = vEventComponent[i];
		}
		vEventComponent = vEventComponentResult;
	}
	public void deleteVEvent(int id)
	{
		VEventComponent[] vEventComponentResult = new VEventComponent[vEventComponent.length - 1];
		int a = 0;
		for(int i = 0; i < vEventComponent.length; i++)
		{
			if(i != id)
			{
				vEventComponentResult[a] = vEventComponent[a];
				a++;
			}
		}
		vEventComponent = vEventComponentResult;
	}
	/**Procedure affichant le textResult */
	public void affiche()//affiche textResult dans la console
	{
		System.out.println(textResult + "END:VCALENDAR"+n);
	}
	/**Procedure sauvegardant notre ics
	 * @param name nom de notre fichier
	 */
	public void save(String name)//sauvegarde notre ics
	{
		for(int i = 0; i < vEventComponent.length; i++)
		{
			textResult += vEventComponent[i].toString();
		}
		textResult += textInconnu;
		textResult += "END:VCALENDAR" + n;
		
		File iCalendare = new File("../resultats/" + name + ".ics");;
		try {
		if (iCalendare.createNewFile()) {
        System.out.println("File created: " + iCalendare.getName());
		} else {
        System.out.println("File already exists. il a donc ete ecrase par le nouveau");
		}
		} catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
		}
			
		try {
			FileWriter writer =new FileWriter(iCalendare);    
			writer.write(textResult);    
			writer.close();    
		}catch(Exception e){System.out.println(e);}    
        System.out.println("Success...");    
	}
	/**Constructeur permettant de modifier un ics en passant son adresse
	 * @param adresse l'adresse du ics
	 */
	public ICal(String adresse) throws FileNotFoundException
    {
        File model = new File(adresse);
        vEventComponent = new VEventComponent[0];

        Scanner mo;
        boolean isOver = false;
        mo = new Scanner(model);
        while(mo.hasNextLine() && isOver == false)
        {
            String line = mo.nextLine();
            int separtion = line.indexOf(':'); 
            if(separtion != -1)
            {
                String propertyName = line.substring(0, separtion);
                String valueName = line.substring(separtion + 1, line.length());
                if(propertyName.equals("BEGIN") && valueName.equals("VCALENDAR"))
                {
                    textResult += "BEGIN:VCALENDAR" + n;
                }
                else if(propertyName.equals("END") && valueName.equals("VCALENDAR"))
                {
                    isOver = true;
                }
                else if(propertyName.equals("END") && valueName.equals("VCALENDAR"))
                {

                }
                else if(propertyName.equals("BEGIN") && valueName.equals("VEVENT"))//on arrive dans un VEVENT
                {
                    addVEvent();
                    vEventComponent[vEventComponent.length - 1] = new VEventComponent(mo);
                }
                else 
                {
                    textInconnu += line + n;
                }
            }
            else textInconnu += line + n;
        }
    }
}
//https://datatracker.ietf.org/doc/html/rfc5545