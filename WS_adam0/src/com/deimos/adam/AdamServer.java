package com.deimos.adam;

import javax.xml.ws.Endpoint;

import com.deimos.adam.ws.RouteWS;
import com.deimos.adam.ws.UserWS;

/**
*
* @author CARM
*/
public class AdamServer {

	
private static RouteWS routeWS = null;
private static UserWS userWS = null;
private static Endpoint endpointRoute = null;



public static void main(String[] args) {
	
	routeWS = new RouteWS();
	userWS = new UserWS();
	
	startWebServices();
	Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
             stopWebServices();
                  
            }
	});
}



private static void startWebServices(){
	System.out.println("Iniciando servicios de ruteo...");
	startRouteWS();
	System.out.println("Iniciando servicios del usuario...");
	startUserWS();
	//System.out.println("Iniciando servicios de informacion...");
	//startInfoWS();
	System.out.println("Servicios iniciados en puerto 5000");
}

private static void startRouteWS(){
	String webServLocalHostConfig="http://localhost:5000/routeWS";
	endpointRoute = Endpoint.publish(webServLocalHostConfig, routeWS);
}

private static void startUserWS(){
	String webServLocalHostConfig="http://localhost:5000/userWS";
	endpointRoute = Endpoint.publish(webServLocalHostConfig, userWS);
}

private static void stopWebServices(){
	System.out.println("Parando Servicios");
	stopRouteWS();
	System.out.println("Servicios detenidos");
}

private static void stopRouteWS(){
	endpointRoute.stop();
}

}