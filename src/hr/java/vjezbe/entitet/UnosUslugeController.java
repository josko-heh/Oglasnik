package hr.java.vjezbe.entitet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hr.java.vjezbe.glavna.Main;
import hr.java.vjezbe.util.GlavnaDatoteke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UnosUslugeController {

	public UnosUslugeController() {}

	
	@FXML private TextField naslovTextField;
	@FXML private TextField opisTextField;
	@FXML private TextField cijenaTextField;
	@FXML private ChoiceBox<Stanje> stanjeChoiceBox;
	
	@FXML private void initialize(){
		ObservableList<Stanje> availableChoices = FXCollections.observableList(Arrays.asList(Stanje.values())); 
		stanjeChoiceBox.setItems(availableChoices);
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
			BorderPane pane = (BorderPane)FXMLLoader.load(getClass().getResource("Usluga.fxml"));

			Main.setMainPage(pane);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void prikaziPretraguStana() {
		try {
			//URL url = new File("src\\hr\\java\\vjezbe\\entitet\\Stan.fxml").toURI().toURL();
			BorderPane pane = (BorderPane)FXMLLoader.load(getClass().getResource("Stan.fxml"));

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
	
	
	@FXML private void unesiGumb() {
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
				long maxId = Stream.of(GlavnaDatoteke.getListaAutomobila(), GlavnaDatoteke.getListaStanova(), GlavnaDatoteke.getListaUsluga())
						   .flatMap(lista -> lista.stream())
						   .collect(Collectors.toList())
						   .stream().mapToLong(Entitet::getId).max()
						   .getAsLong();;
				
				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
															new FileOutputStream("dat/Artikli.txt", true), StandardCharsets.UTF_8) //true za ukljucit append
														); 
				
				out.write("\n1\n" + Long.sum(maxId, 1) +"\n"+ naslov +"\n"+ opis +"\n"+ cijena +"\n"+ stanje.getBrojcanaVrijednostStanja());//stanje broj
			    out.close();
			    
			    /*trebalo bi dodavat i ID artikla u Kategorije.txt*/
			    
			    GlavnaDatoteke.getListaUsluga().add(new Usluga(Long.sum(maxId, 1), naslov, opis, cijena, stanje));
			    
			}catch(FileNotFoundException ex) {
				System.out.println("Greska prilikom ucitavanja datoteke!"); } 
			 catch(NoSuchElementException ex) {
				 System.out.println("Greska prilikom dohvaæanja maxId-a!"); } 
			 catch(IOException ex) {
				 System.out.println("Greska prilikom zapisivanja u datoteku!"); } 
			
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setContentText("Podatci uspjesno uneseni");
			alert.showAndWait();
		}
		
	}
	
}
