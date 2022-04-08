package component;
import java.io.IOException;
import java.util.Scanner;

import component.*;
import property.*;
import value.*;
/*VEVENT https://icalendar.org/iCalendar-RFC-5545/3-3-9-period-of-time.html */
/**  La classe VeventComponent permet de generer une propriete d'un event de notre calendrier 
 @author Jules 
 @author Bavin 
 @version 1.0
*/
public class VEventComponent
{
	private static final String n = "\r\n";//notre CRLF en une variable
	/** String du resultat de notre vEventComponent */
	private String result;
	/**String pour quand on import, ce String correspond au ligne dont la propriete est inconnu ou la valeur est inconnu ou autre*/
	private String textInconnu = "";
	
	/*propriete obligatoire*/
	/**  Un attribut stockant la propriete dTStampProperty, l'heure ou est cree l'event */
	private DTStampProperty dTStampProperty;
	/**  Un attribut stockant la propriete uIDProperty, l'identifiant unique de nos events */
	private UIDProperty uIDProperty;
	/**  Un attribut stockant la propriete dTStartProperty, la date où l'event commence */
	private DTStartProperty dTStartProperty;//ca presque obligatoire, mais nous on oblige que cette propriete soit presente.
	/*plus obligatoire*/
	/**  Un attribut stockant la propriete dTEndProperty, la date où l'event se termine */
	private DTEndProperty dTEndProperty;//ATTENTION soit dtend soit duration mais pas les deux ou aucun*
	/**  Un attribut stockant la propriete durationProperty, la duree de l'event */
	private DurationProperty durationProperty;
	/**  Un attribut stockant la propriete descriptionProperty, la description de l'event */
	private DescriptionProperty descriptionProperty; 
	private SummaryProperty summaryProperty;
	
	
	/**@return la date ou a ete cree l'event */
	public DTStampProperty getDTStampProperty() { return dTStampProperty; }
	/**@return l'identifiant unique de l'event */
    public UIDProperty getUIDProperty() { return uIDProperty; }
	/**@return la date de debut de l'event */
    public DTStartProperty getDTStartProperty() { return dTStartProperty; }
	/**@return la date de fin de l'event */
    public DTEndProperty getDTEndProperty() { return dTEndProperty; }
	/**@return la duree de l'event */
    public DurationProperty getDurationProperty() { return durationProperty; }
	/**@return la description de l'event */
    public DescriptionProperty getDescriptionProperty() { return descriptionProperty; }
	public SummaryProperty getSummaryProperty() { return summaryProperty; }
	
	
	/**
    * Constructeur du vEventComponent a partir d'une DateTimeValue
	*@see DateTimeValue
	*@param dTStartValue date du debut de l'event
    */
	
