package hr.java.vjezbe.entitet;

//import java.util.List;
//import java.util.Scanner;
/**
 * Predstavlja entitet poslovnog korisnika. 
 * Sadrži naziv i web za razliku od privatnog korisnika.
 * @author Josko
 *
 */
public class PoslovniKorisnik extends Korisnik {
	/**
	 * 
	 */
	private static final long serialVersionUID = -608522996692022651L;
	private String naziv, web;
	/**
	 * Kreira novi objekt tipa <code>PoslovniKorisnik</code>.
	 * @param email email korisnika
	 * @param telefon broj telefona korisnika
	 * @param naziv naziv korisnika
	 * @param web web stranica korisnika
	 */
	public PoslovniKorisnik(long id, String email, String telefon, String naziv, String web) {
		super(id, email, telefon);
		this.naziv = naziv;
		this.web = web;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}


	@Override
	public void dohvatiKontakt() {
		System.out.println("Naziv tvrtke: " + naziv + ", mail: " + super.getEmail() + ", tel: " + super.getTelefon() + ", web: " + web);
	}
	
	@Override
	public String toString() {
		return naziv + ", " + web + ", "+ super.getEmail() + ", " + super.getTelefon();
	}

}
