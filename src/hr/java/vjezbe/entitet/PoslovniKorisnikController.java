package hr.java.vjezbe.entitet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.glavna.Main;
import hr.java.vjezbe.util.GlavnaDatoteke;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class PoslovniKorisnikController {

	public PoslovniKorisnikController() {}
	
	@FXML private TextField nazivTextField;
	@FXML private TextField webTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField telefonTextField;
	@FXML private TableView<PoslovniKorisnik> poslovniKorisniciTable;
	@FXML private TableColumn<PoslovniKorisnik, String> nazivStupac;
	@FXML private TableColumn<PoslovniKorisnik, String> webStupac;
	@FXML private TableColumn<PoslovniKorisnik, String> emailStupac;
	@FXML private TableColumn<PoslovniKorisnik, String> telefonStupac;

	@FXML
	public void initialize() {
		nazivStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("naziv"));
		webStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("web"));
		emailStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("email"));
		telefonStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("telefon"));
		
		poslovniKorisniciTable.setItems(FXCollections.observableArrayList(GlavnaDatoteke.getListaPoslovnihKorisnika()));
	}
	

//-----------prikazi funkcije-----------
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
		
		String naziv = nazivTextField.getText();
		String web= webTextField.getText();
		String email= emailTextField.getText();
		String telefon= telefonTextField.getText();
		//filtriraj naslov i opis
		List<PoslovniKorisnik> filtriranaLista = GlavnaDatoteke.getListaPoslovnihKorisnika().stream()
					.filter(a -> a.getNaziv().toUpperCase().contains(naziv.toUpperCase()) && a.getEmail().toUpperCase().contains(email.toUpperCase()) &&  a.getWeb().toUpperCase().contains(web.toUpperCase()) && a.getTelefon().toUpperCase().contains(telefon.toUpperCase()))
					.collect(Collectors.toList());
	
		
		poslovniKorisniciTable.setItems(FXCollections.observableArrayList(filtriranaLista));
	}
	
}
