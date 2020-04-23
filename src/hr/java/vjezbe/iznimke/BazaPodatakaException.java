package hr.java.vjezbe.iznimke;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BazaPodatakaException extends Exception {
	private static final long serialVersionUID = 6186263838409046177L;
	
	public BazaPodatakaException() {
	}
	public BazaPodatakaException(String message) {
		super(message);
	}
	public BazaPodatakaException(Throwable cause) {
		super(cause);
	}
	public BazaPodatakaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	public void prikaziDijalog(String errorText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(errorText);
		alert.showAndWait();		
	}

}
