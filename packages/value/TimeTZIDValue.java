package value;
import java.io.IOException;
/*TIME dans le format LOCAL TIME AND TIME ZONE REFERENCE https://icalendar.org/iCalendar-RFC-5545/3-3-12-time.html */
/**  La classe TimeTZIDValue gere le temps au format TZID (avec une timezone)
 
 @see TimeValue
 
 @author Lilou Undreiner 
 @author Anne Hugo
 @version 15/03/2022
*/
public class TimeTZIDValue extends TimeValue 
{
	/**
    *la timezone, un objet TimeZones
    */
	private TimeZones timeZones;
	
	/**
    *Recupere l'heure
    *@return un entier heure
    */
	public int getHeure() { return heure; }
	/**
    *Recupere les minutes
    *@return un entier minute
    */
	public int getMinute() { return minute; } 
	/**
    *Recupere les secondes
    *@return un entier seconde
    */
	public int getSeconde() { return seconde; }
	/**
    *Recupere la timeZone
    *@return une TimeZones
    */
	public TimeZones getTimeZones() { return timeZones; }

	/**
    * Constructeur par initialisation de la classe TimeUTCValue, utilise le constructeur de sa classe mere TimeValue
    *
	* @param heure l'entier heure
	* @param minute l'entier pour les minutes
    * @param seconde l'entier pour les secondes
	* @param timeZones la TimeZones
    */
	
	public TimeTZIDValue(int heure, int minute, int seconde, TimeZones timeZones) throws IOException
	{
		super(heure, minute, seconde);
		this.timeZones = timeZones;
	}

	/**
    * Constructeur par copie de la classe TimeTZIDValue
    *
    * @param model la TimeTZIDValue a copier
    */
	public TimeTZIDValue(TimeTZIDValue model) throws IOException
	{
		super(model.getHeure(), model.getMinute(), model.getSeconde());
		timeZones = model.timeZones;
	}
	/**
    * Creee la String de l'objet TimeTZIDValue, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public @Override  String toString()
	{
		String heureString = "" + heure;
		if(heureString.length() != 2) heureString = "0" + heureString;//pour avoir 2 chiffres
		String minuteString = "" + minute;
		if(minuteString.length() != 2) minuteString = "0" + minuteString;//pour avoir 2 chiffres
		String secondeString = "" + seconde;
		if(secondeString.length() != 2) secondeString = "0" + secondeString;//pour avoir 2 chiffres
		return "TZID=" + timeZones.toString() + ":" +  heureString + minuteString + secondeString;
	}
	
	/**
     * Retourne vrai si la Time passee en parametre se trouve plus loin dans le temps que this, retourne faux si les deux times sont les memes
	 *
	 * @param comparaison la TimeTZIDValue a comparer
	 *
     * @return un booleen 
     */
	public boolean isPlusLoin(TimeTZIDValue comparaison)
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
     * Verifie si un String a une valeur TimeTZID qui respecte les restrictions
	 * @param text une chaine de caractere correspondant a la ligne TZID d'un fchier ics
     * @return un booleen (vrai si la chaine respecte les criteres)
     */
	public static boolean isStringCorrectTimeValue(String text)
	{
		boolean result = true;
		
		if(text.length() == 6)//on verifie que l'on a bien que des chiffres et 6 chiffres
		{
			int i = 0;
			while(result == true && i < 6)
			{
				if(Character.isDigit((text.charAt(i))) == false) result = false;
				i++;
			}
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
}