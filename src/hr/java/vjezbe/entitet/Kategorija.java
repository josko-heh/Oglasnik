package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet kategorije.
 * Unutar svake kategorije se unosi proizvoljan broj artikala.
 * @author Josko
 *
 */
public class Kategorija<T extends Artikl> extends Entitet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5757292425643273741L;
	private String naziv;
	private List<T> artikli;
	/**
	 * Kreira novi objekt tipa <code>Kategorija</code>.
	 * @param naziv naziv kategorije
	 * @param artikli sadrži artikle kategorije
	 */
	public Kategorija(long id, String naziv, List<T> artikli) {
		super(id);
		this.naziv = naziv;
		this.artikli = artikli;
	}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public List<T> getArtikli() {
		return artikli;
	}
	public void setArtikli(List<T> artikli) {
		this.artikli = artikli;
	}
	
	
	public void dodajArtikl(T obj) {
		artikli.add(obj);
	}
	public T dohvatiArtikl(int i) {
		return artikli.get(i);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
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
		@SuppressWarnings("unchecked")
		Kategorija<T> other = (Kategorija<T>) obj;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		return true;
	}

}
