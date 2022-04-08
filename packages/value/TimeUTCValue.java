package value;
import java.io.IOException;
/*TIME dans le format UTC https://icalendar.org/iCalendar-RFC-5545/3-3-12-time.html */
/** La classe TimeUTCValue gere le temps au format UTC (heure,minute,seconde)
 
 @see TimeValue
 @author Lilou Undreiner 
 @author Anne Hugo
 @version 15/03/2022
*/
public class TimeUTCValue extends TimeValue 
{
	/**
    * Constructeur par initialisation de la classe TimeUTCValue, utilise le constructeur de sa classe mere TimeValue
    *
	* @param heure l'entier heure
	* @param minute l'entier pour les minutes
    * @param seconde l'entier pour les secondes
	* 
    */
	public TimeUTCValue(int heure, int minute, int seconde) throws IOException
	{
		super(heure, minute, seconde);
	}
	/**
    * Constructeur par copie de la classe TimeUTCValue
    *
    * @param model la TimeUTCValue a copier
    */
	public TimeUTCValue(TimeUTCValue model) throws IOException
	{
		super(model.heure, model.minute, model.seconde);
	}
	/**
    * Creee la String de l'objet TimeUTCValue, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public @Override String toString()
	{
		String heureString = "" + heure;
		if(heureString.length() != 2) heureString = "0" + heureString;//pour avoir 2 chiffres
		String minuteString = "" + minute;
		if(minuteString.length() != 2) minuteString = "0" + minuteString;//pour avoir 2 chiffres
		String secondeString = "" + seconde;
		if(secondeString.length() != 2) secondeString = "0" + secondeString;//pour avoir 2 chiffres
		return heureString + minuteString + secondeString + "Z";
	}
	
	/**
     * Retourne vrai si la Time passee en parametre se trouve plus loin dans le temps que this, retourne faux si les deux times sont les memes
	 *
	 * @param comparaison la TimeUTCValue a comparer
	 *
     * @return un booleen 
     */
	public boolean isPlusLoin(TimeUTCValue comparaison)
	{
		boolean result;
		if(heure > comparaison.heure) result = false;
		else 
		{
			if(heure < comparaison.heure) result = true;
			else
			{
				if(minute > comparaison.minute) result = false;
				else 
				{
					if(minute < comparaison.minute) result = true;
					else
					{
						if(seconde >= comparaison.seconde) result = false;
						else
						{
							result = true;
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
     * Verifie si un String a une valeur TimeUTC qui respecte les restrictions
	 * @param text une chaine de caractere correspondant a la ligne UTC d'un fchier ics
     * @return un booleen (vrai si la chaine respecte les criteres)
     */
	public static boolean isStringCorrectTimeUTCValue(String text)
	{
		boolean result = true;
		
		if(text.length() == 7)//on verifie que l'on a bien que des chiffres et 6 chiffres et un Z a la fin
		{
			int i = 0;
			while(result == true && i < 6)
			{
				if(Character.isDigit(text.charAt(i)) == false) result = false;
				i++;
			}
			if(text.charAt(6) != 'Z') result = false;
		}
		else result = false;
		
		if(result == true)//on verifie si les valeurs sont possible: ex: les heures != 44...
		{
			int h = Integer.parseInt(text.substring(0, 2));
			if(h > 23 || h < 0) result = false;
			else
			{
				int min = Integer.parseInt(text.substring(2,4));
				if(min > 59 || min < 0) result = false;
				else 
				{
					int s = Integer.parseInt(text.substring(4,6));
					if(s > 60 || s < 0) result = false;
				}
			}
		}
		return result;
	}

	/**
	 * Constructeur de TimeUTCValue a partir d'une String 
	 * @param value String qui a une valeur TimeUTC
     * 
     */
	public TimeUTCValue(String value) throws IOException
	{
		this(Integer.parseInt(value.substring(0, 2)), Integer.parseInt(value.substring(2, 4)), Integer.parseInt(value.substring(4,6)));
	}
}