package hr.java.vjezbe.entitet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

public class StanController {

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
		
		stanoviTable.setItems(FXCollections.observableArrayList(GlavnaDatoteke.getListaStanova()));
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
		
		String naslov = naslovTextField.getText();
		String opis = opisTextField.getText();
		//filtriraj naslov i opis
		List<Stan> filtriranaLista = GlavnaDatoteke.getListaStanova().stream()
					.filter(a -> a.getNaslov().toUpperCase().contains(naslov.toUpperCase()) && a.getOpis().toUpperCase().contains(opis.toUpperCase()))
					.collect(Collectors.toList());
		//filtriraj kvadraturu
		if(kvadraturaTextField.getText().equals("")) {}
		else {
			try {
				int kvadratura = Integer.parseInt(kvadraturaTextField.getText());
				filtriranaLista = filtriranaLista.stream().filter(a -> a.getKvadratura() == kvadratura)
														.collect(Collectors.toList());
			}catch(NumberFormatException ex) {} //ako je format broja krivi, filtriranje se ne dogaða
		}
		//filtriraj cijenu
		if(cijenaTextField.getText().equals("")) {}
		else {
			try {
				BigDecimal cijena = new BigDecimal(cijenaTextField.getText());
				filtriranaLista = filtriranaLista.stream().filter(a -> a.getCijena().compareTo(cijena) == 0)
						.collect(Collectors.toList());
			}catch(NumberFormatException ex) {} //ako je format broja krivi, filtriranje se ne dogaða
		}
		
		
		stanoviTable.setItems(FXCollections.observableArrayList(filtriranaLista));
	}
	
}
