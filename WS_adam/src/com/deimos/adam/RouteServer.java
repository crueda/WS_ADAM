package com.deimos.adam;

import javax.xml.ws.Endpoint;

import com.deimos.adam.ws.RouteWS;

public class RouteServer {

/**
* @param args
*/

private static RouteWS routeWS = null;
private static Endpoint endpointRoute = null;

public static void main(String[] args) {
	// TODO Auto-generated method stub
	RouteServer server = new RouteServer();
	routeWS = new RouteWS();
	startWebServices();
	Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
             stopWebServices();
                  
            }
	});
}

private static void startWebServices(){
	System.out.println("Iniciando Servicios de ruteo");
	startRouteWS();
}

private static void startRouteWS(){
	String webServLocalHostConfig="http://localhost:9090/adamWS";
	endpointRoute = Endpoint.publish(webServLocalHostConfig, routeWS);
}

private static void stopWebServices(){
	System.out.println("Parando Servicios de ruteo");
	stopRouteWS();
}

private static void stopRouteWS(){
	endpointRoute.stop();
}

}