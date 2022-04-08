import java.util.Scanner;

import component.*;
import property.*;
import value.*;

public class Util
{
	private static final Scanner clavier = new Scanner(System.in);
	/*CHOIX UTILISATEUR*/
	public static int choisirChoixCourt(int a, int b, String phrase)//a et b != -1 retourn la valeur d'un des deux choix
	{
		int result = -1;
		
		while(result == -1)
		{
			System.out.println(phrase);
			String next = clavier.nextLine();
			if(next.equals(a + "")) result = a;
			else if(next.equals(b + "")) result = b;
		}
		
		return result;
	}
	public static int choisirChoixWithInt(int[] choix, String phrase)//choix[x] != -1 retourn le choix, result = choix[x]
	{
		int result = -1;
		
		while(result == -1)
		{
			System.out.println(phrase);
			String next = clavier.nextLine();
			result = isIntArrayContainsString(choix, next);
		}
		
		return choix[result];
	}
	public static String choisirChoixWithString(String[] choix, String phrase)//choix[x] != -1 retourn le choix, result = choix[x]
	{
		int result = -1;
		
		while(result == -1)
		{
			System.out.println(phrase);
			String next = clavier.nextLine();
			result = isStringArrayContainsString(choix, next);
		}
		return choix[result];
	}
	
	/*CONVERT*/
	public static String convertIntTo2String(int value)//permet de convertir un nombre en un string de 2 character
	{
		String result;
		if(value < 0) result = "0" + value;
		else result = value + "";
		return result;
	}
	
	/*ARRAY*/
	public static void addValueInArrayAtEnd(int[] t, int value)//value est ajoute au dernier indice
	{
		int[] result = new int[t.length + 1];
		for(int i = 0; i < t.length; i++)
		{
			result[i] = t[i];
		}
		result[t.length] = value;
		t = result;
	}
	public static void afficherArray(TimeZones[] t)
	{
		for(int i = 0; i < t.length; i++)
		{
			System.out.println(t[i] + "");
		}
	}
	public static int[] copyIntArray(int[] t)
	{
		int[] result = new int[t.length];
		
		for(int i = 0; i < t.length; i++)
		{
			result[i] = t[i];
		}
		
		return result;
	}
	public static int isStringArrayContainsString(String t[], String value)//retourn l'indice, -1 si pas trouve
	{
		int result = -1;
		int i = 0;
		while(i < t.length && result == -1)
		{
			if(value.equals(t[i])) result = i;
			else i++;
		}
		return result;
	}
	public static int isIntArrayContainsString(int[] t, String value)//retourn l'indice, -1 si pas trouve
	{
		int result = -1;
		
		int i = 0;
		while(i < t.length && result == -1)
		{
			if(value.equals(t[i] + "")) result = i;
			else i++;
		}
		return result;
	}
}