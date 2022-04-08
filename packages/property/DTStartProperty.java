package property;
import java.io.IOException;

import component.ICal;
import value.DateTimeValue;
import value.DateTimeUTCValue;
import value.DateTimeTZIDValue;
import value.DateValue;
import value.TimeZones;
/*DTSTART Date-Time Start https://icalendar.org/iCalendar-RFC-5545/3-8-2-4-date-time-start.html */		
/** La classe DTStartProperty gere la propriete DTStart 
 @see DateValue
 @see DateTimeValue
 @see DateTimeTZIDValue
 @see DateTimeUTCValue
 
 @author Lilou Undreiner
 @author Anne Hugo
 @version 15/03/2022
*/
public class DTStartProperty
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
	private String format;
	
	/**
    *une chaine de caractere pour une valeur inconnue
    */
	private String valueInconnu = "";//utilise pour l'import
	
	/**
    *Recupere la valeur de la Date-Time
    *@return une DateTimeValue
    */
	public DateTimeValue getDateTimeValue() { return dateTimeValue; }
	/**
    *Recupere la valeur de la Date-Time au format UTC
    *@return une DateTimeUTCValue
    */
	public DateTimeUTCValue getDateTimeUTCValue() { return dateTimeUTCValue; }
	/**
    *Recupere la valeur de la Date-Time au format TZID
    *@return une DateTimeValueTZID
    */
	public DateTimeTZIDValue getDateTimeTZIDValue() { return dateTimeTZIDValue; }
	/**
    *Recupere la valeur de la Date
    *@return une DateValue
    */
	public DateValue getDateValue() { return dateValue; }
	
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
	
	/**
    *Recupere la TimeZone 
    *@return une TimeZones
    */
	public TimeZones getTimeZones() 
	{ 
		return dateTimeTZIDValue.getTimeZones();
	}
	public String getValueInconnu() { return valueInconnu; }
	
	/**
    * Constructeur par copie, pour la copie d'une DateTimeValue
    *
    * @param model la DateTimeValue a copier
    */
	public DTStartProperty(DateTimeValue model) throws IOException
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
	public DTStartProperty(DateTimeUTCValue model) throws IOException
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
	public DTStartProperty(DateTimeTZIDValue model) throws IOException
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
	public DTStartProperty(DateValue model) throws IOException
	{
		dateValue = new DateValue(model);
		value = "DATE";
		format = "LOCAL TIME";
	}
	
	/**
    * Creee la String de l'objet DTStartProperty, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String result;
		if(valueInconnu.equals("") == true)
		{
			result = "DTSTART";
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
	 * Constructeur de la propriete DTSTART a partir d'une String 
	 * @param value String pour la valeur de la propriete DTSTART
     * @param line String toute la ligne DTSTART(utile si valeur inconnue)
     */
	public DTStartProperty(String value, String line) throws IOException
    {
        if(DateTimeValue.isStringCorrectDateTimeValue(value)) 
        {
            dateTimeValue = new DateTimeValue(value);
            this.value = "DATE-TIME";
            format = "LOCAL TIME";
        }
        else if(DateTimeUTCValue.isStringCorrectDateTimeValue(value)) 
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