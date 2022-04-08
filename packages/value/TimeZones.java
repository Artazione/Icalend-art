package value;
//
/**  L'enumeration TimeZones represente les fuseaux horaires possible tous n'y sont pas inscrit
 @author Jules
 @author Bavin
 @version 1.0
*/
public enum TimeZones
{
	/**area covered Central Africa Time*/
	AfricaMaputo("Africa/Maputo", 2, 0),
	/**area covered Pacific*/
	AmericaLos_Angeles("America/Los_Angeles", -8, 0),
	/**area covered Eastern (most areas)*/
	AmericaNew_York("America/New_York", -5, 0),
	/**area covered Kazakhstan (most areas)*/
	AsiaAlmaty("Asia/Almaty", 6, 0),
	/**area covered Western Australia (most areas)*/
	AustraliaPerth("Australia/Perth", 8, 0),
	/**area covered New South Wales (most areas)*/
	AustraliaSydney("Australia/Sydney", 10, 0),
	/**allemagne*/
	EuropeBerlin("Europe/Berlin", 1, 0),
	/**area covered Portugal (mainland)*/
	EuropeLisbon("Europe/Lisbon", 0, 0),
	/**area covered Spain (mainland)*/
	EuropeMadrid("Europe/Madrid", 1, 0),
	/**area covered MSK+00 - Moscow area*/
	EuropeMoscow("Europe/Moscow", 3, 0),
	/**area covered Europe */
	EuropeParis("Europe/Paris", 1, 0),
	/**area covered New Zealand time*/
	PacificAuckland("Pacific/Auckland", 12, 0),
	/**area covered Hawaii*/
	PacificHonolulu("Pacific/Honolulu", 10, 0);
	/**
    *Cree un String vide pour notre valeur de timezone
    */
	private String result = "";
	/**pour avoir l'offset utc en fonction de la time zone, pour avoir la difference on a: hourOffset h minuteOffset min
	pour la difference avec les heures **/
	private int hourOffset;
	/**Pour avoir la difference avec les minutes*/
	private int minuteOffset;
	/**@return différence avec les heures */
	public int getHourOffset() { return hourOffset; }
	/**@return différence avec les minutes */
	public int getMinuteOffset() { return minuteOffset; }
	
	TimeZones(String res, int hourOffset, int minuteOffset)
	{
		result = res;
		this.hourOffset = hourOffset;
		this.minuteOffset = minuteOffset;
	}

	/**
    * Cree une representation sous forme de String de l'objet TimeZones
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		return result;
	}
	/**
	 * Verifie si un String correspond a une timezone que l'on connait
	* @param value le string que l'on test
    * @return vrai si on reconnait le string comme une timezone valide
	 
     */
	public static boolean isStringCorrectTimeZones(String value)
	{
		boolean result = false;
		TimeZones[] t = TimeZones.values();
		int i = 0;
		while(result == false && i < t.length)
		{
			if(value.equals(t[i].toString())) result = true;
			else i++;
		}
		return result;
	}
	/**
    * Convertit le string passe en parametres en la timezone auquel il correspond
    * @param value le string correspondant a une timezone valide
	* @return la TimeZone valide
    */
	public static TimeZones convertStringToTimeZones(String value)
	{
		TimeZones result = null;
		TimeZones[] t = TimeZones.values();
		int i = 0;
		while(result == null && i < t.length)
		{
			if(value.equals(t[i].toString())) result = t[i];
			else i++;
		}
		return result;
	}		
}