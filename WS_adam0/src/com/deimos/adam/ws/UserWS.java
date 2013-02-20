package com.deimos.adam.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.deimos.adam.utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebService(name="userWS")
public class UserWS {

private Connection connect = null;
private Statement statement = null;
private ResultSet resultSet = null;
	
@WebMethod public @WebResult(name="credit") Integer getCredit(String username){
         
    String dbname = Configuration.getInstance().getProperty(Configuration.MYSQL_DBNAME);
    String dbuser = Configuration.getInstance().getProperty(Configuration.MYSQL_SERVER_USER);
    String dbpass = Configuration.getInstance().getProperty(Configuration.MYSQL_SERVER_PASSWD);
	String dbhost = Configuration.getInstance().getProperty(Configuration.MYSQL_SERVER_IP);
	String dbport = Configuration.getInstance().getProperty(Configuration.MYSQL_SERVER_PORT);
	       
	int credito = 0;
   
	try {
	               
		System.out.println("Creating Mysql JDBC connection...");
		Class.forName("com.mysql.jdbc.Driver");
	    
		// Setup the connection with the DB
	    connect = DriverManager
	          .getConnection("jdbc:mysql://"+dbhost+":"+dbport+"/" + dbname+"?"+"user="+dbuser+"&password="+dbpass);
	    

	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    
	    // Result set get the result of the SQL query
	    resultSet = statement.executeQuery("select * from CREDIT where DESCRIPTION='"+username+"'");
	    while (resultSet.next()) {
	        credito = resultSet.getInt("SMS_CREDIT");
	    }
	    
	} catch (Exception e) {
	      //throw e;
		 e.printStackTrace();
	    } finally {
	      close();
	    }

	return credito;
	}

//You need to close the resultSet
private void close() {
	try {
		if (resultSet != null) {
			resultSet.close();
		}

		if (statement != null) {
			statement.close();
		}

		if (connect != null) {
			connect.close();
		}
	} catch (Exception e) {
	}
}

}