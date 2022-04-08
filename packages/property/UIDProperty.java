package property;

import component.ICal;
/*UID https://icalendar.org/iCalendar-RFC-5545/3-8-4-7-unique-identifier.html */
/**  La classe UIDProperty permet d'obtenir un identifiant unique pour nos events.
 @author Jules
 @author Bavin
 @version 1.0
*/
public class UIDProperty
{
	 /**
    *Initiale un String pour notre valeur d'identifiant
    */

	private String value;
	 /**
    *Constructeur par defaut pour la classe UIDProperty
	* @param value la valeur de l'identifiant
    */
	
	public UIDProperty()
	{
		value = (int)(Math.random() *    (99999 - 10000 + 1) + 10000) + ICal.n;//a revoir la facon de genere

	}
	
	/**
    * Cree une representation sous forme de String de l'objet UIDProperty
    *
    * @return une chaine de caracteres ppur l'identifiant
    */
	public String toString()
	{
		return "UID:" + value;
	}
	
	/**
    * Constructeur par copie pour la classe UIDProperty
    *
    * @param value la valeur de l'identifiant
    */
	public UIDProperty(String value)
	{
		this.value = value + ICal.n;
	}
}