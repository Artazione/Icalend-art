package value;
import java.io.IOException;
import java.time.LocalDateTime;
/*DATE TIME au format LOCAL TIME AND TIME ZONE REFERENCE https://icalendar.org/iCalendar-RFC-5545/3-3-5-date-time.html */
/**  La classe DateTimeTZIDValue sert a traiter les dates avec le fuseau horaire integre

 @see DateValue
 @see TimeTZIDValue
 
 @author Lilou Undreiner
 @author Anne Hugo
 @version 15/03/2022
*/
public class DateTimeTZIDValue extends DateTimeValue
{
	/**
    *la date, un objet DateValue
    */
	private DateValue dateValue;
	/**
    *la valeur de la timezone, un objet TimeTZIDValue
    */
	private TimeTZIDValue timeTZIDValue;
	
	 /**
    *Recupere la valeur de la timezone 
    *@return un objet TimeZones
    */
	public TimeZones getTimeZones() { return timeTZIDValue.getTimeZones(); }

	/**
    * Constructeur par initialisation de la classe DateTimeTZIDValue, initialise les attributs dateValue et timeTZIDValue
    *
    * @param annee l'entier annee 
    * @param mois l'entier mois
    * @param jour l'entier jour
	* @param heure l'entier heure
	* @param minute l'entier pour les minutes
    * @param seconde l'entier pour les secondes
	* @param timeZones objet TimeZones pour la timezone
    */
	public DateTimeTZIDValue(int annee, int mois, int jour, int heure, int minute, int seconde, TimeZones timeZones) throws IOException
	{
		dateValue = new DateValue(annee, mois, jour);
		timeTZIDValue = new TimeTZIDValue(heure, minute, seconde, timeZones);
	}
	/**
    * Constructeur par copie de la classe DateTimeTZIDValue
    *
    * @param model la DateTimeTZIDValue a copier
    */
	public DateTimeTZIDValue(DateTimeTZIDValue model) throws IOException
	{
		timeTZIDValue = new TimeTZIDValue(model.timeTZIDValue);
		dateValue = new DateValue(model.dateValue);
	}
	/**
    * Creee la String de l'objet DateTimeTZIDValue, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public @Override String toString()
	{
		String heureString = "" + timeTZIDValue.heure;
		if(heureString.length() != 2) heureString = "0" + heureString;//pour avoir 2 chiffres
		String minuteString = "" + timeTZIDValue.minute;
		if(minuteString.length() != 2) minuteString = "0" + minuteString;//pour avoir 2 chiffres
		String secondeString = "" + timeTZIDValue.seconde;
		if(secondeString.length() != 2) secondeString = "0" + secondeString;//pour avoir 2 chiffres
		return "TZID=" + timeTZIDValue.getTimeZones().toString() + ":" + dateValue.toString() + "T" + heureString + minuteString + secondeString;
	}
	
	/**
     * Convertit une DateTimeTZIDValue passee en parametre dans le meme fuseau horaire que this et la retourne
	 *
	 * @param model la DateTimeTZIDValue a convertir
	 *
     * @return la DateTimeTZIDValue convertie dans le meme fuseau horaire que l'objet courant
     */
	public DateTimeTZIDValue convertDansMaTimeZone(DateTimeTZIDValue model)//
	{
		int annee = model.dateValue.getAnnee();
		int mois = model.dateValue.getMois();
		int jour = model.dateValue.getJour();
		int heure = model.timeTZIDValue.getHeure();
		int minute = model.timeTZIDValue.getMinute();
		int seconde = model.timeTZIDValue.getSeconde();
		
		int heureOffset = timeTZIDValue.getTimeZones().getHourOffset() - model.timeTZIDValue.getTimeZones().getHourOffset();
		int minuteOffset = timeTZIDValue.getTimeZones().getMinuteOffset() - model.timeTZIDValue.getTimeZones().getMinuteOffset();
		
		LocalDateTime moi = LocalDateTime.of(annee, mois, jour, heure, minute, seconde);
		moi = moi.plusMinutes(minuteOffset);
		moi = moi.plusHours(heureOffset);
		
		DateTimeTZIDValue result = null;
		try{
			result = new DateTimeTZIDValue(moi.getYear(), moi.getMonthValue(), moi.getDayOfMonth(), moi.getHour(), moi.getMinute(), moi.getSecond(), timeTZIDValue.getTimeZones());
		}catch(IOException e) {System.out.println(e.toString());}
		
		return result;
	}
	
