package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Predstavlja entitet korisnika kojeg naslijeðuju klase privatni i poslovni korisnik.
 * @author Josko
 *
 */
public abstract class Korisnik extends Entitet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3710061652205009793L;
	private String email, telefon;
	/**
	 * Koristi se pri kreiranju novih objekata koji naslijeðuju klasu <code>Korisnik</code>.
	 * @param email email korisnika
	 * @param telefon broj telefona korisnika
	 */
	public Korisnik(long id, String email, String telefon) {
		super(id);
		this.email = email;
		this.telefon = telefon;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * Ispisuje informacije o korisniku.
	 */
	public abstract void dohvatiKontakt();		
}