package hr.java.vjezbe.niti;


import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumObjaveNiti implements Runnable {

	@Override
	public void run() {
		try {
			String poruka = BazaPodataka.dohvatiZadnjuUnesenuProdaju().toString();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setContentText(poruka);
			alert.showAndWait();
		} 
		catch (BazaPodatakaException e) {
			e.prikaziDijalog(e.getMessage());
			e.printStackTrace();
		}
	}

}
