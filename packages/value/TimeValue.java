package value;
import java.io.IOException;
/*TIME https://icalendar.org/iCalendar-RFC-5545/3-3-12-time.html */
/**  La classe TimeValue gere le temps (heure,minute,seconde)
 @author Lilou Undreiner 
 @author Anne Hugo
 @version 15/03/2022
*/
public class TimeValue
{
	/**
    *un entier heure, ATTENTION 2 chiffres et 00-23
    */
	protected int heure;
	/**
    *un entier minute, ATTENTION 2 chiffres et 00-59
    */
	protected int minute;
	 /**
    *un entier seconde, ATTENTION 2 chiffres et 00-60
    */
	protected int seconde; 

	/**
    * Initialise l'heure en levant les exceptions liees au format attendu
    *
    * @param heure l'heure, dont le format est verifie par les exceptions
    */
	
	public void setHeure(int heure) throws IOException
	{
		if(heure > 23 || heure < 0)
		{
			throw new IOException("l'heure doit etre comprise entre 0 et 23 inclus");
		}
		else this.heure = heure;//pas d'erreur
	}
	/**
    * Initialise les minutes en levant les exceptions liees au format attendu
    *
    * @param minute les minutes, dont le format est verifie par les exceptions
    */
	public void setMinute(int minute) throws IOException
	{
		if(minute > 59 || minute < 0)throw new IOException("les minutes doivent etre comprises entre 0 et 59 inclus");
		else this.minute = minute;//pas d'erreur
	}
	/**
    * Initialise les secondes en levant les exceptions liees au format attendu
    *
    * @param seconde les secondes, dont le format est verifie par les exceptions
    */
	public void setSeconde(int seconde) throws IOException
	{
		if(seconde > 60 || seconde < 0) throw new IOException("les secondes doivent etre comprises entre 0 et 60 inclus");
		else this.seconde = seconde;
	}
	/** Getter pour les heures
	 * @return heures
	 */
	public int getHeure() { return heure; }
	/** Getters pour les minutes
	 * @return minutes
	 */
	public int getMinute() { return minute; }
	/** Getters pour les secondes
	 * @return secondes
	 */
	public int getSeconde() { return seconde; }
	
	/**
    * Constructeur par initialisation de la classe TimeValue
    *
	* @param heure l'entier heure
	* @param minute l'entier pour les minutes
    * @param seconde l'entier pour les secondes
	* 
    */
	
	public TimeValue(int heure, int minute, int seconde)throws IOException
	{
		setHeure(heure);
		setMinute(minute);
		setSeconde(seconde);
	}

	/**
    * Constructeur par copie de la classe TimeValue
    *
    * @param model la TimeValue a copier
    */
	public TimeValue(TimeValue model)
	{
		heure = model.heure;
		minute = model.minute;
		seconde = model.seconde;
	}
	/**
    * Creee la String de l'objet TimeValue, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String heureString = "" + heure;
		if(heureString.length() != 2) heureString = "0" + heureString;//pour avoir 2 chiffres
		String minuteString = "" + minute;
		if(minuteString.length() != 2) minuteString = "0" + minuteString;//pour avoir 2 chiffres
		String secondeString = "" + seconde;
		if(secondeString.length() != 2) secondeString = "0" + secondeString;//pour avoir 2 chiffres
		return heureString + minuteString + secondeString;
	}
	/**
     * Retourne vrai si la Time passee en parametre se trouve plus loin dans le temps que this, retourne faux si les deux times sont les memes
	 *
	 * @param comparaison la TimeValue a comparer
	 *
     * @return un booleen 
     */
	public boolean isPlusLoin(TimeValue comparaison)
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
     * Verifie si un String a une valeur Time qui respecte les restrictions
	 * @param text une chaine de caractere correspondant a la ligne Time 
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
				if(Character.isDigit(text.charAt(i)) == false) result = false;
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
	/**
	 * Constructeur de TimeValue a partir d'une String 
	 * @param value String qui a une valeur Time
     * 
     */
	public TimeValue(String value) throws IOException
	{
		this(Integer.parseInt(value.substring(0, 2)), Integer.parseInt(value.substring(2, 4)), Integer.parseInt(value.substring(4,6)));
	}
}