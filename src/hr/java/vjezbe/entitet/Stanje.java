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
	
	public static Stanje getStanje(Integer brojcanaVrijednost) {
		if (brojcanaVrijednost.equals(1)) return novo;
		else if (brojcanaVrijednost.equals(2)) return izvrsno;
		else if (brojcanaVrijednost.equals(3)) return rabljeno;
		else if (brojcanaVrijednost.equals(4)) return neispravno;
		else System.out.println("greska kod stanja!");
		return null;
	}
}
