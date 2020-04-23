package hr.java.vjezbe.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;

public class GlavnaDatoteke {

	//-----liste---
	protected static List<Automobil> listaAutomobila = new ArrayList<>();
	protected static List<Stan> listaStanova = new ArrayList<>();
	protected static List<Usluga> listaUsluga = new ArrayList<>();
	protected static List<PoslovniKorisnik> listaPoslovnihKorisnika = new ArrayList<>();
	protected static List<PrivatniKorisnik> listaPrivatnihKorisnika = new ArrayList<>();
	//-----getteri---
	public static List<Automobil> getListaAutomobila() {
		return listaAutomobila;
	}
	public static List<Stan> getListaStanova() {
		return listaStanova;
	}
	public static List<Usluga> getListaUsluga() {
		return listaUsluga;
	}
	public static List<PoslovniKorisnik> getListaPoslovnihKorisnika() {
		return listaPoslovnihKorisnika;
	}
	public static List<PrivatniKorisnik> getListaPrivatnihKorisnika() {
		return listaPrivatnihKorisnika;
	}
	
	//-----funkcija---
	public static void ucitajDatoteke() {
		
	try {
//------------------------------ ucitavanje datoteka red po red u liste stringova-----------------------------------------------
			List<String> linijeKorisnika = Files.readAllLines(Path.of("dat\\Korisnici.txt"), StandardCharsets.UTF_8);
			linijeKorisnika.set(0, linijeKorisnika.get(0).substring(1));
			List<String> linijeKategorija = Files.readAllLines(Path.of("dat\\Kategorije.txt"), StandardCharsets.UTF_8);
			linijeKategorija.set(0, linijeKategorija.get(0).substring(1));
			List<String> linijeArtikala = Files.readAllLines(Path.of("dat\\Artikli.txt"), StandardCharsets.UTF_8);
			linijeArtikala.set(0, linijeArtikala.get(0).substring(1));
					
			/* linijeArtikala (za automobil i stan) po redovima:
			 tip(1 oznaèava uslugu, 2 oznaèava automobila, a 3 oznaèava stan), id, naslov, opis, snaga/kvadratura, cijena, stanje
			 linijeArtikala za uslugu po redovima:
			 tip(1 oznaèava uslugu, 2 oznaèava automobila, a 3 oznaèava stan), id, naslov, opis, cijena, stanje
			 */
//---------------------------------------------------kreiranje konkretnih lista iz linija---------------------------------------
			//--------korisnici-------
			for(int m=0; m < linijeKorisnika.size(); m+=6) {
				if(linijeKorisnika.get(m).equals("1")) listaPrivatnihKorisnika.add(new PrivatniKorisnik(Long.parseLong(linijeKorisnika.get(m+1)), linijeKorisnika.get(m+4), linijeKorisnika.get(m+5), linijeKorisnika.get(m+2), linijeKorisnika.get(m+3)));
				else if(linijeKorisnika.get(m).equals("2"))  listaPoslovnihKorisnika.add(new PoslovniKorisnik(Long.parseLong(linijeKorisnika.get(m+1)), linijeKorisnika.get(m+4), linijeKorisnika.get(m+5), linijeKorisnika.get(m+2), linijeKorisnika.get(m+3)));
				else throw new IOException("Upis korisnika nije uspio");
			}
			//--------kategorije i artikli------
			for(int m=0; m < linijeKategorija.size(); m+=3) {
				/**
				 * Potreban citac jer ova linija ne sadrži samo jednu stavku, nego više(brojeva koji predstavljaju idove artikala.)
				 */
				Scanner citac = new Scanner(linijeKategorija.get(m+2));

				while(citac.hasNextInt()) {
					int idArtikla = citac.nextInt();
					for(int x=0; x<linijeArtikala.size(); x+=7) {
						if(Long.parseLong(linijeArtikala.get(x+1)) == idArtikla) {
							if(linijeArtikala.get(x).equals("1")) listaUsluga.add(new Usluga(Long.parseLong(linijeArtikala.get(x+1)), linijeArtikala.get(x+2), linijeArtikala.get(x+3), new BigDecimal(linijeArtikala.get(x+4)), Stanje.values()[Integer.parseInt(linijeArtikala.get(x+5))-1]));
							else if(linijeArtikala.get(x).equals("2")) listaAutomobila.add(new Automobil(Long.parseLong(linijeArtikala.get(x+1)), linijeArtikala.get(x+2), linijeArtikala.get(x+3), new BigDecimal(linijeArtikala.get(x+5)), new BigDecimal(linijeArtikala.get(x+4)), Stanje.values()[Integer.parseInt(linijeArtikala.get(x+6))-1]));
							else if(linijeArtikala.get(x).equals("3")) listaStanova.add(new Stan(Long.parseLong(linijeArtikala.get(x+1)), linijeArtikala.get(x+2), linijeArtikala.get(x+3), new BigDecimal(linijeArtikala.get(x+5)), Integer.parseInt(linijeArtikala.get(x+4)), Stanje.values()[Integer.parseInt(linijeArtikala.get(x+6))-1]));
							else System.out.println("!Neuspjesno dodavanje artikla!");
							break;
						}
						if(linijeArtikala.get(x).equals("1")) x--; //jer usluga zauzima 6 redova, a automobil i stan 7
					}
				}
				citac.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
