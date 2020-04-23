package hr.java.vjezbe.entitet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.glavna.Main;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import hr.java.vjezbe.niti.ZadnjaUslugaNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class ProdajaController {
	
	@FXML Label zadnjaUslugaLabel;

	public ProdajaController() {}

	@FXML private ComboBox<Artikl> artiklComboBox;
	@FXML private ComboBox<Korisnik> korisnikComboBox;
	@FXML private DatePicker datumPicker;
	@FXML private TableView<Prodaja> prodajaTable;
	@FXML private TableColumn<Prodaja, Artikl> oglasStupac;
	@FXML private TableColumn<Prodaja, Korisnik> korisnikStupac;
	@FXML private TableColumn<Prodaja, LocalDate> datumStupac;

	@FXML
	public void initialize() {
		try {
			ObservableList<Artikl> izborArtikala = FXCollections.observableList(BazaPodataka.dohvatiSveArtikle());
			ObservableList<Korisnik> izborKorisnika = FXCollections.observableList(BazaPodataka.dohvatiSveKorisnike());
			artiklComboBox.setItems(izborArtikala);
			korisnikComboBox.setItems(izborKorisnika);

			oglasStupac.setCellValueFactory(new PropertyValueFactory<Prodaja, Artikl>("artikl"));
			korisnikStupac.setCellValueFactory(new PropertyValueFactory<Prodaja, Korisnik>("korisnik"));
			datumStupac.setCellValueFactory(new PropertyValueFactory<Prodaja, LocalDate>("datumObjave"));
			prodajaTable.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiProdajuPremaKriterijima(null)));
			
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

		Artikl artikl = artiklComboBox.getSelectionModel().getSelectedItem();;
		Korisnik korisnik = korisnikComboBox.getSelectionModel().getSelectedItem();
		LocalDate datumObjave = datumPicker.getValue();
		
		try {
			prodajaTable.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiProdajuPremaKriterijima(new Prodaja(artikl, korisnik, datumObjave))));
		} catch (BazaPodatakaException e) {
			e.prikaziDijalog(e.getMessage());
			//e.printStackTrace();
		}
	}

}
