package value;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
/*DATE TIME https://icalendar.org/iCalendar-RFC-5545/3-3-5-date-time.html */
/**  La classe DateTimeUTCValue gere les dates au format UTC
 
 @see DateValue
 @see TimeUTCValue
 
 @author Lilou Undreiner
 @author Anne Hugo
 @version 15/03/2022
*/
public class DateTimeUTCValue extends DateTimeValue
{
	/**
    *la date, un objet DateValue
    */
	private DateValue dateValue;
	/**
    *la valeur de la timeUTC (heure,minute,seconde), un objet TimeUTC
    */
	private TimeUTCValue timeUTCValue;
	
	/**
	* Constructeur par defaut de DateTimeUTCValue
	* utilise l'heure actuelle que l'on converti en utc
	*
	 */
	public DateTimeUTCValue() throws IOException 
	{
		ZonedDateTime date = ZonedDateTime.now(ZoneOffset.UTC);
		dateValue = new DateValue(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		timeUTCValue = new TimeUTCValue(date.getHour(), date.getMinute(), date.getSecond());
	}
	/**
    * Constructeur par initialisation de la classe DateTimeUTCValue, initialise les attributs dateValue et timeUTCValue
    *
    * @param annee l'entier annee 
    * @param mois l'entier mois
    * @param jour l'entier jour
	* @param heure l'entier heure
	* @param minute l'entier pour les minutes
    * @param seconde l'entier pour les secondes
	* 
    */
	public DateTimeUTCValue(int annee, int mois, int jour, int heure, int minute, int seconde) throws IOException
	{
		dateValue = new DateValue(annee, mois, jour);
		timeUTCValue = new TimeUTCValue(heure, minute, seconde);
	}
	/**
    * Constructeur par copie de la classe DateTimeUTCValue
    *
    * @param model la DateTimeUTCValue a copier
    */
	public DateTimeUTCValue(DateTimeUTCValue model) throws IOException
	{
		dateValue = new DateValue(model.dateValue);
		timeUTCValue = new TimeUTCValue(model.timeUTCValue);
	}
	
	/**
    * Creee la String de l'objet DateTimeUTCValue, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public @Override String toString()
	{
		return dateValue.toString() + "T" + timeUTCValue.toString();
	}
	
	/**
     * Retourne vrai si la date-time passee en parametre se trouve plus loin dans le temps que this
	 *
	 * @param comparaison la DateTimeUTCValue a comparer
	 *
     * @return un booleen 
     */
	public boolean isPlusLoin(DateTimeUTCValue comparaison)
	{
		boolean result;
		if(dateValue.egalA(comparaison.dateValue) == true)//les deux dates sont les memes
		{
			if(timeUTCValue.isPlusLoin(comparaison.timeUTCValue) == true)
			{
				result = true;
			}
			else result = false;
		}
		else if(dateValue.isPlusLoin(comparaison.dateValue) == true)
		{
			result = true;
		}
		else result = false;
		return result;
	}

	/**
     * Verifie si un String a une valeur DateTimeUTC qui respecte les restrictions
	 * @param text une chaine de caractere correspondant a la ligne UTC d'un fchier ics
     * @return un booleen (vrai si la chaine respecte les criteres)
     */
	
	public static boolean isStringCorrectDateTimeUTCValue(String text)
	{
		boolean result;
		if(text.length() == 16)//ex 19980119T070000Z
		{
			if(DateValue.isStringCorrectDateValue(text.substring(0, 8)) && TimeUTCValue.isStringCorrectTimeUTCValue(text.substring(9, 16))
				&& text.charAt(8) == 'T') 
				result = true;
			else result = false;
		}
		else result = false;
		return result;
	}

	/**
	 * Constructeur de DateTimeUTCValue a partir d'une String 
	 * @param value String qui a une valeur DateTimeUTC
     * 
     */
	public DateTimeUTCValue(String value) throws IOException
	{
		this(Integer.parseInt(value.substring(0, 4)), Integer.parseInt(value.substring(4, 6)), Integer.parseInt(value.substring(6, 8)), 
		Integer.parseInt(value.substring(9, 11)), Integer.parseInt(value.substring(11, 13)), Integer.parseInt(value.substring(13, 15)));
	}
}