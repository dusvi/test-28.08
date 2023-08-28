package dao;

import dto.ContoCorrente;
import exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class DaoContiCorrenti implements IDao<ContoCorrente,String>{
	private static final String QUERY_JOIN="select * from conti left join clienti on(conti.proprietario=clienti.codFiscale)";

	private static final String URL="URL";
	private static final String USERNAME="USERNAME";
	private static final String PASSWORD="PASSWORD";

	private String urlDB;
	private String username;
	private String password;

	private void setting() throws DaoException{
		Properties p=new Properties();
		try(InputStream input=getClass().getClassLoader().getResourceAsStream("./config.properties")){
			p.load(input);
			this.urlDB=p.getProperty(URL);
			this.username=p.getProperty(USERNAME);
			this.password=p.getProperty(PASSWORD);
		}catch(IOException e){
			throw new DaoException("Errore accesso al file config");
		}
	}

	protected Connection connect() throws DaoException{
		Connection c;
		try{
			if(this.urlDB==null){
				setting();
			}
			c=DriverManager.getConnection(urlDB,username,password);
		}catch(SQLException e){
			throw new DaoException("Errore connessione database");
		}
		return c;
	}
	public int create(ContoCorrente element) throws DaoException{
		try(Connection c=connect()){
			PreparedStatement stmt=c.prepareStatement("insert into conti(numeroConto,proprietario,abi,cab,cin,saldo) values(?,?,?,?,?,0)");
			stmt.setString(1,element.getNumeroConto());
			stmt.setString(2,element.getCodFiscaleTitolare());
			stmt.setString(3,element.getAbi());
			stmt.setString(4,element.getCab());
			stmt.setString(5,String.valueOf(element.getCin()));
			return stmt.executeUpdate();
		}catch(SQLException e){
			throw new DaoException("Errore metodo create");
		}
	}

	public List<ContoCorrente> ricercaPerCognome(String cognome) throws DaoException{
		List<ContoCorrente> result=new ArrayList<>();
		try(Connection c=connect()){
			Statement stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery(QUERY_JOIN+" where cognome='"+cognome+"';");
			while(rs.next()){
				ContoCorrente conto=new ContoCorrente(rs.getString("numeroConto"),rs.getString("abi"),rs.getString("cab"),rs.getString("cin").toCharArray()[0],rs.getString("nome"),rs.getString("cognome"),rs.getString("proprietario"),rs.getDouble("saldo"));
				result.add(conto);
			}
		}catch(SQLException e){
			throw new DaoException("Errore metodo ricercaPerCognome");
		}
		return result;
	}

	public ContoCorrente ricercaPerCodFiscale(String codFiscale) throws DaoException{
		ContoCorrente result=null;
		try(Connection c=connect()){
			Statement stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery(QUERY_JOIN+" where proprietario='"+codFiscale+"';");
			while(rs.next()){
				result=new ContoCorrente(rs.getString("numeroConto"),rs.getString("abi"),rs.getString("cab"),rs.getString("cin").toCharArray()[0],rs.getString("nome"),rs.getString("cognome"),rs.getString("proprietario"),rs.getDouble("saldo"));
			}
		}catch(SQLException e){
			throw new DaoException("Errore metodo ricercaPerCodFiscale");
		}
		return result;
	}
}
