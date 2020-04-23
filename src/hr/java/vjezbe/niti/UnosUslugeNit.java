package hr.java.vjezbe.niti;

import java.math.BigDecimal;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class UnosUslugeNit implements Runnable {

	@FXML private TextField naslovTextField;
	@FXML private TextField opisTextField;
	@FXML private TextField cijenaTextField;
	@FXML private ChoiceBox<Stanje> stanjeChoiceBox;
	
	public UnosUslugeNit(TextField naslovTextField, TextField opisTextField, TextField cijenaTextField, ChoiceBox<Stanje> stanjeChoiceBox) {
		super();
		this.naslovTextField = naslovTextField;
		this.opisTextField = opisTextField;
		this.cijenaTextField = cijenaTextField;
		this.stanjeChoiceBox = stanjeChoiceBox;
	}
	@Override
	public void run() {
		String errorText = "";

		String naslov = naslovTextField.getText();
		String opis = opisTextField.getText();
		BigDecimal cijena = null;
		Stanje stanje = null;

		if (naslov.trim().isEmpty()) { errorText += "Naslov je obavezan podatak!" + System.lineSeparator();}
		if (opis.trim().isEmpty()) { errorText += "Opis je obavezan podatak!" + System.lineSeparator();}


		try { 
			cijena = new BigDecimal(cijenaTextField.getText()); }
		catch(NumberFormatException ex) { 
			errorText += "Cijena mora biti broj!" + System.lineSeparator();} //ako je format broja krivi

		if(stanjeChoiceBox.getSelectionModel().isEmpty()) errorText += "Stanje mora biti odabrano!" + System.lineSeparator();
		else {stanje = stanjeChoiceBox.getSelectionModel().getSelectedItem(); }



		if(!errorText.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText(errorText);
			alert.showAndWait();
		}else { 
			try {
				BazaPodataka.pohraniNovuUslugu(new Usluga(0, naslov, opis, cijena, stanje));

				
			}catch (BazaPodatakaException ex) {
				ex.prikaziDijalog(ex.getMessage());}
		}
	}

}
