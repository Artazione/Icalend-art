package property;
import java.io.IOException;

import component.ICal;
import value.DurationValue;
/*DURATION https://icalendar.org/iCalendar-RFC-5545/3-8-2-5-duration.html */
/** La classe DurationProperty gere la propriete DURATION
 @see DurationValue
 @see DateTimeValue
 @see DateTimeTZIDValue
 @see DateTimeUTCValue
 
 @author Lilou Undreiner
 @author Anne Hugo
 @version 15/03/2022
*/
public class DurationProperty
{
	/**
    *la duree de l'event dans un objet DurationValue
    */
	private DurationValue durationValue;
	/**
    *une chaine de caractere pour une valeur inconnue
    */
	private String valueInconnu = "";//valeur inconnu, utilise pour l'import
	
	public DurationValue getDurationValue() { return durationValue; }
	public String getValueInconnu() { return valueInconnu; }
	
	/**
    * Constructeur par copie, pour la copie d'une DurationValue
    *
    * @param model la DurationValue a copier
    */
	public DurationProperty(DurationValue model) 
	{
		durationValue = new DurationValue(model);
	}
	
	/**
    * Creee la String de l'objet DURATION, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String result;
		if(valueInconnu.equals("") == true) result = "DURATION:" + durationValue.toString() + ICal.n;
		else result = valueInconnu;
		return result;
	}
		/**
	 * Constructeur de la propriete DURATION a partir d'une String 
	 * @param value String pour la valeur de la propriete DURATION
     * @param line String toute la ligne DURATION(utile si valeur inconnue)
     */
	public DurationProperty(String value, String line)
	{
		try
		{
		int durationFormat = DurationValue.isStringCorrectDurationValue(value);
		if(durationFormat == 1)//dur-date ex: P15DT5H0M20S
		{
			int d = value.indexOf("D");
			int h = value.indexOf("H");
			int m = value.indexOf("M");
			durationValue = new DurationValue(Integer.parseInt(value.substring(1, d)), Integer.parseInt(value.substring(d + 2, h)), 
			Integer.parseInt(value.substring(h + 1, m)), Integer.parseInt(value.substring(m + 1, value.length()-1)));
		}
		else if(durationFormat == 2)//dur-time
		{
			int h = value.indexOf("H");
			int m = value.indexOf("M");
			durationValue = new DurationValue(Integer.parseInt(value.substring(2, h)), Integer.parseInt(value.substring(h+1, m)), 
			Integer.parseInt(value.substring(m+1, value.length() - 1)));
		}
		else if(durationFormat == 3)//dur-week
		{
			durationValue = new DurationValue(Integer.parseInt(value.substring(1, value.length() - 1)));
		}
		else valueInconnu = line + ICal.n;
		}catch(IOException e) { System.out.println(e.toString());}
	}
}