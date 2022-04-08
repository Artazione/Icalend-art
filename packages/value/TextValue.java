package value;
/*TEXT https://icalendar.org/iCalendar-RFC-5545/3-3-11-text.html */
/** La classe TextValue permet de respecter les regles du maximum octet d'une ligne
@author Jules
@author Bavin
@version 1.0
*/
public class TextValue
{
	/**  Un attribut stockant la propriete textValue dans un String */
	private String textValue;
	/**  Un attribut stockant la propriete languageValue avec l'enumeration des langues
	 * @see Language
	 */
	private Language languageValue;//pour LANGUAGE

	/**
    *@return la languageValue
    */
	public Language getLanguageValue() { return languageValue; }
	
	/**  Un constructeur de TextValue a partir d'un string 
	* @param text Le texte dont on veut verifier la longueure d'octet
	  */
	public TextValue(String text)
	{
		textValue = respectRulesOfOctes(text);//on s'assure que l'on ne depasse pas les 75 octes max pas ligne
		languageValue = null;
	}
	/**  Un constructeur de TextValue a partir d'un string et d'une langue
	* @param text Le texte dont on veut verifier la longueure d'octet
	* @param langue La langue du texte
	  */
	public TextValue(String text, Language langue) 
	{
		textValue = respectRulesOfOctes(text);//on s'assure que l'on ne depasse pas les 75 octes max pas ligne
		languageValue = langue;
	}
	/**  Un constructeur par copie de TextValue
	* @param model Tester si le modele respecte la regles des octets
	  */
	public TextValue(TextValue model) 
	{
		textValue = model.textValue;
		languageValue = model.languageValue;
	}
	
	/**  Une fonction forcant un String a respecter la regles des octets d'une ligne
	 * @param text Le texte qui dois respecter la regle
	 * @return Le String au bon format
	  */
	public static String respectRulesOfOctes(String text)
	{
		String result = text;
		if(result != null && result.length() > 0)
		{
			int a = 0;
			int compteurLigne = 1;
			int i = 0;
			while(i < result.length() - 1)//-1 pour eviter les outOfBound des les substring
			{
				try{
					a += result.substring(i, i+1).getBytes("UTF-8").length;

				}catch(java.io.UnsupportedEncodingException e){System.out.println(e.toString());}
				if(a >= 50)//>= 50 pour etre bien sur
				{
					result = result.substring(0, compteurLigne * 50) + "\r\n\t" + result.substring(compteurLigne * 50, result.length());
					a = 0;
					//i += 4;
					compteurLigne++;
				}
				i++;
			}
		}
		return result;
	}
	/**
    * Cree une representation sous forme de String de l'objet TextValue
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String result = "";
		if(languageValue != null) result = "LANGUAGE=" + languageValue.toString() + ":" + textValue;
		else result = textValue;
		return result; 
	}
}