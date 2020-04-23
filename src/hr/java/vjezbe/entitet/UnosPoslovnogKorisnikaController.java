package hr.java.vjezbe.entitet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hr.java.vjezbe.glavna.Main;
import hr.java.vjezbe.util.GlavnaDatoteke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UnosPoslovnogKorisnikaController {

	public UnosPoslovnogKorisnikaController() {}

	@FXML private TextField nazivTextField;
	@FXML private TextField webTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField telefonTextField;

	
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
		
		String naziv = nazivTextField.getText();
		String web = webTextField.getText();
		String email = emailTextField.getText();
		String telefon = telefonTextField.getText();
		
		if (naziv.trim().isEmpty()) { errorText += "Naziv je obavezan podatak!" + System.lineSeparator();}
		if (web.trim().isEmpty()) { errorText += "Web je obavezan podatak!" + System.lineSeparator();}
		if (email.trim().isEmpty()) { errorText += "Email je obavezan podatak!" + System.lineSeparator();}
		if (telefon.trim().isEmpty()) { errorText += "Telefon je obavezan podatak!" + System.lineSeparator();}
		
		
		if(!errorText.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText(errorText);
			alert.showAndWait();
		}else { 
			try {				
				long maxId = Stream.of(GlavnaDatoteke.getListaPrivatnihKorisnika(), GlavnaDatoteke.getListaPoslovnihKorisnika())
						   .flatMap(lista -> lista.stream())
						   .collect(Collectors.toList())
						   .stream().mapToLong(Entitet::getId).max()
						   .getAsLong();;
				
				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
															new FileOutputStream("dat/Korisnici.txt", true), StandardCharsets.UTF_8) //true za ukljucit append
														); 
				
				out.write("\n2\n" + Long.sum(maxId, 1) +"\n"+ naziv +"\n"+ email +"\n"+ web +"\n"+ telefon);
			    out.close();
			    
			    GlavnaDatoteke.getListaPoslovnihKorisnika().add(new PoslovniKorisnik(Long.sum(maxId, 1), email, telefon, naziv, web));
			    
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
