package hr.java.vjezbe.entitet;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * Služi za spremanje oglasa koji je trenutno na prodaju.
 * @author Josko
 *
 */
public class Prodaja extends Entitet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7184972918306047233L;
	private Artikl artikl;
	private Korisnik korisnik;
	private LocalDate datumObjave;
	/**
	 * Kreira novi objekt tipa <code>Prodaja</code>.
	 * @param artikl artikl koji se oglašava
	 * @param korisnik korisnik koji oglašava
	 * @param datumObjave datum objave oglasa
	 */
	public Prodaja(long id, Artikl artikl, Korisnik korisnik, LocalDate datumObjave) {
		super(id);
		this.artikl = artikl;
		this.korisnik = korisnik;
		this.datumObjave = datumObjave;
	}

	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public LocalDate getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(LocalDate datumObjave) {
		this.datumObjave = datumObjave;
	}
}
