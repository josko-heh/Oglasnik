package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
/**
 * Pro�iruje bilo koju nekretninu tako da omogu�ava izra�unavanje poreza na nju.
 * @author Josko
 *
 */
public interface Nekretnina {
	/**
	 * Izra�unava porez na nekretninu.
	 * @param cijena cijena nekretnine
	 * @return Vra�a vrijednost poreza.
	 * @throws CijenaJePreniskaException Baca iznimku ako je cijena preniska.
	 */
	public default BigDecimal izracunajPorez(BigDecimal cijena) throws CijenaJePreniskaException {
		if((cijena.compareTo(BigDecimal.valueOf(10000))) == -1) throw new CijenaJePreniskaException("Cijena je manja od 10000!");
		
		return cijena.multiply(BigDecimal.valueOf(0.03));
	}

}
