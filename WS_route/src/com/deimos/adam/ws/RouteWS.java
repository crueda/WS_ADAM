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
               
	                String dbname = "pgrouting";
	                String dbuser = "user";
	               String dbpass = "user";
		                String dbhost = "172.26.0.240";
		                String dbport = "5432";
	               
             // String dbtable = "jdbc_test";
	               
	            /*    String dropSQL = "drop table " + dbtable;
	                String createSQL = "create table " + dbtable + " (geom geometry, id int4)";
	                String insertPointSQL = "insert into " + dbtable + " values ('POINT (10 10 10)',1)";
	                String insertPolygonSQL = "insert into " + dbtable + " values ('POLYGON ((0 0 0,0 10 0,10 10 0,10 0 0,0 0 0))',2)";
	              */ 
	                try {
	               
	                        System.out.println("Creating JDBC connection...");
	                        Class.forName("org.postgresql.Driver");
	                        String url = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;
	                        conn = DriverManager.getConnection(url, dbuser, dbpass);
	                      //  System.out.println("Adding geometric type entries...");
	                      //  ((org.postgresql.Connection)conn).addDataType("geometry","org.postgis.PGgeometry");
	                      //  ((org.postgresql.Connection)conn).addDataType("box3d","org.postgis.PGbox3d");
	                        Statement s = conn.createStatement();
	                        /*System.out.println("Creating table with geometric types...");
	                        s.execute(dropSQL);
	                        s.execute(createSQL);
	                        System.out.println("Inserting point...");
	                        s.execute(insertPointSQL);
	                        System.out.println("Inserting polygon...");
	                        s.execute(insertPolygonSQL);
	                        System.out.println("Done.");
	                        */
	                        //s = conn.createStatement();
	                        
	                        System.out.println("Querying ...");
	                        ResultSet r = s.executeQuery("SELECT * FROM shortest_path('SELECT gid as id,source::integer,target::integer,length::double precision as cost FROM ways',100, 600, false, false);");
	                        while( r.next() )
	                        {
	                                Object obj =  r.getObject(1);
	                                int id = r.getInt(2);
	                                System.out.println("Row " + id + ":");
	                                //System.out.println(obj.toString());
	                                
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