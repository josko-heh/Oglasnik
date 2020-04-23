package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja tip artikla. 
 * Omoguæuje unos novih podataka pri stvaranju artikla ili ispis podataka za oglas.
 * @author Josko
 *
 */
public class Usluga extends Artikl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1905200277904737121L;


	//private static final Logger logger = LoggerFactory.getLogger(Usluga.class);
	/**
	 * Kreira novi objekt tipa <code>Usluga</code>.
	 * @param naslov naslov usluge
	 * @param opis opis usluge
	 * @param cijena cijena usluge
	 * @param stanje usluge
	 */
	public Usluga(long id, String naslov, String opis, BigDecimal cijena, Stanje stanje) {
		super(id, naslov, opis, cijena, stanje);
	}
	
	
	@Override
	public void tekstOglasa() {
		System.out.println("Naslov usluge: " + super.getNaslov());
		System.out.println("Opis usluge: " + super.getOpis());
		System.out.println("Cijena usluge: " + super.getCijena());
		System.out.println("Stanje usluge: " + super.getStanje());
	}
	
	@Override
	public String toString() {
		return super.getNaslov() + ", " + super.getOpis() + ", " + super.getCijena() + ", " + super.getStanje();
	}

}
