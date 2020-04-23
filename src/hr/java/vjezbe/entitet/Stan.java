package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja tip artikla. 
 * Omoguæuje unos novih podataka pri stvaranju artikla ili ispis podataka za oglas.
 * @author Josko
 *
 */
public class Stan extends Artikl implements Nekretnina {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1904285321841224912L;
	private Integer kvadratura;
	/**
	 * Kreira novi objekt tipa <code>Stan</code>.
	 * @param naslov naslov stana
	 * @param opis opis stana
	 * @param cijena cijena stana
	 * @param kvadratura kvadratura stana
	 * @param stanje stana
	 */
	public Stan(long id, String naslov, String opis, BigDecimal cijena, Integer kvadratura, Stanje stanje) {
		super(id, naslov, opis, cijena, stanje);
		this.kvadratura = kvadratura;
	}

	public Integer getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(Integer kvadratura) {
		this.kvadratura = kvadratura;
	}

	@Override
	public void tekstOglasa() {
		System.out.println("Naslov stana: " + super.getNaslov());
		System.out.println("Opis stana: " + super.getOpis());
		System.out.println("Kvadratura stana: " + kvadratura);
		System.out.println("Cijena stana: " + super.getCijena());
		System.out.println("Stanje nekretnine: " + super.getStanje());
	}
	
	@Override
	public String toString() {
		return super.getNaslov() + ", " + super.getOpis() + ", " + kvadratura + ", " + super.getCijena() + ", " + super.getStanje();
	}
	
}
