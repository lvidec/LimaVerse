package database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.WebApp.Login.Customer;
import com.WebApp.Login.Headphone;
import com.WebApp.Login.Laptop;
import com.WebApp.Login.Tv;

import exception.DatabaseException;

public class Database {
	
	private static final String DATABASE_FILE= "C:\\Java\\Spring Boot\\LimaVerse\\src\\main\\java\\Database\\database.properties";
			
	private static Connection ConnectToDatabase() throws FileNotFoundException, IOException, SQLException{
		
		Properties properties = new Properties();
		
		properties.load(new FileReader(DATABASE_FILE));
		
		String database = properties.getProperty("database");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		Connection connection = DriverManager.getConnection(database,username,password);
	
		return connection;
	}
	
	public static boolean isRegistered(String email, String password) throws DatabaseException {
		
		try (Connection connection = ConnectToDatabase()){
			
			StringBuilder sqlQuery = new StringBuilder("Select * from Customer"
				+ " WHERE Customer.email LIKE '" + email + "' AND Customer.password LIKE '" + password + "'");
			
			Statement query = connection.createStatement();
			
			ResultSet resultSet = query.executeQuery(sqlQuery.toString());
			
			while (resultSet.next()) {
				String emailCustomer = resultSet.getString("email");
				String passwordCustomer = resultSet.getString("password");
				
				if( emailCustomer != null && passwordCustomer != null)
					return true;
			}
			
		}catch (SQLException | IOException e) {
			String poruka = "Error in working with database !";
			
			throw new DatabaseException(poruka, e);
		}
		
		return false;
	}
	
