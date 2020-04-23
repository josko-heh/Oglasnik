package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.Label;

public class ZadnjaUslugaNit implements Runnable {

	Label zadnjaUslugaLabel;
	
	public ZadnjaUslugaNit(Label zadnjaUslugaLabel1) {
		super();
		zadnjaUslugaLabel = zadnjaUslugaLabel1;
	}
	@Override
	public void run() {
		try {
			String poruka = BazaPodataka.dohvatiZadnjuUnesenuUslugu().toString();
			
			zadnjaUslugaLabel.setText(poruka);
		} 
		catch (BazaPodatakaException e) {
			e.prikaziDijalog(e.getMessage());
			e.printStackTrace();
		}
	}

}
