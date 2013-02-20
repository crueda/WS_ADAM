package com.deimos.adam.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


import java.sql.*;
import java.util.*;
import java.lang.*;

@WebService(name="predictionWS")
public class PredictionWS {

@WebMethod public @WebResult(name="prediction") List<Integer> getSector(String vehicleLicense, long fecha){
	
	List<Integer> sector = new ArrayList<Integer>();
	Connection conn;
               
	String dbname = "kyros4f3_7sep";
	String dbuser = "root";
	String dbpass = "";
	String dbhost = "172.26.0.27";
	String dbport = "3306";
	               
    try {
						               
    	System.out.println("Creating JDBC connection...");
    	
		Class.forName("org.mysql.Driver");
		String url = "jdbc:mysql://" + dbhost + ":" + dbport + "/" + dbname;
		
		conn = DriverManager.getConnection(url, dbuser, dbpass);
		Statement s = conn.createStatement();
						                        
		System.out.println("Querying ...");
		ResultSet r = s.executeQuery("SELECT * FROM user_gui");
		while( r.next() )
		{
			Object obj =  r.getObject(1);
			//int id = r.getInt(2);
			//System.out.println("Row " + id + ":");
			System.out.println("ROW");
        }
		s.close();
		conn.close();
	}
	catch( Exception e ) {
		e.printStackTrace();
	}
					

    sector.add(1);
    sector.add(1);
    
    return sector;
}

}