	/**
     * Retourne vrai si la date-time passee en parametre se trouve plus loin dans le temps que this
	 *
	 * @param comparaisonDifferentes la DateTimeTZIDValue a comparer
	 *
     * @return un booleen 
     */
	public boolean isPlusLoin(DateTimeTZIDValue comparaisonDifferentes)
	{
		DateTimeTZIDValue comparaison = null;
		if(comparaisonDifferentes.timeTZIDValue.getTimeZones() != timeTZIDValue.getTimeZones())
		{
			try{
				comparaison = new DateTimeTZIDValue(convertDansMaTimeZone(comparaisonDifferentes));//on convertit dans le meme time zone
			}catch(IOException e) {}
		}
		else 
		{
			try{
				comparaison = new DateTimeTZIDValue(comparaisonDifferentes);//pas besoin de convertir
			}catch(IOException e) {}
		}			
		boolean result;
		
		if(dateValue.egalA(comparaison.dateValue) == true)//les deux dates sont pareilles
		{
			if(timeTZIDValue.isPlusLoin(comparaison.timeTZIDValue) == true)
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
     * Verifie si un String a une valeur DateTimeTZID qui respecte les restrictions
	 * @param text une chaine de caractere correspondant a la ligne TZID d'un fchier ics
     * @return un booleen (vrai si la chaine respecte les criteres)
     */
	public static boolean isStringCorrectDateTimeTZIDValue(String text)
	{
		boolean result;
		int separation = text.indexOf(':');//exemple de se qu'on peut avoir dans text TZID=America/New_York:19970714T133000
		if(separation != -1 && separation != (text.length() - 1) )
		{
			if(text.length() >= 6)
			{
				if(text.substring(0, 5).equals("TZID="))
				{
					String parameterValue = text.substring(5, separation);//ici dans l'exemple America/New_York
					if(TimeZones.isStringCorrectTimeZones(parameterValue))
					{
						String value = text.substring(separation + 1, text.length());//on regarde ca 19970714T133000
						if(DateTimeValue.isStringCorrectDateTimeValue(value)) result = true;
						else result = false;
					}
					else result = false;
				}
				else result = false;
			}
			else result = false;
		}
		else result = false;
		return result;
	}
	
	/**
	 * Constructeur de DateTimeTZIDValue a partir d'une String 
	 * @param value String qui a une valeur DateTimeTZID 
     * 
     */
	public DateTimeTZIDValue(String value) throws IOException
	{
		/*int separation = value.indexOf(':');//exemple de se qu'on peut avoir dans text TZID=America/New_York:19970714T133000
		String parameterValue = value.substring(5, separation);//ici dans l'exemple America/New_York
		String dateTimeValue = value.substring(separation + 1, value.length());//on regarde ca 19970714T133000*/
		
		this(Integer.parseInt(value.substring(value.indexOf(':') + 1, value.length()).substring(0, 4)), 
		Integer.parseInt(value.substring(value.indexOf(':') + 1, value.length()).substring(4, 6)), 
		Integer.parseInt(value.substring(value.indexOf(':') + 1, value.length()).substring(6, 8)), 
		Integer.parseInt(value.substring(value.indexOf(':') + 1, value.length()).substring(9, 11)), 
		Integer.parseInt(value.substring(value.indexOf(':') + 1, value.length()).substring(11, 13)), 
		Integer.parseInt(value.substring(value.indexOf(':') + 1, value.length()).substring(13, 15)),
		TimeZones.convertStringToTimeZones(value.substring(5, value.indexOf(':'))));
		//c'est horrible mais on peut pas faire comme this() doit etre la premiere ligne dans la methode
	}
}