

public enum Mois
{
	janvier(1),
	fevrier(2),
	mars(3),
	avril(4),
	mai(5),
	juin(6),
	juillet(7),
	aout(8),
	septembre(9),
	octobre(10),
	novembre(11),
	decembre(12);
	
	private int moisDigit;
	
	public int getMoisDigit() { return moisDigit; }
	
	private Mois(int moisDigit)
	{
		this.moisDigit = moisDigit;
	}
	
	public static boolean isStringEqualsMois(String value)
	{
		boolean result = false;
		Mois[] t = values();
		int i = 0;
		
		while(result == false && i < t.length)
		{
			if(value.equals(t[i].toString())) result = true;
			else i++;
		}
		
		return result;
	}
	public static Mois convertStringToMois(String value)
	{
		Mois result = null;
		Mois[] t = values();
		int i = 0;
		
		while(result == null && i < t.length)
		{
			if(value.equals(t[i].toString())) result = t[i];
			else i++;
		}
		
		return result;
	}
	public static Mois convertIntToMois(int value)
	{
		value--;
		Mois result = null;
		Mois[] t = values();
		if(value < t.length && value >= 0)
		{
			result = t[value];
		}
		return result;
	}
	public static void afficher()
	{
		Mois[] t = values();
		for(int i = 0; i < t.length; i++)
		{
			System.out.println(t[i].toString());
		}
	}
}