package value;


public class TextLineValue
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
	public TextLineValue(String text)
	{
		textValue = respectRulesOfOctes(text);//on s'assure que l'on ne depasse pas les 75 octes max pas ligne
		languageValue = null;
	}
	/**  Un constructeur de TextValue a partir d'un string et d'une langue
	* @param text Le texte dont on veut verifier la longueure d'octet
	* @param langue La langue du texte
	  */
	public TextLineValue(String text, Language langue) 
	{
		textValue = respectRulesOfOctes(text);//on s'assure que l'on ne depasse pas les 75 octes max pas ligne
		languageValue = langue;
	}
	/**  Un constructeur par copie de TextValue
	* @param model Tester si le modele respecte la regles des octets
	  */
	public TextLineValue(TextLineValue model) 
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
		if(text != null && text.length() > 0)
		{
			int compteur = 0;
			int i = 0;
			while( i < text.length() && compteur <= 45)//pour etre sur <= 55
			{
				try{
					compteur += text.substring(i, i+1).getBytes("UTF-8").length;

				}catch(java.io.UnsupportedEncodingException e){System.out.println(e.toString());} 
				i++;
			}
			text = text.substring(0, i);
		}
		return text;
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