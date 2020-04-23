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

	/**
	 * Omoguæuje unos podataka za novi objekt korisnika koji se potom sprema u listu korisnika.
	 * @param listaKorisnika Sadrži sve unesene korisnike.
	 * @param unos Korisniku omoguæuje unos podataka.
	 * @param brojKorisnika Brojaè stvorenih objekata korisnika.
	 * @param i Odreðuje na koje mjesto u listi korisnika æe se spremiti novi objekt korisnika.
	 */
	/*public static void noviKorisnik(List<Korisnik> listaKorisnika, Scanner unos, int brojKorisnika, int i) {
		System.out.println("Unesite naziv " + (i+1) + ". tvrtke:");			
		String naziv = unos.nextLine();
		System.out.println("Unesite e-Mail " + (i+1) +  ". tvrtke:");			
		String email = unos.nextLine();
		System.out.println("Unesite web " + (i+1) + ". tvrtke:");			
		String web = unos.nextLine();
		System.out.println("Unesite telefon " + (i+1) + ". tvrtke:");			
		String telefon = unos.nextLine();
		listaKorisnika.add(new PoslovniKorisnik(email, telefon, naziv, web));
	}*/

	@Override
	public void dohvatiKontakt() {
		System.out.println("Naziv tvrtke: " + naziv + ", mail: " + super.getEmail() + ", tel: " + super.getTelefon() + ", web: " + web);
	}

}
