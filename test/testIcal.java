import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

import component.*;
import property.*;
import value.*;

public class testIcal
{
	private static final Scanner clavier = new Scanner(System.in);
	private static ICal i1;
	
	public static void main(String[] args)
	{		
		if(Util.choisirChoixCourt(0, 1, "Voulez vous importer(0) ou creer un nouveau Ical(1)") == 0)
		{
			System.out.println("vous voulez importer");
			importerIcal();
		}
		else 
		{
			System.out.println("vous voulez le creer");
			creerIcal();
		}
	}
	private static void creerIcal()
	{
		i1 = new ICal();
		faire();
	}
	private static void importerIcal()
	{
		i1 = null;
		
		while(i1 == null)
		{
			System.out.println("Indiquer l'address du Ical");
			String path = clavier.nextLine();
			try
			{
				i1 = new ICal(path);
				System.out.println("import reussi");
			}catch(FileNotFoundException e) { System.out.println(e.toString()); }
		}
		faire();
	}
	
	private static void faire()
	{
		int[] choix = {0, 1, 2, 3};//0 pour quitter, 1 pour creer un nouveau VEVENT, 2 pour modifier un VEVENT deja existant, 3 pour supprimer un VEvent
		
		boolean isOver = false;
		while(isOver == false)
		{
			int choisirChoixNb = Util.choisirChoixWithInt(choix, "Voulez vous quitter (0), creer un nouveau RDV (1), ou modifier un RDV deja existant (2), ou supprimer un RDV deja existant (3)");
			if(choisirChoixNb == 0)
			{
				System.out.println("Vous quittez");
				quitter();
				isOver = true;
			}
			else if(choisirChoixNb == 1)
			{
				System.out.println("Vous creer un nouveau VEVENT");
				addVEvent();
			}
			else if(choisirChoixNb == 2)
			{
				if(i1.getVEventComponent().length != 0)
				{
					System.out.println("Vous voulez modifier un VEVENT");
					choixModifierVEvent();
				}
				else System.out.println("Il n'y pas de VEvent deja creee");
			}
			else
			{
				if(i1.getVEventComponent().length != 0)
				{
					System.out.println("Vous voulez supprimer un VEVENT");
					choixSupprimerVEvent();
				}
				else System.out.println("Il n'y pas de VEvent deja creee");
			}
		}
	}
	
	private static void quitter()
	{
		if(Util.choisirChoixCourt(0, 1, "Voulez vous quitter sans sauvegarde(0) ou sauvegarde(1)") == 1)
		{
			System.out.println("Entrez le nom du fichier");
			i1.save(clavier.nextLine());
		}
	}
	
	private static void choixModifierVEvent()
	{
		int choisirVEvent = -1;
		
		int[] choix = new int[i1.getVEventComponent().length];
		for(int i = 0; i < i1.getVEventComponent().length; i++)
		{
			if(i1.getVEventComponent()[i].getSummaryProperty() != null) 
			{
				System.out.print("(" + i + ") "); 
				Afficher.afficherSummaryProperty(i1, i);
			}
			else System.out.println("(" + i + ") VEvent");
			choix[i] = i;
		}
		choisirVEvent = Util.choisirChoixWithInt(choix, "Choisisez un entre eux.");
		modifierVEvent(choisirVEvent);
	}
	private static void choixSupprimerVEvent()
	{
		int choisirVEvent = -1;
		
		int[] choix = new int[i1.getVEventComponent().length];
		for(int i = 0; i < i1.getVEventComponent().length; i++)
		{
			if(i1.getVEventComponent()[i].getSummaryProperty() != null) 
			{
				System.out.print("(" + i + ") "); 
				Afficher.afficherSummaryProperty(i1, i);
			}
			else System.out.println("(" + i + ") VEvent");
			choix[i] = i;
		}
		//System.out.println("vous pouvez afficher une VEvent en ecrivant /afficher(X)");
		choisirVEvent = Util.choisirChoixWithInt(choix, "Choisisez un entre eux, vous pouvez afficher une VEvent en ecrivant /afficher(X)");
		i1.deleteVEvent(choisirVEvent);
	}
	private static void modifierVEvent(int id)
	{
		System.out.println("vous modifier le VEVENT numero " + id);
		Afficher.afficherVEvent(i1, id);
		
		int[] choix = {0, 1, 2, 3, 4, 5};//0 pour modifier la date du debut, 1 pour modifier la date de fin, 2 pour modifier la duree de l'event, 3 pour modifier la description, 4 pour modifier le resume, 5 pour quitter
		
		boolean isOver = false;
		while(isOver == false)
		{
			int choisirChoixNb = Util.choisirChoixWithInt(choix, "(0) pour modifier la date du debut, (1) pour modifier la date de fin, (2) pour modifier la duree de l'event, (3) pour modifier la description, (4) pour modifier le resume, (5) pour quitter");
			if(choisirChoixNb == 0)//0 pour modifier la date du debut
			{
				System.out.println("Vous voulez modifier la date de debut");
				addDTStartProperty(id);
			}
			else if(choisirChoixNb == 1)//1 pour modifier la date de fin
			{
				if(i1.getVEventComponent()[id].getDurationProperty() == null)
				{
					System.out.println("Vous voulez modifier la date du fin");
					addDTEndProperty(id);
				}
				else System.out.println("impossible car la duree est deja specifie");
			}
			else if(choisirChoixNb == 2)//2 pour modifier la duree de l'event
			{
				if(i1.getVEventComponent()[id].getDTEndProperty() == null)
				{
					System.out.println("Vous voulez modifier la duree de l'event");
					addDurationProperty(id);
				}
				else System.out.println("impossible car la date de fin est deja specifie");
			}
			else if(choisirChoixNb == 3)//3 pour modifier la description
			{
				System.out.println("Vous voulez modifier la description");
				addDescriptionProperty(id);
			}
			else if(choisirChoixNb == 4)//3 pour modifier la description
			{
				System.out.println("Vous voulez modifier le resume");
				addSummaryProperty(id);
			}
			else//pour revenir en arriere
			{
				System.out.println("Vous partez en arriere");
				isOver = true;
			}
		}
	}
	
