package property;
import java.io.IOException;

import component.ICal;
import value.DateTimeValue;
import value.DateTimeUTCValue;
import value.DateTimeTZIDValue;
import value.DateValue;
/*DTEND Date-Time End https://icalendar.org/iCalendar-RFC-5545/3-8-2-2-date-time-end.html */
/**  La classe DTEnd gere la propriete DTEND
 
 @see DateValue
 @see DateTimeValue
 @see DateTimeTZIDValue
 @see DateTimeUTCValue
 
 @author Lilou Undreiner
 @author Anne Hugo
 @version 15/03/2022
*/
public class DTEndProperty//heritage aurait pu etre possible
{
	/**
    *la date et le temps dans un objet DateTimeValue
    */
	private DateTimeValue dateTimeValue;
	/**
    *la date et le temps au format UTC dans un objet DateTimeUTCValue
    */
	private DateTimeUTCValue dateTimeUTCValue;
	/**
    *la date et le temps avec un fuseau horaire dans un objet DateTimeTZIDValue
    */
	private DateTimeTZIDValue dateTimeTZIDValue;
	/**
    *la date dans un objet DateValue
    */
	private DateValue dateValue;
	/**
    *une chaine de caractere pour la valeur correspondante (DATE-TIME ou DATE)
    */
	private String value;//valeur que l'on utilise
	/**
    *une chaine de caractere pour le format de l'heure (LOCAL TIME, TZID OU UTC)
    */
	private String format;//format de l'heure que l'on utilise
	/**
    *une chaine de caractere pour une valeur inconnue
    */
	private String valueInconnu = "";//valeur inconnu, utilise pour l'import

	/**
    *Recupere la valeur (DATE-TIME OU DATE)
    *@return une chaine de caractere
    */
	public String getValue() { return value; }
	/**
    *Recupere le format (LOCAL TIME, UTC, TZID)
    *@return une chaine de caractere
    */
	public String getFormat() { return format; }
	
	public DateTimeValue getDateTimeValue() { return dateTimeValue; }
	public DateTimeUTCValue getDateTimeUTCValue() { return dateTimeUTCValue; }
	public DateTimeTZIDValue getDateTimeTZIDValue() { return dateTimeTZIDValue; }
	public DateValue getDateValue() { return dateValue; }
	public String getValueInconnu() { return valueInconnu; }
	
	/**
    * Constructeur par copie, pour la copie d'une DateTimeValue
    *
    * @param model la DateTimeValue a copier
    */
	public DTEndProperty(DateTimeValue model) throws IOException
	{
		dateTimeValue = new DateTimeValue(model);
		value = "DATE-TIME";
		format = "LOCAL TIME";
	}
	/**
    * Constructeur par copie, pour la copie d'une DateTimeUTCValue
    *
    * @param model la DateTimeUTCValue a copier
    */
	public DTEndProperty(DateTimeUTCValue model) throws IOException
	{
		dateTimeUTCValue = new DateTimeUTCValue(model);
		value = "DATE-TIME";
		format = "UTC";
	}
	/**
    * Constructeur par copie, pour la copie d'une DateTimeTZIDValue
    *
    * @param model la DateTimeTZIDValue a copier
    */
	public DTEndProperty(DateTimeTZIDValue model) throws IOException
	{
		dateTimeTZIDValue = new DateTimeTZIDValue(model);
		value = "DATE-TIME";
		format = "TZID";
	}
	/**
    * Constructeur par copie, pour la copie d'une DateValue
    *
    * @param model la DateValue a copier
    */
	public DTEndProperty(DateValue model) throws IOException
	{
		dateValue = new DateValue(model);
		value = "DATE";
		format = "LOCAL TIME";
	}
	
	/**
    * Creee la String de l'objet DTEndProperty, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String result;
		if(valueInconnu.equals("") == true)
		{
			result = "DTEND";
			if(dateTimeValue != null) result += ":" + dateTimeValue.toString();
			else if(dateTimeUTCValue != null) result += ":" + dateTimeUTCValue.toString();
			else if(dateTimeTZIDValue != null) result += ";" + dateTimeTZIDValue.toString();
			else if(dateValue != null) result += ";VALUE=DATE:" + dateValue.toString();
			result += ICal.n; 
		}
		else result = valueInconnu;
		return result;
	}
	/**
	 * Constructeur de la propriete DTEND a partir d'une String 
	 * @param value String pour la valeur de la propriete DTEnd
     * @param line String toute la ligne DTEnd(utile si valeur inconnue)
     */
	public DTEndProperty(String value, String line) throws IOException
	{
		if(DateTimeValue.isStringCorrectDateTimeValue(value)) 
		{
			dateTimeValue = new DateTimeValue(value);
			this.value = "DATE-TIME";
			format = "LOCAL TIME";
		}
		else if(DateTimeUTCValue.isStringCorrectDateTimeUTCValue(value)) 
		{
			dateTimeUTCValue = new DateTimeUTCValue(value);
			this.value = "DATE-TIME";
			format = "UTC";
		}
		else if(DateTimeTZIDValue.isStringCorrectDateTimeTZIDValue(value)) 
		{
			dateTimeTZIDValue = new DateTimeTZIDValue(value);
			this.value = "DATE-TIME";
			format = "TZID";
		}
		else if(DateValue.isStringCorrectDateValue(value)) 
		{
			dateValue = new DateValue(value);
			this.value = "DATE";
			format = "LOCAL TIME";
		}
		else valueInconnu = line + ICal.n;
	}
}