	public VEventComponent(DateTimeValue dTStartValue) throws IOException
	{
		setUp();
		dTStartProperty = new DTStartProperty(dTStartValue);
	}
	/**
    * Constructeur du vEventComponent a partir d'une DateTimeUTCValue
	*@see DateTimeUTCValue
	*@param dTStartValue date du debut de l'event
    */
	public VEventComponent(DateTimeUTCValue dTStartValue) throws IOException
	{
		setUp();
		dTStartProperty = new DTStartProperty(dTStartValue);
	}
	/**
    * Constructeur du vEventComponent a partir d'une DateTimeTZIDValue
	*@see DateTimeTZIDValue
	*@param dTStartValue date du debut de l'event
    */
	public VEventComponent(DateTimeTZIDValue dTStartValue) throws IOException
	{
		setUp();
		dTStartProperty = new DTStartProperty(dTStartValue);
	}
	/**
    * Constructeur du vEventComponent a partir d'une DateValue
	*@see DateValue
	*@param dTStartValue date du debut de l'event
    */
	public VEventComponent(DateValue dTStartValue) throws IOException
	{
		setUp();
		dTStartProperty = new DTStartProperty(dTStartValue);
	}
	/**
    * Procedure utiliser par les constructeurs de vEvent pour creer un event
    */
	private void setUp() throws IOException
	{
		result = "BEGIN:VEVENT" + n;
		dTStampProperty = new DTStampProperty(new DateTimeUTCValue());
		uIDProperty = new UIDProperty();
	}
	public void addDTStartProperty(DateTimeValue dTStartValue) throws IOException
	{
		if(dTEndProperty != null)
		{
			if(dTEndProperty.getValue() == "DATE-TIME" && dTEndProperty.getFormat() == "LOCAL TIME")//il faut meme valeur et format que dans dTStartProperty
			{
				if(dTStartValue.isPlusLoin(dTEndProperty.getDateTimeValue()))
				{
					dTStartProperty = new DTStartProperty(dTStartValue);
				}
				else throw new IOException("la date utilise pour DTSart doit se trouver plus top dans le temps que celle dans DTEND");
			}
			else throw new IOException("ce n est pas le type de valeur utilise que dans DTEND");
		}
		else dTStartProperty = new DTStartProperty(dTStartValue);
	}
	public void addDTStartProperty(DateTimeUTCValue dTStartValue) throws IOException
	{
		if(dTEndProperty != null)
		{
			if(dTEndProperty.getValue() == "DATE-TIME" && dTEndProperty.getFormat() == "UTC")//il faut meme valeur et format que dans dTStartProperty
			{
				if(dTStartValue.isPlusLoin(dTEndProperty.getDateTimeUTCValue()))
				{
					dTStartProperty = new DTStartProperty(dTStartValue);
				}
				else throw new IOException("la date utilise pour DTSart doit se trouver plus top dans le temps que celle dans DTEND");
			}
			else throw new IOException("ce n est pas le type de valeur utilise que dans DTEND");
		}
		else dTStartProperty = new DTStartProperty(dTStartValue);
	}
	public void addDTStartProperty(DateTimeTZIDValue dTStartValue) throws IOException
	{
		if(dTEndProperty != null)
		{
			if(dTEndProperty.getValue() == "DATE-TIME" && dTEndProperty.getFormat() == "TZID")//il faut meme valeur et format que dans dTStartProperty
			{
				if(dTStartValue.isPlusLoin(dTEndProperty.getDateTimeTZIDValue()))
				{
					dTStartProperty = new DTStartProperty(dTStartValue);
				}
				else throw new IOException("la date utilise pour DTSart doit se trouver plus top dans le temps que celle dans DTEND");
			}
			else throw new IOException("ce n est pas le type de valeur utilise que dans DTEND");
		}
		else dTStartProperty = new DTStartProperty(dTStartValue);
	}
	public void addDTStartProperty(DateValue dTStartValue) throws IOException
	{
		if(dTEndProperty != null)
		{
			if(dTEndProperty.getValue() == "DATE" && dTEndProperty.getFormat() == "LOCAL TIME")//il faut meme valeur et format que dans dTStartProperty
			{
				if(dTStartValue.isPlusLoin(dTEndProperty.getDateValue()))
				{
					dTStartProperty = new DTStartProperty(dTStartValue);
				}
				else throw new IOException("la date utilise pour DTSart doit se trouver plus top dans le temps que celle dans DTEND");
			}
			else throw new IOException("ce n est pas le type de valeur utilise que dans DTEND");
		}
		else dTStartProperty = new DTStartProperty(dTStartValue);
	}
	/**
    * Setters de la date de fin d'un event a partir d'une DateTimeValue
	*@param dTEndValue date de fin de l'event
	*@see DateTimeValue
    */
	public void addDTEndProperty(DateTimeValue dTEndValue) throws IOException
	{
		if(durationProperty == null)
		{
			if(dTStartProperty != null)
			{
				if(dTStartProperty.getValue() == "DATE-TIME" && dTStartProperty.getFormat() == "LOCAL TIME")//il faut meme valeur et format que dans dTStartProperty
				{
					if(dTStartProperty.getDateTimeValue().isPlusLoin(dTEndValue))
					{
						dTEndProperty = new DTEndProperty(dTEndValue);
					}
					else throw new IOException("la date utilise pour DTEND doit se trouver plus loin dans le temps que celle dans DTSART");
				}
				else throw new IOException("ce n est pas le type de valeur utilise que dans DTSART");
			}
			else dTEndProperty = new DTEndProperty(dTEndValue);
		}
		else throw new IOException("il faut soit utilise DURATION ou DTEND ou aucun mais pas les deux");
	}
	/**
    * Setters de la date de fin d'un event a partir d'une DateTimeUTCValue
	*@param dTEndValue date de fin de l'event
	*@see DateTimeUTCValue
    */
	public void addDTEndProperty(DateTimeUTCValue dTEndValue) throws IOException
	{
		if(durationProperty == null)
		{
			if(dTStartProperty != null)
			{
				if(dTStartProperty.getValue() == "DATE-TIME" && dTStartProperty.getFormat() == "UTC")//il faut meme valeur et format que dans dTStartProperty
				{
					if(dTStartProperty.getDateTimeUTCValue().isPlusLoin(dTEndValue))
					{
						dTEndProperty = new DTEndProperty(dTEndValue);
					}
					else throw new IOException("la date utilise pour DTEND doit se trouver plus loin dans le temps que celle dans DTSART");
				}
				else throw new IOException("ce n est pas le type de valeur utilise que dans DTSART");
			}
			else dTEndProperty = new DTEndProperty(dTEndValue);
		}
		else throw new IOException("il faut soit utilise DURATION ou DTEND ou aucun mais pas les deux");
	}
	/**
    * Setters de la date de fin d'un event a partir d'une DateTimeTZIDValue
	*@param dTEndValue date de fin de l'event
	*@see DateTimeTZIDValue
    */
	public void addDTEndProperty(DateTimeTZIDValue dTEndValue) throws IOException
	{
		if(durationProperty == null)
		{
			if(dTStartProperty != null)
			{
				if(dTStartProperty.getValue() == "DATE-TIME" && dTStartProperty.getFormat() == "TZID")//il faut meme valeur et format que dans dTStartProperty
				{
					if(dTStartProperty.getDateTimeTZIDValue().isPlusLoin(dTEndValue))
					{
						dTEndProperty = new DTEndProperty(dTEndValue);
					}
					else throw new IOException("la date utilise pour DTEND doit se trouver plus loin dans le temps que celle dans DTSART");
				}
				else throw new IOException("ce n est pas le type de valeur utilise que dans DTSART");
			}
			else dTEndProperty = new DTEndProperty(dTEndValue);
		}
		else throw new IOException("il faut soit utilise DURATION ou DTEND ou aucun mais pas les deux");
	}
	/**
    * Setters de la date de fin d'un event a partir d'une DateValue
	*@param dTEndValue date de fin de l'event
	*@see DateValue
    */
	public void addDTEndProperty(DateValue dTEndValue) throws IOException
	{
		if(durationProperty == null)
		{
			if(dTStartProperty != null)
			{
				if(dTStartProperty.getValue() == "DATE" && dTStartProperty.getFormat() == "LOCAL TIME")//il faut meme valeur et format que dans dTStartProperty
				{
					if(dTStartProperty.getDateValue().isPlusLoin(dTEndValue))
					{
						dTEndProperty = new DTEndProperty(dTEndValue);
					}
					else throw new IOException("la date utilise pour DTEND doit se trouver plus loin dans le temps que celle dans DTSART");
				}
				else throw new IOException("ce n est pas le type de valeur utilise que dans DTSART");
			}
			else dTEndProperty = new DTEndProperty(dTEndValue);
		}
		else throw new IOException("il faut soit utilise DURATION ou DTEND ou aucun mais pas les deux");
	}
	/**
    * Setters de la duree d'un event a partir d'une durationValue
	*@param durationValue duree de l'event
	*@see DurationValue
    */
	public void addDurationProperty(DurationValue durationValue) throws IOException
	{
		if(dTEndProperty == null)
		{
			durationProperty = new DurationProperty(durationValue);
		}
		else throw new IOException("il faut soit utilise DURATION ou DTEND ou aucun mais pas les deux");
	}
	/**
    * Setters de la Description d'un event a partir d'une descriptionValue
	*@param descriptionValue description de l'event
	*@see TextValue
    */
	public void addDescriptionProperty(TextValue descriptionValue)
	{
		descriptionProperty = new DescriptionProperty(descriptionValue);
	}
	
