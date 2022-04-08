import java.io.IOException;
import java.io.FileNotFoundException;

import component.*;
import property.*;
import value.*;

public class Afficher 
{
	public static void afficherVEvent(ICal i, int id)
	{
		VEventComponent vEvent = i.getVEventComponent()[id];
		if(vEvent.getDTStartProperty() != null) 
		{
			System.out.print("la date de debut est de: ");
			afficherDTStartProperty(i, id);
		}
		else System.out.println("la date de debut n'a pas a ete specifie");
		if(vEvent.getDTEndProperty() != null) 
		{
			System.out.print("la date de fin est de: ");
			afficherDTEndProperty(i, id);
		}
		else System.out.println("la date de fin n'a pas a ete specifie");
		if(vEvent.getDurationProperty() != null) 
		{
			System.out.print("la date de fin est de: ");
			afficherDurationProperty(i, id);
		}
		else System.out.println("la duree n'a pas ete specifie");
		if(vEvent.getSummaryProperty() != null) 
		{
			System.out.print("le resume est: ");
			afficherSummaryProperty(i, id);
		}
		else System.out.println("le resume n'a pas ete specifie");
		if(vEvent.getDescriptionProperty() != null) 
		{
			System.out.print("la description est: ");
			afficherDescriptionProperty(i, id);
		}
		else System.out.println("la description n'a pas ete specifie");
	}
	/*PROPERTY*/
	public static void afficherDTStartProperty(ICal i, int id)
	{
		DTStartProperty dTStartProperty = i.getVEventComponent()[id].getDTStartProperty();
		if(dTStartProperty != null)
		{
			if(dTStartProperty.getDateTimeValue() != null)
			{
				System.out.println(toStringDateTimeValue(dTStartProperty.getDateTimeValue()));
			}
			else if(dTStartProperty.getDateTimeUTCValue() != null)
			{
				System.out.println(toStringDateTimeUTCValue(dTStartProperty.getDateTimeUTCValue()));
			}
			else if(dTStartProperty.getDateTimeTZIDValue() != null)
			{
				System.out.println(toStringDateTimeTZIDValue(dTStartProperty.getDateTimeTZIDValue()));
			}
			else if(dTStartProperty.getDateValue() != null)
			{
				System.out.println(toStringDateValue(dTStartProperty.getDateValue()));
			}
			else
			{
				System.out.println(dTStartProperty.getValueInconnu());
			}
		}
		else System.out.println("la date de debut n'a pas encore ete specifie");
	}
	public static void afficherDTEndProperty(ICal i, int id)
	{
		DTEndProperty dTEndProperty = i.getVEventComponent()[id].getDTEndProperty();
		if(dTEndProperty != null)
		{
			if(dTEndProperty.getDateTimeValue() != null)
			{
				System.out.println(toStringDateTimeValue(dTEndProperty.getDateTimeValue()));
			}
			else if(dTEndProperty.getDateTimeUTCValue() != null)
			{
				System.out.println(toStringDateTimeUTCValue(dTEndProperty.getDateTimeUTCValue()));
			}
			else if(dTEndProperty.getDateTimeTZIDValue() != null)
			{
				System.out.println(toStringDateTimeTZIDValue(dTEndProperty.getDateTimeTZIDValue()));
			}
			else if(dTEndProperty.getDateValue() != null)
			{
				System.out.println(toStringDateValue(dTEndProperty.getDateValue()));
			}
			else
			{
				System.out.println(dTEndProperty.getValueInconnu());
			}
		}
		else System.out.println("la date de fin n'a pas encore ete specifie");
	}
	public static void afficherDurationProperty(ICal i, int id)
	{
		DurationProperty durationProperty = i.getVEventComponent()[id].getDurationProperty();
		if(durationProperty != null)
		{
			if(durationProperty.getDurationValue() != null) System.out.println(toStringDurationValue(durationProperty.getDurationValue()));
			else System.out.println(durationProperty.getValueInconnu());
		}
		else System.out.println("la duree n'a pas encore ete specifie");
	}
	public static void afficherDescriptionProperty(ICal i, int id)
	{
		DescriptionProperty descriptionProperty = i.getVEventComponent()[id].getDescriptionProperty();
		if(descriptionProperty != null)
		{
			System.out.println(descriptionProperty.getTextValue().toString());
		}
		else System.out.println("la description n'a pas encore ete specifie");
	}
	public static void afficherSummaryProperty(ICal i, int id)
	{
		SummaryProperty summaryProperty = i.getVEventComponent()[id].getSummaryProperty();
		if(summaryProperty != null)
		{
			System.out.println(summaryProperty.getTextLineValue().toString());
		}
		else System.out.println("le sommaire n'a pas encore ete specifie");
	}
	
	/*VALUE*/
	private static String toStringDateValue(DateValue dateValue)
	{
		String result = "";
		if(dateValue != null)
		{
			result = dateValue.getJour() + "/" + dateValue.getMois() + "/" + dateValue.getAnnee();
		}
		return result;
	}
	private static String toStringTimeValue(TimeValue timeValue)
	{
		String result = "";
		if(timeValue != null) result = timeValue.getHeure() + "h" + timeValue.getMinute() + "min" + timeValue.getSeconde() + "s";
		return result;
	}
	private static String toStringDateTimeValue(DateTimeValue dateTimeValue)
	{
		String result = "";
		if(dateTimeValue != null) result = toStringDateValue(dateTimeValue.getDateValue()) + " a " + toStringTimeValue(dateTimeValue.getTimeValue());
		return result;
	}
	private static String toStringDateTimeUTCValue(DateTimeUTCValue dateTimeUTCValue)
	{
		String result = "";
		if(dateTimeUTCValue != null) result = toStringDateValue(dateTimeUTCValue.getDateValue()) + " a " + toStringTimeValue(dateTimeUTCValue.getTimeValue());
		return result;
	}
	private static String toStringDateTimeTZIDValue(DateTimeTZIDValue dateTimeTZIDValue)
	{
		String result = "";
		if(dateTimeTZIDValue != null) result = toStringDateValue(dateTimeTZIDValue.getDateValue()) + " a " + toStringTimeValue(dateTimeTZIDValue.getTimeValue()) +
		" specifie au fuseau horaire " + dateTimeTZIDValue.getTimeZones();
		return result;
	}
	private static String toStringDurationValue(DurationValue durationValue)
	{
		String result = "";
		if(durationValue != null)
		{
			if(durationValue.getDay() != durationValue.nullVariable && durationValue.getHour() != durationValue.nullVariable && durationValue.getMinute() != durationValue.nullVariable && durationValue.getSecond() != durationValue.nullVariable)
			{
				result = durationValue.getDay() + " jours " + durationValue.getHour() + "h" + durationValue.getMinute() + "min" + durationValue.getSecond() + "s";
			}
			else if(durationValue.getHour() != durationValue.nullVariable && durationValue.getMinute() != durationValue.nullVariable && durationValue.getSecond() != durationValue.nullVariable)
			{
				result = durationValue.getHour() + "h" + durationValue.getMinute() + "min" + durationValue.getSecond() + "s";
			}
			else if(durationValue.getWeek() != durationValue.nullVariable)
			{
				result = durationValue.getWeek() + " semaines";
			}
		}
		return result;
	}
}