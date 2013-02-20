package com.deimos.adam;

import javax.xml.ws.Endpoint;

import com.deimos.adam.ws.PredictionWS;

public class PredictionServer {

/**
* @param args
*/

private static PredictionWS predictionWS = null;
private static Endpoint endpointRoute = null;

public static void main(String[] args) {
	
	// TODO Auto-generated method stub
	PredictionServer server = new PredictionServer();
	predictionWS = new PredictionWS();
	startWebServices();
	Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
             stopWebServices();
                  
            }
	});
}

private static void startWebServices(){
	System.out.println("Iniciando Servicios de predicción");
	startPredictionWS();
}

private static void startPredictionWS(){
	String webServLocalHostConfig="http://localhost:9090/adamWS";
	endpointRoute = Endpoint.publish(webServLocalHostConfig, predictionWS);
}

private static void stopWebServices(){
	System.out.println("Parando Servicios de prediccion");
	stopPredictionWS();
}

private static void stopPredictionWS(){
	endpointRoute.stop();
}


}