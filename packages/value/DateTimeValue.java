package value;
import java.io.IOException;
import java.time.ZonedDateTime;
/*DATE TIME format local time without the "TZID" property parameter https://icalendar.org/iCalendar-RFC-5545/3-3-5-date-time.html */
/**  La classe DateValue gere la date (annee,mois,jour)
 @see DateValue
 @see TimeValue

 @author Lilou Undreiner
 @author Anne Hugo
 @version 15/03/2022
*/
public class DateTimeValue
{
	/**
    *la date, un objet DateValue
    */
	private DateValue dateValue;
	/**
    *le temps (heure,minute,seconde), un objet TimeValue
    */
	private TimeValue timeValue;
	
	public DateValue getDateValue() { return dateValue; }
	public TimeValue getTimeValue() { return timeValue; }
	
	/**
	* Constructeur par defaut de DateTimeUTCValue
	* utilise l'heure actuelle
	*
	 */
	public DateTimeValue() throws IOException 
	{ 
		ZonedDateTime date = ZonedDateTime.now();
		dateValue = new DateValue(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		timeValue = new TimeValue(date.getHour(), date.getMinute(), date.getSecond());
	}
	/**
    * Constructeur par initialisation de la classe DateTimeValue
    *
	* @param annee l'entier annee 
    * @param mois l'entier mois
    * @param jour l'entier jour
	* @param heure l'entier heure
	* @param minute l'entier pour les minutes
    * @param seconde l'entier pour les secondes
	* 
    */
	public DateTimeValue(int annee, int mois, int jour, int heure, int minute, int seconde) throws IOException
	{
		dateValue = new DateValue(annee, mois, jour);
		timeValue = new TimeValue(heure, minute, seconde);
	}
	/**
    * Constructeur par copie de la classe DateTimeValue
    *
    * @param model la DateTimeValue a copier
    */
	public DateTimeValue(DateTimeValue model) throws IOException
	{
		dateValue = new DateValue(model.dateValue);
		timeValue = new TimeValue(model.timeValue);
	}
	

	/**
    * Creee la String de l'objet DateTimeValue, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()//retourne la date time sous le bon format en fonction des valeurs des attributs que l'on a
	{
		return dateValue.toString() + "T" + timeValue.toString();
	}
	/**
    * Verifie la DateTime passe en parametre se trouv eplus loins dans le temps que this
    *@param comparaison la date time que l'on test
    * @return une chaine de caracteres
    */
	public boolean isPlusLoin(DateTimeValue comparaison)//retourne vrai si la date-time passe en parameter se trouve plus loin dans le temps que this
	{
		boolean result;
        if(dateValue.egalA(comparaison.dateValue) == true)//les deux dates sont pareilles
        {
            if(timeValue.isPlusLoin(comparaison.timeValue) == true)
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
     * Verifie si un String a une valeur DateTime qui respecte les restrictions
	 * @param text une chaine de caractere correspondant a la ligne DateTime d'un fchier ics
     * @return un booleen (vrai si la chaine respecte les criteres)
     */
	
	public static boolean isStringCorrectDateTimeValue(String text)
	{
		boolean result;
		if(text.length() == 15)
		{
			result = (DateValue.isStringCorrectDateValue(text.substring(0, 8)) == true && TimeValue.isStringCorrectTimeValue(text.substring(9, 15))
			&& text.charAt(8) == 'T');
		}
		else result = false;
			
		return result;
	}
	/**
	 * Constructeur de DateTimeValue a partir d'une String 
	 * @param value String qui a une valeur DateTime
     * 
     */
	public DateTimeValue(String value)throws IOException
	{
		this(Integer.parseInt(value.substring(0, 4)), Integer.parseInt(value.substring(4, 6)), Integer.parseInt(value.substring(6, 8)), 
			Integer.parseInt(value.substring(9, 11)), Integer.parseInt(value.substring(11, 13)), Integer.parseInt(value.substring(13, 15)));
	}
}