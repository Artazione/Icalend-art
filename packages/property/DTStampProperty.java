package property;
import java.io.IOException;

import component.ICal;
import value.DateTimeUTCValue;
/*DTSTAMP Date-Time Stamp https://icalendar.org/iCalendar-RFC-5545/3-8-7-2-date-time-stamp.html */
/**  La classe DTStampProperty gere la propriete DTStamp
 
 @see DateTimeTZIDValue
 @see DateTimeUTCValue
 @author Lilou Undreiner
 @author Anne Hugo
@version 15/03/2022
*/
public class DTStampProperty
{
	/**
    *la date et le temps au format UTC dans un objet DateTimeUTCValue
    */
	private DateTimeUTCValue dateTimeUTCValue;
	
	/**
    *une chaine de caractere pour une valeur inconnue
    */
	private String valueInconnu = "";//valeur inconnue, utilise pour l'import
	
	/**
    * Constructeur par copie, pour la copie d'une DateTimeUTCValue
    *
    * @param model la DateTimeUTCValue a copier
    */
	public DTStampProperty(DateTimeUTCValue model) throws IOException
	{
		dateTimeUTCValue = new DateTimeUTCValue(model);
	}
	
	/**
    * Creee la String de l'objet DTStampProperty, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String result;
		if(valueInconnu.equals("") == true)
		{
			result = "DTSTAMP:" + dateTimeUTCValue.toString() + ICal.n;
		}
		else result = valueInconnu;
		return result;
	}
	
	/**
	 * Constructeur de la propriete DTSTAMP a partir d'une String 
	 * @param value String pour la valeur de la propriete DTSTAMP
     * @param line String toute la ligne DTSTAMP(utile si valeur inconnue)
     */
	public DTStampProperty(String value, String line) throws IOException
	{
		if(DateTimeUTCValue.isStringCorrectDateTimeUTCValue(value)) 
		{
			dateTimeUTCValue = new DateTimeUTCValue(value);
		}
		else valueInconnu = line + ICal.n; 
	}
}