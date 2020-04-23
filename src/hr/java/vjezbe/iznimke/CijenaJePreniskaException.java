package hr.java.vjezbe.iznimke;
/**
 * Baca se u sluèaju da  je cijena preniska.
 * @author Josko
 *
 */
public class CijenaJePreniskaException extends RuntimeException {
	private static final long serialVersionUID = -7966744782829041743L;
	/**
	 * Kreira novu iznimku.
	 */
	public CijenaJePreniskaException() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Kreira novu iznimku.
	 * @param message poruka iznimke
	 */
	public CijenaJePreniskaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Kreira novu iznimku.
	 * @param cause vrsta iznimke
	 */
	public CijenaJePreniskaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Kreira novu iznimku.
	 * @param message poruka iznimke
	 * @param cause vrsta iznimke
	 */
	public CijenaJePreniskaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}


}
