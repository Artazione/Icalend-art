import java.util.Scanner;
import java.io.IOException;

import component.*;
import property.*;
import value.*;

public class Ask
{
	private static Scanner clavier = new Scanner(System.in);
	
	public static DurationValue askDurationDateValue()
	{
		DurationValue result = null;
		
		while(result == null)
		{
			System.out.println("rentrer la duree comme suivant: T jours XhYminZs avec les chiffres colles sans espace");
			String value = clavier.nextLine();
			try 
			{
				int jours = value.indexOf(" jours ");
				int h = value.indexOf('h');
				int min = value.indexOf("min");
				int jour = Integer.parseInt(value.substring(0, jours));
				int heure = Integer.parseInt(value.substring(jours + 7, h));
				int minute = Integer.parseInt(value.substring(h + 1, min));
				int seconde = Integer.parseInt(value.substring(min + 3, value.length() - 1));
				
				result = new DurationValue(jour, heure, minute, seconde);
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Les espaces sont mals fait");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Ce qui est specifier avec des chiffres ne doit pas etre ecrit avec des lettres");
			}
			catch(IOException e)
			{
				System.out.println(e.toString() + " donnee invalide");
				result = null;
			}
		}
		
		return result;
	}
	public static DurationValue askDurationTimeValue()
	{
		DurationValue result = null;
		
		while(result == null)
		{
			System.out.println("rentrer la duree comme suivant: XhYminZs avec les chiffres colles sans espace");
			String value = clavier.nextLine();
			try 
			{
				int h = value.indexOf('h');
				int min = value.indexOf("min");
				int heure = Integer.parseInt(value.substring(0, h));
				int minute = Integer.parseInt(value.substring(h + 1, min));
				int seconde = Integer.parseInt(value.substring(min + 3, value.length() - 1));
				
				result = new DurationValue(heure, minute, seconde);
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Les espaces sont mals fait");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Ce qui est specifier avec des chiffres ne doit pas etre ecrit avec des lettres");
			}
			catch(IOException e)
			{
				System.out.println(e.toString() + " donnee invalide");
				result = null;
			}
		}
		
		return result;
	}
	public static DurationValue askDurationWeekValue()
	{
		DurationValue result = null;
		
		while(result == null)
		{
			System.out.println("rentrer la duree comme suivant: X semaines");
			String value = clavier.nextLine();
			try 
			{
				int semaines = value.indexOf(" semaines");
				int week = Integer.parseInt(value.substring(0, semaines));
				
				result = new DurationValue(week);
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Les espaces sont mals fait");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Ce qui est specifier avec des chiffres ne doit pas etre ecrit avec des lettres");
			}
			catch(IOException e)
			{
				System.out.println(e.toString() + " donnee invalide");
				result = null;
			}
		}
		
		return result;
	}
	public static DateValue askDateValue()
	{
		DateValue result = null;
		
		while(result == null)
		{
			System.out.println("rentrer l'annee comme suivant: jour(en chiffre) mois(en lettre) annee(en chiffre) avec les chiffres colles sans espace");
			String value = clavier.nextLine();
			try 
			{
				int firstSpace = value.indexOf(' ');
				int secondSpace = value.lastIndexOf(' ');
				
				int jour = Integer.parseInt(value.substring(0, firstSpace));
				int annee = Integer.parseInt(value.substring(secondSpace + 1, value.length()));
				
				Mois mois = Mois.convertStringToMois(value.substring(firstSpace + 1, secondSpace));
				if(mois != null)
				{
					result = new DateValue(annee, mois.getMoisDigit(), jour);
				}
				else
				{
					System.out.println("le mois est mal ecrit, une liste de tous les mois");
					Mois.afficher();
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Les espaces sont mals fait");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Ce qui est specifier avec des chiffres ne doit pas etre ecrit avec des lettres");
			}
			catch(IOException e)
			{
				System.out.println(e.toString() + " donnee invalide");
				result = null;
			}
		}
		
		return result;
	}
	public static DateTimeValue askDateTimeValue()
	{
		DateTimeValue result = null;
		
		while(result == null)
		{
			System.out.println("rentrer l'annee comme suivant: jour(en chiffre) mois(en lettre) annee(en chiffre) XhYminZs avec les chiffres colles sans espace ex: 3 mars 2021 3h20min0s");
			String value = clavier.nextLine();
			try 
			{
				int firstSpace = value.indexOf(' ');
				int secondSpace = value.indexOf(' ', firstSpace + 1);
				int thirdSpace = value.lastIndexOf(' ');
				
				int jour = Integer.parseInt(value.substring(0, firstSpace));
				int annee = Integer.parseInt(value.substring(secondSpace + 1, thirdSpace));
				int h = value.indexOf('h');
				int min = value.indexOf("min");
				int heure = Integer.parseInt(value.substring(thirdSpace + 1, h));
				int minute = Integer.parseInt(value.substring(h + 1, min));
				int seconde = Integer.parseInt(value.substring(min + 3, value.length() - 1));
				
				Mois mois = Mois.convertStringToMois(value.substring(firstSpace + 1, secondSpace));
				if(mois != null)
				{
					result = new DateTimeValue(annee, mois.getMoisDigit(), jour, heure, minute, seconde);
				}
				else
				{
					System.out.println("le mois est mal ecrit, une liste de tous les mois");
					Mois.afficher();
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Les espaces sont mals fait");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Ce qui est specifier avec des chiffres ne doit pas etre ecrit avec des lettres");
			}
			catch(IOException e)
			{
				System.out.println(e.toString() + " donnee invalide");
				result = null;
			}
		}
		
		return result;
	}
	public static DateTimeUTCValue askDateTimeUTCValue()
	{
		DateTimeUTCValue result = null;
		
		while(result == null)
		{
			System.out.println("rentrer l'annee comme suivant: jour(en chiffre) mois(en lettre) annee(en chiffre) XhYminZs avec les chiffres colles sans espace");
			String value = clavier.nextLine();
			try 
			{
				int firstSpace = value.indexOf(' ');
				int secondSpace = value.indexOf(' ', firstSpace + 1);
				int thirdSpace = value.lastIndexOf(' ');
				
				int jour = Integer.parseInt(value.substring(0, firstSpace));
				int annee = Integer.parseInt(value.substring(secondSpace + 1, thirdSpace));
				int h = value.indexOf('h');
				int min = value.indexOf("min");
				int heure = Integer.parseInt(value.substring(thirdSpace + 1, h));
				int minute = Integer.parseInt(value.substring(h + 1, min));
				int seconde = Integer.parseInt(value.substring(min + 3, value.length() - 1));
				
				Mois mois = Mois.convertStringToMois(value.substring(firstSpace + 1, secondSpace));
				if(mois != null)
				{
					result = new DateTimeUTCValue(annee, mois.getMoisDigit(), jour, heure, minute, seconde);
				}
				else
				{
					System.out.println("le mois est mal ecrit, une liste de tous les mois");
					Mois.afficher();
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Les espaces sont mals fait");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Ce qui est specifier avec des chiffres ne doit pas etre ecrit avec des lettres");
			}
			catch(IOException e)
			{
				System.out.println(e.toString() + " donnee invalide");
				result = null;
			}
		}
		
		return result;
	}
	public static DateTimeTZIDValue askDateTimeTZIDValue()
	{
		DateTimeTZIDValue result = null;
		
		while(result == null)
		{
			System.out.println("rentrer l'annee comme suivant: jour(en chiffre) mois(en lettre) annee(en chiffre) XhYminZs avec les chiffres colles sans espace");
			String value = clavier.nextLine();
			try 
			{
				int firstSpace = value.indexOf(' ');
				int secondSpace = value.indexOf(' ', firstSpace + 1);
				int thirdSpace = value.lastIndexOf(' ');
				
				int jour = Integer.parseInt(value.substring(0, firstSpace));
				int annee = Integer.parseInt(value.substring(secondSpace + 1, thirdSpace));
				int h = value.indexOf('h');
				int min = value.indexOf("min");
				int heure = Integer.parseInt(value.substring(thirdSpace + 1, h));
				int minute = Integer.parseInt(value.substring(h + 1, min));
				int seconde = Integer.parseInt(value.substring(min + 3, value.length() - 1));
				
				Mois mois = Mois.convertStringToMois(value.substring(firstSpace + 1, secondSpace));
				if(mois != null)
				{
					result = new DateTimeTZIDValue(annee, mois.getMoisDigit(), jour, heure, minute, seconde, askTimeZones());
				}
				else 
				{
					System.out.println("le mois est mal ecrit, une liste de tous les mois");
					Mois.afficher();
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Les espaces sont mals fait");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Ce qui est specifier avec des chiffres ne doit pas etre ecrit avec des lettres");
			}
			catch(IOException e)
			{
				System.out.println(e.toString() + " donnee invalide");
				result = null;
			}
		}
		
		return result;
	}
	public static TimeZones askTimeZones()
	{
		TimeZones result = null;
		
		while(result == null)
		{
			System.out.println("Rentrer un des phuseaux horaires");
			Util.afficherArray(TimeZones.values());
			String text = clavier.nextLine();
			if(TimeZones.isStringCorrectTimeZones(text)) result = TimeZones.convertStringToTimeZones(text);
		}
		
		return result;
	}
}