	public static List<Customer> getCustomers(Customer customer) throws DatabaseException {
		
		List<Customer> listOfCutomers = new ArrayList<>();
		
		try (Connection connection = ConnectToDatabase()){
			
			StringBuilder sqlQuery = new StringBuilder("Select * from Customer");
			
			if (Optional.ofNullable(customer).isPresent()) {
				
				sqlQuery.append(" WHERE Customer.id = Customer.id");
				
//				System.out.println("\n\nCustomer is present\n");
				
				if (Optional.ofNullable(customer).map(Customer::getCid).isPresent()) 
					sqlQuery.append(" AND Customer.id = " + customer.getCid());
//				System.out.println("id");}
//				System.out.println("Proso id\n");
				
				if (Optional.ofNullable(customer.getCname()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Customer.username LIKE '%" + customer.getCname() + "%'");
//				System.out.println("username");}
//				System.out.println("proso username\n");
				
				if (Optional.ofNullable(customer.getCemail()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Customer.email LIKE '%" + customer.getCemail() + "%'");
//				System.out.println("email");}
//				System.out.println("proso email\n");

				if (Optional.ofNullable(customer.getCpassword()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Customer.password LIKE '%" + customer.getCpassword() + "%'");
//				System.out.println("password");}
//				System.out.println("proso password\n");
			}
			
			
			Statement query = connection.createStatement();
			
			ResultSet resultSet = query.executeQuery(sqlQuery.toString());
			
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("username");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				
				Customer newCustomer = new Customer(id, name, email, password);
				
				listOfCutomers.add(newCustomer);
			}
			
		}catch (SQLException | IOException e) {
			String poruka = "Error in working with database !";
			
			throw new DatabaseException(poruka, e);
		}
		
		return listOfCutomers;
	}
	
	
	public static void setCustomer(Customer customer) throws DatabaseException {
		try (Connection connection = ConnectToDatabase()) {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into Customer(username, email, password) " +
			"values (?, ?, ?);");
			
			preparedStatement.setString(1, customer.getCname());
			preparedStatement.setString(2, customer.getCemail());
			preparedStatement.setString(3, customer.getCpassword());
			preparedStatement.executeUpdate();
			
		}catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";

			throw new DatabaseException(poruka, ex);
		}

	}
	
	
	public static List<Laptop> getLaptop(Laptop laptop) throws DatabaseException {
		
		List<Laptop> listOfLaptops = new ArrayList<>();
		
		try (Connection connection = ConnectToDatabase()){
			
			StringBuilder sqlQuery = new StringBuilder("SELECT distinct Product.id, productName, productDescription, pathOfPic, price, specs \r\n" + 
					"FROM Product inner join typeOfProduct on Product.idTypeOfProduct = typeOfProduct.id\r\n" + 
					"WHERE typeOfProduct.typeName = 'Laptop'");
			
			if (Optional.ofNullable(laptop).isPresent()) {
				
//				sqlQuery.append(" WHERE Product.id = Product.id");
				
//				System.out.println("\n\nProduct is present\n");
				
				if (Optional.ofNullable(laptop).map(Laptop::getPid).isPresent())
					sqlQuery.append(" AND Product.id = " + laptop.getPid());
//				System.out.println("id");}
//				System.out.println("Proso id\n");
				
				if (Optional.ofNullable(laptop.getPname()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.productName LIKE '%" + laptop.getPname() + "%'");
//				System.out.println("name");}
//				System.out.println("proso name\n");
				
				if (Optional.ofNullable(laptop.getPdescription()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.productDescription LIKE '%" + laptop.getPdescription() + "%'");
//				System.out.println("productDescription");}
//				System.out.println("proso productDescription\n");

				if (Optional.ofNullable(laptop.getPpicPath()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.pathOfPic LIKE '%" + laptop.getPpicPath() + "%'");
//				System.out.println("pathOfPic");}
//				System.out.println("proso pathOfPic\n");
			
				if (Optional.ofNullable(laptop).map(Laptop::getPprice).isPresent()) 
					sqlQuery.append(" AND Product.price LIKE '%" + laptop.getPpicPath() + "%'");
//				System.out.println("price");}
//				System.out.println("proso price\n");
				
				if (Optional.ofNullable(laptop.getSpecs()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.specs LIKE '%" + laptop.getSpecs() + "%'");
//				System.out.println("specs");}
//				System.out.println("proso specs\n");
			
			}
			
			
			Statement query = connection.createStatement();
			
			ResultSet resultSet = query.executeQuery(sqlQuery.toString());
			
			while (resultSet.next()) {
				Integer pid = resultSet.getInt("id");
				String pname = resultSet.getString("productName");
				String pdescription = resultSet.getString("productDescription");
				String ppicPath = resultSet.getString("pathOfPic");
				Float pprice = resultSet.getFloat("price");
				String specs = resultSet.getString("specs");
				
				Laptop newLaptop = new Laptop(pid, pname, pdescription, ppicPath, pprice, specs);
				
				listOfLaptops.add(newLaptop);
			}
			
		}catch (SQLException | IOException e) {
			String poruka = "Error in working with database !";
			
			throw new DatabaseException(poruka, e);
		}
		
		return listOfLaptops;
	}
	
	
	public static void setLaptop(Laptop laptop) throws DatabaseException {
		try (Connection connection = ConnectToDatabase()) {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into Product(productName, productDescription, pathOfPic, price, specs, idTypeOfProduct) " +
			"values (?, ?, ?, ?, ?, 1);");
			
			preparedStatement.setString(1, laptop.getPname());
			preparedStatement.setString(2, laptop.getPdescription());
			preparedStatement.setString(3, laptop.getPpicPath());
			preparedStatement.setFloat(4, laptop.getPprice());
			preparedStatement.setString(5, laptop.getSpecs());
			preparedStatement.executeUpdate();
			
		}catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";

			throw new DatabaseException(poruka, ex);
		}

	}
	
	
	
	public static List<Tv> getTv(Tv tv) throws DatabaseException {
		
		List<Tv> listOfTvs = new ArrayList<>();
		
		try (Connection connection = ConnectToDatabase()){
			
			StringBuilder sqlQuery = new StringBuilder("SELECT distinct Product.id, productName, productDescription, pathOfPic, price, resolution \r\n" + 
					"FROM Product inner join typeOfProduct on Product.idTypeOfProduct = typeOfProduct.id\r\n" + 
					"WHERE typeOfProduct.typeName = 'TV'");
			
			if (Optional.ofNullable(tv).isPresent()) {
				
//				sqlQuery.append(" WHERE Product.id = Product.id");
				
//				System.out.println("\n\nProduct is present\n");
				
				if (Optional.ofNullable(tv).map(Tv::getPid).isPresent()) 
					sqlQuery.append(" AND Product.id = " + tv.getPid());
//				System.out.println("id");}
//				System.out.println("Proso id\n");
				
				if (Optional.ofNullable(tv.getPname()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.productName LIKE '%" + tv.getPname() + "%'");
//				System.out.println("name");}
//				System.out.println("proso name\n");
				
				if (Optional.ofNullable(tv.getPdescription()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.productDescription LIKE '%" + tv.getPdescription() + "%'");
//				System.out.println("productDescription");}
//				System.out.println("proso productDescription\n");

				if (Optional.ofNullable(tv.getPpicPath()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.pathOfPic LIKE '%" + tv.getPpicPath() + "%'");
//				System.out.println("pathOfPic");}
//				System.out.println("proso pathOfPic\n");
			
				if (Optional.ofNullable(tv).map(Tv::getPprice).isPresent()) 
					sqlQuery.append(" AND Product.price LIKE '%" + tv.getPpicPath() + "%'");
//				System.out.println("price");}
//				System.out.println("proso price\n");
				
				if (Optional.ofNullable(tv.getResolution()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.resolution LIKE '%" + tv.getResolution() + "%'");
//				System.out.println("specs");}
//				System.out.println("proso specs\n");
			
			}
			
			
			Statement query = connection.createStatement();
			
			ResultSet resultSet = query.executeQuery(sqlQuery.toString());
			
			while (resultSet.next()) {
				Integer pid = resultSet.getInt("id");
				String pname = resultSet.getString("productName");
				String pdescription = resultSet.getString("productDescription");
				String ppicPath = resultSet.getString("pathOfPic");
				Float pprice = resultSet.getFloat("price");
				String resolution = resultSet.getString("resolution");
				
				Tv newTv = new Tv(pid, pname, pdescription, ppicPath, pprice, resolution);
				
				listOfTvs.add(newTv);
			}
			
		}catch (SQLException | IOException e) {
			String poruka = "Error in working with database !";
			
			throw new DatabaseException(poruka, e);
		}
		
		return listOfTvs;
	}
	
	
	public static void setTv(Tv Tv) throws DatabaseException {
		try (Connection connection = ConnectToDatabase()) {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into Product(productName, productDescription, pathOfPic, price, resolution, idTypeOfProduct) " +
			"values (?, ?, ?, ?, ?, 2);");
			
			preparedStatement.setString(1, Tv.getPname());
			preparedStatement.setString(2, Tv.getPdescription());
			preparedStatement.setString(3, Tv.getPpicPath());
			preparedStatement.setFloat(4, Tv.getPprice());
			preparedStatement.setString(5, Tv.getResolution());
			preparedStatement.executeUpdate();
			
		}catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";

			throw new DatabaseException(poruka, ex);
		}

	}
	
	
	public static List<Headphone> getHeadphone(Headphone headphone) throws DatabaseException {
		
		List<Headphone> listOfHeadphones = new ArrayList<>();
		
		try (Connection connection = ConnectToDatabase()){
			
			StringBuilder sqlQuery = new StringBuilder("SELECT distinct Product.id, productName, productDescription, pathOfPic, price \r\n" + 
					"FROM Product inner join typeOfProduct on Product.idTypeOfProduct = typeOfProduct.id\r\n" + 
					"WHERE typeOfProduct.typeName = 'Headphone'");
			
			if (Optional.ofNullable(headphone).isPresent()) {
				
//				sqlQuery.append(" WHERE Product.id = Product.id");
				
//				System.out.println("\n\nProduct is present\n");
				
				if (Optional.ofNullable(headphone).map(Headphone::getPid).isPresent()) 
					sqlQuery.append(" AND Product.id = " + headphone.getPid());
//				System.out.println("id");}
//				System.out.println("Proso id\n");
				
				if (Optional.ofNullable(headphone.getPname()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.productName LIKE '%" + headphone.getPname() + "%'");
//				System.out.println("name");}
//				System.out.println("proso name\n");
				
				if (Optional.ofNullable(headphone.getPdescription()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.productDescription LIKE '%" + headphone.getPdescription() + "%'");
//				System.out.println("productDescription");}
//				System.out.println("proso productDescription\n");

				if (Optional.ofNullable(headphone.getPpicPath()).map(String::isEmpty).orElse(true) == false) 
					sqlQuery.append(" AND Product.pathOfPic LIKE '%" + headphone.getPpicPath() + "%'");
//				System.out.println("pathOfPic");}
//				System.out.println("proso pathOfPic\n");
			
				if (Optional.ofNullable(headphone).map(Headphone::getPprice).isPresent()) 
					sqlQuery.append(" AND Product.price LIKE '%" + headphone.getPpicPath() + "%'");
//				System.out.println("price");}
//				System.out.println("proso price\n");
			
			}
			
			
			Statement query = connection.createStatement();
			
			ResultSet resultSet = query.executeQuery(sqlQuery.toString());
			
			while (resultSet.next()) {
				Integer pid = resultSet.getInt("id");
				String pname = resultSet.getString("productName");
				String pdescription = resultSet.getString("productDescription");
				String ppicPath = resultSet.getString("pathOfPic");
				Float pprice = resultSet.getFloat("price");
				
				Headphone newHeadphone = new Headphone(pid, pname, pdescription, ppicPath, pprice);
				
				listOfHeadphones.add(newHeadphone);
			}
			
		}catch (SQLException | IOException e) {
			String poruka = "Error in working with database !";
			
			throw new DatabaseException(poruka, e);
		}
		
		return listOfHeadphones;
	}
	
	
	public static void setHeadphone(Headphone Headphone) throws DatabaseException {
		try (Connection connection = ConnectToDatabase()) {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into Product(productName, productDescription, pathOfPic, price, idTypeOfProduct) " +
			"values (?, ?, ?, ?, 3);");
			
			preparedStatement.setString(1, Headphone.getPname());
			preparedStatement.setString(2, Headphone.getPdescription());
			preparedStatement.setString(3, Headphone.getPpicPath());
			preparedStatement.setFloat(4, Headphone.getPprice());
			preparedStatement.executeUpdate();
			
		}catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";

			throw new DatabaseException(poruka, ex);
		}

	}
	
	
//		public static List<Usluga> dohvatiUslugePremaKriterijima(Usluga usluga) throws BazaPodatakaException {
//			List<Usluga> listaUsluga = new ArrayList<>();
//			
//			try (Connection connection = spajanjeNaBazu()) {
//				StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv "
//					+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
//					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");
//				if (Optional.ofNullable(usluga).isEmpty() == false) {
//					
//					if (Optional.ofNullable(usluga).map(Usluga::getId).isPresent())
//						sqlUpit.append(" AND artikl.id = " + usluga.getId());
//					
//					if (Optional.ofNullable(usluga.getNaslov()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND artikl.naslov LIKE '%" + usluga.getNaslov() + "%'");
//
//					if (Optional.ofNullable(usluga.getOpis()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND artikl.opis LIKE '%" + usluga.getOpis() + "%'");
//					
//					if (Optional.ofNullable(usluga).map(Usluga::getCijena).isPresent())
//						sqlUpit.append(" AND artikl.cijena = " + usluga.getCijena());
//				}
//				
//				Statement query = connection.createStatement();
//				
//				ResultSet resultSet = query.executeQuery(sqlUpit.toString());
//				
//				while (resultSet.next()) {
//					Long id = resultSet.getLong("id");
//					String naslov = resultSet.getString("naslov");
//					String opis = resultSet.getString("opis");
//					BigDecimal cijena = resultSet.getBigDecimal("cijena");
//					String stanje = resultSet.getString("naziv");
//					
//					Usluga novaUsluga = new Usluga(naslov, opis, cijena, Stanje.valueOf(stanje), id);
//					
//					listaUsluga.add(novaUsluga);
//				}
//			} catch (SQLException | IOException e) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, e);
//				throw new BazaPodatakaException(poruka, e);
//			}
//			
//			return listaUsluga;
//		}
//		
//		
//		public static void pohraniNovuUslugu(Usluga usluga) throws BazaPodatakaException {
//			try (Connection veza = spajanjeNaBazu()) {
//				PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) " +
//				"values (?, ?, ?, ?, 2);");
//				
//				preparedStatement.setString(1, usluga.getNaslov());
//				preparedStatement.setString(2, usluga.getOpis());
//				preparedStatement.setBigDecimal(3, usluga.getCijena());
//				preparedStatement.setLong(4, (usluga.getStanje().ordinal() + 1));
//				preparedStatement.executeUpdate();
//			}catch (SQLException | IOException ex) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, ex);
//				throw new BazaPodatakaException(poruka, ex);
//			}
//
//		}
//		
//		
//		
//		
//		public static List<Automobil> dohvatiAutomobilePremaKriterijima(Automobil automobil) throws BazaPodatakaException {
//			List<Automobil> listaAutomobila = new ArrayList<>();
//			
//			try (Connection connection = spajanjeNaBazu()) {
//				StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv\r\n" + 
//						"FROM artikl inner join stanje on stanje.id = artikl.idStanje\r\n" + 
//						"inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv =\r\n" + 
//						"'Automobil'");
//				
//				if (Optional.ofNullable(automobil).isEmpty() == false) {
//					
//					if (Optional.ofNullable(automobil).map(Automobil::getId).isPresent())
//						sqlUpit.append(" AND artikl.id = " + automobil.getId());
//					
//					if (Optional.ofNullable(automobil.getNaslov()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND artikl.naslov LIKE '%" + automobil.getNaslov() + "%'");
//					
//					if (Optional.ofNullable(automobil.getOpis()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND artikl.opis LIKE '%" + automobil.getOpis() + "%'");
//					
//					if (Optional.ofNullable(automobil).map(Automobil::getCijena).isPresent())
//						sqlUpit.append(" AND artikl.cijena = " + automobil.getCijena());
//
//					if (Optional.ofNullable(automobil).map(Automobil::getSnagaKs).isPresent())
//						sqlUpit.append(" AND artikl.snaga = " + automobil.getSnagaKs());
//				}
//				
//				Statement query = connection.createStatement();
//				
//				ResultSet resultSet = query.executeQuery(sqlUpit.toString());
//				
//				while (resultSet.next()) {
//					Long id = resultSet.getLong("id");
//					String naslov = resultSet.getString("naslov");
//					String opis = resultSet.getString("opis");
//					BigDecimal cijena = resultSet.getBigDecimal("cijena");
//					BigDecimal snagaKs = resultSet.getBigDecimal("snaga");
//					String stanje = resultSet.getString("naziv");
//					
//					Automobil noviAutomobil = new Automobil(naslov, opis, cijena, Stanje.valueOf(stanje), snagaKs, id);
//					
//					listaAutomobila.add(noviAutomobil);
//				}
//			} catch (SQLException | IOException e) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, e);
//				throw new BazaPodatakaException(poruka, e);
//			}
//			
//			return listaAutomobila;
//		}
//		
//		
//		public static void pohraniNoviAutomobil(Automobil automobil) throws BazaPodatakaException {
//			try (Connection veza = spajanjeNaBazu()) {
//				PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) " +
//						"values (?, ?, ?, ?, ?, 1);");
//				
//				preparedStatement.setString(1, automobil.getNaslov());
//				preparedStatement.setString(2, automobil.getOpis());
//				preparedStatement.setBigDecimal(3, automobil.getCijena());
//				preparedStatement.setBigDecimal(4, automobil.getSnagaKs());
//				preparedStatement.setLong(5, (automobil.getStanje().ordinal() + 1));
//				preparedStatement.executeUpdate();
//			}catch (SQLException | IOException ex) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, ex);
//				throw new BazaPodatakaException(poruka, ex);
//			}
//			
//		}
//		
//
//		
//		
//		
//		public static List<Stan> dohvatiStanovePremaKriterijima(Stan stan) throws BazaPodatakaException {
//			List<Stan> listaStanova = new ArrayList<>();
//			
//			try (Connection connection = spajanjeNaBazu()) {
//				StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv "
//					+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
//					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");
//				if (Optional.ofNullable(stan).isEmpty() == false) {
//					
//					if (Optional.ofNullable(stan).map(Stan::getId).isPresent())
//						sqlUpit.append(" AND artikl.id = " + stan.getId());
//					
//					if (Optional.ofNullable(stan.getNaslov()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND artikl.naslov LIKE '%" + stan.getNaslov() + "%'");
//
//					if (Optional.ofNullable(stan.getOpis()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND artikl.opis LIKE '%" + stan.getOpis() + "%'");
//					
//					if (Optional.ofNullable(stan).map(Stan::getCijena).isPresent())
//						sqlUpit.append(" AND artikl.cijena = " + stan.getCijena());
//				
//					if (Optional.ofNullable(stan).map(Stan::getKvadratura).isPresent())
//						sqlUpit.append(" AND artikl.kvadratura = " + stan.getKvadratura());
//				}
//				
//				Statement query = connection.createStatement();
//				
//				ResultSet resultSet = query.executeQuery(sqlUpit.toString());
//				
//				while (resultSet.next()) {
//					Long id = resultSet.getLong("id");
//					String naslov = resultSet.getString("naslov");
//					String opis = resultSet.getString("opis");
//					BigDecimal cijena = resultSet.getBigDecimal("cijena");
//					Integer kvadratura = resultSet.getInt("kvadratura");
//					String stanje = resultSet.getString("naziv");
//					
//					Stan noviStan = new Stan(naslov, opis, cijena, kvadratura, Stanje.valueOf(stanje), id);
//					
//					listaStanova.add(noviStan);
//				}
//			} catch (SQLException | IOException e) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, e);
//				throw new BazaPodatakaException(poruka, e);
//			}
//			
//			return listaStanova;
//		}
//		
//		
//		public static void pohraniNoviStan(Stan stan) throws BazaPodatakaException {
//			try (Connection veza = spajanjeNaBazu()) {
//				PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) " +
//				"values (?, ?, ?, ?, ?, 3);");
//				
//				preparedStatement.setString(1, stan.getNaslov());
//				preparedStatement.setString(2, stan.getOpis());
//				preparedStatement.setBigDecimal(3, stan.getCijena());
//				preparedStatement.setInt(4, stan.getKvadratura());
//				preparedStatement.setLong(5, (stan.getStanje().ordinal() + 1));
//				preparedStatement.executeUpdate();
//			}catch (SQLException | IOException ex) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, ex);
//				throw new BazaPodatakaException(poruka, ex);
//			}
//
//		}
//
//		
//		
//		
//		public static List<PrivatniKorisnik> dohvatiPrivatnePremaKriterijima(PrivatniKorisnik privatni) throws BazaPodatakaException {
//			List<PrivatniKorisnik> listaPrivatnih = new ArrayList<>();
//			
//			try (Connection connection = spajanjeNaBazu()) {
//				StringBuilder sqlUpit = new StringBuilder("select distinct korisnik.id, ime, prezime, email, telefon\r\n" + 
//						"from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika\r\n" + 
//						"where tipKorisnika.naziv = 'PrivatniKorisnik'");
//				
//				if (Optional.ofNullable(privatni).isEmpty() == false) {
//					
//					if (Optional.ofNullable(privatni).map(PrivatniKorisnik::getId).isPresent())
//						sqlUpit.append(" AND korisnik.id = " + privatni.getId());
//					
//					if (Optional.ofNullable(privatni.getIme()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND korisnik.ime LIKE '%" + privatni.getIme() + "%'");
//					
//					if (Optional.ofNullable(privatni.getPrezime()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND korisnik.prezime LIKE '%" + privatni.getPrezime() + "%'");
//					
//					if (Optional.ofNullable(privatni).map(PrivatniKorisnik::getEmail).isPresent())
//						sqlUpit.append(" AND korisnik.email LIKE '%" + privatni.getEmail());
//					
//					if (Optional.ofNullable(privatni).map(PrivatniKorisnik::getTelefon).isPresent())
//						sqlUpit.append(" AND korisnik.telefon LIKE '%" + privatni.getTelefon());
//				}
//				
//				Statement query = connection.createStatement();
//				
//				ResultSet resultSet = query.executeQuery(sqlUpit.toString());
//				
//				while (resultSet.next()) {
//					Long id = resultSet.getLong("id");
//					String ime = resultSet.getString("ime");
//					String prezime = resultSet.getString("prezime");
//					String email = resultSet.getString("email");
//					String telefon = resultSet.getString("telefon");
//					
//					PrivatniKorisnik noviPrivatni = new PrivatniKorisnik(ime, prezime, email, telefon, id);
//					
//					listaPrivatnih.add(noviPrivatni);
//				}
//			} catch (SQLException | IOException e) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, e);
//				throw new BazaPodatakaException(poruka, e);
//			}
//			
//			return listaPrivatnih;
//		}
//		
//		
//		public static void pohraniNoviPrivatniKorisnik(PrivatniKorisnik PrivatniKorisnik) throws BazaPodatakaException {
//			try (Connection veza = spajanjeNaBazu()) {
//				PreparedStatement preparedStatement = veza.prepareStatement("insert into korisnik(ime, prezime, email, telefon, idtipkorisnika) " +
//						"values (?, ?, ?, ?, 1);");
//				
//				preparedStatement.setString(1, PrivatniKorisnik.getIme());
//				preparedStatement.setString(2, PrivatniKorisnik.getPrezime());
//				preparedStatement.setString(3, PrivatniKorisnik.getEmail());
//				preparedStatement.setString(4, PrivatniKorisnik.getTelefon());
//				preparedStatement.executeUpdate();
//			}catch (SQLException | IOException ex) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, ex);
//				throw new BazaPodatakaException(poruka, ex);
//			}
//			
//		}
//
//		
//		
//		
//		
//		public static List<PoslovniKorisnik> dohvatiPoslovnePremaKriterijima(PoslovniKorisnik poslovni) throws BazaPodatakaException {
//			List<PoslovniKorisnik> listaPoslovnih = new ArrayList<>();
//			
//			try (Connection connection = spajanjeNaBazu()) {
//				StringBuilder sqlUpit = new StringBuilder("select distinct korisnik.id, korisnik.naziv, web, email, telefon\r\n" + 
//						"from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika\r\n" + 
//						"where tipKorisnika.naziv = 'PoslovniKorisnik'");
//				
//				if (Optional.ofNullable(poslovni).isEmpty() == false) {
//					
//					if (Optional.ofNullable(poslovni).map(PoslovniKorisnik::getId).isPresent())
//						sqlUpit.append(" AND korisnik.id = " + poslovni.getId());
//					
//					if (Optional.ofNullable(poslovni.getNaziv()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND korisnik.naziv LIKE '%" + poslovni.getNaziv() + "%'");
//					
//					if (Optional.ofNullable(poslovni.getWeb()).map(String::isBlank).orElse(true) == false)
//						sqlUpit.append(" AND korisnik.web LIKE '%" + poslovni.getWeb() + "%'");
//					
//					if (Optional.ofNullable(poslovni).map(PoslovniKorisnik::getEmail).isPresent())
//						sqlUpit.append(" AND korisnik.email LIKE '%" + poslovni.getEmail());
//					
//					if (Optional.ofNullable(poslovni).map(PoslovniKorisnik::getTelefon).isPresent())
//						sqlUpit.append(" AND korisnik.telefon LIKE '%" + poslovni.getTelefon());
//				}
//				
//				Statement query = connection.createStatement();
//				
//				ResultSet resultSet = query.executeQuery(sqlUpit.toString());
//				
//				while (resultSet.next()) {
//					Long id = resultSet.getLong("id");
//					String naziv = resultSet.getString("naziv");
//					String web = resultSet.getString("web");
//					String email = resultSet.getString("email");
//					String telefon = resultSet.getString("telefon");
//					
//					PoslovniKorisnik noviPoslovni = new PoslovniKorisnik(naziv, web, email, telefon, id);
//					
//					listaPoslovnih.add(noviPoslovni);
//				}
//			} catch (SQLException | IOException e) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, e);
//				throw new BazaPodatakaException(poruka, e);
//			}
//			
//			return listaPoslovnih;
//		}
//		
//		
//		public static void pohraniNoviPoslovniKorisnik(PoslovniKorisnik PoslovniKorisnik) throws BazaPodatakaException {
//			try (Connection veza = spajanjeNaBazu()) {
//				PreparedStatement preparedStatement = veza.prepareStatement("insert into korisnik(naziv, web, email, telefon, idtipkorisnika) " +
//						"values (?, ?, ?, ?, 2);");
//				
//				preparedStatement.setString(1, PoslovniKorisnik.getNaziv());
//				preparedStatement.setString(2, PoslovniKorisnik.getWeb());
//				preparedStatement.setString(3, PoslovniKorisnik.getEmail());
//				preparedStatement.setString(4, PoslovniKorisnik.getTelefon());
//				preparedStatement.executeUpdate();
//			}catch (SQLException | IOException ex) {
//				String poruka = "Došlo je do pogreške u radu s bazom podataka";
//				
//				logger.error(poruka, ex);
//				throw new BazaPodatakaException(poruka, ex);
//			}
//			
//		}
//		
//	}


	
}
