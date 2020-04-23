package hr.java.vjezbe.iznimke;
/**
 * Baca se ako parametar pri odreðivanju grupe osiguranja nije unutar odreðenog skupa.
 * @author Josko
 *
 */
public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {
	private static final long serialVersionUID = -2291535264076831445L;

	public NemoguceOdreditiGrupuOsiguranjaException() {
		super();
	}
	/**
	 * Kreira novu iznimku.
	 * @param message poruka iznimke
	 * @param cause vrsta iznimke
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * Kreira novu iznimku.
	 * @param message poruka iznimke
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(String message) {
		super(message);
	}
	/**
	 * Kreira novu iznimku.
	 * @param cause vrsta iznimke
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(Throwable cause) {
		super(cause);
	}

}
