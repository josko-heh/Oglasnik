package hr.java.vjezbe.entitet;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * Predstavlja entitet artikla koji je definiran naslovom, opisom i cijenom-
 * @author Josko
 */
public abstract class Artikl extends Entitet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5883943586199831742L;
	private String naslov, opis;
	private BigDecimal cijena;
	private Stanje stanje;
	/**
	 * Koristi se pri kreiranju novih objekata koji naslijeðuju klasu <code>Artikl</code>.
	 * @param naslov naslov artikla
	 * @param opis opis artikla
	 * @param cijena cijena artikla
	 * @param stanje stanje artikla
	 */
	public Artikl(long id, String naslov,String opis, BigDecimal cijena, Stanje stanje) {
		super(id);
		this.naslov = naslov;
		this.opis = opis;
		this.cijena = cijena;
		this.stanje = stanje;
	}

	
	public Stanje getStanje() {
		return stanje;
	}
	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public BigDecimal getCijena() {
		return cijena;
	}
	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cijena == null) ? 0 : cijena.hashCode());
		result = prime * result + ((naslov == null) ? 0 : naslov.hashCode());
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((stanje == null) ? 0 : stanje.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikl other = (Artikl) obj;
		if (cijena == null) {
			if (other.cijena != null)
				return false;
		} else if (!cijena.equals(other.cijena))
			return false;
		if (naslov == null) {
			if (other.naslov != null)
				return false;
		} else if (!naslov.equals(other.naslov))
			return false;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (stanje != other.stanje)
			return false;
		return true;
	}
	/**
	 * Ispisuje tekst oglasa.
	 */
	public abstract void tekstOglasa();
}