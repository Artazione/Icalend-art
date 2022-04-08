package property;

import component.ICal;
import value.TextValue;

/*DESCRIPTION https://icalendar.org/iCalendar-RFC-5545/3-8-1-5-description.html */
/** La classe DescriptionProperty permet de cree une propriete description d'un event 
 @author Jules
 @author Bavin
 @version 1.0
*/
public class DescriptionProperty
{
	/**  Un attribut stockant la propriete textValue */
	private TextValue textValue;
	
	public TextValue getTextValue() { return textValue; }
	
	/**  Un constructeur de DescriptionProperty 
	 * @param model la valeur a copier de la propriete DescriptionProperty.
	 * @see TextValue
	  */
	public DescriptionProperty(TextValue model) 
	{
		textValue = new TextValue(model);
	}
	
	/**
    * Cree une representation sous forme de String de l'objet DescriptionProperty
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String result = "DESCRIPTION";
		if(textValue.getLanguageValue() != null) result += ";";
		else result += ":";
		return result + textValue.toString() + ICal.n;
	}
	
	/**  Un constructeur de DescriptionProperty 
	 * @param value le String qu'on va utiliser pour la description.
	 * @see TextValue
	  */
	public DescriptionProperty(String value)
	{
		this(new TextValue(value));
	}
}