	private static void addVEvent()
	{
		i1.addVEvent();
		int[] choix = {0, 1, 2, 3};//0 pour une date, 1 pour une dateTime, 2 pour dateTimeUTC, 3 pour dateTimeTZID
		int choisirChoixNb = Util.choisirChoixWithInt(choix, "Choisir le format de la date de debut, (0) pour une date, (1)pour une dateTime, (2) pour dateTimeUTC, (3) pour dateTimeTZID");
		if(choisirChoixNb == 0)
		{
			try 
			{
				i1.getVEventComponent()[i1.getVEventComponent().length - 1] = new VEventComponent(Ask.askDateValue());
				System.out.println("La date du debut est cree");
			}catch(IOException e) { System.out.println(e.toString()); }
		}
		else if(choisirChoixNb == 1)// 1 pour une dateTime
		{
			try 
			{
				i1.getVEventComponent()[i1.getVEventComponent().length - 1] = new VEventComponent(Ask.askDateTimeValue());
				System.out.println("La date du debut est cree");
			}catch(IOException e) { System.out.println(e.toString()); }
		}
		else if(choisirChoixNb == 2)//2 pour dateTimeUTC
		{
			try 
			{
				i1.getVEventComponent()[i1.getVEventComponent().length - 1] = new VEventComponent(Ask.askDateTimeUTCValue());
				System.out.println("La date du debut est cree");
			}catch(IOException e) { System.out.println(e.toString()); }
		}
		else //3 pour dateTimeTZID
		{
			try 
			{
				i1.getVEventComponent()[i1.getVEventComponent().length - 1] = new VEventComponent(Ask.askDateTimeTZIDValue());
				System.out.println("La date du debut est cree");
			}catch(IOException e) { System.out.println(e.toString()); }
		}
	}
	private static void addDTStartProperty(int id)
	{
		if(i1.getVEventComponent()[id].getDTEndProperty() != null)
		{
			String value = i1.getVEventComponent()[id].getDTEndProperty().getValue();
			String format = i1.getVEventComponent()[id].getDTEndProperty().getFormat();
			boolean isOver = false;
			Afficher.afficherDTStartProperty(i1, id);
			while(isOver == false)
			{
				if(value.equals("DATE-TIME") && format.equals("LOCAL TIME"))//pour dateTime
				{
					try 
					{
						System.out.println("On specie la date en dateTime");
						i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateTimeValue());
						System.out.println("La date de debut est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
				else if(value.equals("DATE-TIME") && format.equals("UTC"))//pour dateTimeUTC
				{
					try 
					{
						System.out.println("On specie la date en dateTimeUTC");
						i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateTimeUTCValue());
						System.out.println("La date de debut est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
				else if(value.equals("DATE-TIME") && format.equals("TZID"))//pour DateTimeTZID
				{
					try 
					{
						System.out.println("On specie la date en dateTimeTZID");
						i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateTimeTZIDValue());
						System.out.println("La date de debut est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
				else//pour Date
				{
					try 
					{
						System.out.println("On specie la date en date");
						i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateValue());
						System.out.println("La date de debut est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
			}
		}
		else 
		{
			int[] choix = {0, 1, 2, 3};//0 pour une date, 1 pour une dateTime, 2 pour dateTimeUTC, 3 pour dateTimeTZID
			int choisirChoixNb = Util.choisirChoixWithInt(choix, "Choisir le format de la date de debut, (0) pour une date, (1)pour une dateTime, (2) pour dateTimeUTC, (3) pour dateTimeTZID");
			if(choisirChoixNb == 0)
			{
				try 
				{
					i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateValue());
					System.out.println("La date du debut est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
			else if(choisirChoixNb == 1)// 1 pour une dateTime
			{
				try 
				{
					i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateTimeValue());
					System.out.println("La date du debut est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
			else if(choisirChoixNb == 2)//2 pour dateTimeUTC
			{
				try 
				{
					i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateTimeUTCValue());
					System.out.println("La date du debut est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
			else //3 pour dateTimeTZID
			{
				try 
				{
					i1.getVEventComponent()[id].addDTStartProperty(Ask.askDateTimeTZIDValue());
					System.out.println("La date du debut est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
		}
	}
	private static void addDTEndProperty(int id)
	{
		if(i1.getVEventComponent()[id].getDTStartProperty() != null)
		{
			String value = i1.getVEventComponent()[id].getDTStartProperty().getValue();
			String format = i1.getVEventComponent()[id].getDTStartProperty().getFormat();
			boolean isOver = false;
			Afficher.afficherDTEndProperty(i1, id);
			while(isOver == false)
			{
				if(value.equals("DATE-TIME") && format.equals("LOCAL TIME"))//pour dateTime
				{
					try 
					{
						System.out.println("On specie la date en dateTime");
						i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateTimeValue());
						System.out.println("La date de fin est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
				else if(value.equals("DATE-TIME") && format.equals("UTC"))//pour dateTimeUTC
				{
					try 
					{
						System.out.println("On specie la date en dateTimeUTC");
						i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateTimeUTCValue());
						System.out.println("La date de fin est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
				else if(value.equals("DATE-TIME") && format.equals("TZID"))//pour DateTimeTZID
				{
					try 
					{
						System.out.println("On specie la date en dateTimeTZID");
						i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateTimeTZIDValue());
						System.out.println("La date de fin est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
				else//pour Date
				{
					try 
					{
						System.out.println("On specie la date en date");
						i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateValue());
						System.out.println("La date de fin est creee ou modifier");
						isOver = true;
					}catch(IOException e) { System.out.println(e.toString()); }
				}
			}
		}
		else
		{
			int[] choix = {0, 1, 2, 3};//0 pour une date, 1 pour une dateTime, 2 pour dateTimeUTC, 3 pour dateTimeTZID
			int choisirChoixNb = Util.choisirChoixWithInt(choix, "Choisir le format de la date de fin, (0) pour une date, (1)pour une dateTime, (2) pour dateTimeUTC, (3) pour dateTimeTZID");
			if(choisirChoixNb == 0)
			{
				try 
				{
					i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateValue());
					System.out.println("La date du fin est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
			else if(choisirChoixNb == 1)// 1 pour une dateTime
			{
				try 
				{
					i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateTimeValue());
					System.out.println("La date du fin est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
			else if(choisirChoixNb == 2)//2 pour dateTimeUTC
			{
				try 
				{
					i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateTimeUTCValue());
					System.out.println("La date du fin est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
			else //3 pour dateTimeTZID
			{
				try 
				{
					i1.getVEventComponent()[id].addDTEndProperty(Ask.askDateTimeTZIDValue());
					System.out.println("La date du fin est cree");
				}catch(IOException e) { System.out.println(e.toString()); }
			}
		}
	}
	private static void addDurationProperty(int id)
	{
		int[] choix = {0, 1, 2};//0 pour une dur-date, 1 pour une dur-time, 2 pour dur-week
		int choisirChoixNb = Util.choisirChoixWithInt(choix, "Une duration en dur-date(0), dur-time(1), dur-time(2)");
		
		DurationValue duration = null;
		Afficher.afficherDurationProperty(i1, id);
		if(choisirChoixNb == 0)
		{
			System.out.println("On specie la duree en date");
			try 
			{
				i1.getVEventComponent()[id].addDurationProperty(Ask.askDurationDateValue());
			}catch(IOException e) { System.out.println(e.toString()); }
			System.out.println("La duree est creee ou modifier");
		}
		else if(choisirChoixNb == 1)
		{
			System.out.println("On specie la duree en date");
			try 
			{
				i1.getVEventComponent()[id].addDurationProperty(Ask.askDurationTimeValue());
			}catch(IOException e) { System.out.println(e.toString()); }
			System.out.println("La duree est creee ou modifier");
		}
		else if(choisirChoixNb == 2)
		{
			System.out.println("On specie la duree en date");
			try 
			{
				i1.getVEventComponent()[id].addDurationProperty(Ask.askDurationWeekValue());
			}catch(IOException e) { System.out.println(e.toString()); }
			System.out.println("La duree est creee ou modifier");
		}
	}
	private static void addSummaryProperty(int id)
	{
		System.out.println("Ecrivez le resume");
		Afficher.afficherSummaryProperty(i1, id);
		String value = clavier.nextLine();
		
		i1.getVEventComponent()[id].addSummaryProperty(new TextLineValue(value));
		System.out.println("Le resumer a ete creee ou modifie avec comme intitule: " + value);
	}
	private static void addDescriptionProperty(int id)
	{
		System.out.println("Ecriver la description");
		Afficher.afficherDescriptionProperty(i1, id);
		String value = clavier.nextLine();
		
		i1.getVEventComponent()[id].addDescriptionProperty(new TextValue(value));
		System.out.println("propriete description creee ou modifie avec comme description: " + value);
	}
}