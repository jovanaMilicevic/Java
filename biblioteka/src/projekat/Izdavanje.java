package projekat;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Izdavanje {
	private int id_korisnika, id_knjige;
	private String datum_vracanja;
	static File nazivIzdavanja = new File("izdavanja.txt");
	
	public Izdavanje(int id_korisnika, int id_knjige, String datum_vracanja) {
		this.id_korisnika = id_korisnika;
		this.id_knjige = id_knjige;
		this.datum_vracanja = datum_vracanja;
	}
	public Izdavanje() {
		this.id_korisnika = 0;
		this.id_knjige = 0;
		this.datum_vracanja = "";
	}

	public int getId_korisnika() {
		return id_korisnika;
	}

	public void setId_korisnika(int id_korisnika) {
		this.id_korisnika = id_korisnika;
	}

	public int getId_knjige() {
		return id_knjige;
	}

	public void setId_knjige(int id_knjige) {
		this.id_knjige = id_knjige;
	}

	public String getDatum_vracanja() {
		return datum_vracanja;
	}

	public void setDatum_vracanja(String datum_vracanja) {
		this.datum_vracanja = datum_vracanja;
	}

	
	@Override
	public String toString() {
		return  id_korisnika + "," +  id_knjige + "," + datum_vracanja ;
	}

	public static void upisiIzdavanja(ArrayList<Izdavanje> lista) throws FileNotFoundException, IOException
	{
		try {
			PrintWriter pw = new PrintWriter(nazivIzdavanja);
			for(int i = 0; i < lista.size(); i++)
				pw.write(lista.get(i).toString() + "\n");

			pw.flush();
			pw.close();
		}
		catch(Exception ex)
		{
			System.out.println("Greska!");
		}

	}


	public static ArrayList<Izdavanje> citajIzdavanja() throws FileNotFoundException, IOException
	{
		String pom;
		String nazivIzdavanja = "izdavanja.txt";
		ArrayList<Izdavanje> listaIzdavanja = new ArrayList<>();


		try {
			FileReader fr = new FileReader(nazivIzdavanja);
			Scanner sc = new Scanner(fr);

			while(sc.hasNextLine())
			{
				pom = sc.nextLine();

				if(pom.length()>0)
				{
					Izdavanje i = new Izdavanje(Integer.parseInt(pom.split(",")[0]),Integer.parseInt(pom.split(",")[1]),pom.split(",")[2]);
					listaIzdavanja.add(i);
				}
			}


			sc.close();
			fr.close();
		}
		catch(IOException ex){
			System.out.println("Desila se greska");
		}

		return listaIzdavanja;
	}
	
}
