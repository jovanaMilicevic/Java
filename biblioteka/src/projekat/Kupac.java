package projekat;

public class Kupac extends Korisnik{

	private String ime, prezime, adresa, tel;
	private int id_kupca;
	
	public Kupac(String kor_ime, String lozinka, String ime, String prezime, String adresa, String tel, int id_kupca) {
		super(kor_ime, lozinka);
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.tel = tel;
		this.id_kupca = id_kupca;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getId_kupca() {
		return id_kupca;
	}

	public void setId_kupca(int id_kupca) {
		this.id_kupca = id_kupca;
	}

	@Override
	public String toString() {
		return kor_ime +","+ lozinka +","+ime +","+ prezime +","+ adresa +","+ tel +","+ id_kupca;
	}
	
	
}
