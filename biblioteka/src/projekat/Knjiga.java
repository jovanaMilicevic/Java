package projekat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;


public class Knjiga implements  Provera{
	private int id_knjige;
	private String naziv, zanr, autor;
	private int god_izdavanja, kolicina;
	static File nazivFajla = new File("knjige.txt");
	
	public Knjiga(int id_knjige, String naziv, String zanr, String autor, int god_izdavanja, int kolicina) {
		this.id_knjige = id_knjige;
		this.naziv = naziv;
		this.zanr = zanr;
		this.autor = autor;
		this.god_izdavanja = god_izdavanja;
		this.kolicina = kolicina;
	}
	public Knjiga() {
		this.id_knjige = 0;
		this.naziv = "";
		this.zanr = "";
		this.autor = "";
		this.god_izdavanja = 0;
		this.kolicina = 0;
	}
	
	public int getId_knjige() {
		return id_knjige;
	}
	public void setId_knjige(int id_knjige) {
		this.id_knjige = id_knjige;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getGod_izdavanja() {
		return god_izdavanja;
	}
	public void setGod_izdavanja(int god_izdavanja) {
		this.god_izdavanja = god_izdavanja;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	

	@Override
	public String toString() {
		return  id_knjige + "," +  naziv + "," +  zanr + "," +  autor + "," +  god_izdavanja + "," + kolicina;
	}
	
	public static void upisiDat(ArrayList<Knjiga> lista) throws FileNotFoundException, IOException
	{
		try {
			PrintWriter pw = new PrintWriter(nazivFajla);
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
	
	
	public static ArrayList<Knjiga> citajDat() throws FileNotFoundException, IOException
	{
		String pom;
		String nazivFajla = "knjige.txt";
		ArrayList<Knjiga> listaKnjiga = new ArrayList<>(); 
		

		try {
			FileReader fr = new FileReader(nazivFajla);
			Scanner sc = new Scanner(fr);
			
			while(sc.hasNextLine())
			{
				pom = sc.nextLine();
				
				if(pom.length()>0)
				{
					Knjiga k = new Knjiga(Integer.parseInt(pom.split(",")[0]),pom.split(",")[1],pom.split(",")[2],pom.split(",")[3],Integer.parseInt(pom.split(",")[4]),Integer.parseInt(pom.split(",")[5]));
					listaKnjiga.add(k);
				}
			}
			
			
			sc.close();
			fr.close();
		}
		catch(IOException ex){
			System.out.println("Desila se greska");
		}
		
		return listaKnjiga;
	}


	@Override
	public boolean Prvr(int idk) throws IOException, KnjigaPostojiException {

		Provere.proveriIdKnjige(idk);
		ArrayList<Knjiga> lista = new ArrayList<>();
		boolean kol = false;

		lista = Knjiga.citajDat();

		for(Knjiga k : lista)
		{
			if(k.getId_knjige() == idk)
			{
				if(k.kolicina > 0)
				{
					kol = true;
				}
			}
		}

		return kol;
	}
}
