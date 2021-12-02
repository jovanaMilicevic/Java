package projekat;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Korisnik{

	static File nazivLogina = new File("login.txt");
	public Admin(String kor_ime, String lozinka) {
		super(kor_ime, lozinka);
	}

	public static void upisiKorisnika(ArrayList<Kupac> korisnik) throws FileNotFoundException, IOException
	{
		try {
			PrintWriter pw = new PrintWriter(nazivLogina);
			for(int i = 0; i < korisnik.size(); i++)
				pw.write(korisnik.get(i).toString() + "\n");

			pw.flush();
			pw.close();
		}
		catch(Exception ex)
		{
			System.out.println("Greska!");
		}

	}


	public static ArrayList<Kupac> citajKorisnika() throws FileNotFoundException, IOException
	{
		String pom;
		String nazivLog = "login.txt";
		ArrayList<Kupac> listaKupaca = new ArrayList<>();


		try {
			FileReader fr = new FileReader(nazivLog);
			Scanner sc = new Scanner(fr);

			while(sc.hasNextLine())
			{
				pom = sc.nextLine();

				if(pom.length()>0)
				{
					Kupac k = new Kupac(pom.split(",")[0],pom.split(",")[1],pom.split(",")[2],pom.split(",")[3],pom.split(",")[4],pom.split(",")[5],Integer.parseInt(pom.split(",")[6]));
					listaKupaca.add(k);

				}
			}

			sc.close();
			fr.close();
		}
		catch(IOException ex){
			System.out.println("Desila se greska");
		}

		return listaKupaca;
	}

	@Override
	public String toString() {
		return  kor_ime +","+ lozinka;
	}
}
