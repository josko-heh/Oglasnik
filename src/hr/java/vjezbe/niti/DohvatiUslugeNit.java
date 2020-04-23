package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class DohvatiUslugeNit implements Runnable {

	@FXML private TableView<Usluga> uslugaTable;
	
	public DohvatiUslugeNit(TableView<Usluga> uslugaTable1) {
		super();
		uslugaTable = uslugaTable1;
	}
	@Override
	public void run() {
		try {
			uslugaTable.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiUslugePremaKriterijima("", "", null)));
		} catch (BazaPodatakaException e) {
			e.prikaziDijalog(e.getMessage());
			//e.printStackTrace();
		}
	}

}
