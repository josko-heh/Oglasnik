package hr.java.vjezbe.entitet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.glavna.Main;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import hr.java.vjezbe.niti.ZadnjaUslugaNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class StanController {
	
	@FXML Label zadnjaUslugaLabel;

	public StanController() {}
	
	@FXML private TextField naslovTextField;
	@FXML private TextField opisTextField;
	@FXML private TextField kvadraturaTextField;
	@FXML private TextField cijenaTextField;
	@FXML private TableView<Stan> stanoviTable;
	@FXML private TableColumn<Stan, String> naslovStupac;
	@FXML private TableColumn<Stan, String> opisStupac;
	@FXML private TableColumn<Stan, Integer> kvadraturaStupac;
	@FXML private TableColumn<Stan, BigDecimal> cijenaStupac;
	@FXML private TableColumn<Stan, Stanje> stanjeStupac;

	@FXML
	public void initialize() {
		naslovStupac.setCellValueFactory(new PropertyValueFactory<Stan, String>("naslov"));
		opisStupac.setCellValueFactory(new PropertyValueFactory<Stan, String>("opis"));
		cijenaStupac.setCellValueFactory(new PropertyValueFactory<Stan, BigDecimal>("cijena"));
		kvadraturaStupac.setCellValueFactory(new PropertyValueFactory<Stan, Integer>("kvadratura"));
		stanjeStupac.setCellValueFactory(new PropertyValueFactory<Stan, Stanje>("stanje"));
		
		try {
			stanoviTable.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiStanovePremaKriterijima("", "", null, null)));
		} catch (BazaPodatakaException e) {
			e.prikaziDijalog(e.getMessage());
			//e.printStackTrace();
		}
		
		
		Timeline prikazSlavljenika = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new ZadnjaUslugaNit(zadnjaUslugaLabel));
			}
		}
				));
		prikazSlavljenika.setCycleCount(Timeline.INDEFINITE);
		prikazSlavljenika.play();
	
	}
	

//-----------prikazi funkcije-----------
	@FXML private void prikaziPretraguProdaje() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\Prodaja.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziUnosProdaje() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\UnosProdaje.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziPretraguAutomobila() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\Automobil.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziPretraguUsluge() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\Usluga.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziPretraguStana() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\Stan.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziPretraguPoslovnogKorisnika() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\Poslovni_korisnik.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziPretraguPrivatnogKorisnika() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\Privatni_korisnik.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML private void prikaziUnosAutomobila() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\UnosAutomobila.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziUnosStana() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\UnosStana.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziUnosUsluge() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\UnosUsluge.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziUnosPrivatnogKorisnika() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\UnosPrivatnogKorisnika.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziUnosPoslovnogKorisnika() {
		try {
			URL url = new File("src\\hr\\java\\vjezbe\\entitet\\UnosPoslovnogKorisnika.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(url);

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML private void pretraziGumb() {
		String naslov = naslovTextField.getText();
		String opis = opisTextField.getText();
		Integer kvadratura = null;
		BigDecimal cijena = null;
		
		try {
			kvadratura = Integer.parseInt(kvadraturaTextField.getText());
		}catch(NumberFormatException ex) {} //ako je format broja krivi, filtriranje se ne dogaða
		
		try {
			cijena = new BigDecimal(cijenaTextField.getText());
		}catch(NumberFormatException ex) {} //ako je format broja krivi, filtriranje se ne dogaða
		
		
		try {
			stanoviTable.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiStanovePremaKriterijima(naslov, opis, cijena, kvadratura)));
		} catch (BazaPodatakaException e) {
			e.prikaziDijalog(e.getMessage());
			//e.printStackTrace();
		}
	}
	
}
