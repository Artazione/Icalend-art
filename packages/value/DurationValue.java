package value;
import java.io.IOException;
/*DURATION https://icalendar.org/iCalendar-RFC-5545/3-3-6-duration.html */
/**  La classe DurationValue gere la valeur de la propriete DURATION
 @see DurationValue
 @see DateTimeValue
 @see DateTimeTZIDValue
 @see DateTimeUTCValue
 
 @author Lilou Undreiner 
 @author Anne Hugo
 @version 15/03/2022
*/
public class DurationValue 
{
	/**
    *un entier jour
    */
	private int day;
	/**
    *un entier heure
    */
	private int hour;
	/**
    *un entier minute
    */
	private int minute;
	/**
    *un entier seconde
    */
	private int second;
	/**
    *un entier nombre de semaines
    */
	private int week;
	/**
    *un entier pour une variable nulle
    */
	public static final int nullVariable = -9999999;//on assigne ca au variable(les int) qui devraient etre null au cas ou pour savoir si c'est nous qui lui avions donne une valeur
	/**
	*une chaine de caractere, qui va etre instancie dans un format attendu dans le fichier ics
	*/
	private String result;

	public int getDay() { return day; }
	public int getHour() { return hour; }
	public int getMinute() { return minute; }
	public int getSecond() { return second; }
	public int getWeek() { return week; }

	/**
    * Initialise l'heure en levant les exceptions liees au format attendu
    *
    * @param hour l'heure, dont le format est verifie par les exceptions
    */
	private void setHour(int hour) throws IOException//nous on dit que l'on connait pas pour les valeurs negatifs
	{
		if(hour > 23 || hour < 0)
		{
			throw new IOException("l'heure doit etre comprise entre 0 et 23 inclus");
		}
		else this.hour = hour;//pas d'erreur
	}
	/**
    * Initialise les minutes en levant les exceptions liees au format attendu
    *
    * @param minute les minutes, dont le format est verifie par les exceptions
    */
	private void setMinute(int minute) throws IOException
	{
		if(minute > 59 || minute < 0)throw new IOException("les minutes doivent etre comprises entre 0 et 59 inclus");
		else this.minute = minute;//pas d'erreur
	}
	/**
    * Initialise les secondes en levant les exceptions liees au format attendu
    *
    * @param second les secondes, dont le format est verifie par les exceptions
    */
	private void setSecond(int second) throws IOException
	{
		if(second > 60 || second < 0) throw new IOException("les secondes doivent etre comprises entre 0 et 60 inclus");
		else this.second = second;
	}
	/**
    * Constructeur par initialisation, dans le cas d'une duree d'un nombre de jour(s), d'heure(s), de minute(s) et de seconde(s)
    *
	* @param day l'entier jour
	* @param hour l'entier heure
	* @param minute l'entier pour les minutes
    * @param second l'entier pour les secondes
	* 
    */
	public DurationValue(int day, int hour, int minute, int second) throws IOException
	{//pour le choix dur-date
		this.day = day;
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		result = "P" + day + "D" + "T" + hour + "H" + minute + "M" + second + "S";
		week = nullVariable;
	}
	/**
    * Constructeur par initialisation, dans le cas d'une duree d'un nombre d'heure(s), de minute(s) et de seconde(s)
    *
	* @param hour l'entier heure
	* @param minute l'entier pour les minutes
    * @param second l'entier pour les secondes
	* 
    */
	public DurationValue(int hour, int minute, int second) throws IOException
	{//pour le choix dur-time
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		result = "P" + "T" + hour + "H" + minute + "M" + second + "S";
		day = nullVariable;
		week = nullVariable;
	}
	/**
    * Constructeur par initialisation, dans le cas d'une duree d'un nombre de semaine(s)
	* 
	* @param week l'entier pour les semaines
	* 
    */
	public DurationValue(int week) throws IOException
	{//pour le choix dur-week
		this.week = week;
		result = "P" + week + "W";
		day = nullVariable;
		hour = nullVariable;
		minute = nullVariable;
		second = nullVariable;
	}
	/**
    * Constructeur par copie, pour la copie d'une DurationValue
    *
    * @param model la DurationValue a copier
    */
	public DurationValue(DurationValue model) 
	{
		day = model.day;
		hour = model.hour;
		minute = model.minute;
		second = model.second;
		week = model.week;
		result = model.result;
	}
	
