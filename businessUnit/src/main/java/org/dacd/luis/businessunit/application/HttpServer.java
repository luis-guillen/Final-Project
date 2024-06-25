package org.dacd.luis.businessunit.application;

import org.dacd.luis.businessunit.service.TripRecommendHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer {

    public static void startHttpServer() throws IOException {
        int serverPort = 9090;
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/api/vacation-recommendations", new TripRecommendHandler(new HotelSqliteRepository(), new WeatherSqliteRepository(), new DatalakeFileRepository()));
        server.setExecutor(null);
        server.start();
        System.out.println("HTTP server started on port " + serverPort);
    }
}
