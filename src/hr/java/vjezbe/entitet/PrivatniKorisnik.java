package hr.java.vjezbe.entitet;

//import java.util.List;
//import java.util.Scanner;
/**
 * Predstavlja entitet privatnog korisnika. 
 * Sadrži ime i prezime za razliku od poslovnog korisnika.
 * @author Josko
 */
public class PrivatniKorisnik extends Korisnik {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8652880059116358628L;
	private String ime, prezime;
	/**
	 * Kreira novi objekt tipa <code>PrivatniKorisnik</code>.
	 * @param email email korisnika
	 * @param telefon broj telefona korisnka
	 * @param ime ime korisnika
	 * @param prezime prezime korisnika
	 */
	public PrivatniKorisnik(long id, String email, String telefon, String ime, String prezime) {
		super(id, email, telefon);
		this.ime = ime;
		this.prezime = prezime;
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
	

	@Override
	public void dohvatiKontakt() {
		System.out.println("Osobni podaci prodavatelja: " + ime + " " + prezime + ", mail: " + super.getEmail() + ", tel: " + super.getTelefon());
	}
	
	@Override
	public String toString() {
		return ime + ", " + prezime + ", "+ super.getEmail() + ", " + super.getTelefon();
	}

}
