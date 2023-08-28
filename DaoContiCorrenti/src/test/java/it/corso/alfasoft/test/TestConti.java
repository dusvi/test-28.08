package it.corso.alfasoft.test;

import dao.DaoContiCorrenti;
import dto.ContoCorrente;
import exception.DaoException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class TestConti
{
    private static final DaoContiCorrenti dao=new DaoContiCorrenti();
    private static final String URL="URL";
    private static final String USERNAME="USERNAME";
    private static final String PASSWORD="PASSWORD";

    private static String urlDB;
    private static String username;
    private static String password;

    private static void setting() throws DaoException{
        Properties p=new Properties();
        try(InputStream input=ClassLoader.getSystemResourceAsStream("./config.properties")){
            p.load(input);
            urlDB=p.getProperty(URL);
            username=p.getProperty(USERNAME);
            password=p.getProperty(PASSWORD);
        }catch(IOException e){
            throw new DaoException("Errore accesso al file config");
        }
    }

    private static Connection connect() throws DaoException{
        Connection c;
        try{
            if(urlDB==null){
                setting();
            }
            c=DriverManager.getConnection(urlDB,username,password);
        }catch(SQLException e){
            throw new DaoException(e.getMessage());
        }
        return c;
    }

    @BeforeAll
    public static void initDB() throws DaoException{
        try(Connection c=connect();BufferedReader br=new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("testConti.sql")))){
            String query="", line="";
            while(line!=null){
                query+=line;
                line=br.readLine();
            }
            String[] queries=query.split(";");
            for(String s: queries){
                c.createStatement().executeUpdate(s);
            }
        }catch(SQLException|DaoException|IOException e){
            throw new DaoException(e.getMessage());
        }
    }
    @Test
    public void testCreate() throws DaoException{
        ContoCorrente conto=new ContoCorrente("000000000004","00000","00000",'d',"D","Bianchi","AAAAAD11A01A111A",0);
        Assertions.assertEquals(dao.create(conto),1,"Errore nel metodo create. Dati non inseriti");
    }
    @Test
    public void testRicercaPerCognome() throws DaoException{
        List<ContoCorrente> expected=new ArrayList<>();
        expected.add(new ContoCorrente("000000000001","00000","00000",'a',"A","Rossi","AAAAAA11A01A111A",0));
        expected.add(new ContoCorrente("000000000002","00000","00000",'b',"B","Rossi","AAAAAB11A01A111A",0));
        List<ContoCorrente> actual=dao.ricercaPerCognome("Rossi");
        for(ContoCorrente conto: expected){
            Assertions.assertTrue(actual.contains(conto),"Errore nel metodo testRicercaPerCognome. Dati non corretti");
        }
        for(ContoCorrente conto: actual){
            Assertions.assertTrue(expected.contains(conto),"Errore nel metodo testRicercaPerCognome. Dati non corretti");
        }
    }
    @Test
    public void testRicercaPerCodFiscale() throws DaoException{
        ContoCorrente expected=new ContoCorrente("000000000003","00000","00000",'c',"C","Verdi","AAAAAC11A01A111A",0);
        ContoCorrente actual=dao.ricercaPerCodFiscale("AAAAAC11A01A111A");
        Assertions.assertEquals(expected,actual,"Errore nel metodo testRicercaPerCodFiscale. Dati non corretti");
    }
}
