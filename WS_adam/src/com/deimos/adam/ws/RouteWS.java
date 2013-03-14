package com.deimos.adam.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.deimos.adam.dto.VertexDto;

import java.sql.*;
import java.util.*;
import java.lang.*;
import org.postgis.*;

@WebService(name="routeWS")
public class RouteWS {

@WebMethod public @WebResult(name="route") List<VertexDto> getRoute(int initVertex, int endVertex){

	List<VertexDto> route = new ArrayList<VertexDto>();
	VertexDto vertex;
	//VertexDto vertex = new VertexDto();
	//vertex.setId("1");
	//vertex.setLatitude("4");
	//vertex.setLongitude("4");
	
	Connection conn;
              
    String dbname = "adam";
    String dbuser = "user";
    String dbpass = "user";
	String dbhost = "172.26.0.2";
	String dbport = "5432";
	               

	try {
	               
		System.out.println("Creating JDBC connection...");
	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;
	    conn = DriverManager.getConnection(url, dbuser, dbpass);
	    Statement s = conn.createStatement();
	                        
	    System.out.println("Querying ...");
	    
	    String query = "SELECT * FROM shortest_path('SELECT gid as id,source::integer,target::integer,length::double precision as cost FROM ways'," + initVertex + ", " +endVertex+ ", false, false);";
	    ResultSet r = s.executeQuery(query);
	    while( r.next() )
	    {
	    	Object obj =  r.getObject(1);
	        int id = r.getInt(2);
	        System.out.println("Row " + id + ":");
	                                
	        vertex = new VertexDto();
	        vertex.setId(id);
	        route.add(vertex);
	     }
	     	s.close();
	     	conn.close();
	   }
	   catch( Exception e ) {
		   e.printStackTrace();
	   }

	return route;
	}
}