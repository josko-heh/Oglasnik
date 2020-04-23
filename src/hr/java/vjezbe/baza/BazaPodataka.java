package hr.java.vjezbe.baza;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {
	private static final String DATABASE_FILE = "dat/database.properties";

	private static Connection spajanjeNaBazu() throws SQLException, IOException{
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));

		String urlBaze = svojstva.getProperty("urlBaze");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");

		Connection veza = DriverManager.getConnection(urlBaze, korisnickoIme, lozinka);

		return veza;
	}
	
	public static Usluga dohvatiZadnjuUnesenuUslugu() throws BazaPodatakaException{
		Usluga usluga = null;

		try(Connection veza = spajanjeNaBazu()){
			String sqlUpit = "SELECT distinct artikl.* FROM artikl inner join stanje on stanje.id = artikl.idStanje"
					+ " inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'"
					+ " order by id desc limit 1;";
			Statement query = veza.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit);
			resultSet.next();

			long dohvacenId = resultSet.getLong("id");
			String dohvaceniNaslov = resultSet.getString("naslov");
			String dohvaceniOpis = resultSet.getString("opis");
			BigDecimal dohvacenaCijena = resultSet.getBigDecimal("cijena");
			Integer dohvacenoStanje = resultSet.getInt("IDSTANJE");

			query.close();
			resultSet.close();
			
			usluga = new Usluga(dohvacenId, dohvaceniNaslov, dohvaceniOpis, dohvacenaCijena, Stanje.getStanje(dohvacenoStanje));

		}catch(SQLException | IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}
		return usluga;
	}
	
	public static List<Prodaja> dohvatiProdajuPremaKriterijima(Prodaja prodaja) throws BazaPodatakaException {
		List<Prodaja> listaProdaje = new ArrayList<>();
		
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
							"select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika, korisnik.naziv as nazivKorisnika, web, email, telefon, korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla, artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura, artikl.snaga, stanje.naziv as stanje, prodaja.datumObjave, artikl.id as idArtikla from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join prodaja on prodaja.idKorisnik = korisnik.id inner join artikl on artikl.id = prodaja.idArtikl inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join stanje on stanje.id = artikl.idStanje where 1=1");
			
			if (Optional.ofNullable(prodaja).isEmpty() == false) {
				if (Optional.ofNullable(prodaja.getArtikl()).isPresent())
					sqlUpit.append(" AND prodaja.idArtikl = " + prodaja.getArtikl().getId());

				if (Optional.ofNullable(prodaja.getKorisnik()).isPresent())
					sqlUpit.append(" AND prodaja.idArtikl = " + prodaja.getKorisnik().getId());

				if (Optional.ofNullable(prodaja.getDatumObjave()).isPresent()) 
					sqlUpit.append(" AND prodaja.datumObjave = '" + prodaja.getDatumObjave().format(DateTimeFormatter.ISO_DATE) + "'"); 
			}
			
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Korisnik korisnik = null;
				if(resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
					korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"),  resultSet.getString("email"), resultSet.getString("telefon"),  resultSet.getString("ime"), resultSet.getString("prezime"));
				}
				else if(resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
					korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("email"), resultSet.getString("telefon"), resultSet.getString("nazivKorisnika"), resultSet.getString("web"));
				}

				Artikl artikl = null;
				if(resultSet.getString("tipArtikla").equals("Automobil")) {
					artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("naslov"), resultSet.getString("opis"), resultSet.getBigDecimal("cijena"), resultSet.getBigDecimal("snaga"), Stanje.valueOf(resultSet.getString("stanje")));
				}
				else if(resultSet.getString("tipArtikla").equals("Usluga")) {
					artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("naslov"), resultSet.getString("opis"), resultSet.getBigDecimal("cijena"), Stanje.valueOf(resultSet.getString("stanje")));
				}
				else if(resultSet.getString("tipArtikla").equals("Stan")) {
					artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("naslov"), resultSet.getString("opis"), resultSet.getBigDecimal("cijena"), resultSet.getInt("kvadratura"), Stanje.valueOf(resultSet.getString("stanje")));
				}

				Prodaja novaProdaja = new Prodaja(artikl, korisnik, resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				listaProdaje.add(novaProdaja);
			}
			query.close();
			resultSet.close();
		} catch (SQLException | IOException e) {
			throw new BazaPodatakaException( "Došlo je do pogreške u radu s bazom podataka", e);
		}
		return listaProdaje;
	} 

	public static List<Stan> dohvatiStanovePremaKriterijima(String naslov, String opis, BigDecimal cijena, Integer kvadratura) throws BazaPodatakaException {
		List<Stan> listaStanova = new ArrayList<>();

		try (Connection connection = spajanjeNaBazu()) {

			StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv " + "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");

			//if (Optional.ofNullable(stan).isEmpty() == false) {
			if (!naslov.isBlank()) 
				sqlUpit.append(" AND upper(artikl.naslov) LIKE '%" + naslov.toUpperCase() + "%'");

			if (!opis.isBlank()) 
				sqlUpit.append(" AND upper(artikl.opis) LIKE '%" + opis.toUpperCase() + "%'");

			if (cijena != null) 
				sqlUpit.append(" AND artikl.cijena = " + cijena);

			if (kvadratura != null) 
				sqlUpit.append(" AND artikl.kvadratura = " + kvadratura);
			//}


			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				long dohvacenId = resultSet.getLong("id");
				String dohvaceniNaslov = resultSet.getString("naslov");
				String dohvaceniOpis = resultSet.getString("opis");
				BigDecimal dohvacenaCijena = resultSet.getBigDecimal("cijena");
				Integer dohvacenaKvadratura = resultSet.getInt("kvadratura");
				String dohvacenoStanje = resultSet.getString("naziv");

				Stan newStan = new Stan(dohvacenId, dohvaceniNaslov, dohvaceniOpis, dohvacenaCijena, dohvacenaKvadratura, Stanje.valueOf(dohvacenoStanje));
				listaStanova.add(newStan);
			}
			resultSet.close();
			query.close();

		} catch (SQLException  ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}catch (IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške kod ucitavanja Properties", ex);
		}

		return listaStanova;
	}

	public static List<Automobil> dohvatiAutomobilePremaKriterijima(String naslov, String opis, BigDecimal cijena, BigDecimal snaga) throws BazaPodatakaException {
		List<Automobil> listaAutomobila = new ArrayList<>();

		try (Connection connection = spajanjeNaBazu()) {

			StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv " + "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil'");

			if (!naslov.isBlank()) 
				sqlUpit.append(" AND upper(artikl.naslov) LIKE '%" + naslov.toUpperCase() + "%'");

			if (!opis.isBlank()) 
				sqlUpit.append(" AND upper(artikl.opis) LIKE '%" + opis.toUpperCase() + "%'");

			if (cijena != null) 
				sqlUpit.append(" AND artikl.cijena = " + cijena);

			if (snaga != null) 
				sqlUpit.append(" AND artikl.snaga = " + snaga);


			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				long dohvacenId = resultSet.getLong("id");
				String dohvaceniNaslov = resultSet.getString("naslov");
				String dohvaceniOpis = resultSet.getString("opis");
				BigDecimal dohvacenaCijena = resultSet.getBigDecimal("cijena");
				BigDecimal dohvacenaSnaga= resultSet.getBigDecimal("snaga");
				String dohvacenoStanje = resultSet.getString("naziv");

				Automobil newAutomobil = new Automobil(dohvacenId, dohvaceniNaslov, dohvaceniOpis, dohvacenaCijena, dohvacenaSnaga, Stanje.valueOf(dohvacenoStanje));
				listaAutomobila.add(newAutomobil);
			}
			resultSet.close();
			query.close();

		} catch (SQLException  ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}catch (IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške kod ucitavanja Properties", ex);
		}

		return listaAutomobila;
	}

	public static List<Usluga> dohvatiUslugePremaKriterijima(String naslov, String opis, BigDecimal cijena) throws BazaPodatakaException {
		List<Usluga> listaUsluga = new ArrayList<>();

		try (Connection connection = spajanjeNaBazu()) {

			StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena,  stanje.naziv " + "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");

			if (!naslov.isBlank()) 
				sqlUpit.append(" AND upper(artikl.naslov) LIKE '%" + naslov.toUpperCase() + "%'");

			if (!opis.isBlank()) 
				sqlUpit.append(" AND upper(artikl.opis) LIKE '%" + opis.toUpperCase() + "%'");

			if (cijena != null) 
				sqlUpit.append(" AND artikl.cijena = " + cijena);


			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				long dohvacenId = resultSet.getLong("id");
				String dohvaceniNaslov = resultSet.getString("naslov");
				String dohvaceniOpis = resultSet.getString("opis");
				BigDecimal dohvacenaCijena = resultSet.getBigDecimal("cijena");
				String dohvacenoStanje = resultSet.getString("naziv");

				Usluga newUsluga = new Usluga(dohvacenId, dohvaceniNaslov, dohvaceniOpis, dohvacenaCijena, Stanje.valueOf(dohvacenoStanje));
				listaUsluga.add(newUsluga);
			}
			resultSet.close();
			query.close();

		} catch (SQLException  ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}catch (IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške kod ucitavanja Properties", ex);
		}

		return listaUsluga;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Korisnik> List<T> dohvatiKorisnikePremaKriterijima(T korisnik) throws BazaPodatakaException {
		List<T> listaKorisnika = new ArrayList<>();

		try (Connection connection = spajanjeNaBazu()) {
			if(korisnik instanceof PoslovniKorisnik) {
				StringBuilder sqlUpit = new StringBuilder("SELECT distinct korisnik.id, korisnik.naziv, web, email, telefon " + "FROM korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika WHERE tipKorisnika.naziv = 'PoslovniKorisnik'");

				if (!((PoslovniKorisnik) korisnik).getNaziv().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.naziv) LIKE '%" + ((PoslovniKorisnik) korisnik).getNaziv().toUpperCase() + "%'");

				if (!((PoslovniKorisnik) korisnik).getWeb().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.web) LIKE '%" + ((PoslovniKorisnik) korisnik).getWeb().toUpperCase() + "%'");

				if (!((PoslovniKorisnik) korisnik).getEmail().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.email) LIKE '%" + ((PoslovniKorisnik) korisnik).getEmail().toUpperCase() + "%'");

				if (!((PoslovniKorisnik) korisnik).getTelefon().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.telefon) LIKE '%" + ((PoslovniKorisnik) korisnik).getTelefon().toUpperCase() + "%'");


				Statement query = connection.createStatement();
				ResultSet resultSet = query.executeQuery(sqlUpit.toString());
				while (resultSet.next()) {
					long id = resultSet.getLong("id");
					String naziv = resultSet.getString("naziv");
					String web = resultSet.getString("web");
					String email = resultSet.getString("email");
					String telefon = resultSet.getString("telefon");

					PoslovniKorisnik newKorisnik =  new PoslovniKorisnik(id, email, telefon, naziv, web);
					listaKorisnika.add((T) newKorisnik);
				}
				resultSet.close();
				query.close();

			}else if(korisnik instanceof PrivatniKorisnik) {
				StringBuilder sqlUpit = new StringBuilder("SELECT distinct korisnik.id, korisnik.ime, prezime, email, telefon " + "FROM korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika WHERE tipKorisnika.naziv = 'PrivatniKorisnik'");

				if (!((PrivatniKorisnik) korisnik).getIme().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.ime) LIKE '%" + ((PrivatniKorisnik) korisnik).getIme().toUpperCase() + "%'");

				if (!((PrivatniKorisnik) korisnik).getPrezime().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.prezime) LIKE '%" + ((PrivatniKorisnik) korisnik).getPrezime().toUpperCase() + "%'");

				if (!((PrivatniKorisnik) korisnik).getEmail().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.email) LIKE '%" + ((PrivatniKorisnik) korisnik).getEmail().toUpperCase() + "%'");

				if (!((PrivatniKorisnik) korisnik).getTelefon().isBlank()) 
					sqlUpit.append(" AND upper(korisnik.telefon) LIKE '%" + ((PrivatniKorisnik) korisnik).getTelefon().toUpperCase() + "%'");


				Statement query = connection.createStatement();
				ResultSet resultSet = query.executeQuery(sqlUpit.toString());
				while (resultSet.next()) {
					long id = resultSet.getLong("id");
					String ime = resultSet.getString("ime");
					String prezime = resultSet.getString("prezime");
					String email = resultSet.getString("email");
					String telefon = resultSet.getString("telefon");

					PrivatniKorisnik newKorisnik =  new PrivatniKorisnik(id, email, telefon, ime, prezime);
					listaKorisnika.add((T) newKorisnik);
				}
				resultSet.close();
				query.close();
			}

		} catch (SQLException  ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}catch (IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške kod ucitavanja Properties", ex);
		}

		return listaKorisnika;
	}	

	public static List<Artikl> dohvatiSveArtikle() throws BazaPodatakaException {
		try (Connection connection = spajanjeNaBazu()) {
			List<Artikl> listaArtikala= new ArrayList<>();

			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM ARTIKL");

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				if(resultSet.getLong("idTipArtikla") == 1) {
					long dohvacenId = resultSet.getLong("id");
					String dohvaceniNaslov = resultSet.getString("naslov");
					String dohvaceniOpis = resultSet.getString("opis");
					BigDecimal dohvacenaCijena = resultSet.getBigDecimal("cijena");
					BigDecimal dohvacenaSnaga= resultSet.getBigDecimal("snaga");
					Integer dohvacenoStanje = resultSet.getInt("idStanje");

					Automobil newAutomobil = new Automobil(dohvacenId, dohvaceniNaslov, dohvaceniOpis, dohvacenaCijena, dohvacenaSnaga, Stanje.getStanje(dohvacenoStanje));
					listaArtikala.add(newAutomobil);
				}
				else if(resultSet.getLong("idTipArtikla") == 2) {
					long dohvacenId = resultSet.getLong("id");
					String dohvaceniNaslov = resultSet.getString("naslov");
					String dohvaceniOpis = resultSet.getString("opis");
					BigDecimal dohvacenaCijena = resultSet.getBigDecimal("cijena");
					Integer dohvacenoStanje = resultSet.getInt("idStanje");

					Usluga newUsluga = new Usluga(dohvacenId, dohvaceniNaslov, dohvaceniOpis, dohvacenaCijena, Stanje.getStanje(dohvacenoStanje));
					listaArtikala.add(newUsluga);
				}
				else if(resultSet.getLong("idTipArtikla") == 3) {
					long dohvacenId = resultSet.getLong("id");
					String dohvaceniNaslov = resultSet.getString("naslov");
					String dohvaceniOpis = resultSet.getString("opis");
					BigDecimal dohvacenaCijena = resultSet.getBigDecimal("cijena");
					Integer dohvacenaKvadratura = resultSet.getInt("kvadratura");
					Integer dohvacenoStanje = resultSet.getInt("idStanje");

					Stan newStan = new Stan(dohvacenId, dohvaceniNaslov, dohvaceniOpis, dohvacenaCijena, dohvacenaKvadratura, Stanje.getStanje(dohvacenoStanje));
					listaArtikala.add(newStan);
				}
			}
			resultSet.close();
			query.close();
			
			return listaArtikala;
			
		} catch (SQLException  ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}catch (IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške kod ucitavanja Properties", ex);
		}
	}
	
	public static List<Korisnik> dohvatiSveKorisnike() throws BazaPodatakaException {
		try (Connection connection = spajanjeNaBazu()) {
			List<Korisnik> listaKorisnika= new ArrayList<>();

			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM KORISNIK");

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				if(resultSet.getLong("idTipKorisnika") == 1) {
					long id = resultSet.getLong("id");
					String ime = resultSet.getString("ime");
					String prezime = resultSet.getString("prezime");
					String email = resultSet.getString("email");
					String telefon = resultSet.getString("telefon");

					PrivatniKorisnik newKorisnik =  new PrivatniKorisnik(id, email, telefon, ime, prezime);
					listaKorisnika.add(newKorisnik);
				}
				else if(resultSet.getLong("idTipKorisnika") == 2) {
					long id = resultSet.getLong("id");
					String naziv = resultSet.getString("naziv");
					String web = resultSet.getString("web");
					String email = resultSet.getString("email");
					String telefon = resultSet.getString("telefon");

					PoslovniKorisnik newKorisnik =  new PoslovniKorisnik(id, email, telefon, naziv, web);
					listaKorisnika.add(newKorisnik);
				}
			}
			resultSet.close();
			query.close();
			
			return listaKorisnika;
			
		} catch (SQLException  ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}catch (IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške kod ucitavanja Properties", ex);
		}
	}


	public static void pohraniNovuProdaju(Prodaja prodaja) throws BazaPodatakaException{
		try (Connection veza = spajanjeNaBazu()) {

			PreparedStatement preparedStatement = veza.prepareStatement("insert into prodaja(idartikl, idkorisnik, datumObjave) " + "values (?, ?, ?);"); 

			preparedStatement.setLong(1, prodaja.getArtikl().getId());
			preparedStatement.setLong(2, prodaja.getKorisnik().getId());
			preparedStatement.setObject(3, prodaja.getDatumObjave());

			preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException | IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}
	}
	
	public static void pohraniNoviStan(Stan stan) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {

			PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, ?, 3);"); 

			preparedStatement.setString(1, stan.getNaslov());
			preparedStatement.setString(2, stan.getOpis());
			preparedStatement.setBigDecimal(3, stan.getCijena());
			preparedStatement.setInt(4, stan.getKvadratura());
			preparedStatement.setLong(5, (stan.getStanje().ordinal() + 1)); //getBrojcanaVrijednostStanja()

			preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException | IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}
	}

	public static void pohraniNoviAutomobil(Automobil automobil) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {

			PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, ?, 1);"); 

			preparedStatement.setString(1, automobil.getNaslov());
			preparedStatement.setString(2, automobil.getOpis());
			preparedStatement.setBigDecimal(3, automobil.getCijena());
			preparedStatement.setBigDecimal(4, automobil.getSnagaKs());
			preparedStatement.setLong(5, (automobil.getStanje().ordinal() + 1)); //getBrojcanaVrijednostStanja()

			preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException | IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}
	}

	public static void pohraniNovuUslugu(Usluga usluga) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {

			PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, 2);"); 

			preparedStatement.setString(1, usluga.getNaslov());
			preparedStatement.setString(2, usluga.getOpis());
			preparedStatement.setBigDecimal(3, usluga.getCijena());
			preparedStatement.setLong(4, (usluga.getStanje().ordinal() + 1)); //getBrojcanaVrijednostStanja()

			preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException | IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}
	}

	public static <T extends Korisnik> void pohraniNovogKorisnika(T korisnik) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			if(korisnik instanceof PoslovniKorisnik) {
				PreparedStatement preparedStatement = veza.prepareStatement("insert into korisnik(naziv, web, email, telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 2);"); 

				preparedStatement.setString(1, ((PoslovniKorisnik) korisnik).getNaziv());
				preparedStatement.setString(2, ((PoslovniKorisnik) korisnik).getWeb());
				preparedStatement.setString(3, ((PoslovniKorisnik) korisnik).getEmail());
				preparedStatement.setString(4, ((PoslovniKorisnik) korisnik).getTelefon());

				preparedStatement.executeUpdate();

				preparedStatement.close();

			}else if(korisnik instanceof PrivatniKorisnik) {
				PreparedStatement preparedStatement = veza.prepareStatement("insert into korisnik(ime, prezime,  email, telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 1);"); 

				preparedStatement.setString(1, ((PrivatniKorisnik) korisnik).getIme());
				preparedStatement.setString(2, ((PrivatniKorisnik) korisnik).getPrezime());
				preparedStatement.setString(3, ((PrivatniKorisnik) korisnik).getEmail());
				preparedStatement.setString(4, ((PrivatniKorisnik) korisnik).getTelefon());

				preparedStatement.executeUpdate();

				preparedStatement.close();
			}

		} catch (SQLException | IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}
	}
	public static Prodaja dohvatiZadnjuUnesenuProdaju() throws BazaPodatakaException{
		Prodaja prodaja = null;
		
		try(Connection veza = spajanjeNaBazu()){
			String sqlUpit = "select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika, korisnik.naziv as nazivKorisnika, web, email, telefon," + 
					" korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla, artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura, artikl.snaga, stanje.naziv as stanje," +
					" prodaja.datumObjave, artikl.id as idArtikla from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join" + 
					" prodaja on prodaja.idKorisnik = korisnik.id inner join artikl on artikl.id = prodaja.idArtikl inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join stanje on stanje.id = artikl.idStanje" + 
					" order by datumObjave desc limit 1";
			Statement query = veza.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit);
			resultSet.next();
			
			Korisnik korisnik = null;
			if(resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
				korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"),  resultSet.getString("email"), resultSet.getString("telefon"),  resultSet.getString("ime"), resultSet.getString("prezime"));
			}
			else if(resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
				korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("email"), resultSet.getString("telefon"), resultSet.getString("nazivKorisnika"), resultSet.getString("web"));
			}

			Artikl artikl = null;
			if(resultSet.getString("tipArtikla").equals("Automobil")) {
				artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("naslov"), resultSet.getString("opis"), resultSet.getBigDecimal("cijena"), resultSet.getBigDecimal("snaga"), Stanje.valueOf(resultSet.getString("stanje")));
			}
			else if(resultSet.getString("tipArtikla").equals("Usluga")) {
				artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("naslov"), resultSet.getString("opis"), resultSet.getBigDecimal("cijena"), Stanje.valueOf(resultSet.getString("stanje")));
			}
			else if(resultSet.getString("tipArtikla").equals("Stan")) {
				artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("naslov"), resultSet.getString("opis"), resultSet.getBigDecimal("cijena"), resultSet.getInt("kvadratura"), Stanje.valueOf(resultSet.getString("stanje")));
			}
			
			prodaja = new Prodaja(artikl, korisnik, resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			
		}catch(SQLException | IOException ex) {
			throw new BazaPodatakaException("Došlo je do pogreške u radu s bazom podataka", ex);
		}
		return prodaja;
	}

}