	/**
    * Retourne la Sring result: la valeur de la duree, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		return result;
	}
	
	/**
     * Verifie si un String a une valeur qui respecte les restrictions
	 * Retourne -1 pour rien, 1 pour dur-date, 2 pour dur-time, 3 pour dur-week
	 *
	 * @param text ne chaine de caractere correspondant a la ligne 
	 *
     * @return un entier (-1,1,2,3)
     */
	public static int isStringCorrectDurationValue(String text) 
	{
		int result;
		if(text.indexOf("P") == 0 && text.contains("D") && text.contains("T") && text.contains("H") && text.contains("M") &&
		text.contains("S"))//pour dur-date
		{
			int d = text.indexOf('D');
			int t = text.indexOf('T');
			int h = text.indexOf('H');
			int m = text.indexOf('M');
			int s = text.indexOf('S');
			if(d < t && t < h && h < m && m < s && s == text.length() -1)//ex: P15DT5H0M20S
			{
				String dayString = text.substring(1, d);
				String hourString = text.substring(t + 1, h);
				String minuteString = text.substring(h + 1, m);
				String secondeString = text.substring(m + 1, s);
				if(isStringFullOfDigit(dayString) && isStringFullOfDigit(hourString) && isStringFullOfDigit(minuteString) && 
				isStringFullOfDigit(secondeString))
				{
					int hour = Integer.parseInt(hourString);
					if(hour > 23 || hour < 0) result = -1;
					else
					{
						int minute = Integer.parseInt(minuteString);
						if(minute > 59 || minute < 0) result = -1;
						else
						{
							int seconde = Integer.parseInt(secondeString);
							if(seconde > 60 || seconde < 0) result = -1;
							else result = 1;
						}
					}
				}
				else result = -1;
			}
			else result = -1;
		}
		else if(text.indexOf("P") == 0 && text.contains("T") && text.contains("H") && text.contains("M"))//pour dur-time
		{
			int t = text.indexOf('T');
			int h = text.indexOf('H');
			int m = text.indexOf('M');
			int s = text.indexOf('S');
			if(t < h && h < m && m < s && s == text.length() -1)
			{
				String hourString = text.substring(t + 1, h);
				String minuteString = text.substring(h + 1, m);
				String secondeString = text.substring(m + 1, s);
				if(isStringFullOfDigit(hourString) && isStringFullOfDigit(minuteString) && 
				isStringFullOfDigit(secondeString))
				{
					int hour = Integer.parseInt(hourString);
					if(hour > 23 || hour < 0) result = -1;
					else
					{
						int minute = Integer.parseInt(minuteString);
						if(minute > 59 || minute < 0) result = -1;
						else
						{
							int seconde = Integer.parseInt(secondeString);
							if(seconde > 60 || seconde < 0) result = -1;
							else result = 2;
						}
					}
				}
				else result = -1;
			}
			else result = -1;
		}
		else if(text.indexOf("P") == 0 && text.contains("W"))//pour dur-week
		{
			int w = text.indexOf("W");//Ex P7W
			if(w == text.length() - 1)
			{
				String weekString = text.substring(1, w);
				if(isStringFullOfDigit(weekString)) result = 3;
				else result = -1;
			}
			else result = -1;
		}
		else result = -1;
		return result;
	}
	/**
     * Verifie que la String n'est faite que de chiffres
	 * @param text une chaine de caractere correspondant a la ligne 
     * @return un booleen (vrai si que des chiffres)
     */
	public static boolean isStringFullOfDigit(String text)
	{
		boolean result = true;
		int i = 0;
		while(result == false && i < text.length())
		{
			if(Character.isDigit(text.charAt(i)) == false)
			{
				result = false;
			}
			else i++;
		}
		return result;
	}
}