package hr.java.vjezbe.entitet;
/**
 * Opisuje stanje artikla.
 * @author Josko
 *
 */
public enum Stanje {
	novo(1), izvrsno(2), rabljeno(3), neispravno(4);
	
	
	private int brojcanaVrijednostStanja;
	
	Stanje(int brojcanaVrijednostStanja){
		this.brojcanaVrijednostStanja = brojcanaVrijednostStanja;
	}

	public int getBrojcanaVrijednostStanja() {
		return brojcanaVrijednostStanja;
	}
}
