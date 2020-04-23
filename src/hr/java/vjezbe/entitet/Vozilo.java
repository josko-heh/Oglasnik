package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;
/**
 * Proširuje neko vozilo tako da omoguæava izraèun snage u kilovatima i izraèun grupe osiguranja.
 * @author Josko
 *
 */
public interface Vozilo {
	public static final Logger logger = LoggerFactory.getLogger(Vozilo.class);
	/**
	 * Pretvara snagu iz konjskih snaga u kilovate.
	 * @param hp snaga u konjskim snagama
	 * @return vraæa snagu kilovatima
	 */
	public default BigDecimal izracunajKw(BigDecimal hp) {
		return hp.multiply(BigDecimal.valueOf(0.745699872));
	}
	/**
	 * Raèuna grupu osiguranja na osnovu konjske snage.
	 * @return vraæa integer od 1 do 5 koji predstavlja broj grupe.
	 * @throws NemoguceOdreditiGrupuOsiguranjaException baca iznimku ako je konjska snaga prevelika ili premalena.
	 */
	public int izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;
	/**
	 * Raèuna cijenu osiguranja na osnovu grupe osiguranja.
	 * @return Vraæa <code>BigDecimal</code> vrijednost cijene osiguranja.
	 */
	public default BigDecimal izracunajCijenuOsiguranja() {
	/*	return switch(izracunajGrupuOsiguranja()) {
					case 1: yield BigDecimal.valueOf(100.3);
					case 2: yield BigDecimal.valueOf(110.4);
					case 3: yield BigDecimal.valueOf(150.3);
					case 4: yield BigDecimal.valueOf(160.8887);
					case 5: yield BigDecimal.valueOf(200.3771);
					default: { 
						System.out.println("Vasa grupa nije izmedju 1 i 5!");
						yield BigDecimal.valueOf(0);}
		};*/
		try {
			switch(izracunajGrupuOsiguranja()) {
				case 1: return BigDecimal.valueOf(100.3);
				case 2: return BigDecimal.valueOf(110.4);
				case 3: return BigDecimal.valueOf(150.3);
				case 4: return BigDecimal.valueOf(160.88);
				case 5: return BigDecimal.valueOf(200.37);				
			}
		}
		catch(NemoguceOdreditiGrupuOsiguranjaException ex) {
			System.out.println(ex.getMessage() + " Cijena postavljena na 0.");
			logger.info(ex.getMessage() + " Cijena postavljena na 0.", ex);
		}
		
		return BigDecimal.valueOf(0);
	}

}
