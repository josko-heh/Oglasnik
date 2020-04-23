package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
//import java.util.InputMismatchException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Scanner;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Predstavlja tip artikla. 
 * Omoguæuje unos novih podataka pri stvaranju artikla ili ispis podataka za oglas.
 * @author Josko
 *
 */
public class Automobil extends Artikl implements Vozilo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9009219065879701806L;
	
	private BigDecimal snagaKs;
	/**
	 * Kreira novi objekt tipa <code>Automobil</code>.
	 * @param naslov naslov automobila
	 * @param opis opis automobila
	 * @param cijena cijena automobila
	 * @param snagaKs snaga automobila u konjskim snagama
	 * @param stanje automobila
	 */
	public Automobil(long id, String naslov, String opis, BigDecimal cijena, BigDecimal snagaKs, Stanje stanje) {
		super(id, naslov, opis, cijena, stanje);
		this.snagaKs = snagaKs;
	}
	

	public BigDecimal getSnagaKs() {
		return snagaKs;
	}
	public void setSnagaKs(BigDecimal snagaKs) {
		this.snagaKs = snagaKs;
	}

	
	@Override
	public void tekstOglasa() {
		System.out.println("Naslov automobila: " + super.getNaslov());
		System.out.println("Opis automobila: " + super.getOpis());
		System.out.println("Snaga automobila: " + snagaKs);
		System.out.println("Izraèun osiguranja automobila: " + izracunajCijenuOsiguranja());
		System.out.println("Cijena automobila: " + super.getCijena());
		System.out.println("Stanje automobila: " + super.getStanje());
	}
	@Override
	public int izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
		if((snagaKs.compareTo(BigDecimal.valueOf(45)) == 0) || (snagaKs.compareTo(BigDecimal.valueOf(45)) == -1)) throw new NemoguceOdreditiGrupuOsiguranjaException("Premali broj ks!");
		if((snagaKs.compareTo(BigDecimal.valueOf(450)) == 0) || (snagaKs.compareTo(BigDecimal.valueOf(450)) == 1)) throw new NemoguceOdreditiGrupuOsiguranjaException("Prevelik broj ks!");

		if((snagaKs.compareTo(BigDecimal.valueOf(45)) == 0) || ((snagaKs.compareTo(BigDecimal.valueOf(45)) == 1) && (snagaKs.compareTo(BigDecimal.valueOf(60)) == -1))) return 1;
		if((snagaKs.compareTo(BigDecimal.valueOf(60)) == 0) || ((snagaKs.compareTo(BigDecimal.valueOf(60)) == 1) && (snagaKs.compareTo(BigDecimal.valueOf(75)) == -1))) return 2;
		if((snagaKs.compareTo(BigDecimal.valueOf(75)) == 0) || ((snagaKs.compareTo(BigDecimal.valueOf(75)) == 1) && (snagaKs.compareTo(BigDecimal.valueOf(85)) == -1))) return 3;
		if((snagaKs.compareTo(BigDecimal.valueOf(85)) == 0) || ((snagaKs.compareTo(BigDecimal.valueOf(85)) == 1) && (snagaKs.compareTo(BigDecimal.valueOf(100)) == -1))) return 4;
		return 5;
	}

}
