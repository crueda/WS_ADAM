package com.deimos.adam.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.deimos.adam.utils.Configuration;


import java.sql.*;


@WebService(name="routeWS")
public class RouteWS {

@WebMethod public @WebResult(name="routevertex") List<Integer> getVertexRoute(int initVertex, int endVertex){

	//List<VertexDto> route = new ArrayList<VertexDto>();
	//VertexDto vertex;
	
	List<Integer> route = new ArrayList<Integer>();
	
	Connection conn;
   
	/*
    String dbname = "adam";
    String dbuser = "user";
    String dbpass = "user";
	String dbhost = "172.26.0.2";
	String dbport = "5432";*/
	
	String dbname = Configuration.getInstance().getProperty(Configuration.PG_DBNAME);
    String dbuser = Configuration.getInstance().getProperty(Configuration.PG_SERVER_USER);
    String dbpass = Configuration.getInstance().getProperty(Configuration.PG_SERVER_PASSWD);
	String dbhost = Configuration.getInstance().getProperty(Configuration.PG_SERVER_IP);
	String dbport = Configuration.getInstance().getProperty(Configuration.PG_SERVER_PORT);
	               

	try {
	               
	    System.out.println("getVertexRoute ("+initVertex+","+endVertex+")");

	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;
	    conn = DriverManager.getConnection(url, dbuser, dbpass);
	    Statement s = conn.createStatement();
	                        
	    
	    String query = "SELECT * FROM shortest_path('SELECT gid as id,source::integer,target::integer,length::double precision as cost FROM ways'," + initVertex + ", " +endVertex+ ", false, false);";
	    ResultSet r = s.executeQuery(query);
	    while( r.next() )
	    {
	        int id = r.getInt(1);

	        if (id!=-1)
	        	route.add(id);
	     }
	     	s.close();
	     	conn.close();
	   }
	   catch( Exception e ) {
		   e.printStackTrace();
	   }

	return route;
	}

@WebMethod public @WebResult(name="routepath") List<Integer> getPathRoute(int initVertex, int endVertex){

	//List<VertexDto> route = new ArrayList<VertexDto>();
	//VertexDto vertex;
	
	List<Integer> route = new ArrayList<Integer>();
	
	Connection conn;
              
	String dbname = Configuration.getInstance().getProperty(Configuration.PG_DBNAME);
    String dbuser = Configuration.getInstance().getProperty(Configuration.PG_SERVER_USER);
    String dbpass = Configuration.getInstance().getProperty(Configuration.PG_SERVER_PASSWD);
	String dbhost = Configuration.getInstance().getProperty(Configuration.PG_SERVER_IP);
	String dbport = Configuration.getInstance().getProperty(Configuration.PG_SERVER_PORT);
	               

	try {
	               
	    System.out.println("getPathRoute ("+initVertex+","+endVertex+")");

	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;
	    conn = DriverManager.getConnection(url, dbuser, dbpass);
	    Statement s = conn.createStatement();
	                        
	    
	    String query = "SELECT * FROM shortest_path('SELECT gid as id,source::integer,target::integer,length::double precision as cost FROM ways'," + initVertex + ", " +endVertex+ ", false, false);";
	    ResultSet r = s.executeQuery(query);
	    while( r.next() )
	    {
	        int id = r.getInt(2);
	        
	        if (id!=-1)
	        	route.add(id);
	     }
	     	s.close();
	     	conn.close();
	   }
	   catch( Exception e ) {
		   e.printStackTrace();
	   }

	return route;
	}

@WebMethod public @WebResult(name="routedistance") double getRouteDistance(int initVertex, int endVertex){

	Connection conn;
              
    String dbname = "adam";
    String dbuser = "user";
    String dbpass = "user";
	String dbhost = "172.26.0.2";
	String dbport = "5432";
	               
    double distanciaTotal = 0;
    
	try {
	               
		System.out.println("getDistanceRoute ("+initVertex+","+endVertex+")");

		Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;
	    conn = DriverManager.getConnection(url, dbuser, dbpass);
	    Statement s = conn.createStatement();
	    
	    String query = "SELECT * FROM shortest_path('SELECT gid as id,source::integer,target::integer,length::double precision as cost FROM ways'," + initVertex + ", " +endVertex+ ", false, false);";
	    ResultSet r = s.executeQuery(query);
	    
	    while( r.next() )
	    {
	        distanciaTotal += r.getDouble(3); 
	     }
	     	s.close();
	     	conn.close();
	   }
	   catch( Exception e ) {
		   e.printStackTrace();
	   }

	return distanciaTotal;
	}
}