	public void addSummaryProperty(TextLineValue summaryValue)
	{
		summaryProperty = new SummaryProperty(summaryValue);
	}
	
	/**
    * Ajoute dans un String les proprietes obligatoires d'un event et le ferme.
    *
    * @return une chaine de caracteres
    */
	public String toString() 
	{
		result = "BEGIN:VEVENT" + n;
        result += uIDProperty.toString();
        result += dTStampProperty.toString();
        if(dTStartProperty != null) result += dTStartProperty.toString();
        if(dTEndProperty != null) result += dTEndProperty.toString();
        if(durationProperty != null) result += durationProperty.toString();
		if(summaryProperty != null) result += summaryProperty.toString();
        if(descriptionProperty != null) result += descriptionProperty.toString();
        result += textInconnu;
        return result + "END:VEVENT" + n;
	}
	
	/**
    * Constructeur d'un Event par lecture d'une fichier importe
	* @param mo Scanner permettant le parsing du ics
    */
	public VEventComponent(Scanner mo)//utile quand on import un ics
	{
		result = "BEGIN:VEVENT" + n; 
		uIDProperty = new UIDProperty();
		boolean isOver = false;
		boolean isPreviousLineDescription = false;//pour savoir si la ligne d'avant etait une partie de description
		String previousLineDescription = "";//les lignes d'avant dans la description
		try{
		while(isOver == false)
		{
			String line = mo.nextLine();
			int separationP = line.indexOf(':');
			int separationV = line.indexOf(';');
			int separation;
			if(separationP < separationV || separationV == -1)
			{
				separation = separationP;
			}
			else
			{
				separation = separationV;
			}
			if(separation != -1 || ( (line.indexOf("\t") == 0 || line.indexOf(" ") == 0) && isPreviousLineDescription == true))//on saute les lignes ou y a pas proprietes pour l'instant
			{
				String propertyName = "";
				String valueName = "";
				if(line.indexOf("\t") != 0 && line.indexOf(" ") != 0)
				{
					if(isPreviousLineDescription == true)
					{
						isPreviousLineDescription = false;
						descriptionProperty = new DescriptionProperty(previousLineDescription);
						previousLineDescription = "";
					}
					propertyName = line.substring(0, separation);
					valueName = line.substring(separation + 1, line.length());
				}
				if(propertyName.equals("END") && valueName.equals("VEVENT"))//on arrive dans a la fin du VEVENT
				{
					isOver = true;
				}
				else if(propertyName.equals("UID"))
				{
					uIDProperty = new UIDProperty(valueName);
				}
				else if(propertyName.equals("DTSTAMP"))
				{
					dTStampProperty = new DTStampProperty(valueName, line);
				}
				else if(propertyName.equals("DTSTART"))
				{
					dTStartProperty = new DTStartProperty(valueName, line);
				}
				else if(propertyName.equals("DTEND"))
				{
					dTEndProperty = new DTEndProperty(valueName, line);
				}
				else if(propertyName.equals("DURATION"))
				{
					durationProperty = new DurationProperty(valueName, line);
				}
				else if(propertyName.equals("DESCRIPTION") || ((line.indexOf("\t") == 0 || line.indexOf(" ") == 0) && isPreviousLineDescription == true))
				{
					if(line.indexOf("\t") == 0 || line.indexOf(" ") == 0)
					{
						previousLineDescription += line.substring(2, line.length());
					}
					else
					{
						isPreviousLineDescription = true;
						previousLineDescription += valueName;
					}
				}
				else if(propertyName.equals("SUMMARY"))
				{
					summaryProperty = new SummaryProperty(valueName);
				}
				else 
				{
					textInconnu += line + ICal.n;
				}
			}
			else 
			{
				textInconnu += line + ICal.n;
			}
		}
		}catch(Exception e) { System.out.println(e.toString()); }
	}
}