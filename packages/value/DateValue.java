package value;
import java.io.IOException;
/*DATE https://icalendar.org/iCalendar-RFC-5545/3-3-4-date.html */
/**  La classe DateValue gere la date (annee,mois,jour)

 @author Lilou Undreiner
 @author Anne Hugo
 @version 15/03/2022
*/
public class DateValue
{
	/**
    *un entier annee, ATTENTION l'annee doit etre en 4 chiffres
    */
	private int annee;
	/**
    *un entier mois, ATTENTION le mois doit etre en 2 chiffres
    */
	private int mois;
	/**
    *un entier jour, ATTENTION le jour doit etre en 2 chiffres
    */
	private int jour;
	
	/**
    *Recupere l'annee
    *@return un entier annee
    */
	public int getAnnee() { return annee; }
	/**
    *Recupere le mois
    *@return un entier mois
    */
	public int getMois() { return mois; }
	/**
    *Recupere le jour
    *@return un entier jour
    */
	public int getJour() { return jour; }
	
	/**
    * Initialise l'annee en levant les exceptions liees au format attendu
    *
    * @param annee l'annee, dont le format est verifie par les exceptions
    */
	public void setAnnee(int annee)throws IOException
	{
		if(annee < 0 || annee > 9999) throw new IOException("l'annee doit etre comprise entre 0 et 9999 incluse");
		else this.annee = annee;
	}
	/**
    * Initialise le mois en levant les exceptions liees au format attendu
    *
    * @param mois le mois, dont le format est verifie par les exceptions
    */
	public void setMois(int mois) throws IOException
	{
		if(mois < 1 || mois > 12) throw new IOException("le mois doit etre compris entre 0 et 12 inclus");
		else this.mois = mois;
	}
	/**
    * Initialise le jour en levant les exceptions liees au format attendu
    *
    * @param jour le jour, dont le format est verifie par les exceptions
    */
	public void setJour(int jour) throws IOException
	{
		if(jour < 1 || jour > getMaxJour(mois, annee)) throw new IOException("le doit etre comprise entre 0 et le max possible dans le mois incluse");
		else this.jour = jour;
	}
	/**
    * Constructeur par initialisation de la classe DateValue
    *
	* @param annee l'entier annee 
    * @param mois l'entier mois
    * @param jour l'entier jour
	* 
    */
	
	public DateValue(int annee, int mois, int jour) throws IOException
	{
		setAnnee(annee);
		setMois(mois);
		setJour(jour);//ATTENTION setJour doit etre appele apres setAnnee et setMois
	}
	/**
    * Constructeur par copie de la classe DateValue
    *
    * @param model la DateValue a copier
    */
	public DateValue(DateValue model) throws IOException
	{
		annee = model.annee;
		mois = model.mois;
		jour = model.jour;
	}
	/**
    * Creee la String de l'objet DateValue, au format attendu dans le fichier ics
    *
    * @return une chaine de caracteres
    */
	public String toString()
	{
		String anneeString = "" + annee;
		while(anneeString.length() != 4)//pour avoir 4 chiffres
		{
			anneeString = "0" + anneeString;
		}
		String moisString = "" + mois;
		if(moisString.length() != 2) moisString = "0" + moisString;//pour avoir 2 chiffres
		String jourString = "" + jour;
		if(jourString.length() != 2) jourString = "0" + jourString;//pour avoir 2 chiffres
		return anneeString + "" + moisString + "" + jourString;
	}
	/**
     * Retourne vrai si la Date passee en parametre est egale a la date de this
	 *
	 * @param comparaison la DateValue a comparer
	 *
     * @return un booleen 
     */
	public boolean egalA(DateValue comparaison)
	{
		boolean result;
		if(annee == comparaison.annee)
		{
			if(mois == comparaison.mois)
			{
				if(jour == comparaison.jour)
				{
					result = true;
				}
				else result = false;
			}
			else result = false;
		}
		else result = false;
		return result;
	}
	/**
     * Retourne vrai si la Date passee en parametre se trouve plus loin dans le temps que this, retourne vrai si les deux dates sont les memes
	 *
	 * @param comparaison la DateValue a comparer
	 *
     * @return un booleen 
     */
	public boolean isPlusLoin(DateValue comparaison)
	{
		boolean result;
		if(annee > comparaison.annee) result = false;
		else 
		{
			if(annee < comparaison.annee) result = true;
			else
			{
				if(mois > comparaison.mois) result = false;
				else 
				{
					if(mois < comparaison.mois) result = true;
					else
					{
						if(jour > comparaison.jour) result = false;
						{
							result = true;
						}
					}
				}
			}
		}
		return result;
	}
	

	private int getMaxJour(int mois, int annee)
	{
		if(mois == 2)
		{
			if(annee % 4 == 0 || annee % 400 == 0) return 29;
			else return 28;
		}
		else if (mois == 4 || mois == 6 || mois == 9 || mois == 11)
		{
			return 30;
		}
		else return 31;
	}
	/**
     * Verifie si un String a une valeur Date qui respecte les restrictions
	 * @param text une chaine de caractere correspondant a la ligne Date
     * @return un booleen (vrai si la chaine respecte les criteres)
     */
	public static boolean isStringCorrectDateValue(String text)
	{
		boolean result = true;
		if(text.length() == 8)
		{
			int i = 0;
			while(result == true && i < 8)
			{
				if(Character.isDigit(text.charAt(i)) == false) result = false;
				i++;
			}
		}
		else result = false;

		return result;
	}
	/**
	 * Constructeur de DateValue a partir d'une String 
	 * @param value String qui a une valeur Date
     * 
     */
	public DateValue(String value) throws IOException 
	{//cree un DateValue en utilisant deja une valeur qui est sous la forme d'un String
		this(Integer.parseInt(value.substring(0, 4)), Integer.parseInt(value.substring(4, 6)), Integer.parseInt(value.substring(6, 8)));
	}
}